/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Catalogo;
import entity.Categoria;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe che gestisce un entità Catalogo
 * @author Davide
 */
@Stateless
public class CatalogoFacade extends AbstractFacade<Catalogo> implements CatalogoFacadeLocal {
    @PersistenceContext(unitName = "faccioshopping-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoFacade() {
        super(Catalogo.class);
    }
  
    
    /**
     * Metodo che restituisce la lista dei cataloghi validi
     * @return 
     */
    @Override
    public List<Catalogo> findCataloghiValidi() {
        try
        {
       return em.createQuery("SELECT c FROM Catalogo c WHERE c.dataInizio <= :dataInizio AND c.dataFine >= :dataFine")
                .setParameter("dataInizio", Calendar.getInstance().getTime()).setParameter("dataFine", Calendar.getInstance().getTime()).getResultList();
        }
        catch (Exception e)
                {return null;}
    }
}
