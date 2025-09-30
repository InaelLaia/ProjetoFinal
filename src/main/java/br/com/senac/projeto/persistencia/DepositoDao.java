package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DepositoDao extends BaseDAO<Deposito> {

    public DepositoDao() {
        super(Deposito.class);
    }

    /* Função para Listar o nome de todos os Depositos dentro do Banco de Dados */
    public List<String> listarNomes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT d.nome FROM Deposito d";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /* Função para obter o id do deposito */
    public Integer obterIdPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT d.id FROM Deposito d WHERE d.nome = :nome";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
