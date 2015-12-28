/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Regalo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe che gestisce un Regalo
 * @author Davide
 */
@Stateless
public class RegaloFacade extends AbstractFacade<Regalo> implements RegaloFacadeLocal {
    @PersistenceContext(unitName = "faccioregali-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RegaloFacade() {
        super(Regalo.class);
    }

    /**
     * Metodo che ricerca una List di Regalo dell'Utente che ha
     * idUtente
     * @param idUtente dell'utente
     * @return List di Regalo dell'utente
     */
    @Override
    public List<Regalo> findByIdAmico(int idAmico) {
        return em.createNamedQuery("Regalo.findByIdAmico")
                    .setParameter("idAmico", idAmico)
                    .getResultList();
    }

    @Override
    public Regalo getByIdAmicoIdRegalo(int idAmico, int idRegalo) {
        Regalo retValue = null;
        try
        {
            retValue = (Regalo) em.createQuery("SELECT r FROM Regalo r WHERE r.idRegalo = :idRegalo AND r.idUtente = :idUtente")
                    .setParameter("idAmico", idAmico)
                    .setParameter("idRegalo", idRegalo).getSingleResult();   
        }
        finally
        {
           return retValue; 
        }
    }
    
    @Override
    public void create(int idUtente,String nome,String descrizione,String url)
    {
        Regalo _regalo = new Regalo(1,idUtente);        
        _regalo.setNome(nome);
        _regalo.setDescrizione(descrizione);
        _regalo.setUrlArticolo(url);
        
        super.create(_regalo);
    }
}
