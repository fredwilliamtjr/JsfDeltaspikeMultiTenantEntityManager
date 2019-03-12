package br.com.fwtj.MavenJSfPrimefaces.seguranca;

import br.com.fwtj.MavenJSfPrimefaces.modelo.cliente.Usuario;
import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Login;
import org.apache.deltaspike.data.api.audit.CurrentUser;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UsuarioLogado implements Serializable {

    private Login login;
    private Usuario usuario;
    private boolean logado = false;
    private String schemaName;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * Injeta dados do login na auditoria
     */
    @Produces
    @CurrentUser
    public String currentUser() {
        if (usuario != null) {
            return usuario.getEmail();
        } else {
            return "Não Logado";
        }

    }

    @Override
    public String toString() {
        return "UsuarioLogado{" +
                "usuario=" + usuario +
                ", logado=" + logado +
                ", schemaName='" + schemaName + '\'' +
                '}';
    }
}
