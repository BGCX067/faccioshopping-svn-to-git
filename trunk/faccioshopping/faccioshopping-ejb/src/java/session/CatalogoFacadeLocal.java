/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Catalogo;
import entity.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface CatalogoFacadeLocal {

    void create(Catalogo catalogo);

    void edit(Catalogo catalogo);

    void remove(Catalogo catalogo);

    Catalogo find(Object id);

    List<Catalogo> findAll();

    List<Catalogo> findRange(int[] range);

    int count();
    
    List<Catalogo> findCataloghiValidi();     
}
