package br.edu.utfpr.posjava.soa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by talyssoncastro on 14/05/17.
 */
@Table( name = "livro" )
@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "titulo", length = 120, nullable = false )
    private String titulo;

    @Column( name = "ano" )
    private Integer ano;

    @Column( name = "paginas" )
    private Integer paginas;

    @Column( name = "isbn", length = 40 )
    private String isbn;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "idGenero", referencedColumnName = "id" )
    private Genero genero;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "idEditora", referencedColumnName = "id" )
    private Editora editora;

}
