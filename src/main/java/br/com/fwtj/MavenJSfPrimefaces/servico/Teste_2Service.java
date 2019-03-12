//package br.com.fwtj.MavenJSfPrimefaces.servico;
//
//import br.com.fwtj.MavenJSfPrimefaces.modelo.Teste_2;
//import br.com.fwtj.MavenJSfPrimefaces.repositorio.Teste_2Repository;
//
//import javax.inject.Inject;
//import java.io.Serializable;
//import java.util.List;
//
//public class Teste_2Service implements Serializable {
//
//    @Inject
//    private Teste_2Repository teste_2Repository;
//
//    public List<Teste_2> findAll() {
//        List<Teste_2> all = teste_2Repository.findAll();
//        return all;
//    }
//
//    public Teste_2 save(Teste_2 teste_2){
//        Teste_2 save = teste_2Repository.save(teste_2);
//        return save;
//    }
//
//}
