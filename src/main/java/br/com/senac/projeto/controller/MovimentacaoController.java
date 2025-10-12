package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovimentacaoController {
    
    private MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    private DepositoDao depositoDao = new DepositoDao();
    private ProdutoDao produtoDao = new ProdutoDao();
    
    @GetMapping("/movimentacao")
    public String movimentacao(Model model) {
        List<String> depositos = depositoDao.listarNomes();
        model.addAttribute("depositos", depositos);
        return "movimentacao";
    }
    
    @PostMapping("/movimentacao")
    public String salvarMovimentacao(
            @RequestParam("deposito") String nomeDeposito,
            @RequestParam("produto") String nomeProduto,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("tipo") Integer tipo) {
        
        try {
            Integer depositoId = depositoDao.obterIdPorNome(nomeDeposito);
            Integer produtoId = produtoDao.obterIdPorNome(nomeProduto);
            
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setProdutoId(produtoId);
            movimentacao.setDepositoId(depositoId);
            movimentacao.setQuantidade(quantidade);
            movimentacao.setTipo(tipo);
            
            movimentacaoDao.save(movimentacao);
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar movimentação: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/";
    }
}