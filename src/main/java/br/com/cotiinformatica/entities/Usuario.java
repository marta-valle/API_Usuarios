package br.com.cotiinformatica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//SPRING DATA
@Entity
@Table(name = "usuario")
//SPTRING BOOT
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//AUTOINCREMENTO
	@Column(name = "idusuario") 
	private Integer idUsuario;

	@Column(name = "nome", length = 150, nullable = false) // NOME TAMANHO E PREENCHIMENTO OBRIGATORIO
	private String nome;

	@Column(name = "email", length = 100, nullable = false, unique = true) // NOME TAMANHO PREENCHIMENTO OBRIGATORIO E UNICO
	private String email;

	@Column(name = "senha", length = 40, nullable = false)
	private String senha;

}
