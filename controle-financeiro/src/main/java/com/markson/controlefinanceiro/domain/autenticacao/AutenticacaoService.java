package com.markson.controlefinanceiro.domain.autenticacao;

import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import com.markson.controlefinanceiro.domain.usuario.Usuario;
import com.markson.controlefinanceiro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }


    public Usuario cadastrar(DadosAutenticacao dadosAutenticacao) {
        if (repository.existsByLogin(dadosAutenticacao.login())) {
            throw new ValidacaoException("Login já está sendo utilizado");
        }
        String senhaEncriptada = encriptarSenha(dadosAutenticacao.senha());
        Usuario usuario = new Usuario(null, dadosAutenticacao.login(), senhaEncriptada);
        return repository.save(usuario);
    }

    private String encriptarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }
}
