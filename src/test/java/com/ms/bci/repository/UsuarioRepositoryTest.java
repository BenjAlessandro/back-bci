package com.ms.bci.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ms.bci.model.Telefono;
import com.ms.bci.model.Usuario;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UsuarioRepositoryTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@Test
	public void testFindByEmail() {
		Usuario usuario = new Usuario();
		usuario.setName("Juan");
		usuario.setEmail("juan@test.com");
		usuario.setPassword("password");
		usuario.setActive(true);
		usuario.setCreatedDate(LocalDateTime.now());
		usuario.setLastLogin(LocalDateTime.now());
		usuario.setToken("token");
		usuario.setTokenDate(LocalDateTime.now());

		Telefono telefono1 = new Telefono();
		telefono1.setNumber("12345678");
		telefono1.setCitycode("56");
		telefono1.setContrycode("1");
		telefono1.setUser(usuario);

		Telefono telefono2 = new Telefono();
		telefono2.setNumber("87654321");
		telefono2.setCitycode("56");
		telefono2.setContrycode("1");
		telefono2.setUser(usuario);

		usuario.setPhones(Arrays.asList(telefono1, telefono2));
		when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(usuario);
		Usuario found = usuarioRepository.findByEmail(usuario.getEmail());

		assertThat(found).isNotNull();
		assertThat(found.getEmail()).isEqualTo(usuario.getEmail());
		assertThat(found.getName()).isEqualTo(usuario.getName());
		assertThat(found.getPhones().size()).isEqualTo(usuario.getPhones().size());
	}

}
