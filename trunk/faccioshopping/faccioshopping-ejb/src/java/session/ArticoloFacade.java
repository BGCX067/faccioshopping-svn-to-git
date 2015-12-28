/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Articolo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe che gestisce un entità articolo
 * @author Davide
 */
@Stateless
public class ArticoloFacade extends AbstractFacade<Articolo> implements ArticoloFacadeLocal {
    @PersistenceContext(unitName = "faccioshopping-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticoloFacade() {
        super(Articolo.class);
    }

    /**
     * Metodo che permette di ricercare un articolo 
     * che ha nome nomeArticolo
     * @param nomeArticolo nome dell'articolo 
     * @return Articolo cercato
     */
    @Override
    public Articolo getByNome(String nome) {
        Articolo retValue;
        try
        {
            retValue = (Articolo) em.createNamedQuery("Articolo.findByNome")
                    .setParameter("nome", nome)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            retValue = null;
        }
        return retValue;
    }
    
}
