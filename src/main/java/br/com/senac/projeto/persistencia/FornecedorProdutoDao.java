package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class FornecedorProdutoDao extends BaseDAO<FornecedorProduto> {
    
    public FornecedorProdutoDao() {
        super(FornecedorProduto.class);
    }
    
    /* Função para buscar o relacionamento entre Fornecedor e Produto */
    public Optional<FornecedorProduto> buscarPorFornecedorEProduto(Integer fornecedorId, Integer produtoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT fp FROM FornecedorProduto fp WHERE fp.fornecedorId = :fornecedorId AND fp.produtoId = :produtoId";
            TypedQuery<FornecedorProduto> query = em.createQuery(jpql, FornecedorProduto.class);
            query.setParameter("fornecedorId", fornecedorId);
            query.setParameter("produtoId", produtoId);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
    
    /* Função para excluir o relacionamento entre Fornecedor e Produto */
    public void excluirPorFornecedorEProduto(Integer fornecedorId, Integer produtoId) {
        buscarPorFornecedorEProduto(fornecedorId, produtoId).ifPresent(this::delete);
    }
}