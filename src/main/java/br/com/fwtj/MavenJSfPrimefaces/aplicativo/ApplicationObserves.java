package br.com.fwtj.MavenJSfPrimefaces.aplicativo;

import org.apache.deltaspike.core.api.lifecycle.Destroyed;
import org.apache.deltaspike.core.api.lifecycle.Initialized;
import org.apache.deltaspike.jsf.api.listener.phase.AfterPhase;
import org.apache.deltaspike.jsf.api.listener.phase.BeforePhase;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseId;

import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class ApplicationObserves implements Serializable {


    public void init(@Observes PostConstructApplicationEvent event) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> PostConstructApplicationEvent : " + event.toString());
    }

    public void init(@Observes PreDestroyApplicationEvent event) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> PreDestroyApplicationEvent : " + event.toString());
    }

    public void init(@Observes ExceptionQueuedEvent event) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ExceptionQueuedEvent : " + event.toString());
    }

    public void onBeforeFacesRequest(@Observes @Initialized FacesContext facesContext) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> onBeforeFacesRequest");
    }

    public void onAfterFacesRequest(@Observes @Destroyed FacesContext facesContext) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> onAfterFacesRequest");
    }

    public void onBeforePhase(@Observes @BeforePhase(JsfPhaseId.ANY_PHASE) PhaseEvent event) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> onBeforePhase");
    }

    public void onAfterPhase(@Observes @AfterPhase(JsfPhaseId.ANY_PHASE) PhaseEvent event) {
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> onAfterPhase");
    }


}
