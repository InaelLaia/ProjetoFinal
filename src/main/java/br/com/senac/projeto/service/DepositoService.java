package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.Deposito;
import br.com.senac.projeto.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {
    
    @Autowired
    private DepositoRepository depositoRepository;
    
    public List<Deposito> listarTodosDepositos() {
        return depositoRepository.findAll();
    }
    
    public Optional<Deposito> buscarDepositoPorId(Integer id) {
        return depositoRepository.findById(id);
    }
    
    public Deposito salvarDeposito(Deposito deposito) {
        return depositoRepository.save(deposito);
    }
    
    public void deletarDeposito(Integer id) {
        depositoRepository.deleteById(id);
    }
    
    public List<String> listarNomesDepositos() {
        return depositoRepository.listarNomes();
    }
    
    public Integer obterIdPorNome(String nome) {
        return depositoRepository.obterIdPorNome(nome);
    }
    
    public List<Object[]> listarProdutosPorDeposito(String depositoNome) {
        return depositoRepository.findProdutosByDeposito(depositoNome);
    }
}
