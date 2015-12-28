/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import gestori.GestoreAmicoLocal;
import entity.Amico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che gestisce la procedura di login e di registrazione 
 * di un nuovo utente nel sistema.
 * @author Davide
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        
        String action= (request.getParameter("action")!= null) ? request.getParameter("action") : ""; 
        
        if (action.equals("entra"))
        {
            Amico _utente = gestoreAmico.loginUtente(request.getParameter("email"),request.getParameter("password"));
            
            if (_utente == null) 
            {
                request.setAttribute("err", "Errore nel processo di login.");  
                pathUtente = "/view/login";
            }
            else 
            {
                request.getSession().setAttribute("utentefaccioregali", _utente);
                pathUtente = "/index";
            }
        }
                
        
        if (action.equals("logout"))
        {
            HttpSession session = request.getSession();
            if (session != null) {
                    session.invalidate();
                    pathUtente= "index";
            }
        }                
        
         if (action.equals("registra"))
        {
            pathUtente= "/view/registra";
        }
        
        if (action.equals("registraNuovo"))
        {
            
            Amico _nuovoUtente = gestoreAmico.registraNuovoUtente(request.getParameter("lastname"),request.getParameter("firstname"),
                    request.getParameter("email"),request.getParameter("password"));
            if (_nuovoUtente != null)
            {
                request.setAttribute("ok", "Utente registrato con successo.");
                request.getSession().setAttribute("utentefaccioregali", _nuovoUtente);                
                pathUtente= "index";            
            }
            else
            {
                request.setAttribute("err", "Errore durante la registrazione dell'utente.");
                pathUtente= "/view/registra";  
            }
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
