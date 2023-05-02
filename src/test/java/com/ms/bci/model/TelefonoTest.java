package com.ms.bci.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TelefonoTest {

	@InjectMocks
	private Telefono telefono;

	@Mock
	private Usuario usuario;

	@Test
	public void testSetAndGetId() {
		Long expectedId = 1L;
		telefono.setId(expectedId);
		assertEquals(expectedId, telefono.getId());
	}

	@Test
	public void testSetAndGetNumber() {
		String expectedNumber = "123456789";
		telefono.setNumber(expectedNumber);
		assertEquals(expectedNumber, telefono.getNumber());
	}

	@Test
	public void testSetAndGetCitycode() {
		String expectedCitycode = "1";
		telefono.setCitycode(expectedCitycode);
		assertEquals(expectedCitycode, telefono.getCitycode());
	}

	@Test
	public void testSetAndGetContrycode() {
		String expectedContrycode = "01";
		telefono.setContrycode(expectedContrycode);
		assertEquals(expectedContrycode, telefono.getContrycode());
	}

	@Test
	public void testSetAndGetUser() {
		telefono.setUser(usuario);
		assertEquals(usuario, telefono.getUser());
	}

}
