package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.FornecedorProduto;
import br.com.senac.projeto.repository.FornecedorProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorProdutoService {
    
    @Autowired
    private FornecedorProdutoRepository fornecedorProdutoRepository;
    
    public FornecedorProduto save(FornecedorProduto fornecedorProduto) {
        return fornecedorProdutoRepository.save(fornecedorProduto);
    }
    
    public void excluirPorFornecedorEProduto(Integer fornecedorId, Integer produtoId) {
        fornecedorProdutoRepository.deleteByFornecedorIdAndProdutoId(fornecedorId, produtoId);
    }
    
    public boolean existeRelacionamento(Integer fornecedorId, Integer produtoId) {
        return fornecedorProdutoRepository.existsByFornecedorIdAndProdutoId(fornecedorId, produtoId);
    }
}