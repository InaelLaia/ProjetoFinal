package br.com.senac.projeto.controller;

import br.com.senac.projeto.entity.Deposito;
import br.com.senac.projeto.entity.DepositoProduto;
import br.com.senac.projeto.service.DepositoService;
import br.com.senac.projeto.service.ProdutoService;
import br.com.senac.projeto.service.DepositoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deposito")
public class DepositoController {
    
    @Autowired
    private DepositoService depositoService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private DepositoProdutoService depositoProdutoService;
    
    @GetMapping
    public String depositoPage(Model model) {
        model.addAttribute("depositos", depositoService.listarNomesDepositos());
        return "deposito";
    }
    
    @GetMapping("/adicionar-deposito")
    public String adicionarDepositoForm(Model model) {
        model.addAttribute("deposito", new Deposito());
        return "adicionar-deposito";
    }
    
    @PostMapping("/adicionar-deposito")
    public String salvarDeposito(@Valid @ModelAttribute("deposito") Deposito deposito, 
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "adicionar-deposito";
        }
        
        try {
            depositoService.salvarDeposito(deposito);
            model.addAttribute("success", "Depósito cadastrado com sucesso!");
            return "redirect:/deposito";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao cadastrar depósito: " + e.getMessage());
            return "adicionar-deposito";
        }
    }
    
    @GetMapping("/adicionar-produto")
    public String adicionarProdutoForm(Model model) {
        model.addAttribute("depositos", depositoService.listarNomesDepositos());
        model.addAttribute("produtos", produtoService.listarNomesProdutos());
        return "adicionar-produto";
    }
    
    @PostMapping("/adicionar-produto")
    public String adicionarProdutoDeposito(
            @RequestParam("deposito") String nomeDeposito,
            @RequestParam("produto") String nomeProduto,
            @RequestParam("ponto-reposicao") Integer pontoReposicao) {
        
        try {
            Integer depositoId = depositoService.obterIdPorNome(nomeDeposito);
            Integer produtoId = produtoService.obterIdPorNome(nomeProduto);
            
            if (depositoProdutoService.existeRelacionamento(depositoId, produtoId)) {
                System.err.println("Produto já existe no depósito: " + nomeDeposito + " - " + nomeProduto);
                return "redirect:/deposito/adicionar-produto?error=Produto+já+existe+no+depósito";
            }
            
            DepositoProduto relacao = new DepositoProduto(depositoId, produtoId, pontoReposicao, 0);
            depositoProdutoService.save(relacao);
            
            System.out.println("Produto adicionado com sucesso ao depósito: " + nomeDeposito + " - " + nomeProduto);
            
        } catch (Exception e) {
            System.err.println("Erro ao adicionar produto ao depósito: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/deposito/adicionar-produto?error=Erro+ao+adicionar+produto";
        }
        
        return "redirect:/deposito";
    }
    
}