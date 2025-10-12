package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.FornecedorProdutosViewDao;
import br.com.senac.projeto.persistencia.ProdutoDao;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorProdutosViewController {

    private final FornecedorProdutosViewDao fornecedorProdutosViewDao = new FornecedorProdutosViewDao();
    private final ProdutoDao produtoDao = new ProdutoDao();

    @GetMapping("/produtos-fornecedor")
    public ResponseEntity<List<Object[]>> getProdutosPorFornecedor(@RequestParam("fornecedor") String fornecedor) {
        try {
            List<Object[]> produtos = fornecedorProdutosViewDao.listarDadosProdutosPorFornecedor(fornecedor);

            if (produtos == null || produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/produtos-disponiveis")
    public ResponseEntity<List<String>> getProdutosDisponiveis(@RequestParam("fornecedor") String fornecedor) {
        try {
            List<Object[]> produtosAssociados = fornecedorProdutosViewDao.listarDadosProdutosPorFornecedor(fornecedor);
            
            List<String> todosProdutos = produtoDao.listarNomes();
            
            for (Object[] produtoAssociado : produtosAssociados) {
                String nomeProdutoAssociado = (String) produtoAssociado[0];
                todosProdutos.remove(nomeProdutoAssociado);
            }
            
            return ResponseEntity.ok(todosProdutos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}