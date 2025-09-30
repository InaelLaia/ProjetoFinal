package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class FornecedorProdutosViewDao {
    
    /* Chama a View e Lista o nome, marca e preço dos produtos relacionados ao fornecedor selecionado */
    public List<Object[]> listarDadosProdutosPorFornecedor(String fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v.nomeProduto, v.marcaProduto, v.precoProduto " +
                         "FROM FornecedorProdutosView v WHERE v.nomeFornecedor = :fornecedor";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("fornecedor", fornecedor);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}