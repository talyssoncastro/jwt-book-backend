package br.edu.utfpr.posjava.soa.repository;

import br.edu.utfpr.posjava.soa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by talyssoncastro on 14/05/17.
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
