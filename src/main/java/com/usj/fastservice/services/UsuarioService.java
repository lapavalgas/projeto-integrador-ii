package com.usj.fastservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.fastservice.models.Usuario;
import com.usj.fastservice.models.dto.DadosUsuarioDTO;
import com.usj.fastservice.models.mapper.UsuarioMapper;
import com.usj.fastservice.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario cadastroDeUsuario(DadosUsuarioDTO usuarioCadastroRequestDTO) {
		Usuario usuario = UsuarioMapper.toUsuario(usuarioCadastroRequestDTO);
		return usuarioRepository.save(usuario);
	}

	public DadosUsuarioDTO logarUsuario(Long id) throws Exception {
		Usuario usuario = readUsuarioRepositoryById(id);
		try {
			return UsuarioMapper.toUsuarioDTO(usuario);
		} catch (Exception e) {
			return UsuarioMapper.setMsg(id, "Usuario não encontrado!");
		}
	}

	// TODO: talvez implementar a solução anterior 
	public DadosUsuarioDTO carregarDadosDoUsuario(Long id) throws Exception {
		return UsuarioMapper.toUsuarioDTO(readUsuarioRepositoryById(id));
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
