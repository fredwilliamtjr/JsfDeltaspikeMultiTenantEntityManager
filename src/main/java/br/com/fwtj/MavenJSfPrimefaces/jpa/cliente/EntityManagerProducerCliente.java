package br.com.fwtj.MavenJSfPrimefaces.jpa.cliente;

import br.com.fwtj.MavenJSfPrimefaces.seguranca.UsuarioLogado;
import br.com.fwtj.MavenJSfPrimefaces.util.ConstantesUtils;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
public class EntityManagerProducerCliente implements Serializable {

    @Inject
    private UsuarioLogado usuarioLogado;

    @Produces
    @AnnotationEntityManagerCliente
    @SessionScoped
    public EntityManager createEntityManagerMultiTenant() {
        System.out.println("createEntityManagerMultiTenant : " + usuarioLogado.getSchemaName());
        PersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.default_schema", usuarioLogado.getSchemaName());
        criarSchema(usuarioLogado.getSchemaName());
        EntityManagerFactory entityManagerFactory = persistenceProvider.createEntityManagerFactory("CLIENTE", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public void disposeEntityManagerMultiTenant(@Disposes @AnnotationEntityManagerCliente EntityManager entityManager) {
        System.out.println("disposeEntityManagerMultiTenant : " + usuarioLogado.getSchemaName());
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    private void criarSchema(String schemaName) {
        System.out.println("criarSchema : " + usuarioLogado.getSchemaName());
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + ConstantesUtils.IP_BANCO_DADOS + ":" + ConstantesUtils.PORTA_BANCO_DADOS + "/" + ConstantesUtils.NOME_BANCO_DADOS_CLIENTE, ConstantesUtils.USUARIO_SISTEMA_BANCO_DADOS, ConstantesUtils.SENHA_SISTEMA_BANCO_DADOS);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE SCHEMA " + schemaName + " AUTHORIZATION \"" + ConstantesUtils.USUARIO_SISTEMA_BANCO_DADOS + "\";");
            statement.executeUpdate("GRANT ALL ON SCHEMA " + schemaName + " TO \"" + ConstantesUtils.USUARIO_SISTEMA_BANCO_DADOS + "\";");
            statement.close();
            connection.close();
        } catch (Exception e) {
            try {
                statement.close();
                connection.close();
            } catch (Exception ignored) {

            }

        }

    }

}
