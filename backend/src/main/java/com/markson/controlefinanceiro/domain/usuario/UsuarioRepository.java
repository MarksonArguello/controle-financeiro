package com.markson.controlefinanceiro.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String subject);

    boolean existsByLogin(String login);
}
