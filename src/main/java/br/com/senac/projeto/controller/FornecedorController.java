package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class FornecedorController {
    
    private FornecedorDao fornecedorDao = new FornecedorDao();
    private ProdutoDao produtoDao = new ProdutoDao();
    private FornecedorProdutoDao fornecedorProdutoDao = new FornecedorProdutoDao();
    
    @GetMapping("/fornecedor")
    public String fornecedor(Model model) {
        model.addAttribute("fornecedores", fornecedorDao.listarNomes());
        return "fornecedor";
    }
    
    @PostMapping("/fornecedor/adicionar")
    public String adicionarProdutoFornecedor(
            @RequestParam("fornecedor") String nomeFornecedor,
            @RequestParam("produto") String nomeProduto) {
        
        try {
            Integer fornecedorId = fornecedorDao.obterIdPorNome(nomeFornecedor);
            Integer produtoId = produtoDao.obterIdPorNome(nomeProduto);
            
            FornecedorProduto relacao = new FornecedorProduto();
            relacao.setFornecedorId(fornecedorId);
            relacao.setProdutoId(produtoId);
            
            fornecedorProdutoDao.save(relacao);
            
        } catch (Exception e) {
            System.err.println("Erro ao adicionar produto ao fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/fornecedor";
    }
    
    @PostMapping("/fornecedor/excluir")
    @ResponseBody
    public ResponseEntity<String> excluirProdutoFornecedor(
            @RequestParam("fornecedor") String nomeFornecedor,
            @RequestParam("produto") String nomeProduto) {
        
        try {
            System.out.println("Tentando excluir produto: " + nomeProduto + " do fornecedor: " + nomeFornecedor);
            
            Integer fornecedorId = fornecedorDao.obterIdPorNome(nomeFornecedor);
            Integer produtoId = produtoDao.obterIdPorNome(nomeProduto);
            
            System.out.println("IDs encontrados - Fornecedor: " + fornecedorId + ", Produto: " + produtoId);
            
            fornecedorProdutoDao.excluirPorFornecedorEProduto(fornecedorId, produtoId);
            
            System.out.println("Exclusão realizada com sucesso");
            return ResponseEntity.ok("Produto excluído com sucesso");
            
        } catch (Exception e) {
            System.err.println("Erro ao excluir produto do fornecedor: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao excluir produto: " + e.getMessage());
        }
    }
}