/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package carrello;

import entity.Articolo;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe necessaria per la gestione di un carrello della spesa virtuale
 * @author Davide
 */
public class Carrello {
    List<ElementoCarrello> elementi;
    int numeroDiElementi;
    double totale;

    public Carrello() {
        elementi = new ArrayList<ElementoCarrello>();
        numeroDiElementi = 0;
        totale = 0;
    }

    /**
     * Aggiunge un <code>ElementoCarrello</code> alla lista di <code>elementi</code> del <code>Carrello</code>.
     * Se un <code>articolo</code> esiste già
     * nella lista del carrello, la sua quantità viene incrementata
     *
     * @param articolo l'<code>Articolo</code> che definisce il tipo di elemento del carrello
     * @see ElementoCarrello
     */
    public synchronized void addArticolo(Articolo articolo,String idCaratteristica) {

        boolean newItem = true;

        for (ElementoCarrello scItem : elementi) {

            if (scItem.getArticolo().getId() == articolo.getId() 
                    && scItem.getIdCaratteristica() == idCaratteristica) {
                newItem = false;
                scItem.incrementaQuantita();
            }
        }

        if (newItem) {
            ElementoCarrello scItem = new ElementoCarrello(articolo,idCaratteristica);
            elementi.add(scItem);
        }
    }
    
    /**
     * Cancellazione di un <code>Articolo</code> dalla lista del carrello
     * @param articolo l'articolo di tipo <code>Articolo</code> da cancellare dalla lista
     * @param caratteristica dell'articolo
     */
    public synchronized void delArticolo(Articolo articolo,String idCaratteristica) {

        boolean existItem = false;
        ElementoCarrello selElemento = null;

        for (ElementoCarrello scItem : elementi) {

            if (scItem.getArticolo().getId() == articolo.getId()
                    && scItem.getIdCaratteristica() == idCaratteristica) {
                selElemento = scItem;
                existItem = true;
                break;
            }
        }

        if (existItem) {
            elementi.remove(selElemento);
        }
    }

    /**
     * Aggiorna l' <code>ElementoCarrello</code> dell'
     * <code>articolo</code> con la quantità specificata. Se '<code>0</code>'
     * l' <code>ElementoCarrello</code> è cancellato dalla lista <code>elementi</code>
     * del <code>Carrello</code>.
     *
     * @param articolo l'<code>Articolo</code> che definisce il tipo di elemento del carrello
     * @param quantita il numero di <code>ElementoCarrello</code> da aggiornare
     * @see ElementoCarrello
     */
    public synchronized void aggiorna(Articolo articolo,String idCaratteristica, short quantita) {


        if (quantita >= 0) {

            ElementoCarrello item = null;

            for (ElementoCarrello scItem : elementi) {

                if (scItem.getArticolo().getId() == articolo.getId()
                        && scItem.getIdCaratteristica() == idCaratteristica) {

                    if (quantita != 0) {
                        // quantita = nuovo valore
                        scItem.setQuantita(quantita);
                    } else {
                        // if quantita = 0, salvataggio dell'item ed uscita dal ciclo for
                        item = scItem;
                        break;
                    }
                }
            }

            if (item != null) {
                // cancellazione dal carrello
                elementi.remove(item);
            }
        }
    }

    /**
     * Lista degli <code>ElementoCarrellos</code>.
     *
     * @return la lista di <code>elementi</code>
     * @see ElementoCarrello
     */
    public synchronized List<ElementoCarrello> getElementiCarrello() {

        return elementi;
    }

    /**
     * Somma di tutti gli
     * <code>elementi</code> del carrello.
     *
     * @return il numero degli elementi nel carrello
     * @see ElementoCarrello
     */
    public synchronized int getNumeroDiElementi() {

        numeroDiElementi = 0;

        for (ElementoCarrello scItem : elementi) {

            numeroDiElementi += scItem.getQuantita();
        }

        return numeroDiElementi;
    }

    /**
     * Totale degli articoli nel carrello escluse le spese di spedizione
     *
     * @return il costo di tutti gli elementi per la loro quantità
     * @see ElementoCarrello
     */
    public synchronized double getSubtotale() {

        double somma = 0;

        for (ElementoCarrello scItem : elementi) {

            Articolo articolo = (Articolo) scItem.getArticolo();
            somma += (scItem.getQuantita() * articolo.getPrezzo().doubleValue());
        }

        return somma;
    }

    /**
     * Calcola il totale del costo dell'ordine. Questo metodo aggiunge al subtotale le
     * speseSpedizione e assegna il <code>total</code> come risultato dell'operazione.    
     *
     * @param speseSpedizione le spese di spedizione
     * @see ElementoCarrello
     */
    public synchronized void calcolaTotale(double speseSpedizione) {

        double amount = 0;

        amount = this.getSubtotale();
        amount += speseSpedizione;

        totale = amount;
    }

    /**
     * Restituisce il costo totale dell'istanza 
     * <code>carrello</code>.
     *
     * @return il costo di tutti gli elementi per la loro quantità più le speseSpedizione
     */
    public synchronized double getTotale() {

        return totale;
    }

    /**
     * Svuota il carrello. Tutti gli elementi sono rimossi dalla lista di
     * <code>elementi</code> , <code>numeroDiElementi</code> e
     * <code>total</code> vengono assegnati a '<code>0</code>'.
     *
     * @see ElementoCarrello
     */
    public synchronized void svuota() {
        elementi.clear();
        numeroDiElementi = 0;
        totale = 0;
    }
}
