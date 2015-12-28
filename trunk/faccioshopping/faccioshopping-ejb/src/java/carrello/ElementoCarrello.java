/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package carrello;

import entity.Articolo;
import java.io.Serializable;

/**
 * Elemento del carrello della spesa
 * @author Davide
 * 
 */
public class ElementoCarrello implements Serializable{
    Articolo articolo;
    int quantita;
    String idCaratteristica;

    public String getIdCaratteristica() {
        return idCaratteristica;
    }

    public void setIdCaratteristica(String idCaratteristica) {
        this.idCaratteristica = idCaratteristica;
    }
 

    public ElementoCarrello(Articolo articolo,String idCaratteristica) {
        this.articolo = articolo;
        this.idCaratteristica = idCaratteristica;
        quantita = 1;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(short quantita) {
        this.quantita = quantita;
    }

    public void incrementaQuantita() {
        quantita++;
    }

    public void decrementaQuantita() {
        quantita--;
    }

    public double getTotale() {
        double somma = 0;
        somma = (this.getQuantita() * articolo.getPrezzo().doubleValue());
        return somma;
    }
}
