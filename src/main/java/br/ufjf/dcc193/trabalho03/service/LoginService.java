package br.ufjf.dcc193.trabalho03.service;

import org.springframework.stereotype.Service;

import br.ufjf.dcc193.trabalho03.model.Usuario;

@Service
public class LoginService {
    private static Usuario usuario = null;

    public LoginService(){

    }

    public void login(Usuario usuarioEmail)
    {
        usuario = usuarioEmail;

    }

    public Usuario getAvaliador(){
        return usuario;
    }

    public void logout() {
        usuario = null;
    }

    
}