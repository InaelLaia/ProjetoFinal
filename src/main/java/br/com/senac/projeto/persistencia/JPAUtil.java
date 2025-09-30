package br.com.senac.projeto.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {

    //constante para centralizar o nome da unidade de persistência
    private static final String PERSISTENCE_UNIT = "loja_celular";
    private static EntityManagerFactory entityManagerFactory;

    private JPAUtil() {
        // Construtor privado para evitar instanciação
    }

    //cria a entidade se estiver nula e a retorna
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return entityManagerFactory.createEntityManager();
    }

    //fecha o EntityManager e o factory
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
