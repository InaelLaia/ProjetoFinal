package br.com.senac.projeto.repository;

import br.com.senac.projeto.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Optional<Fornecedor> findByNome(String nome);

    @Query("SELECT f.nome FROM Fornecedor f")
    List<String> listarNomes();

    @Query(value = "SELECT p.NOME, p.MARCA, p.PRECO "
            + "FROM FORNECEDORPRODUTO fp "
            + "JOIN PRODUTO p ON fp.PRODUTOID = p.ID "
            + "JOIN FORNECEDOR f ON fp.FORNECEDORID = f.ID "
            + "WHERE f.NOME = :fornecedorNome", nativeQuery = true)
    List<Object[]> findProdutosByFornecedor(@Param("fornecedorNome") String fornecedorNome);

    @Query(value = "SELECT p.NOME FROM PRODUTO p "
            + "WHERE p.NOME NOT IN ("
            + "    SELECT p2.NOME FROM FORNECEDORPRODUTO fp "
            + "    JOIN PRODUTO p2 ON fp.PRODUTOID = p2.ID "
            + "    JOIN FORNECEDOR f ON fp.FORNECEDORID = f.ID "
            + "    WHERE f.NOME = :fornecedorNome"
            + ")", nativeQuery = true)
    List<String> findProdutosDisponiveis(@Param("fornecedorNome") String fornecedorNome);
    
    @Query("SELECT f.id FROM Fornecedor f WHERE f.nome = :nome")
    Integer obterIdPorNome(@Param("nome") String nome);

    @Query(value = "INSERT INTO FORNECEDORPRODUTO (FORNECEDORID, PRODUTOID) "
            + "SELECT f.ID, p.ID FROM FORNECEDOR f, PRODUTO p "
            + "WHERE f.NOME = :fornecedorNome AND p.NOME = :produtoNome", nativeQuery = true)
    void adicionarProdutoFornecedor(@Param("fornecedorNome") String fornecedorNome,
            @Param("produtoNome") String produtoNome);

    @Query(value = "DELETE FROM FORNECEDORPRODUTO WHERE FORNECEDORID = "
            + "(SELECT ID FROM FORNECEDOR WHERE NOME = :fornecedorNome) "
            + "AND PRODUTOID = (SELECT ID FROM PRODUTO WHERE NOME = :produtoNome)", nativeQuery = true)
    void removerProdutoFornecedor(@Param("fornecedorNome") String fornecedorNome,
            @Param("produtoNome") String produtoNome);
}
