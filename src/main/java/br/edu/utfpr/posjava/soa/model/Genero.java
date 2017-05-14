package br.edu.utfpr.posjava.soa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@Table( name = "genero" )
@Entity
@Getter
@Setter
public class Genero {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "nome", length = 60, nullable = false )
    private String nome;

}
