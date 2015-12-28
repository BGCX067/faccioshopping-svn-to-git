/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Regalo;
import java.util.List;
import javax.ejb.Local;

/**
 * Classe che identifica un regalo su faccioregali
 * @author Davide
 */
@Local
public interface RegaloFacadeLocal {

    void create(Regalo regalo);

    void edit(Regalo regalo);

    void remove(Regalo regalo);

    Regalo find(Object id);

    List<Regalo> findAll();

    List<Regalo> findRange(int[] range);

    int count();
    
    List<Regalo> findByIdAmico(int idAmico);
    
    Regalo getByIdAmicoIdRegalo(int idAmico,int idRegalo);
    
    void create(int idUtente,String nome,String descrizione,String url);
}
