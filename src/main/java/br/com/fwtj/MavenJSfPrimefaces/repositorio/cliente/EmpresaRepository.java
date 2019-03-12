package br.com.fwtj.MavenJSfPrimefaces.repositorio.cliente;

import br.com.fwtj.MavenJSfPrimefaces.jpa.cliente.AnnotationEntityManagerCliente;
import br.com.fwtj.MavenJSfPrimefaces.jpa.cliente.EntityManagerResolverCliente;
import br.com.fwtj.MavenJSfPrimefaces.modelo.cliente.Empresa;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerConfig;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

@EntityManagerConfig(entityManagerResolver = EntityManagerResolverCliente.class, flushMode = FlushModeType.COMMIT)
public abstract class EmpresaRepository extends AbstractEntityRepository<Empresa, Long> {

    @Inject
    @AnnotationEntityManagerCliente
    private EntityManager entityManager;


}
