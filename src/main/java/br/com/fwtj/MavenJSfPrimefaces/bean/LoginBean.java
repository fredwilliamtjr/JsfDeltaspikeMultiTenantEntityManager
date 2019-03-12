package br.com.fwtj.MavenJSfPrimefaces.bean;

import br.com.fwtj.MavenJSfPrimefaces.aplicativo.ApplicationResources;
import br.com.fwtj.MavenJSfPrimefaces.modelo.cliente.Empresa;
import br.com.fwtj.MavenJSfPrimefaces.modelo.cliente.Usuario;
import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Login;
import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Pagina;
import br.com.fwtj.MavenJSfPrimefaces.repositorio.cliente.UsuarioRepository;
import br.com.fwtj.MavenJSfPrimefaces.repositorio.sistema.LoginRepository;
import br.com.fwtj.MavenJSfPrimefaces.seguranca.QualificadorMetodoProtegido;
import br.com.fwtj.MavenJSfPrimefaces.seguranca.UsuarioLogado;
import br.com.fwtj.MavenJSfPrimefaces.util.jsf.FacesUtil;
import br.com.fwtj.MavenJSfPrimefaces.util.jsf.SessionUtil;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


@Named
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    private Event<ExceptionToCatchEvent> catchEvent;

    @Inject
    private ApplicationResources applicationResources;

    @Inject
    private LoginRepository loginRepository;

    @Inject
    private UsuarioRepository usuarioRepository;


    public ApplicationResources getApplicationResources() {
        return applicationResources;
    }

    private String email = "1@1.com";
    private String senha = "1";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private Login podeLogar(String email, String senha) {
        Login login = loginRepository.findBy(1L);
        return login;
    }


    public void preparaSistema() {

        Empresa empresa1 = new Empresa("1", "1111");

        Login login1 = new Login("1@1.com", "1", empresa1.getCnpj(), true);
        login1.getPaginas().add(new Pagina("index"));
        login1.getPaginas().add(new Pagina("dadosUsuario"));
        login1.getPaginas().add(new Pagina("graficoGoogleChartBarra"));
        login1.getPaginas().add(new Pagina("graficoGoogleChartPizza"));
        Login login1Salvo = loginRepository.save(login1);

        Empresa empresa2 = new Empresa("2", "2222");

        Usuario usuario1 = new Usuario(login1Salvo.getEmail(), false, empresa1);
        //empresa1.getUsuarios().add(usuario1);
        //empresa2.getUsuarios().add(usuario1);
        usuario1.getEmpresas().add(empresa1);
        usuario1.getEmpresas().add(empresa2);

        usuarioLogado.setSchemaName("schema".concat(login1Salvo.getCnpjMatriz()));

        Usuario usuario1Salvo = usuarioRepository.save(usuario1);


    }


    public void login() throws IOException {

        Login login = podeLogar(email, senha);

        if (login != null) {

            usuarioLogado.setLogin(login);
            usuarioLogado.setSchemaName("schema".concat(login.getCnpjMatriz()));

            Usuario usuario = usuarioRepository.findBy(1L);
            usuarioLogado.setUsuario(usuario);
            usuarioLogado.setLogado(true);
            FacesUtil.redirect("/index");
        } else {
            FacesUtil.redirect("/negado");
        }

    }

    public void logout() throws IOException {
        SessionUtil.invalidate();
        FacesUtil.redirect("/login");
    }

    public void dadosUsuario() throws IOException {
        FacesUtil.redirect("/dadosUsuario");
    }

    public void voltar() throws IOException {
        FacesUtil.redirect("/index");
    }

    public void erro() {
        try {
            Integer integer = Integer.valueOf("fgutrutu");
            System.out.println(integer);
        } catch (NumberFormatException e) {
            catchEvent.fire(new ExceptionToCatchEvent(e));
        }
    }

    public void DoubleSubmit() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("DoubleSubmit");
    }

    @QualificadorMetodoProtegido
    public void ApenasAdmin() {
        FacesUtil.addInfoMessage("Metodo Apenas Admin");
    }


}
