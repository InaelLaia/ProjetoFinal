package br.com.senac.projeto.controller;

import br.com.senac.projeto.entity.FornecedorProduto;
import br.com.senac.projeto.service.FornecedorService;
import br.com.senac.projeto.service.ProdutoService;
import br.com.senac.projeto.service.FornecedorProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private FornecedorProdutoService fornecedorProdutoService;
    
    @GetMapping
    public String fornecedorPage(Model model) {
        model.addAttribute("fornecedores", fornecedorService.listarNomesFornecedores());
        return "fornecedor";
    }
    
    @PostMapping("/adicionar")
    public String adicionarProdutoFornecedor(
            @RequestParam("fornecedor") String nomeFornecedor,
            @RequestParam("produto") String nomeProduto) {
        
        try {
            Integer fornecedorId = fornecedorService.obterIdPorNome(nomeFornecedor);
            Integer produtoId = produtoService.obterIdPorNome(nomeProduto);
            
            if (fornecedorProdutoService.existeRelacionamento(fornecedorId, produtoId)) {
                System.err.println("Relacionamento já existe: " + nomeFornecedor + " - " + nomeProduto);
                return "redirect:/fornecedor";
            }
            
            FornecedorProduto relacao = new FornecedorProduto(fornecedorId, produtoId);
            fornecedorProdutoService.save(relacao);
            
            System.out.println("Produto adicionado com sucesso ao fornecedor: " + nomeFornecedor + " - " + nomeProduto);
            
        } catch (Exception e) {
            System.err.println("Erro ao adicionar produto ao fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/fornecedor";
    }
    
    @PostMapping("/excluir")
    @ResponseBody
    public ResponseEntity<String> excluirProdutoFornecedor(
            @RequestParam("fornecedor") String nomeFornecedor,
            @RequestParam("produto") String nomeProduto) {
        
        try {
            System.out.println("Tentando excluir produto: " + nomeProduto + " do fornecedor: " + nomeFornecedor);
            
            Integer fornecedorId = fornecedorService.obterIdPorNome(nomeFornecedor);
            Integer produtoId = produtoService.obterIdPorNome(nomeProduto);
            
            System.out.println("IDs encontrados - Fornecedor: " + fornecedorId + ", Produto: " + produtoId);
            
            fornecedorProdutoService.excluirPorFornecedorEProduto(fornecedorId, produtoId);
            
            System.out.println("Exclusão realizada com sucesso");
            return ResponseEntity.ok("Produto excluído com sucesso");
            
        } catch (Exception e) {
            System.err.println("Erro ao excluir produto do fornecedor: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao excluir produto: " + e.getMessage());
        }
    }
}