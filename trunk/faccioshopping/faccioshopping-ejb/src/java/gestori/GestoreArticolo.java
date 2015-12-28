/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Articolo;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.ArticoloFacadeLocal;

/**
 * Classe che gestisce un articolo
 * @author Davide
 */
@Stateless
public class GestoreArticolo implements GestoreArticoloLocal {

    @EJB 
    ArticoloFacadeLocal articoloFacade;

    
    /**
     * Metodo che permette di selezionare un articolo dato il suo nome
     * @param nome
     * @return Articolo
     */
    @Override
    public Articolo selezionaByNome(String nome) {
        return articoloFacade.getByNome(nome);
    }
    
    /**
     * Metodo che seleziona un articolo dato il suo id
     * @param id
     * @return Articolo
     */
    @Override    
    public Articolo selezionaById(int id) {
        return articoloFacade.find(id);
    }
}
