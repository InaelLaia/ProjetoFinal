package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class FornecedorDao extends BaseDAO<Fornecedor> {

    public FornecedorDao() {
        super(Fornecedor.class);
    }

    /* Função para Listar o nome de todos os Fornecedores dentro do Banco de Dados */
    public List<String> listarNomes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f.nome FROM Fornecedor f";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /* Função para obter o id do fornecedor */
    public Integer obterIdPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f.id FROM Fornecedor f WHERE f.nome = :nome";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
