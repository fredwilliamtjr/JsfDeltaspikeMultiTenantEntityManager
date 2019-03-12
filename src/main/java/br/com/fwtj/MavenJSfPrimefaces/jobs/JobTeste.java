package br.com.fwtj.MavenJSfPrimefaces.jobs;

import br.com.fwtj.MavenJSfPrimefaces.modelo.sistema.Configuracao;
import br.com.fwtj.MavenJSfPrimefaces.servico.Teste_1Service;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Scheduled(cronExpression = "0 0/1 * * * ?")
public class JobTeste implements org.quartz.Job, Serializable {

    @Inject
    private Teste_1Service teste_1Service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobTeste");
        List<Configuracao> all = teste_1Service.findAll();
        System.out.println(all);
    }
}
