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

@Controller
public class EtiquetaController {

    @Autowired
    EtiquetaRepository etiquetarep;

    @GetMapping( "/etiqueta-cadastro.html")
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
        mv.setViewName("redirect:/");
       return mv;  
    }

    @GetMapping("etiquetas-lista.html")
    public ModelAndView etiquetas(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiquetas",etiquetarep.findAll());
        return mv;
    }

    @GetMapping("etiqueta-editar/{id}.html")
    public ModelAndView areaEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiqueta", etiquetarep.getClass());
        mv.setViewName("etiqueta-editar");
        return mv;
    }
    
}