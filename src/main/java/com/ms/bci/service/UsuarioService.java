package com.ms.bci.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ms.bci.model.Usuario;
import com.ms.bci.repository.UsuarioRepository;
import com.ms.bci.utils.EmailValidator;
import com.ms.bci.utils.ErrorResponse;

import io.swagger.annotations.ApiOperation;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Crear un usuario")
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Usuario usuario) {
		try {
			// Validar campos
			if ((usuario.getName().isBlank()) || (usuario.getEmail().isBlank())
					|| (usuario.getPassword().isBlank())) {
				return new ResponseEntity(new ErrorResponse("Todos los campos son requeridos"),
						HttpStatus.BAD_REQUEST);
			}
			if (!EmailValidator.isValidEmail(usuario.getEmail())) {
				return new ResponseEntity(new ErrorResponse("El email ingresado no es válido"),
						HttpStatus.BAD_REQUEST);
			}
			// Verificar si el usuario ya existe en la base de datos
			Usuario existingUser = usuarioRepository.findByEmail(usuario.getEmail());
			if (existingUser != null) {
				return new ResponseEntity(new ErrorResponse("El email ingresado ya está registrado"),
						HttpStatus.BAD_REQUEST);
			}
			
			// Generar un UUID y asignarlo al ID del usuario
			String uuid = UUID.randomUUID().toString();
			usuario.setId(uuid);

			// Guardar el usuario en la base de datos
			usuarioRepository.save(usuario);

			// Retornar la respuesta de éxito
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse("Error al crear usuario");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

}
