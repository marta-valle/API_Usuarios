package br.com.cotiinformatica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CriarUsuarioRequestDTO;
import br.com.cotiinformatica.dtos.CriarUsuarioResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/criar-usuario")
public class CriarUsuarioController {
	//Criação dia 25/05 desenvolvimento Dia:30/05
	@PostMapping
	public ResponseEntity<CriarUsuarioResponseDTO> post(@RequestBody @Valid CriarUsuarioRequestDTO dto) {
		
		
		return null;
	}
}
