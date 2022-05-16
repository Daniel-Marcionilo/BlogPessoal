package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;

//import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {
		repository.save(new Usuario(0L, "Maiar da Silva", "isadora@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg"));
		repository.save(new Usuario(0L, "Brocco da Silva", "sim@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg"));
		repository.save(new Usuario(0L, "Rafael Silva", "isaa@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg"));
		repository.save(new Usuario(0L, "Maiar", "isra@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg"));
	}

	@Test
	@DisplayName("Teste que retorna 1 usuario")
	public void retornaUmUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com"));
	}
	
	@Test
	@DisplayName("Teste que retorna 3 usuarios")
	public void retornaTresUsuarios() {
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getUsuario().equals("Maiar da Silva"));
		assertTrue(listaDeUsuarios.get(1).getUsuario().equals("Brocco da Silva"));
		assertTrue(listaDeUsuarios.get(2).getUsuario().equals("Rafael Silva"));
	}

	@AfterAll
	public void end() {
		repository.deleteAll();
	}
}
