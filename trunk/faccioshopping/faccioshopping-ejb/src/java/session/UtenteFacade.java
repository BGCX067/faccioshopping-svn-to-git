/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utente;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Davide
 */
@Stateless
public class UtenteFacade extends AbstractFacade<Utente> implements UtenteFacadeLocal {
    @PersistenceContext(unitName = "faccioshopping-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UtenteFacade() {
        super(Utente.class);
    }

     /**
     * Metodo che ricerca un utente con indirizzo email
     * @param email indirizzo email dell'utente
     * @return Utente trovato oppure null
     */
    @Override
    public Utente getByEmail(String email, Boolean isInterno) {
        Utente retValue = null;
        try
        {
            retValue = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = :email AND u.isInterno = :isInterno").setParameter("email", email).setParameter("isInterno",isInterno).getSingleResult();
        }
        finally
        {
            return retValue;
        }        
    }

    /**
     * Metodo che ricerca un utente con indirizzo email e password
     * @param email dell'utente
     * @param password dell'utente
     * @return Utente cercato oppure null
     */
    @Override
    public Utente getByEmailPassword(String email, String password) {
        Utente retValue = null;
        try
        {            
            retValue = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password AND u.isInterno = 1")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        }
        finally
        {
            return retValue;
        } 
    }
    
    /**
     * Metodo utilizzato per creare un nuovo utente
     * @param email
     * @param password
     * @param nome
     * @param cognome
     * @param isInterno
     * @return 
     */
    @Override 
    public Utente create(int idRuolo,String email,String password,String nome,String cognome,Boolean isInterno)
    {
        Utente _utente = new Utente(1,email,nome,cognome, isInterno);         
        _utente.setPassword(password);
        _utente.setIdRuolo(idRuolo);
        _utente.setDataRegistrazione(Calendar.getInstance().getTime());     
        
        super.create(_utente);
        
        return _utente;
    }
    
}
