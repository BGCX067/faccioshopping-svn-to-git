/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Amico;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe che gestisce un amico su faccioregali
 * @author Davide
 */
@Stateless
public class AmicoFacade extends AbstractFacade<Amico> implements AmicoFacadeLocal {
    @PersistenceContext(unitName = "faccioregali-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AmicoFacade() {
        super(Amico.class);
    }

        /**
     * Metodo che ricerca un utente con indirizzo email
     * @param email indirizzo email dell'utente
     * @return Utente trovato oppure null
     */
    @Override
    public Amico findByEmail(String email) {
        Amico retValue = null;
        try
        {
            retValue = (Amico) em.createQuery("SELECT u FROM Amico u WHERE u.email = :email").setParameter("email", email).getSingleResult();
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
    public Amico findByEmailPassword(String email, String password) {
        Amico retValue = null;
        try
        {
            retValue = (Amico) em.createQuery("SELECT u FROM Amico u WHERE u.email = :email AND u.password = :password")
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
     * Metodo per ricercare gli utente tra tutti gli utenti presenti 
     * con una chiave di ricerca che
     * può essere nome il cognome oppure email
     * @param keyword con dui cercare l'utente
     * @return Lista di Utente trovata
     */
    @Override
    public List<Amico> findByKeyword(String keyword) {
        return em.createQuery("SELECT u FROM Amico u WHERE u.email = :email OR u.nome = :nome OR u.cognome = :cognome")
                    .setParameter("email", keyword)
                    .setParameter("nome", keyword)
                    .setParameter("cognome", keyword)
                    .getResultList();
    }
    
    /**
     * Metodo che ricerca gli utenti con keyword che può essere nome cognome o email
     * ma che non sia l'utente con idUtente
     * @param keyword di ricerca
     * @param idUtente da escludere dalla ricerca
     * @return Lista di Utente trovata
     */
    @Override
    public List<Amico> findByKeywordOtherUsers(String keyword,int idUtente) {
        return em.createQuery("SELECT u FROM Amico u WHERE (u.email = :email OR u.nome = :nome OR u.cognome = :cognome) AND u.id <> :idAmico")
                    .setParameter("email", keyword)
                    .setParameter("nome", keyword)
                    .setParameter("cognome", keyword)
                    .setParameter("idAmico", idUtente)
                    .getResultList(); 
    }
    
    
        /**
     * Metodo utilizzato per creare un nuovo amico
     * @param email
     * @param password
     * @param nome
     * @param cognome
     * @param isInterno
     * @return 
     */
    @Override 
    public Amico create(String email,String password,String nome,String cognome)
    {
        Amico _utente = new Amico(1,email,nome,cognome);         
        _utente.setPassword(password);        
        _utente.setDataRegistrazione(Calendar.getInstance().getTime());  
        
        super.create(_utente);
        
        return _utente;
    }
}
