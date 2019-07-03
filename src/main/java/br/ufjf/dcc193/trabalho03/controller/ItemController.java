package br.ufjf.dcc193.trabalho03.controller;

import javax.validation.Valid;

import br.ufjf.dcc193.trabalho03.model.Anotacao;
import br.ufjf.dcc193.trabalho03.repository.AnotacaoRepository;
import br.ufjf.dcc193.trabalho03.repository.EtiquetaRepository;
import br.ufjf.dcc193.trabalho03.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho03.model.Item;
import br.ufjf.dcc193.trabalho03.repository.ItemRepository;

import java.time.LocalDateTime;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRep;

    @Autowired
    private AnotacaoRepository anotacaoRep;

    @Autowired
    private EtiquetaRepository etiquetaRep;

    @GetMapping("/item-cadastrar.html")
    public ModelAndView  itemCadastrar()
    {
        Item item = new Item();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("item-cadastrar");
        mv.addObject("item", item);
        return mv;
    }

    @PostMapping(value = "/salvar-item.html")
    public ModelAndView itemSalvar(@Valid Item item, BindingResult binding){
    
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("item-cadastrar");
            mv.addObject("item", item);
            return mv;
        }
        itemRep.save(item);
        mv.setViewName("redirect:/item-listar.html");
       return mv;  
    }

    @GetMapping("item-listar.html")
    public ModelAndView itens(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("itens", itemRep.findAll());
        return mv;
    }

    @GetMapping("item-editar/{id}")
    public ModelAndView itemEditar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", itemRep.findById(id));
        mv.setViewName("item-editar");
        return mv;
    }

    @GetMapping("/item-deletar/{id}")
    public RedirectView itemDeletar(@PathVariable Long id){
        itemRep.deleteById(id);
        return new RedirectView("/item-listar.html");
    }


    @GetMapping("/item-etiquetar/{id}")
    public ModelAndView itemEtiquetar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("etiquetas", etiquetaRep.findAll());
        mv.addObject("item", itemRep.findById(id).get());
        mv.setViewName("item-etiquetar");
        return mv;
    }

    @PostMapping("/item-etiquetar/{id}")
    public ModelAndView salvarEtiqueta(@PathVariable Long id, Item itemEtiquetas){
        ModelAndView mv = new ModelAndView();
        Item item = itemRep.findById(id).get();
        item.setEtiquetas(itemEtiquetas.getEtiquetas());
        itemRep.save(item);
        mv.setViewName("redirect:/inicio.html");
        return mv;
    }

    @GetMapping("/item-deletar-etiqueta/{id}")
    public RedirectView itemDeletarEtiqueta(@PathVariable Long id){
        Item item = new Item();
        item = itemRep.findById(id).get();
        item.setEtiquetas(null);
        itemRep.save(item);
        return new RedirectView("/item-listar.html");
    }

    @GetMapping("/item-anotar/{id}")
    public ModelAndView itemAnotar(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Anotacao anotacao = new Anotacao();
        mv.addObject("anotacao", anotacao);
        mv.addObject("item", itemRep.findById(id).get());
        mv.setViewName("item-anotar");
        return mv;
    }

    @PostMapping("/item-anotar/{id}")
    public ModelAndView salvarAnotacaoItem(@PathVariable Long id, Anotacao anotacao){
        ModelAndView mv = new ModelAndView();
        LoginService service = new LoginService();
        Item item = itemRep.findById(id).get();
        if(anotacao.getDataInclusao() == null){
            anotacao.setDataInclusao(LocalDateTime.now());
        }
        anotacao.setDataAlteracao(LocalDateTime.now());
        anotacao.setUsuario(service.getUsuario());
        if(anotacao.getId()==null) {
            item.getAnotacoes().add(anotacao);
            itemRep.save(item);
        }
        anotacaoRep.save(anotacao);
        mv.setViewName("redirect:/inicio.html");
        return mv;
    }

}