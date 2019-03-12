package br.com.fwtj.MavenJSfPrimefaces.seguranca;


import br.com.fwtj.MavenJSfPrimefaces.modelo.cliente.Usuario;
import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Login;
import org.apache.deltaspike.security.api.authorization.Secures;

import javax.interceptor.InvocationContext;

public class SegurancaMetodoControlador {

    @Secures
    @QualificadorMetodoProtegido
    public boolean doSecuredCheck(InvocationContext invocationContext, UsuarioLogado usuarioLogado) {
        String name = invocationContext.getMethod().getName();
        System.out.println(name);
        boolean logado = usuarioLogado.isLogado();
        Usuario usuario = usuarioLogado.getUsuario();

        if (logado && usuario != null && usuario.isAdmin()) {
            return true;
        } else {
            return false;
        }

    }

}
