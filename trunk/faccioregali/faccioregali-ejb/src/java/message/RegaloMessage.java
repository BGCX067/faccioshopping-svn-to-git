/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import gestori.GestoreAmicoLocal;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Classe messaggio jms Regalo di faccioregali
 * @author Davide
 */
@MessageDriven(mappedName = "jms/FQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RegaloMessage implements MessageListener {
    
    @EJB
    GestoreAmicoLocal regaloLocal;
    @Resource
    private MessageDrivenContext mdc;
    
    public RegaloMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                int idAmico = msg.getIntProperty("idUtente");
                String url = msg.getStringProperty("url");
                String descrizione = msg.getStringProperty("descrizione");
                String nome = msg.getStringProperty("nome");               
                regaloLocal.AggiungiRegalo(nome, descrizione, url, idAmico);
            }
        } catch (Exception e) {
            mdc.setRollbackOnly();
        }   
    }
}
