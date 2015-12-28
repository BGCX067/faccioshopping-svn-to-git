/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface CategoriaFacadeLocal {

    void create(Categoria categoria);

    void edit(Categoria categoria);

    void remove(Categoria categoria);

    Categoria find(Object id);

    List<Categoria> findAll();

    List<Categoria> findRange(int[] range);

    int count();
    
}
