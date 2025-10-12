package br.com.senac.projeto.controller;

import br.com.senac.projeto.persistencia.Produto;
import br.com.senac.projeto.persistencia.ProdutoDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {
    
    private ProdutoDao produtoDao = new ProdutoDao();
    
    @GetMapping("/produto")
    public String produto() {
        return "produto";
    }
    
    @PostMapping("/produto")
    public String salvarProduto(
            @RequestParam("nome") String nome,
            @RequestParam("marca") String marca,
            @RequestParam("preco") Double preco) {
        
        try {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setMarca(marca);
            produto.setPreco(preco);
            
            produtoDao.save(produto);
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/";
    }
}