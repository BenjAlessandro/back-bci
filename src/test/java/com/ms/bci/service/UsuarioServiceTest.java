package com.ms.bci.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ms.bci.model.Usuario;
import com.ms.bci.repository.UsuarioRepository;
import com.ms.bci.utils.ErrorResponse;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioService usuarioService;

	@Test
	public void testCreateUser_Success() {
		Usuario usuario = new Usuario();
		usuario.setName("Juan");
		usuario.setEmail("juan@test.com");
		usuario.setPassword("password");

		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(null);

		ResponseEntity<Object> response = usuarioService.createUser(usuario);

		Mockito.verify(usuarioRepository).save(Mockito.eq(usuario));
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testCreateUser_MissingFields() {
		Usuario usuario = new Usuario();
		usuario.setName("");
		usuario.setEmail("");
		usuario.setPassword("");

		ResponseEntity<Object> response = usuarioService.createUser(usuario);

		ErrorResponse errorResponse = (ErrorResponse) response.getBody();
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("Todos los campos son requeridos", errorResponse.getMessage());
	}

	@Test
	public void testCreateUser_InvalidEmail() {
		Usuario usuario = new Usuario();
		usuario.setName("Juan");
		usuario.setEmail("juantest");
		usuario.setPassword("password");

		ResponseEntity<Object> response = usuarioService.createUser(usuario);

		ErrorResponse errorResponse = (ErrorResponse) response.getBody();
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("El email ingresado no es válido", errorResponse.getMessage());
	}

	@Test
	public void testCreateUser_EmailAlreadyExists() {
		Usuario usuario = new Usuario();
		usuario.setName("Juan");
		usuario.setEmail("juan@test.com");
		usuario.setPassword("password");

		Usuario existingUser = new Usuario();
		existingUser.setName("Existing User");
		existingUser.setEmail("juan@test.com");
		existingUser.setPassword("password");

		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(existingUser);

		ResponseEntity<Object> response = usuarioService.createUser(usuario);

		ErrorResponse errorResponse = (ErrorResponse) response.getBody();
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("El email ingresado ya está registrado", errorResponse.getMessage());
	}

	@Test
	public void testCreateUser_InternalServerError() {
		Usuario usuario = new Usuario();
		usuario.setName("Juan");
		usuario.setEmail("juan@test.com");
		usuario.setPassword("password");

		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenThrow(new RuntimeException());

		ResponseEntity<Object> response = usuarioService.createUser(usuario);

		ErrorResponse errorResponse = (ErrorResponse) response.getBody();
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		Assertions.assertEquals("Error al crear usuario", errorResponse.getMessage());
	}
}
