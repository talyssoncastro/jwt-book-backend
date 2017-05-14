package br.edu.utfpr.posjava.soa.service;

import br.edu.utfpr.posjava.soa.model.security.Usuario;
import br.edu.utfpr.posjava.soa.repository.security.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@Service
public class UsuarioService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null){
			throw new UsernameNotFoundException("Email não encontrado!");
		}
		return usuario;
	}

}




