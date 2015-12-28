/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Catalogo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.CatalogoFacadeLocal;

/**
 * Classe che definisce il gestore di un catalogo
 * @author Davide
 */
@Stateless
public class GestoreCatalogo implements GestoreCatalogoLocal {

    @EJB
    CatalogoFacadeLocal catalogoFacade;
    
    /**
     * Metodo che restituisce la lista dei cataloghi validi 
     * @return List di Catalogo
     */
    @Override
    public List<Catalogo> selezionaListaCataloghi() {
        return catalogoFacade.findCataloghiValidi();
    }

    /**
     * Metodo che seleziona un catalogo dato il suo id
     * @param idCatalogo
     * @return Catalogo
     */
    @Override
    public Catalogo selezionaById(int idCatalogo) {
        return catalogoFacade.find(idCatalogo);
    }
    
}
