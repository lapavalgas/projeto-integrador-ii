package com.usj.fastservice.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.usj.fastservice.models.DisponibilidadeDataHora;
import com.usj.fastservice.models.enums.FormaDePagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class DadosPedidoDTO {
	
	private Long pedido_id;
	
	private DadosUsuarioDTO cliente;
	
	private DadosServicoDTO servicoContratado;

	@JsonIgnoreProperties("pedido")
	private List<DisponibilidadeDataHora> disponibilidade;
	
	private FormaDePagamento formaDePagamento;
	
	private String avaliacaoDoCliente;

	private String servicoFinalizadoCliente;
	
	private String servicoFinalizadoProfissional;

	private String msg;	
	
}
