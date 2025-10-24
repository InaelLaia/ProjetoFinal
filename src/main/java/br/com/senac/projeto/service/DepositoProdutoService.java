package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.DepositoProduto;
import br.com.senac.projeto.repository.DepositoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoProdutoService {

    @Autowired
    private DepositoProdutoRepository depositoProdutoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private DepositoService depositoService;

    public DepositoProduto save(DepositoProduto depositoProduto) {
        return depositoProdutoRepository.save(depositoProduto);
    }

    public void excluirPorDepositoEProduto(Integer depositoId, Integer produtoId) {
        depositoProdutoRepository.deleteByDepositoIdAndProdutoId(depositoId, produtoId);
    }

    public boolean existeRelacionamento(Integer depositoId, Integer produtoId) {
        return depositoProdutoRepository.existsByDepositoIdAndProdutoId(depositoId, produtoId);
    }

    public List<DepositoProduto> listarProdutosPorDeposito(Integer depositoId) {
        return depositoProdutoRepository.findByDepositoId(depositoId);
    }

    public Optional<DepositoProduto> buscarRelacionamento(Integer depositoId, Integer produtoId) {
        return depositoProdutoRepository.findRelacionamento(depositoId, produtoId);
    }

    public Integer obterQuantidadeAtual(Integer depositoId, Integer produtoId) {
        return depositoProdutoRepository.findRelacionamento(depositoId, produtoId)
                .map(DepositoProduto::getQuantidadeAtual)
                .orElse(0);
    }

    public void excluirProdutoDoDeposito(String nomeDeposito, String nomeProduto) {
        Integer depositoId = depositoService.obterIdPorNome(nomeDeposito);
        Integer produtoId = produtoService.obterIdPorNome(nomeProduto);

        if (depositoId != null && produtoId != null) {
            excluirPorDepositoEProduto(depositoId, produtoId);
        } else {
            throw new RuntimeException("Depósito ou produto não encontrado");
        }
    }
}
