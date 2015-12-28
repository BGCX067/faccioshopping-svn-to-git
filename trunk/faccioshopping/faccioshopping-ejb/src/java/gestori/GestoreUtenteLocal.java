/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Utente;
import javax.ejb.Local;

/**
 * Interfaccia che definisce i metodi del gestore di un utente
 * @author Davide
 */
@Local
public interface GestoreUtenteLocal {
    
    Utente loginOpenID(String email);
    
    Utente registrazione(String cognome,String nome, String email,String password,Boolean isInterno);
    
    Utente login(String cognome,String nome);
    
    Utente modifica(int idUtente, String cognome,String nome,String email,String oldpassword,String newpassword,Boolean isInterno);

}
