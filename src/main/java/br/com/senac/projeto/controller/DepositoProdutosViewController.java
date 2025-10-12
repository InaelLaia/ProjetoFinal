package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepositoProdutosViewController {

    private final DepositoProdutosViewDao depositoProdutosViewDao = new DepositoProdutosViewDao();
    private final DepositoDao depositoDao = new DepositoDao();
    private final ProdutoDao produtoDao = new ProdutoDao();
    private final DepositoProdutoDao depositoProdutoDao = new DepositoProdutoDao();
    
    @GetMapping("/produtos")
    public ResponseEntity<List<String>> getNomesProdutosPorDeposito(@RequestParam("deposito") String deposito) {
        try {
            List<String> produtos = depositoProdutosViewDao.listarProdutosPorDeposito(deposito);

            if (produtos == null || produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/produtos-deposito")
    public ResponseEntity<List<Object[]>> getProdutosPorDeposito(@RequestParam("deposito") String deposito) {
        try {
            List<Object[]> produtos = depositoProdutosViewDao.listarDadosProdutosPorDeposito(deposito);

            if (produtos == null || produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping("/produto-deposito")
    public ResponseEntity<String> excluirProdutoDeposito(
            @RequestParam("deposito") String nomeDeposito,
            @RequestParam("produto") String nomeProduto) {
        try {
            Integer depositoId = depositoDao.obterIdPorNome(nomeDeposito);
            Integer produtoId = produtoDao.obterIdPorNome(nomeProduto);
            
            depositoProdutoDao.excluirPorDepositoEProduto(depositoId, produtoId);
            
            return ResponseEntity.ok("Produto removido do depósito com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao excluir produto do depósito: " + e.getMessage());
        }
    }
}