/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Articolo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface ArticoloFacadeLocal {

    void create(Articolo articolo);

    void edit(Articolo articolo);

    void remove(Articolo articolo);

    Articolo find(Object id);

    List<Articolo> findAll();

    List<Articolo> findRange(int[] range);

    int count();
    
    Articolo getByNome(String nome);
}
