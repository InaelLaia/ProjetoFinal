package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.MovimentacaoGeralView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoGeralViewRepository extends JpaRepository<MovimentacaoGeralView, Integer> {
    
}

