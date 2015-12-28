/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Categoria;
import javax.ejb.Local;

/**
 * Interfaccia per la gestione di una categoria
 * @author Davide
 */
@Local
public interface GestoreCategoriaLocal {
    
    Categoria selezionaById(int id);
    
}
