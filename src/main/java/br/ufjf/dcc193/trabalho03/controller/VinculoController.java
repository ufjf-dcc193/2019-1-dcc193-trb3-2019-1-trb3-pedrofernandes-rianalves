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
import java.util.List;
import java.util.Optional;

@Controller
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRep;

    @Autowired
    private ItemRepository itemRep;

    @GetMapping( "/vinculo-cadastrar/{id}")
    public ModelAndView vinculoCadastrar(@PathVariable Long id)
    {

        Vinculo vinculo = new Vinculo();
        ModelAndView mv = new ModelAndView();
        mv.addObject("itens", itemRep.findAll());
        mv.addObject("itemId",id);
        mv.setViewName("vinculo-cadastrar");
        mv.addObject("vinculo", vinculo);
        return mv;
    }

    @PostMapping("/salvar-vinculo/{id}")
    public ModelAndView vinculoSalvar(@PathVariable Long id, Vinculo vinculo){
        ModelAndView mv = new ModelAndView();
        Item item = itemRep.findById(id).get();
        vinculoRep.save(vinculo);
        item.getVinculos().add(vinculo);
        itemRep.save(item);
        mv.setViewName("redirect:/vinculo-listar/{id}");
        return mv;
    }

    @GetMapping("vinculo-listar/{id}")
    public ModelAndView vinculos(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Optional<Item> item = itemRep.findById(id);
        List<Vinculo> vinculos = vinculoRep.findAllByItemDestinoOrItemOrigem(item.get(), item.get());
        mv.addObject("item", item.get());
        mv.addObject("vinculos", vinculos);
        mv.setViewName("vinculos-listar");
        return mv;
    }

    @GetMapping("vinculo-editar/{id}")
    public ModelAndView vinculoEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", vinculoRep.findById(id));
        mv.setViewName("vinculo-editar");
        return mv;
    }

    @GetMapping("/vinculo-deletar/{id}")
    public RedirectView vinculoDeletar(@PathVariable Long id){
        vinculoRep.deleteById(id);
        return new RedirectView("/inicio.html");
    }

    @GetMapping("/vinculo-anotar/{id}")
    public ModelAndView vinculoAnotar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    @GetMapping("/vinculo-etiquetar/{id}")
    public ModelAndView vinculoEtiquetar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();

        return mv;
    }
}
