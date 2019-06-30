package br.ufjf.dcc193.trabalho03.controller;

import br.ufjf.dcc193.trabalho03.model.Item;
import br.ufjf.dcc193.trabalho03.model.Vinculo;
import br.ufjf.dcc193.trabalho03.repository.ItemRepository;
import br.ufjf.dcc193.trabalho03.repository.VinculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRep;

    @Autowired
    private ItemRepository itemRep;

    @GetMapping( "/vinculo-cadastrar.html")
    public ModelAndView vinculoCadastrar()
    {
        Vinculo vinculo = new Vinculo();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("vinculo-cadastrar");
        mv.addObject("vinculo", vinculo);
        return mv;
    }

    @PostMapping(value = "/salvar-vinculo.html")
    public ModelAndView itemSalvar(@Valid Vinculo vinculo, BindingResult binding){

        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("vinculo-cadastrar");
            mv.addObject("vinculo", vinculo);
            return mv;
        }
        vinculoRep.save(vinculo);
        mv.setViewName("redirect:/vinculo-listar.html");
        return mv;
    }

    @GetMapping("vinculo-listar/{id}")
    public ModelAndView vinculos(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Optional<Item> item = itemRep.findById(id);
        mv.addObject("item", item.get());
        mv.addObject("vinculos",vinculoRep.findAllByItemDestinoOrItemOrigem(item.get(), item.get()));
        mv.setViewName("vinculos-listar");
        return mv;
    }

    @GetMapping("vinculo-editar/{id}")
    public ModelAndView itemEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", vinculoRep.findById(id));
        mv.setViewName("vinculo-editar");
        return mv;
    }

    @GetMapping("/vinculo-deletar/{id}")
    public RedirectView itemDeletar(@PathVariable Long id){
        vinculoRep.deleteById(id);
        return new RedirectView("/inicio.html");
    }
}
