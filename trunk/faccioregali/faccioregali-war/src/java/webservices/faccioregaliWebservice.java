/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import entity.Regalo;
import entity.Amico;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import session.AmicoFacadeLocal;
import gestori.GestoreAmicoLocal;

/**
 * Webservice di faccioregali
 * @author Davide
 */
@WebService(serviceName = "faccioregaliWebservice")
public class faccioregaliWebservice {
    @EJB
    private AmicoFacadeLocal utenteFacade;
    @EJB
    private GestoreAmicoLocal gestoreAmico;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")


    /**
     * Web method che ricerca la lista regali di un amico
     * @param email dell'amico
     * @return lista dei regali dell'amico
     */
    @WebMethod(operationName = "ricercaListaRegaliByEmail")
    public List<Regalo> ricercaListaRegaliByEmail(@WebParam(name = "email") String email) {
        Amico _utente = gestoreAmico.selezionaByEmail(email);
        List<Regalo> retList = null;
        if (_utente != null)
        {
            retList = gestoreAmico.selezionaByIdAmico(_utente.getId());
        }
        return retList;
    }
    
        
    /**
     * Web method che ricerca la lista regali di un utente
     * @param idAmico dell'utente
     * @return lista dei regali dell'utente
     */
    @WebMethod(operationName = "ricercaListaRegaliByIdAmico")
    public List<Regalo> ricercaListaRegaliByIdAmico(@WebParam(name = "idAmico") int idAmico) {
        List<Regalo> retList = null;
        retList = gestoreAmico.selezionaByIdAmico(idAmico);
        return retList;
    }
    
    /**
     * Web method che ricerca gli amici che soddisfano il criterio di ricerca
     * @param keyword chiave di ricerca 
     * può essere il nome o il cognome o l'email
     * @return lista degli amici cercati
     */
    @WebMethod(operationName="ricercaListaAmiciByKeyword")
    public List<Amico> ricercaListaAmiciByKeyword(@WebParam(name="keyword") String keyword)
    {
        List<Amico> retList= null;
        retList = gestoreAmico.selezionaByKeyword(keyword);
        return retList;
    }
    
    
    /**
     * Web method che ricerca l'amico per idutente
     * @param idAmico 
     * @return amico cercato
     */
    @WebMethod(operationName="ricercaUtenteById")
    public Amico ricercaAmicoById(@WebParam(name="idAmico") int idAmico)
    {
        Amico retValue;
        try
        {
            retValue = utenteFacade.find(idAmico);
        }
        catch (Exception ex)
        {
            retValue = null;
        }
        return retValue;
    }
    
    /**
     * Web method che ricerca un utente per email
     * @param email dell'utente
     * @return amico cercato
     */
    @WebMethod(operationName="ricercaUtenteByEmail")
    public Amico ricercaUtenteByEmail(@WebParam(name="email") String email)
    {
        Amico retValue;
        try
        {
            retValue = gestoreAmico.selezionaByEmail(email);
        }
        catch (Exception ex)
        {
            retValue = null;
        }
        return retValue;
    }
}
