package br.edu.utfpr.posjava.soa.model.security;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable=false)
	private String descricao;
	
}



