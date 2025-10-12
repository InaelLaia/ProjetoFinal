package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DepositoProdutoController {
    
    private ProdutoDao produtoDao = new ProdutoDao();
    private DepositoDao depositoDao = new DepositoDao();
    private DepositoProdutoDao depositoProdutoDao = new DepositoProdutoDao();
    
    @GetMapping("/adicionar-produto")
    public String adicionarProduto(Model model) {
        List<String> produtos = produtoDao.listarNomes();
        List<String> depositos = depositoDao.listarNomes();
        
        model.addAttribute("produtos", produtos);
        model.addAttribute("depositos", depositos);
        return "adicionar-produto";
    }
    
    @PostMapping("/adicionar-produto")
    public String salvarProdutoDeposito(
            @RequestParam("produto") String nomeProduto,
            @RequestParam("deposito") String nomeDeposito,
            @RequestParam("ponto-reposicao") Integer pontoReposicao) {
        
        try {
            Integer produtoId = produtoDao.obterIdPorNome(nomeProduto);
            Integer depositoId = depositoDao.obterIdPorNome(nomeDeposito);
            
            DepositoProduto depositoProduto = new DepositoProduto();
            depositoProduto.setProdutoId(produtoId);
            depositoProduto.setDepositoId(depositoId);
            depositoProduto.setPontoReposicao(pontoReposicao);
            depositoProduto.setQuantidadeAtual(0); 
            
            depositoProdutoDao.save(depositoProduto);
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar produto no depósito: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/";
    }
}