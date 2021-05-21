package com.usj.fastservice.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "servicos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Servicos {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "valor")
    private String valor;

    @Column(name = "statusOperante")
    private String statusOperante;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties("servicosOferecidos")
	@ToString.Exclude
    private Usuario usuario;
    
    @OneToMany(mappedBy = "servico", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("servico")    
	@ToString.Exclude
    private List<Pedidos> pedidos;

}
