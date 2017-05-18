package br.edu.utfpr.posjava.soa.repository.security;

import br.edu.utfpr.posjava.soa.model.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by talyssoncastro on 14/05/17.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
}
