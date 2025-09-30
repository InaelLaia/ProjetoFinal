package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProdutoDao extends BaseDAO<Produto> {
    
    public ProdutoDao() {
        super(Produto.class);
    }
    
    /* Função para Listar o nome de todos os Produtos dentro do Banco de Dados */
    public List<String> listarNomes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p.nome FROM Produto p";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /* Função para obter o id do produto */
    public Integer obterIdPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p.id FROM Produto p WHERE p.nome = :nome";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}