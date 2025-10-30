package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.Produto;
import br.com.senac.projeto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }
    
    public Optional<Produto> buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }
    
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public void deletarProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
    
    public List<String> listarNomesProdutos() {
        return produtoRepository.listarNomes();
    }
    
    public Integer obterIdPorNome(String nome) {
        return produtoRepository.obterIdPorNome(nome);
    }
    
    public List<String> listarProdutosPorDeposito(String depositoNome) {
        return produtoRepository.findProdutosByDeposito(depositoNome);
    }
    
}
