package br.ufjf.dcc193.trabalho03.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trabalho03.model.Etiqueta;
import br.ufjf.dcc193.trabalho03.repository.EtiquetaRepository;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EtiquetaController {

    @Autowired
    EtiquetaRepository etiquetarep;

    @GetMapping( "/etiqueta-cadastrar.html")
    public ModelAndView etiquetaCadastrar()
    {
        Etiqueta etiqueta = new Etiqueta();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("etiqueta-cadastrar");
        mv.addObject("etiqueta", etiqueta);
        return mv;
    }

    @PostMapping(value = "/salvar-etiqueta.html")
    public ModelAndView etiquetaSalvar(@Valid Etiqueta etiqueta, BindingResult binding){
    
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("etiqueta-cadastrar");
            mv.addObject("etiqueta", etiqueta);
            return mv;
        }
        etiquetarep.save(etiqueta);
        mv.setViewName("redirect:/etiquetas-listar.html");
       return mv;  
    }

    @GetMapping("etiquetas-listar.html")
    public ModelAndView etiquetas(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiquetas",etiquetarep.findAll());
        return mv;
    }

    @GetMapping("etiqueta-editar/{id}")
    public ModelAndView etiquetaEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiqueta", etiquetarep.findById(id));
        mv.setViewName("etiqueta-editar");
        return mv;
    }

    @GetMapping("/etiqueta-deletar/{id}")
    public RedirectView etiquetaDeletar(@PathVariable Long id){
        etiquetarep.deleteById(id);
        return new RedirectView("/etiquetas-listar.html");
    }
    
}