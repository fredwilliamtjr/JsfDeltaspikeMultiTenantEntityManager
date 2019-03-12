package br.com.fwtj.MavenJSfPrimefaces.servico;

import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Configuracao;
import br.com.fwtj.MavenJSfPrimefaces.repositorio.sistema.ConfiguracaoRepository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class Teste_1Service implements Serializable {

    @Inject
    private ConfiguracaoRepository teste_1Repository_;

    public List<Configuracao> findAll() {
        List<Configuracao> all = teste_1Repository_.findAll();
        return all;
    }

    public Configuracao save(Configuracao configuracao) {
        Configuracao save = teste_1Repository_.save(configuracao);
        return save;
    }



}
