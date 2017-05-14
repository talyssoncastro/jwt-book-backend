package br.edu.utfpr.posjava.soa.controller;

import br.edu.utfpr.posjava.soa.model.security.AuthenticationRequest;
import br.edu.utfpr.posjava.soa.model.security.AuthenticationResponse;
import br.edu.utfpr.posjava.soa.model.security.Usuario;
import br.edu.utfpr.posjava.soa.security.TokenUtils;
import br.edu.utfpr.posjava.soa.service.UsuarioService;
import br.edu.utfpr.posjava.soa.shared.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by talyssondecastro on 13/05/17.
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        UserDetails userDetails = this.usuarioService.loadUserByUsername( authenticationRequest.getUsername() );

        String token = this.tokenUtils.generateToken( userDetails );

        return ResponseEntity.ok( new AuthenticationResponse(token));

    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {

        String token = request.getHeader(AppConstant.tokenHeader);

        String username = this.tokenUtils.getUsernameFromToken(token);

        Usuario usuario = (Usuario) this.usuarioService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed( token, usuario.getLastPasswordReset() )) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }


}
