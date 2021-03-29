package com.usj.fastservice.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * dependentes da parent class.
 */

@Entity
@Table(name = "contato")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "usuario" })
@Builder
public class Contato {

	@Id
	@Column(name = "usuario_id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@OneToOne(cascade = CascadeType.PERSIST)
	@MapsId
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties("contato")
	@ToString.Exclude
	private Usuario usuario;

}
