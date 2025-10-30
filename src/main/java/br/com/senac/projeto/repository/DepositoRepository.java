package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Integer> {
    
    Optional<Deposito> findByNome(String nome);
    
    @Query("SELECT d.nome FROM Deposito d")
    List<String> listarNomes();

    @Query("SELECT d.id FROM Deposito d WHERE d.nome = :nome")
    Integer obterIdPorNome(@Param("nome") String nome);
        
    @Query(value = "SELECT p.NOME, dp.PONTOREPOSICAO, dp.QUANTIDADEATUAL " +
                   "FROM DEPOSITOPRODUTO dp " +
                   "JOIN PRODUTO p ON dp.PRODUTOID = p.ID " +
                   "JOIN DEPOSITO d ON dp.DEPOSITOID = d.ID " +
                   "WHERE d.NOME = :depositoNome", nativeQuery = true)
    List<Object[]> findProdutosByDeposito(@Param("depositoNome") String depositoNome);

}
