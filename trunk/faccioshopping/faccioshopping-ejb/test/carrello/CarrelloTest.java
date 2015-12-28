/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrello;

import java.util.ArrayList;
import entity.Articolo;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe unit test del carrello
 * @author Davide
 */
public class CarrelloTest {
    
    public CarrelloTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addArticolo method, of class Carrello.
     */
    @Test
    public void testAddArticolo() {
        System.out.println("addArticolo");
        Articolo articolo = null;
        String caratteristica = "u";
        Carrello instance = new Carrello();
        instance.addArticolo(articolo,caratteristica);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delArticolo method, of class Carrello.
     */
    @Test
    public void testDelArticolo() {
        System.out.println("delArticolo");
        Articolo articolo = null;
        String caratteristica = "u";
        Carrello instance = new Carrello();
        instance.delArticolo(articolo,caratteristica);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of aggiorna method, of class Carrello.
     */
    @Test
    public void testAggiorna() {
        System.out.println("aggiorna");
        
        double expResult = 250;
        
        Carrello instance = new Carrello();
        
        Articolo articolo = new Articolo();
        String caratteristica = "u";
        articolo.setPrezzo(new BigDecimal(50));
        
        instance.addArticolo(articolo,caratteristica);        
        
        short quantita = 5;
        
        instance.aggiorna(articolo,caratteristica, quantita);                
        double subTotal = instance.getSubtotale();
        assertEquals(expResult,subTotal,0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getElementiCarrello method, of class Carrello.
     */
    @Test
    public void testGetElementiCarrello() {
        System.out.println("getElementiCarrello");
        Carrello instance = new Carrello();
        List<ElementoCarrello> expResult = new ArrayList<ElementoCarrello>();
        List<ElementoCarrello> result = instance.getElementiCarrello();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroDiElementi method, of class Carrello.
     */
    @Test
    public void testGetNumeroDiElementi() {
        System.out.println("getNumeroDiElementi");
        Carrello instance = new Carrello();
        int expResult = 0;
        int result = instance.getNumeroDiElementi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotale method, of class Carrello.
     */
    @Test
    public void testGetSubtotale() {
        System.out.println("getSubtotale");
        Carrello instance = new Carrello();
        double expResult = 0.0;
        double result = instance.getSubtotale();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calcolaTotale method, of class Carrello.
     */
    @Test
    public void testCalcolaTotale() {
        System.out.println("calcolaTotale");
        double expResult = 10;
        double speseSpedizione = 5;
        Carrello instance = new Carrello();
        
        Articolo articolo= new Articolo();
        BigDecimal prezzo = new BigDecimal(5);
        String caratteristica = "u";
        articolo.setPrezzo(prezzo);
        instance.addArticolo(articolo,caratteristica);
        
        instance.calcolaTotale(speseSpedizione);
        assertEquals(expResult,instance.getTotale(),0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTotale method, of class Carrello.
     */
    @Test
    public void testGetTotale() {
        System.out.println("getTotale");
        Carrello instance = new Carrello();
        double expResult = 0.0;
        double result = instance.getTotale();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of svuota method, of class Carrello.
     */
    @Test
    public void testSvuota() {
        System.out.println("svuota");
        Carrello instance = new Carrello();
        instance.svuota();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
