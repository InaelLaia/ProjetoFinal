package br.com.senac.projeto.controller;

import br.com.senac.projeto.entity.Produto;
import br.com.senac.projeto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public String produtoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto";
    }
    
    @PostMapping
    public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, 
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "produto";
        }
        
        try {
            produtoService.salvarProduto(produto);
            model.addAttribute("success", "Produto cadastrado com sucesso!");
            return "produto";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar produto: " + e.getMessage());
            return "produto";
        }
    }
    
}