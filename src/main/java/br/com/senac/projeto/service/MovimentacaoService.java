package br.com.senac.projeto.service;

import br.com.senac.projeto.entity.Movimentacao;
import br.com.senac.projeto.entity.MovimentacaoGeralView;
import br.com.senac.projeto.repository.MovimentacaoGeralViewRepository;
import br.com.senac.projeto.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {
    
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    
    @Autowired
    private MovimentacaoGeralViewRepository movimentacaoGeralViewRepository;
    
    public Movimentacao salvarMovimentacao(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }
    
    public List<MovimentacaoGeralView> listarTodasMovimentacoes() {
        return movimentacaoGeralViewRepository.findAll();
    }
}
