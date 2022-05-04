package br.edu.utfpr.controlelivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.controlelivros.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
