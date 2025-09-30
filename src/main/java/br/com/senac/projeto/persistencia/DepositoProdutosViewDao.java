package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DepositoProdutosViewDao {

    /* Chama a View e Lista os nomes dos produtos relacionados ao deposito selecionado */
    public List<String> listarProdutosPorDeposito(String deposito) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v.nomeProduto FROM DepositoProdutosView v WHERE v.nomeDeposito = :deposito";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("deposito", deposito);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /* Chama a View e Lista o nome, ponto de reposicao e quantidade atual dos produtos relacionados ao deposito selecionado */
    public List<Object[]> listarDadosProdutosPorDeposito(String deposito) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v.nomeProduto, v.pontoReposicao, v.quantidadeAtual "
                    + "FROM DepositoProdutosView v WHERE v.nomeDeposito = :deposito";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("deposito", deposito);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
