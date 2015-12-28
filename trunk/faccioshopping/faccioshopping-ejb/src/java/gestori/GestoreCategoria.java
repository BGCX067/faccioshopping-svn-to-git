/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Categoria;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.CategoriaFacadeLocal;

/**
 *
 * @author Davide
 */
@Stateless
public class GestoreCategoria implements GestoreCategoriaLocal {

   @EJB
   CategoriaFacadeLocal categoriaFacade;

    /**
    * Metodo che seleziona una categoria dato il suo Id
    * @param id
    * @return Categoria selezionata
    */
    @Override
    public Categoria selezionaById(int id) {
       return categoriaFacade.find(id);
    }   
}
