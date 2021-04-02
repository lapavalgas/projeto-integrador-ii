package com.usj.fastservice.models.dto;

import java.util.List;

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
public class DadosPedidoDTO {

	private String pedido_id;
	
	private DadosUsuarioDTO cliente;
	
	private DadosServicoDTO servicoContratado;
	
	private List<DisponibilidadeDataHora> disponibilidade;
	
	private FormaDePagamento formaDePagamento;
	
	private boolean servicoFinalizadoCliente;

	private String avaliacaoDoCliente;
	
	private boolean servicoFinalizadoProfissional;

	private String msg;	
}
