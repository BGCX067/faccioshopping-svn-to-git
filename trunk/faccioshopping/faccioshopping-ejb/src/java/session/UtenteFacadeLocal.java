/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface UtenteFacadeLocal {

    void create(Utente utente);

    void edit(Utente utente);

    void remove(Utente utente);

    Utente find(Object id);

    List<Utente> findAll();

    List<Utente> findRange(int[] range);

    int count();
   
    Utente getByEmailPassword(String email, String password);
    
    Utente getByEmail(String email,Boolean isInterno);
    
    Utente create(int idRuolo,String email,String password,String nome,String cognome,Boolean isInterno);
}
