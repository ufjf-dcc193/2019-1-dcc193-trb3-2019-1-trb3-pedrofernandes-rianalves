package br.ufjf.dcc193.trabalho03.controller;

import br.ufjf.dcc193.trabalho03.model.Anotacao;
import br.ufjf.dcc193.trabalho03.model.Item;
import br.ufjf.dcc193.trabalho03.model.Vinculo;
import br.ufjf.dcc193.trabalho03.repository.AnotacaoRepository;
import br.ufjf.dcc193.trabalho03.repository.ItemRepository;
import br.ufjf.dcc193.trabalho03.repository.VinculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnotacaoController {

    @Autowired
    private VinculoRepository vinculoRep;

    @Autowired
    private ItemRepository itemRep;

    @Autowired
    private AnotacaoRepository anotacaoRep;


    @GetMapping("/anotacao-vinculo-listar/{id}")
    public ModelAndView anotacaoVinculos(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = vinculoRep.findById(id).get();
        mv.addObject("vinculo", vinculo);
        mv.addObject("anotacoes", vinculo.getAnotacoes());
        mv.setViewName("anotacao-vinculo-listar");
        return mv;
    }

    @GetMapping("/anotacao-vinculo-editar/{id}/{idAnotacao}")
    public ModelAndView anotacaoVinculosEditar(@PathVariable Long id, @PathVariable Long idAnotacao) {
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = vinculoRep.findById(id).get();
        Anotacao anotacao = anotacaoRep.findById(idAnotacao).get();
        mv.addObject("vinculo", vinculo);
        mv.addObject("anotacao", anotacao);
        mv.setViewName("vinculo-anotar");
        return mv;
    }


    @GetMapping("/anotacao-vinculo-deletar/{id}/{idAnotacao}")
    public ModelAndView anotacaoVinculosDeletar(@PathVariable Long id, @PathVariable Long idAnotacao) {
        ModelAndView mv = new ModelAndView();
        Vinculo vinculo = vinculoRep.findById(id).get();
        Anotacao anotacao = anotacaoRep.findById(idAnotacao).get();
        vinculo.getAnotacoes().remove(anotacao);
        vinculoRep.save(vinculo);
        mv.setViewName("redirect:/anotacao-vinculo-listar/{id}");
        anotacaoRep.delete(anotacao);
        return mv;
    }

    @GetMapping("/anotacao-item-listar/{id}")
    public ModelAndView anotacaoItens(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Item item = itemRep.findById(id).get();
        mv.addObject("item", item);
        mv.addObject("anotacoes", item.getAnotacoes());
        mv.setViewName("anotacao-item-listar");
        return mv;
    }

    @GetMapping("/anotacao-item-editar/{id}/{idAnotacao}")
    public ModelAndView anotacaoItensEditar(@PathVariable Long id, @PathVariable Long idAnotacao) {
        ModelAndView mv = new ModelAndView();
        Item item = itemRep.findById(id).get();
        Anotacao anotacao = anotacaoRep.findById(idAnotacao).get();
        mv.addObject("item", item);
        mv.addObject("anotacao", anotacao);
        mv.setViewName("item-anotar");
        return mv;
    }


    @GetMapping("/anotacao-item-deletar/{id}/{idAnotacao}")
    public ModelAndView anotacaoItensDeletar(@PathVariable Long id, @PathVariable Long idAnotacao) {
        ModelAndView mv = new ModelAndView();
        Item item = itemRep.findById(id).get();
        Anotacao anotacao = anotacaoRep.findById(idAnotacao).get();
        item.getAnotacoes().remove(anotacao);
        itemRep.save(item);
        anotacaoRep.delete(anotacao);
        mv.setViewName("redirect:/anotacao-item-listar/{id}");
        return mv;
    }


}
