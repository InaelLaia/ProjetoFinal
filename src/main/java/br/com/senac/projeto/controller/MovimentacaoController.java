package br.com.senac.projeto.controller;

import br.com.senac.projeto.entity.Movimentacao;
import br.com.senac.projeto.service.DepositoService;
import br.com.senac.projeto.service.MovimentacaoService;
import br.com.senac.projeto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    @Autowired
    private MovimentacaoService movimentacaoService;
    
    @Autowired
    private DepositoService depositoService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public String movimentacaoForm(Model model) {
        model.addAttribute("movimentacao", new Movimentacao());
        model.addAttribute("depositos", depositoService.listarNomesDepositos());
        return "movimentacao";
    }
    
    @PostMapping
    public String salvarMovimentacao(@RequestParam String deposito,
                                    @RequestParam String produto,
                                    @RequestParam Integer quantidade,
                                    @RequestParam Integer tipo) {
        
        Integer depositoId = depositoService.obterIdPorNome(deposito);
        Integer produtoId = produtoService.obterIdPorNome(produto);
        
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDepositoId(depositoId);
        movimentacao.setProdutoId(produtoId);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        
        movimentacaoService.salvarMovimentacao(movimentacao);
        
        return "redirect:/";
    }
}