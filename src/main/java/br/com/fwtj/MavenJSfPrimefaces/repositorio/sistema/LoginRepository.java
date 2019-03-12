package br.com.fwtj.MavenJSfPrimefaces.repositorio.sistema;

import br.com.fwtj.MavenJSfPrimefaces.jpa.sistema.AnnotationEntityManagerSistema;
import br.com.fwtj.MavenJSfPrimefaces.jpa.sistema.EntityManagerResolverSistema;
import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Login;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerConfig;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

@EntityManagerConfig(entityManagerResolver = EntityManagerResolverSistema.class, flushMode = FlushModeType.COMMIT)
public abstract class LoginRepository extends AbstractEntityRepository<Login, Long> {

    @Inject
    @AnnotationEntityManagerSistema
    private EntityManager entityManager;


}
