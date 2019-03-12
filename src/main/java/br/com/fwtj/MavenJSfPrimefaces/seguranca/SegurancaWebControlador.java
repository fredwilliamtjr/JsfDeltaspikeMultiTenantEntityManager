package br.com.fwtj.MavenJSfPrimefaces.seguranca;


import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Pagina;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SegurancaWebControlador implements AccessDecisionVoter {

    @Inject
    private UsuarioLogado usuarioLogado;

    @Override
    public Set<SecurityViolation> checkPermission(AccessDecisionVoterContext accessDecisionVoterContext) {

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        String emailUsuario = "Não Logado";
        List<Pagina> paginas = new ArrayList<>();

        if (usuarioLogado != null && usuarioLogado.getUsuario() != null && usuarioLogado.getLogin() != null) {
            emailUsuario = usuarioLogado.getUsuario().getEmail();
            paginas = usuarioLogado.getLogin().getPaginas();
        }
        System.out.println("SegurancaWebControlador : " + viewId + ", E-Mail : " + emailUsuario);

        Set<SecurityViolation> violations = new HashSet<>();

        boolean paginaLiberada = isPaginaLiberada(viewId, paginas);

        if (!paginaLiberada) {
            SecurityViolation violation = new SecurityViolation() {
                @Override
                public String getReason() {
                    return "Acesso não permitido!";
                }
            };
            violations.add(violation);
        }

        return violations;
    }

    private boolean isPaginaLiberada(String pagina, List<Pagina> paginas) {

        pagina = pagina.replace("/", "").replaceFirst(".xhtml", "");

        boolean retorno = false;

        if (
                pagina.equalsIgnoreCase("login") ||
                        pagina.equalsIgnoreCase("error") ||
                        pagina.equalsIgnoreCase("error2") ||
                        pagina.equalsIgnoreCase("naologado") ||
                        pagina.equalsIgnoreCase("negado") ||
                        pagina.equalsIgnoreCase("p404")
        ) {
            retorno = true;
        }

        boolean contains = paginas.contains(new Pagina(pagina));
        if (contains) {
            retorno = true;
        }

        return retorno;

    }

}
