/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.GregorianCalendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe unit test di un utente
 * @author Davide
 */
public class UtenteTest {
    
    public UtenteTest() {
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
     * Test of getIdUtente method, of class Utente.
     */
    @Test
    public void testGetIdUtente() {
        System.out.println("getIdUtente");
        Utente instance = new Utente();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUtente method, of class Utente.
     */
    @Test
    public void testSetIdUtente() {
        System.out.println("setIdUtente");
        Integer idUtente = null;
        Utente instance = new Utente();
        instance.setId(idUtente);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Utente.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Utente instance = new Utente();
        instance.setEmail("antonio@yahoo.it");
        String expResult = "antonio@yahoo.it";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Utente.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Utente instance = new Utente();
        instance.setEmail(email);
        assertEquals(email,instance.getEmail());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Utente.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Utente instance = new Utente();
        instance.setNome("antonio");
        String expResult = "antonio";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Utente.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Utente instance = new Utente();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCognome method, of class Utente.
     */
    @Test
    public void testGetCognome() {
        System.out.println("getCognome");
        Utente instance = new Utente();
        instance.setCognome("rossi");
        String expResult = "rossi";
        String result = instance.getCognome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCognome method, of class Utente.
     */
    @Test
    public void testSetCognome() {
        System.out.println("setCognome");
        String cognome = "";
        Utente instance = new Utente();
        instance.setCognome(cognome);
        assertEquals(cognome,instance.getCognome());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Utente.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Utente instance = new Utente();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Utente.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Utente instance = new Utente();
        instance.setPassword(password);
        assertEquals(password,instance.getPassword());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDataRegistrazione method, of class Utente.
     */
    @Test
    public void testGetDataRegistrazione() {
        System.out.println("getDataRegistrazione");
        Utente instance = new Utente();
        Date expResult = null;
        Date result = instance.getDataRegistrazione();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDataRegistrazione method, of class Utente.
     */
    @Test
    public void testSetDataRegistrazione() {
        System.out.println("setDataRegistrazione");
        Date dataRegistrazione = GregorianCalendar.getInstance().getTime();
        Utente instance = new Utente();
        instance.setDataRegistrazione(dataRegistrazione);
        assertEquals(dataRegistrazione,instance.getDataRegistrazione());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getIsInterno method, of class Utente.
     */
    @Test
    public void testGetIsInterno() {
        System.out.println("getIsInterno");
        Utente instance = new Utente();
        Boolean expResult = false;
        Boolean result = instance.getIsInterno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setIsInterno method, of class Utente.
     */
    @Test
    public void testSetIsInterno() {
        System.out.println("setIsInterno");
        Boolean isInterno = true;
        Utente instance = new Utente();
        instance.setIsInterno(isInterno);
        assertEquals(isInterno,instance.getIsInterno());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Utente.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Utente instance = new Utente();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Utente.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Utente instance = new Utente();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }    
}
