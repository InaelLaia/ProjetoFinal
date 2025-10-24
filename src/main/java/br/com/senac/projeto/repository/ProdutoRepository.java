package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
    Optional<Produto> findByNome(String nome);
    
    @Query("SELECT p.nome FROM Produto p")
    List<String> listarNomes();
    
    @Query("SELECT p.id FROM Produto p WHERE p.nome = :nome")
    Integer obterIdPorNome(@Param("nome") String nome);
    
    @Query(value = "SELECT p.nome FROM PRODUTO p " +
                   "JOIN DEPOSITOPRODUTO dp ON p.ID = dp.PRODUTOID " +
                   "JOIN DEPOSITO d ON dp.DEPOSITOID = d.ID " +
                   "WHERE d.NOME = :depositoNome", nativeQuery = true)
    List<String> findProdutosByDeposito(@Param("depositoNome") String depositoNome);
}
