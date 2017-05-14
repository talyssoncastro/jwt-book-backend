package br.edu.utfpr.posjava.soa.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {

    private String token;

}
