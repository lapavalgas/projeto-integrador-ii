package com.usj.fastservice.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "complemento")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "enderecoCompleto" })
@Builder
public class Complemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id;
	
	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;
	
	@OneToOne(mappedBy = "complemento", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("complemento")
	@ToString.Exclude
	private EnderecoCompleto enderecoCompleto;
    
}
