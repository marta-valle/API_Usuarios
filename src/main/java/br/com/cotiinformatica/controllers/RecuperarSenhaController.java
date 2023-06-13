package br.com.cotiinformatica.controllers;

import java.util.Optional;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.RecuperarSenhaRequestDTO;
import br.com.cotiinformatica.dtos.RecuperarSenhaResponseDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import br.com.cotiinformatica.services.EmailService;
import br.com.cotiinformatica.services.MD5Service;
import jakarta.validation.Valid;
//ANOTAÇÕES DO SPRING DATA
@RestController
@RequestMapping(value = "/api/recuperar-senha")
public class RecuperarSenhaController {
	//Dia: 30/05: criação
	//Dia 30/06 Implementado
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired //dia 30/06
	private EmailService emailService;
	
	@Autowired//dia 30/06
	private MD5Service md5Service;
	
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
			//CAPTURA O OBJETO RECEBIDO PELO BD
			Usuario usuario = optional.get();
			
			//GERANDO UMA NOVA SENHA PARA O USUARIO
			String novaSenha = new Faker().internet().password(8,10,true, true, true);
			
			//ENVIANDO UM EMAIL PARA O USUARIO COM A NOVA SENHA
			String to = usuario.getEmail();
			String subject = "Recuperação de senha de usuário - API SpringBoot";
			String body = "Prezado, " + usuario.getNome()
							+ "\n\nUma nova senha foi gerada com sucesso. Utilize a senha:" + novaSenha
							+ "\n\nAtt"
							+ "\nEquipe COTI Informática";
			
			emailService.send(to, subject, body);
			
			//ATUALIZANDO A SENHA NO BD
			usuario.setSenha(md5Service.encrypt(novaSenha));
			usuarioRepository.save(usuario);
			
			//RETORNANDO A RESPOSTA
			response.setStatus(200);
			response.setMensagem("Recuperação de senha realizada com sucesso.Verifique sua conta de email");

		}
		
	} catch (Exception e) {
		response.setStatus(500);
		response.setMensagem("Falha ao recuperar senhad o usuario:" + e.getMessage());
	}
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
