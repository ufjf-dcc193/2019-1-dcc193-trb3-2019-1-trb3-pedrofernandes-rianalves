package br.ufjf.dcc193.trabalho03.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trabalho03.model.Usuario;
import br.ufjf.dcc193.trabalho03.repository.UsuarioRepository;
import br.ufjf.dcc193.trabalho03.service.LoginService;

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuariorep;

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("login");
        return mv;
    }


    @PostMapping(value = "login.html")
    public ModelAndView login(@Valid Usuario usuario, BindingResult binding, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (binding.hasErrors()) {
            mv.setViewName("login");
            mv.addObject("usuario", usuario);

        }
        Usuario u = usuariorep.findOneByEmailAndCodigoAcesso(usuario.getEmail(), usuario.getCodigoAcesso());
        System.err.println(u);
        if (u != null) {
            loginService.login(u);
            mv.setViewName("redirect:inicio.html");
        }

        return mv;
    }

    @GetMapping(value = { "/logout.html" })
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView();
        loginService.logout();
        mv.setViewName("redirect:/login.html");
        return mv;
    }
    
    @GetMapping( "/usuario-cadastro.html")
    public ModelAndView usuarioCadastrar()
    {
        Usuario usuario = new Usuario();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario-cadastrar");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @PostMapping(value = "/salvar.html")
    public ModelAndView avaliadorSalvar(@Valid Usuario usuario, BindingResult binding){
    
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-cadastrar");
            mv.addObject("usuario", usuario);
            return mv;
        }
        usuariorep.save(usuario);
        mv.setViewName("redirect:/");
       return mv;  
    }

    @GetMapping("usuarios-lista.html")
    public ModelAndView usuarios(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuarios",usuariorep.findAll());
        return mv;
    }

    @GetMapping("usuario-editar/{id}.html")
    public ModelAndView areaEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", usuariorep.getClass());
        mv.setViewName("usuario-editar");
        return mv;
    }


    
    
}