package com.usj.fastservice.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * O modelo de relação dessa classe segue a estratégia <b>Shared Primary Key</b>
 * in JPA. Nesse modelo a classe compartilha o user_id, se tornando uma extensão
 * direta de usuário. Realmente útil quando a relação é de one-to-one e os dados
 * dependentes da parent class. <br>
 * <br>
 * Essa classe também utiliza estratégia de <b>Foreign Key</b> através da
 * relação one-to-one com atributo complemento; para fins didáticos, pois o
 * ideal seria não existiri classe complemento e incorporar os atributos a essa
 * classe.
 */

@Entity
@Table(name = "enderecoCompleto")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "usuario" })
@Builder
public class EnderecoCompleto {

	@Id
	@Column(name = "usuario_id")
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "complemento_id", referencedColumnName = "id")
	private Complemento complemento;

	@OneToOne(cascade = CascadeType.PERSIST)
	@MapsId
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties("enderecoCompleto")
	@ToString.Exclude
	private Usuario usuario;
}
