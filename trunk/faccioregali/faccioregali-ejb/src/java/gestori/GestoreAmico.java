/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Amico;
import entity.Regalo;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.AmicoFacadeLocal;
import session.RegaloFacadeLocal;
import utility.DesEncrypter;

/**
 * Classe che gestisce un amico
 * @author Davide
 */
@Stateless
public class GestoreAmico implements GestoreAmicoLocal {

    @EJB
    AmicoFacadeLocal amicoFacade;
    @EJB
    RegaloFacadeLocal regaloFacade;
    
    DesEncrypter encoder = DesEncrypter.getInstance();
    /**
     * Metodo che seleziona un amico dalla email
     * @param email
     * @return 
     */
    @Override
    public Amico selezionaByEmail(String email) {
        return amicoFacade.findByEmail(email);
    }

    /**
     * 
     * @param keyword
     * @return 
     */
    @Override
    public List<Amico> selezionaByKeyword(String keyword) {
        return amicoFacade.findByKeyword(keyword);
    }

    /**
     * 
     * @param keyword
     * @param idUtente
     * @return 
     */
    @Override
    public List<Amico> selezionaByKeywordAltri(String keyword, int idUtente) {
        return amicoFacade.findByKeywordOtherUsers(keyword, idUtente);
    }

    
    /** 
     * Registra un nuovo utente nella base dati
     * @param cognome utente
     * @param nome utente
     * @param email utente
     * @param password utente     
     */ 
    @Override
    public Amico registraNuovoUtente(String cognome,String nome, String email,String password) {
              
        Amico _utente = amicoFacade.findByEmail(email);         
                
        if (_utente!=null)
            return null;
        else
        {
            String encPass = encoder.encrypt(password);            
            _utente = amicoFacade.create(email, encPass, nome, cognome);            
            return _utente;
        }               
    }
    
    
     /**
     * Verifica che l'utente che sta cercando di accedere sia registrato nella base dati
     * @param email
     * @param password
     * @return utente != null se trovato
     */
    @Override
    public Amico loginUtente(String email, String password) {
        String enPass = encoder.encrypt(password);
        Amico _utente = amicoFacade.findByEmailPassword(email,enPass);
        return _utente;
    }
    
    
     @Override
    public List<Regalo> selezionaByIdAmico(int idAmico) {
        return regaloFacade.findByIdAmico(idAmico);
    }

     /**
     * Metodo che rimuove un Regalo con idRegalo dalla lista regali di un Utente con 
     * idUtente
     * @param idAmico dell'utente a cui rimuovere il regalo
     * @param idRegalo del regalo da rimuovere
     * @return True se l'operazione è andata a buon fine/False altrimenti
     */
    @Override
    public Boolean removeByIdAmicoIdRegalo(int idAmico, int idRegalo) {
        Boolean retValue;        
        try
        {
            Regalo _regalo = regaloFacade.getByIdAmicoIdRegalo(idAmico, idRegalo);     
            if (_regalo == null) return false;
            regaloFacade.remove(_regalo);
            retValue = true;
        }
        catch (Exception ex)
        {
            retValue =  false;
        }
        return retValue;
    }  
    
    
    /**
     * Moetodo che aggiunge un nuovo regalo alla lista desideri di un utente
     * @param nome del regalo
     * @param descrizione del regalo (prezzo caratteristiche....)
     * @param url del regalo (link al sito dove si trova)
     * @param idUtente dell'utente che ha aggiunto un regalo alla sua lista
     * @return true/false se l'operazione di inserimento del regalo nella lista è andata a buon fine o meno
     */
    @Override
    public Boolean AggiungiRegalo(String nome,String descrizione,String url,Integer idUtente)
    {
        Boolean retValue;        
        try
        {
            regaloFacade.create(idUtente,nome,descrizione,url);
            retValue = true; 
        }
        catch (Exception ex)
        {
           retValue = false; 
        }
        return retValue;
    }
}
