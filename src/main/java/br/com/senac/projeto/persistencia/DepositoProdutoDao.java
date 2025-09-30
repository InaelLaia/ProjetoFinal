package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class DepositoProdutoDao extends BaseDAO<DepositoProduto> {
    
    public DepositoProdutoDao() {
        super(DepositoProduto.class);
    }
    
    /* Função para buscar o relacionamento entre Deposito e Produto */
    public Optional<DepositoProduto> buscarPorDepositoEProduto(Integer depositoId, Integer produtoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT dp FROM DepositoProduto dp WHERE dp.depositoId = :depositoId AND dp.produtoId = :produtoId";
            TypedQuery<DepositoProduto> query = em.createQuery(jpql, DepositoProduto.class);
            query.setParameter("depositoId", depositoId);
            query.setParameter("produtoId", produtoId);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
    
    /* Função para excluir o relacionamento entre Deposito e Produto */
    public void excluirPorDepositoEProduto(Integer depositoId, Integer produtoId) {
        buscarPorDepositoEProduto(depositoId, produtoId).ifPresent(this::delete);
    }
}
