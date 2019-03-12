package br.com.fwtj.MavenJSfPrimefaces.jpa.sistema;

import br.com.fwtj.MavenJSfPrimefaces.aplicativo.QualificadorStartup;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityManagerProducerSistema {

    @QualificadorStartup
    @Produces
    @AnnotationEntityManagerSistema
    @ApplicationScoped
    public EntityManager create() {
        PersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        Map<String, String> properties = new HashMap<>();
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("SISTEMA", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public void dispose(@Disposes @AnnotationEntityManagerSistema EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
