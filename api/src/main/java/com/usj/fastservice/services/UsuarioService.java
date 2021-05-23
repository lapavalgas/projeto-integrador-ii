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
import com.usj.fastservice.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public DadosUsuarioDTO cadastroDeUsuario(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		try {
			usuarioCadastroRequestDTO.removeCaracteresEspeciaisCpf();
			validaCpf(usuarioCadastroRequestDTO.getCpf());
			Usuario usuario = UsuarioMapper.toModel(usuarioCadastroRequestDTO);
			Contato contato = ContatoMapper.toContato(usuarioCadastroRequestDTO);
			EnderecoCompleto endereco = EnderecoMapper.toModel(usuarioCadastroRequestDTO);
			contato.setUsuario(usuario);
			endereco.setUsuario(usuario);
			usuario.setContato(contato);
			usuario.setEnderecoCompleto(endereco);
			usuario.setServicosOferecidos(new ArrayList<Servicos>());
			usuario.setPedidosRealizados(new ArrayList<Pedidos>());
			usuario.setAtivo(true);
			;
			usuario = usuarioRepository.save(usuario);
			return UsuarioMapper.toLoggedUsuarioDTO(usuario);
		} catch (Exception e) {
			if (e.getMessage().contains("CPF")) {
				return UsuarioMapper.setMsg(null, e.getMessage());				
			} else {
				return UsuarioMapper.setMsg(null, "Problemas no serviço. Entre em contato com o administrador!  :(");
			}
		}
	}

	public DadosUsuarioDTO logarUsuario(String cpfUsuario) throws Exception {
		try {
			Usuario usuario = readUsuarioRepositoryByCpf(cpfUsuario);
			isUsuarioAtivo(usuario);
			UsuarioService.isUsuarioAtivo(usuario);
			return UsuarioMapper.toLoggedUsuarioDTO(usuario);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO carregarDadosDoUsuario(Long id) throws Exception {
		try {
			Usuario usuario = readUsuarioRepositoryById(id);
			UsuarioService.isUsuarioAtivo(usuario);
			return UsuarioMapper.toDto(usuario);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(null, e.getMessage());
		}
	}

	public DadosUsuarioDTO deletarUsuario(Long id) throws Exception {
		try {
//			usuarioRepository.deleteById(id);
			Usuario usuario = readUsuarioRepositoryById(id);
			UsuarioService.isUsuarioAtivo(usuario);
			usuario.setAtivo(false);
			usuarioRepository.save(usuario);
			return UsuarioMapper.setMsg(id, "Usuario deletado com sucesso!");
		} catch (Exception e) {
			return UsuarioMapper.setMsg(id, "Usuario não encontrado!");
		}
	}

	Usuario readUsuarioRepositoryById(Long id) throws Exception {
		return usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado!"));
	}

	Usuario readUsuarioRepositoryByCpf(String cpfUsuario) throws Exception {
		return usuarioRepository.findByCpf(cpfUsuario);
	}
	
	boolean validaCpf(String cpfUsuario) throws Exception {
		Usuario usuario = usuarioRepository.findByCpf(cpfUsuario);
		if (usuario == null) {
			return false;
		} else {
			throw new Exception("CPF já cadastrado!!");
		}
	}


	public static boolean isUsuarioAtivo(Usuario usuario) throws Exception {
		if (usuario.isAtivo()) {
			return usuario.isAtivo();
		} else {
			throw new Exception("Usuário desativado!");
		}
	}
}
