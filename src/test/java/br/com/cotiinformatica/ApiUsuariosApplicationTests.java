package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.AutenticarRequestDTO;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDTO;
import br.com.cotiinformatica.dtos.RecuperarSenhaRequestDTO;

//Anotação do spring Data
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiUsuariosApplicationTests {
	// CRIAÇÃO AUTOMATICA JUNTO COM O PROGRAMA
	// Dia: 13/06 Implementado

	@Autowired
	private MockMvc mock;
	@Autowired
	private ObjectMapper objectMapper;

	//atributos
		private static String email;
		private static String senha;
		
	@Order(1)	
	@Test
	public void testCriarConta() throws Exception {

		// DADOS QUE SERÃO ENVIADOS PARA API
		CriarUsuarioRequestDTO dto = new CriarUsuarioRequestDTO();
		Faker faker = new Faker();

		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setSenha("Senha@123");

		// EXECUTANDO O SERVIDOR DE CADASTRO DE CONTA DE USUÁRIO
		mock.perform(post("/api/criar-usuario")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());

		email = dto.getEmail();
		senha = dto.getSenha();
	}

	@Test
	@Order(2)
	public void testAutenticar() throws Exception{
		AutenticarRequestDTO dto = new AutenticarRequestDTO();
		dto.setEmail(email);
		dto.setSenha(senha);
		
		mock.perform(post("/api/autenticar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
				
	}

	@Test
	@Order(3)
	public void testRecuperarSenha() throws Exception {
		RecuperarSenhaRequestDTO dto = new RecuperarSenhaRequestDTO();
		dto.setEmail(email);
		
		//EXECUTANDO O SERVIÇO DE RECUPERAÇÃO DE SENHA DO USUÁRIO
		mock.perform(post("/api/recuperar-senha")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
		
		
	}

}
