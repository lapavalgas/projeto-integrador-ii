package com.usj.fastservice.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Contato;
import com.usj.fastservice.models.EnderecoCompleto;
import com.usj.fastservice.models.Pedidos;
import com.usj.fastservice.models.Servicos;
import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.ContatoMapper;
import com.usj.fastservice.models.mapper.EnderecoMapper;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.EnderecoRepository;
import com.usj.fastservice.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ComplementoService complementoService;

	public DadosUsuarioDTO cadastroDeUsuario(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		try {
			Usuario usuario = UsuarioMapper.toUsuario(usuarioCadastroRequestDTO);
//			usuario = usuarioRepository.save(usuario);
//			usuarioCadastroRequestDTO.setUsuario_id(usuario.getId());
			Contato contato = ContatoMapper.toContato(usuarioCadastroRequestDTO);
			contato.setUsuario(usuario);
			EnderecoCompleto endereco = EnderecoMapper.toEnderecoLessId(usuarioCadastroRequestDTO);
			endereco.setEndereco(enderecoService.save(endereco.getEndereco()));
			endereco.setComplemento(complementoService.save(endereco.getComplemento())); 
			endereco.setUsuario(usuario);
			usuario.setContato(contato);
			usuario.setEnderecoCompleto(endereco);
			usuario.setServicosOferecidos(new ArrayList<Servicos>());
			usuario.setPedidosRealizados(new ArrayList<Pedidos>());
			usuario = usuarioRepository.save(usuario);
			return UsuarioMapper.toLoggedUsuarioDTO(usuario); 
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null,"Problemas no serviço. Entre em contato com o administrador!  :(");
		}
	}

	public DadosUsuarioDTO logarUsuario(Long id) throws Exception {
		try {
			Usuario usuario = readUsuarioRepositoryById(id);
			return UsuarioMapper.toLoggedUsuarioDTO(usuario);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO carregarDadosDoUsuario(Long id) throws Exception {
		try {
			Usuario usuario = readUsuarioRepositoryById(id);
			return UsuarioMapper.toUsuarioDTO(usuario);	
		} catch (Exception e) { 
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO deletarUsuario(Long id) throws Exception {
		try {
			usuarioRepository.deleteById(id);
			return UsuarioMapper.setMsg(id, "Usuario deletado com sucesso!");
		} catch (Exception e) {
			return UsuarioMapper.setMsg(id, "Usuario não encontrado!");
		}
	}
	
	Usuario readUsuarioRepositoryById(Long id) throws Exception {
		return usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado!"));
	}
	
}
