package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.FornecedorProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FornecedorProdutoRepository extends JpaRepository<FornecedorProduto, Integer> {
    
    @Modifying
    @Transactional
    @Query("DELETE FROM FornecedorProduto fp WHERE fp.fornecedorId = :fornecedorId AND fp.produtoId = :produtoId")
    void deleteByFornecedorIdAndProdutoId(@Param("fornecedorId") Integer fornecedorId, 
                                         @Param("produtoId") Integer produtoId);
    
    Optional<FornecedorProduto> findByFornecedorIdAndProdutoId(Integer fornecedorId, Integer produtoId);
    
    boolean existsByFornecedorIdAndProdutoId(Integer fornecedorId, Integer produtoId);
}