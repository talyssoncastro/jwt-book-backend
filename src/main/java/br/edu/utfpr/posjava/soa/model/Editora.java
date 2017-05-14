package br.edu.utfpr.posjava.soa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@Table( name = "editora" )
@Entity
@Getter
@Setter
public class Editora {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "nome", length = 60, nullable = false )
    private String nome;

}
