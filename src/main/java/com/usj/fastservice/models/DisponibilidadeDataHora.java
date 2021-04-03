package com.usj.fastservice.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "disponibilidadeDataHora")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "pedido" })
@Builder
@JsonInclude(value = Include.NON_NULL)
public class DisponibilidadeDataHora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dataHora")
	private LocalDate dataHora;

	@Column(name = "dataSelecionadaPeloUsuario")
	private boolean dataSelecionadaPeloUsuario;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "disponibilidade_id")
	@JsonIgnoreProperties("disponibilidade")
	@ToString.Exclude
	private Pedidos pedido;


}
