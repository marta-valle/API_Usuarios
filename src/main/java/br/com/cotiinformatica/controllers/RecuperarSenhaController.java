package br.com.cotiinformatica.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.RecuperarSenhaRequestDTO;
import br.com.cotiinformatica.dtos.RecuperarSenhaResponseDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import jakarta.validation.Valid;
//ANOTAÇÕES DO SPRING DATA
@RestController
@RequestMapping(value = "/api/recuperar-senha")
public class RecuperarSenhaController {
	//Dia: 30/05: criação
	//Dia 06/06 Implementado
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<RecuperarSenhaResponseDTO > post(@RequestBody @Valid RecuperarSenhaRequestDTO dto) {
		
	RecuperarSenhaResponseDTO response = new RecuperarSenhaResponseDTO();
	try {
		Optional<Usuario> optional = usuarioRepository.findByEmail(dto.getEmail());
		
		if(optional.isEmpty()) {
			//HTTP 400
			response.setStatus(400);
			response.setMensagem("Usuario não encontrado, verifique o email informado");
			
		}else {
			//TODO: Implementar a recuperação de senha do usuario, enviando para ele um email para troca de senha. 
			
		}
		
	} catch (Exception e) {
		response.setStatus(500);
		response.setMensagem("Falha ao recuperar senhad o usuario:" + e.getMessage());
	}
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
