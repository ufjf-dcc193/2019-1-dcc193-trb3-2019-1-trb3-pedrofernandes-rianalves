package br.ufjf.dcc193.trabalho03.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.trabalho03.model.Item;
import br.ufjf.dcc193.trabalho03.repository.ItemRepository;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemrep;

    @GetMapping( "/item-cadastro.html")
    public ModelAndView  itemCadastrar()
    {
        Item item = new Item();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("item-cadastrar");
        mv.addObject("item", item);
        return mv;
    }

    @PostMapping(value = "/salvar-item.html")
    public ModelAndView etiquetaSalvar(@Valid Item item, BindingResult binding){
    
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("item-cadastrar");
            mv.addObject("item", item);
            return mv;
        }
        itemrep.save(item);
        mv.setViewName("redirect:/");
       return mv;  
    }

    @GetMapping("itens-listar.html")
    public ModelAndView itens(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("itens",itemrep.findAll());
        return mv;
    }

    @GetMapping("item-editar/{id}.html")
    public ModelAndView itemEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", itemrep.getClass());
        mv.setViewName("item-editar");
        return mv;
    }
    
    
}