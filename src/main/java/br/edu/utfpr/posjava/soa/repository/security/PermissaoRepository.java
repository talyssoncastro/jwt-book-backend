package br.edu.utfpr.posjava.soa.repository.security;

import br.edu.utfpr.posjava.soa.model.security.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by talyssoncastro on 14/05/17.
 */
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	Permissao findByPermissao(String permissao);
	
}
