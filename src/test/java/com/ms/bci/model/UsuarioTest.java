package com.ms.bci.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

	private Usuario usuario;

	@BeforeEach
	public void setUp() {
		usuario = new Usuario();
	}

	@Test
	public void testSetAndGetId() {
		String id = "UUID";
		usuario.setId(id);
		assertEquals(id, usuario.getId());
	}

	@Test
	public void testSetAndGetName() {
		String name = "Juan";
		usuario.setName(name);
		assertEquals(name, usuario.getName());
	}

	@Test
	public void testSetAndGetEmail() {
		String email = "juan@test.com";
		usuario.setEmail(email);
		assertEquals(email, usuario.getEmail());
	}

	@Test
	public void testSetAndGetPassword() {
		String password = "password123";
		usuario.setPassword(password);
		assertEquals(password, usuario.getPassword());
	}

	@Test
	public void testSetAndGetPhones() {
		List<Telefono> phones = new ArrayList<>();
		Telefono telefono1 = mock(Telefono.class);
		Telefono telefono2 = mock(Telefono.class);
		phones.add(telefono1);
		phones.add(telefono2);
		usuario.setPhones(phones);
		assertEquals(phones, usuario.getPhones());
	}

	@Test
	public void testSetAndGetCreatedDate() {
		LocalDateTime createdDate = LocalDateTime.now();
		usuario.setCreatedDate(createdDate);
		assertEquals(createdDate, usuario.getCreatedDate());
	}

	@Test
	public void testSetAndGetLastLogin() {
		LocalDateTime lastLogin = LocalDateTime.now();
		usuario.setLastLogin(lastLogin);
		assertEquals(lastLogin, usuario.getLastLogin());
	}

	@Test
	public void testSetAndGetToken() {
		String token = "abc123";
		usuario.setToken(token);
		assertEquals(token, usuario.getToken());
	}

	@Test
	public void testSetAndGetTokenDate() {
		LocalDateTime tokenDate = LocalDateTime.now();
		usuario.setTokenDate(tokenDate);
		assertEquals(tokenDate, usuario.getTokenDate());
	}

	@Test
	public void testSetAndGetActive() {
		boolean active = true;
		usuario.setActive(active);
		assertEquals(active, usuario.isActive());
	}
}
