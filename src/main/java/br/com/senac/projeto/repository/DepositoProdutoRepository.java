package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.DepositoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositoProdutoRepository extends JpaRepository<DepositoProduto, Integer> {
    
    @Modifying
    @Transactional
    @Query("DELETE FROM DepositoProduto dp WHERE dp.depositoId = :depositoId AND dp.produtoId = :produtoId")
    void deleteByDepositoIdAndProdutoId(@Param("depositoId") Integer depositoId, 
                                       @Param("produtoId") Integer produtoId);
    
    Optional<DepositoProduto> findByDepositoIdAndProdutoId(Integer depositoId, Integer produtoId);
    
    boolean existsByDepositoIdAndProdutoId(Integer depositoId, Integer produtoId);
    
    List<DepositoProduto> findByDepositoId(Integer depositoId);
    
    @Query("SELECT dp FROM DepositoProduto dp WHERE dp.depositoId = :depositoId AND dp.produtoId = :produtoId")
    Optional<DepositoProduto> findRelacionamento(@Param("depositoId") Integer depositoId, 
                                                @Param("produtoId") Integer produtoId);
}