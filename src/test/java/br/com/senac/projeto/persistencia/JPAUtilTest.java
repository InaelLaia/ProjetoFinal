package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


//Teste de Conexão com Banco de Dados
public class JPAUtilTest {
    
    private EntityManager em;
    
    @Before
    public void setUp() {
        em = JPAUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        JPAUtil.close();
    }
    
    @Test
    public void testConexaoBancoDados() {
        assertNotNull("EntityManager não deve ser nulo", em);
        assertTrue("EntityManager deve estar aberto", em.isOpen());
    }
    
    @Test
    public void testTransacaoAtiva() {
        assertFalse("Transação não deve estar ativa inicialmente", 
                   em.getTransaction().isActive());
        
        em.getTransaction().begin();
        assertTrue("Transação deve estar ativa após begin", 
                  em.getTransaction().isActive());
        
        em.getTransaction().rollback();
    }
}