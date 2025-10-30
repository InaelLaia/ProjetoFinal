package br.com.senac.projeto.controller;

import br.com.senac.projeto.service.DepositoProdutoService;
import br.com.senac.projeto.service.DepositoService;
import br.com.senac.projeto.service.FornecedorService;
import br.com.senac.projeto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private DepositoService depositoService;
    
    @Autowired
    private FornecedorService fornecedorService;
    
    @Autowired
    private DepositoProdutoService depositoProdutoService;
    
    @GetMapping("/produtos")
    public ResponseEntity<List<String>> getProdutosPorDeposito(@RequestParam String deposito) {
        try {
            List<String> produtos = produtoService.listarProdutosPorDeposito(deposito);
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/produtos-deposito")
    public ResponseEntity<List<Object[]>> getProdutosDeposito(@RequestParam String deposito) {
        try {
            List<Object[]> produtos = depositoService.listarProdutosPorDeposito(deposito);
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/fornecedor/produtos-fornecedor")
    public ResponseEntity<List<Object[]>> getProdutosFornecedor(@RequestParam String fornecedor) {
        try {
            List<Object[]> produtos = fornecedorService.listarProdutosPorFornecedor(fornecedor);
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/fornecedor/produtos-disponiveis")
    public ResponseEntity<List<String>> getProdutosDisponiveis(@RequestParam String fornecedor) {
        try {
            List<String> produtos = fornecedorService.listarProdutosDisponiveis(fornecedor);
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/produto-deposito")
public ResponseEntity<String> deleteProdutoDeposito(@RequestParam String deposito, 
                                                   @RequestParam String produto) {
    try {
        depositoProdutoService.excluirProdutoDoDeposito(deposito, produto);
        return ResponseEntity.ok("Produto removido do depósito com sucesso");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erro ao remover produto: " + e.getMessage());
    }
}
}