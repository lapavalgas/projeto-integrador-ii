package com.usj.fastservice.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * O modelo de relação dessa classe segue estratégia Foreign Key in JPA. Nesse
 * modelo a chave estrangeira (dessa entidade) é depositada na tabela de
 * enderecoCompleto. Realmente útil quando a entidade mantém independencia da
 * classe.
 */

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "enderecoCompleto" })
@Builder
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "bairro")
	private String bairro;

	@Column(name = "logradouro")
	private String logradouro;

	@OneToMany(mappedBy = "endereco", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("endereco")
	@ToString.Exclude
	private List<EnderecoCompleto> enderecoCompleto;

}
