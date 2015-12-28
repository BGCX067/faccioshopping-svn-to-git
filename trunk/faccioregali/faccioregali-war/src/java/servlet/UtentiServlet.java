/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import gestori.GestoreAmicoLocal;
import entity.Regalo;
import entity.Amico;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AmicoFacadeLocal;

/**
 * Servlet per la gestione delle ricerca utenti
 * @author Davide
 */
@WebServlet(name = "UtentiServlet", urlPatterns = {"/home","/getUtenti","/getUtente"})
public class UtentiServlet extends HttpServlet {
    @EJB
    private GestoreAmicoLocal gestoreUtente;
    @EJB
    private AmicoFacadeLocal amicoFacade;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * pathUtente è una stringa che contiene qual'è stato l'url richiesto alla servlet, 
         * sulla base del suo valore la servlet esegue le operazioni necessarie e redirige sulla vista che
         * si occupa del rendering della pagina
         */
        String pathUtente = request.getServletPath();
        HttpSession session = request.getSession();
        List<Amico> utenti;
        List<Regalo> regali;
        Amico amico;
       
        if (pathUtente.equals("/home") || pathUtente.equals("/")) {
            pathUtente = "/index";
        }
        else if (pathUtente.equals("/getUtente"))
        {
            String sidUtente = request.getParameter("id");
            if (!sidUtente.equals(""))
            {
                int idUtente = Integer.parseInt(request.getParameter("id"));
                amico = amicoFacade.find(idUtente);
                regali = gestoreUtente.selezionaByIdAmico(idUtente);
                session.setAttribute("amico", amico);
                session.setAttribute("regali", regali);
            }
            pathUtente = "/view/amico";
        }
        
        if (pathUtente.equals("/getUtenti"))
        {
            String keyword = request.getParameter("pRicerca");
            Amico utenteLoggato = (Amico) session.getAttribute("utente");
            if (utenteLoggato != null)
                utenti = gestoreUtente.selezionaByKeywordAltri(keyword, utenteLoggato.getId());
            else
                utenti = gestoreUtente.selezionaByKeyword(keyword);
            request.setAttribute("amici", utenti);
            pathUtente = "/index";
        }
        // use RequestDispatcher to forward request internally
        String url =  pathUtente + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
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
}
