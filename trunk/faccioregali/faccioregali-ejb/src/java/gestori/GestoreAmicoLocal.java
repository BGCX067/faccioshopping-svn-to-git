/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Amico;
import entity.Regalo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Davide
 */
@Local
public interface GestoreAmicoLocal {
    
    Amico selezionaByEmail(String email);
    
    List<Amico> selezionaByKeyword(String keyword);
    
    List<Amico> selezionaByKeywordAltri(String keyword,int idUtente);
    
    Amico registraNuovoUtente(String cognome,String nome, String email,String password);
    
    Amico loginUtente(String email, String password);
    
    List<Regalo> selezionaByIdAmico(int idAmico);
    
    Boolean removeByIdAmicoIdRegalo(int idAmico, int idRegalo);
    
    Boolean AggiungiRegalo(String nome,String descrizione,String url,Integer idUtente);
}
