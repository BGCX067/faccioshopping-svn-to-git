/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Articolo;
import javax.ejb.Local;

/**
 * Interfaccia che definisce i metodi del gestore Articolo
 * @author Davide
 */
@Local
public interface GestoreArticoloLocal {
    
    Articolo selezionaByNome(String nome);
    
    Articolo selezionaById(int id);
       
}
