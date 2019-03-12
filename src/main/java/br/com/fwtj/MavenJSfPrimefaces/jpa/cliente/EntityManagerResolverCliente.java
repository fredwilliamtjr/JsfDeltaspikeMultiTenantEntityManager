package br.com.fwtj.MavenJSfPrimefaces.jpa.cliente;


import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerResolver;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EntityManagerResolverCliente implements EntityManagerResolver {

    @Inject
    @AnnotationEntityManagerCliente
    private EntityManager entityManager;


    @Override
    public EntityManager resolveEntityManager() {
        return entityManager;
    }
}
