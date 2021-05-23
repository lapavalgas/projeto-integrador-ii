package com.usj.fastservice.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.usj.fastservice.models.enums.FormaDePagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = { "usuario" })
@Builder
public class Pedidos {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties("pedidosRealizados")
	@ToString.Exclude
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "servico_id")
	@JsonIgnoreProperties("pedidos")
	@ToString.Exclude
    private Servicos servico;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DisponibilidadeDataHora> disponibilidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento_id")
    private FormaDePagamento formaDePagamento;

    @Column(name = "servicoFinalizadoPeloUsuario")
    private boolean servicoFinalizadoPeloUsuario;

    @Column(name = "servicoFinalizadoPeloProfissional")
    private boolean servicoFinalizadoPeloProfissional;
    
    @Column(name = "avaliacaoDoServicoPeloUsuario")
    private String avaliacaoDoServicoPeloUsuario;

}
