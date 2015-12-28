/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
import gestori.GestoreAmicoLocal;

/**
 * Servlet che gestisce un regalo
 * @author Davide
 */
@WebServlet(name = "RegaloServlet", urlPatterns = {"/regalo"})
public class RegaloServlet extends HttpServlet {
    @EJB
    private GestoreAmicoLocal gestoreAmico;
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
       String pathUtente = "/view" + request.getServletPath();
       String action = request.getParameter("action");
       
       Amico _utente = (Amico) request.getSession().getAttribute("utentefaccioregali");
       
       if (action.equals("getRegali"))
        {
            List<Regalo> _regali = gestoreAmico.selezionaByIdAmico(_utente.getId());            
            request.getSession().setAttribute("regali", _regali);            
            pathUtente = "/view/regalo";
        }
       
       if (action.equals("addRegalo"))
        {
            String _nome = request.getParameter("nomeregalo");
            String _descrizione = request.getParameter("descrizione");
            String _url = request.getParameter("url");
            
            
            if (gestoreAmico.AggiungiRegalo(_nome,_descrizione,_url,_utente.getId())) 
            {
                request.setAttribute("ok", "L'articolo è stato aggiunto alla tua lista.");
                List<Regalo> _regali = gestoreAmico.selezionaByIdAmico(_utente.getId());            
                request.setAttribute("regali", _regali); 
            }                        
            else request.setAttribute("err", "Errore durante l'aggiornamento della tua lista.");
             pathUtente = "/view/regalo";
        }
       
       // use RequestDispatcher to forward request internally
        String url = pathUtente + ".jsp";
       
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
