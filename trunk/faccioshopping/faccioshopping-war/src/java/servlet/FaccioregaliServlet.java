/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Articolo;
import entity.Catalogo;
import entity.Categoria;
import gestori.GestoreArticoloLocal;
import gestori.GestoreCatalogoLocal;
import gestori.GestoreCategoriaLocal;
import java.io.IOException;
import java.util.List;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.Amico;
import webservices.Regalo;


/**
 * Servlet che gestisce l'integrazione di faccioshopping con il webservice di faccioregali
 * @author Davide
 */
@WebServlet(name = "FaccioregaliServlet", urlPatterns = {"/getRegali",
    "/cercaUtenti",
    "/getUtenti",
    "/getUtente",
    "/addRegalo"})
public class FaccioregaliServlet extends HttpServlet {


    @EJB
    private GestoreCatalogoLocal gestoreCatalogo;
    @EJB
    private GestoreCategoriaLocal gestoreCategoria;
    @EJB
    private GestoreArticoloLocal gestoreArticolo;
    
    @Resource(mappedName= "jms/FQueue")
    private Queue faccioregaliMessage;
    @Resource(mappedName = "jms/FQueueConnectionFactory")
    private ConnectionFactory faccioregaliMessageFactory;
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathUtente = request.getServletPath();
        HttpSession session = request.getSession();   
        webservices.FaccioregaliWebservice_Service service = new webservices.FaccioregaliWebservice_Service();
	webservices.FaccioregaliWebservice port = service.getFaccioregaliWebservicePort();
        Amico amico;
        List<Amico> utenti;
        List<Regalo> regali;
        
        Catalogo catalogoSelezionato;
        Categoria categoriaSelezionata;
        Articolo articoloSelezionato;
        
        if (pathUtente.equals("/cercaUtenti"))
        {
            pathUtente = "/ricerca";
        }       
                
        else if (pathUtente.equals("/getUtente"))
        {
            String sidUtente = request.getParameter("id");
            if (!sidUtente.equals(""))
            {
                int idUtente = Integer.parseInt(request.getParameter("id"));
                amico = port.ricercaUtenteById(idUtente);
                session.setAttribute("amico", amico);
                regali = port.ricercaListaRegaliByIdAmico(idUtente);
                session.setAttribute("regali", regali);
            }
            pathUtente = "/amico";
        }
        
        else if (pathUtente.equals("/getUtenti"))
        {
            String keyword = request.getParameter("pRicerca");            
            utenti = port.ricercaListaAmiciByKeyword(keyword);
            request.setAttribute("utenti", utenti);
            pathUtente = "/ricerca";        
                
        } else if (pathUtente.equals("/addRegalo")) {
            
            entity.Utente _utente = (entity.Utente) session.getAttribute("utentefaccioshopping");
            Amico _faccioregaliUtente = null;
            
            if (_utente != null) _faccioregaliUtente = port.ricercaUtenteByEmail(_utente.getEmail());
            
            if (_faccioregaliUtente != null)
            {
                
                    String _nomeArticolo = request.getParameter("nomeArticolo");
                    String _idArticolo = request.getParameter("idArticolo");
                    String _idCatalogo = session.getAttribute("idCatalogo").toString();
                    String _idCategoria = session.getAttribute("idCategoria").toString();
                    String _descrizione = request.getParameter("descrizione");
                    String _url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/faccioshopping-war/getArticolo?idArticolo=" + _idArticolo + "&idCategoria=" + _idCategoria + "&idCatalogo=" + _idCatalogo;
                                       
                    
                try {
                    sendfaccioregaliJMSMessage(_faccioregaliUtente.getId(),_url,_descrizione, _nomeArticolo);
                    request.setAttribute("ok", "regalo aggiunto alla tua lista su faccioregali.");
                }                
                catch (JMSException ex) {
                    request.setAttribute("err", "errore faccioregali regalo non aggiunto.");
                    Logger.getLogger(FaccioregaliServlet.class.getName()).log(Level.SEVERE, null, ex);
                }                               
            }   
            else request.setAttribute("err", "non sei registrato su faccioregali.");
            
            int idArticolo = Integer.parseInt(session.getAttribute("idArticolo").toString()); 
            articoloSelezionato = gestoreArticolo.selezionaById(idArticolo);
            request.setAttribute("articoloSelezionato", articoloSelezionato);
            
            int idCatalogo = Integer.parseInt(session.getAttribute("idCatalogo").toString());
            catalogoSelezionato = gestoreCatalogo.selezionaById(idCatalogo);
            request.setAttribute("catalogoSelezionato", catalogoSelezionato);
       

            int idCategoria = Integer.parseInt(session.getAttribute("idCategoria").toString());
            categoriaSelezionata = gestoreCategoria.selezionaById(idCategoria); 
            request.setAttribute("categoriaSelezionata", categoriaSelezionata);
 
            
            pathUtente = "/articolo"; 
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/view" + pathUtente + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FaccioregaliServlet.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

    /**
     * Metodo che invia il messaggio JMS a faccioregali
     * @param messageData
     * @throws JMSException
     */
    private void sendfaccioregaliJMSMessage(int idUtente,String url,String descrizione,String nomeArticolo) throws JMSException {
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            connection = faccioregaliMessageFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(faccioregaliMessage);            
            //creazione messaggio e spedizione alla coda 
            ObjectMessage msg = session.createObjectMessage();
            msg.setIntProperty("idUtente",idUtente);
            msg.setStringProperty("url",url);
            msg.setStringProperty("descrizione",descrizione);
            msg.setStringProperty("nome",nomeArticolo);
            messageProducer.send(msg);
            messageProducer.close();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Non è possibile chiudere la sessione.", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
