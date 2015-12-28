/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Amico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface AmicoFacadeLocal {

    void create(Amico amico);

    void edit(Amico amico);

    void remove(Amico amico);

    Amico find(Object id);

    List<Amico> findAll();

    List<Amico> findRange(int[] range);

    int count();
    
    Amico findByEmail(String email);
    
    Amico findByEmailPassword(String email, String password);
    
    List<Amico> findByKeyword(String keyword);
    
    List<Amico> findByKeywordOtherUsers(String keyword,int idUtente);
    
    Amico create(String email,String password,String nome,String cognome);    
}
