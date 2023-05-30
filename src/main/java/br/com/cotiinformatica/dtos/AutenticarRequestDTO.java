package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AutenticarRequestDTO {
	//Dia:30/05

	@Email(message = "Por favor, insira um email valido")
	@NotBlank(message = "Por favor, informe o email do usuário")
	private String email;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
			message = "Por favor, informe uma senha com letras maiúsculas, mínúsculas, números, símbolos e no mínimo 8 caracteres.")
	@NotBlank(message = "Por favor, informe a senha do usuário.")
	private String senha;
}
