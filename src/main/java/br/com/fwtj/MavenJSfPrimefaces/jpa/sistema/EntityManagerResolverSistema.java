package br.com.fwtj.MavenJSfPrimefaces.jpa.sistema;


import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerResolver;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EntityManagerResolverSistema implements EntityManagerResolver {

    @Inject
    @AnnotationEntityManagerSistema
    private EntityManager entityManager;


    @Override
    public EntityManager resolveEntityManager() {
        return entityManager;
    }
}
