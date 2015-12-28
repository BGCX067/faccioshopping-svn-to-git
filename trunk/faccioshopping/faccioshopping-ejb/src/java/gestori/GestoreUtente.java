/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Utente;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.UtenteFacadeLocal;
import utility.DesEncrypter;

/**
 * Classe che definisce il gestore di un utente
 * @author Davide
 */
@Stateless
public class GestoreUtente implements GestoreUtenteLocal {

    
    
    @EJB 
    UtenteFacadeLocal utenteFacade;        
    
    DesEncrypter encoder = DesEncrypter.getInstance();
    
     /**
     * Metodo che permette il login con OpenID di un utente
     * @param email indirizzo email dell'utente
     * @return Utente trovato oppure null
     */
    @Override
    public Utente loginOpenID(String email) {
        Utente retValue = null;
        try
        {
            retValue = utenteFacade.getByEmail(email,Boolean.FALSE);
        }
        finally
        {
            return retValue;
        }        
    }

    /**
     * Metodo che permette il login al sito di un utente
     * @param email dell'utente
     * @param password dell'utente
     * @return Utente cercato oppure null
     */
    @Override
    public Utente login(String email, String password) {    
            String enPass = encoder.encrypt(password);
            return utenteFacade.getByEmailPassword(email, enPass);       
    }
    
    
    /**
     * Metodo che registra un nuovo utente 
     * @param cognome
     * @param nome
     * @param email
     * @param password
     * @param isInterno
     * @return 
     */
    @Override
    public Utente registrazione(String cognome,String nome, String email,String password,Boolean isInterno) {
        
        Utente _utente = null;    
        _utente = utenteFacade.getByEmail(email,isInterno);      
        if (_utente == null)
        {     
            String encPass = (password != null) ? encoder.encrypt(password) : ""; 
            _utente = utenteFacade.create(0,email, encPass, nome, cognome, isInterno);                        
        }               
        return _utente;
    }

    /**
     * Metodo che modifica i dati di un utente
     * @param idUtente
     * @param cognome
     * @param nome
     * @param email 
     * @param vecchiapassword 
     * @param nuovapassword
     * @param isInterno
     * @return 
     */
    @Override
    public Utente modifica(int idUtente, String cognome, String nome, String email, String vecchiapassword, String nuovapassword,Boolean isInterno) {
        Utente retValue = utenteFacade.find(idUtente);
        
        if (retValue != null)
        {
            retValue.setCognome(cognome);
            retValue.setNome(nome);
            if (isInterno)
            {
                retValue.setEmail(email);
                String enVecchiaPass = encoder.encrypt(vecchiapassword);                               
                
                if (enVecchiaPass.equals(retValue.getPassword())) 
                {
                    String enNuovaPass = encoder.encrypt(nuovapassword);
                    retValue.setPassword(enNuovaPass);
                }                
                else return null;
            }
            utenteFacade.edit(retValue);  
        }
        
        return retValue;
    }
    
}
