package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MovimentacaoGeralViewDao {
    
    /* Chama a View e Lista todos os seus dados */
    public List<MovimentacaoGeralView> listarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT m FROM MovimentacaoGeralView m";
            TypedQuery<MovimentacaoGeralView> query = em.createQuery(jpql, MovimentacaoGeralView.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}