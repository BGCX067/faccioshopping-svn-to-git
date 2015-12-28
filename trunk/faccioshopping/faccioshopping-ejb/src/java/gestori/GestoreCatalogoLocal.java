/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestori;

import entity.Catalogo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaccia per la definizione del gestore di un catalogo
 * @author Davide
 */
@Local
public interface GestoreCatalogoLocal {
    
    List<Catalogo> selezionaListaCataloghi();  
    
    Catalogo selezionaById(int idCatalogo);
}
