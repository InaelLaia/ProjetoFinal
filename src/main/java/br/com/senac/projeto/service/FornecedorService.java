package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.Fornecedor;
import br.com.senac.projeto.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarFornecedorPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Integer id) {
        fornecedorRepository.deleteById(id);
    }

    public List<String> listarNomesFornecedores() {
        return fornecedorRepository.listarNomes();
    }

    public List<Object[]> listarProdutosPorFornecedor(String fornecedorNome) {
        return fornecedorRepository.findProdutosByFornecedor(fornecedorNome);
    }

    public List<String> listarProdutosDisponiveis(String fornecedorNome) {
        return fornecedorRepository.findProdutosDisponiveis(fornecedorNome);
    }

    public Fornecedor salvarProduto(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
    
    public Integer obterIdPorNome(String nome) {
        return fornecedorRepository.obterIdPorNome(nome);
    }

    public void adicionarProdutoAoFornecedor(String fornecedorNome, String produtoNome) {
        fornecedorRepository.adicionarProdutoFornecedor(fornecedorNome, produtoNome);
    }

    public void removerProdutoDoFornecedor(String fornecedorNome, String produtoNome) {
        fornecedorRepository.removerProdutoFornecedor(fornecedorNome, produtoNome);
    }
}
