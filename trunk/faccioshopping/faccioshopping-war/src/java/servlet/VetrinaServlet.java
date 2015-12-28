/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import gestori.GestoreArticoloLocal;
import gestori.GestoreCatalogoLocal;
import gestori.GestoreCategoriaLocal;
import entity.Articolo;
import entity.Catalogo;
import entity.Categoria;
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

/**
 * Servlet per la gestione di un catalogo
 * @author Davide
 */
@WebServlet(name = "VetrinaServlet", 
urlPatterns = {
    "/home",
    "/getCatalogo",
    "/getCategoria",
    "/getArticolo"})
public class VetrinaServlet extends HttpServlet {

    
    @EJB
    private GestoreCatalogoLocal gestoreCatalogo;
    @EJB
    private GestoreCategoriaLocal gestoreCategoria;
    @EJB
    private GestoreArticoloLocal gestoreArticolo;
    
    
    @Override
    public void init() throws ServletException {

        // store category list in servlet context
            List<Catalogo> cataloghi = gestoreCatalogo.selezionaListaCataloghi();
	    getServletContext().setAttribute("cataloghi",cataloghi);
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathUtente = request.getServletPath();
        HttpSession session = request.getSession();
                
        Catalogo catalogoSelezionato;
        Categoria categoriaSelezionata;
        Articolo articoloSelezionato;
        
        if (pathUtente.equals("/home")) {	    
            pathUtente = "/index";
        }
        else if (pathUtente.equals("/getCatalogo")) {        
            int idCatalogo = Integer.parseInt(request.getParameter("idCatalogo"));                                   
            if (idCatalogo != -1) {

                catalogoSelezionato = gestoreCatalogo.selezionaById(idCatalogo);

		session.setAttribute("idCatalogo", idCatalogo);

		session.setAttribute("idCategoria", catalogoSelezionato.getCategoriaList().get(0).getId());
                
                request.setAttribute("catalogoSelezionato", catalogoSelezionato);

                request.setAttribute("categoriaSelezionata", catalogoSelezionato.getCategoriaList().get(0));
            }
            pathUtente = "/view/categoria";            
        }
        else if (pathUtente.equals("/getCategoria")) {
            int idCatalogo = Integer.parseInt(request.getParameter("idCatalogo"));
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            
            if (idCategoria != -1) {
                catalogoSelezionato = gestoreCatalogo.selezionaById(idCatalogo);                
                request.setAttribute("catalogoSelezionato", catalogoSelezionato);
		session.setAttribute("idCatalogo",idCatalogo);
                
                categoriaSelezionata = gestoreCategoria.selezionaById(idCategoria);                
                request.setAttribute("categoriaSelezionata", categoriaSelezionata);
		session.setAttribute("idCategoria",idCategoria);
            }
            pathUtente = "/view/categoria";

        } 
        else if (pathUtente.equals ("/getArticolo")) {

            int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));

            if (idArticolo != -1) {

                int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                int idCatalogo = Integer.parseInt(request.getParameter("idCatalogo"));

                
                articoloSelezionato = gestoreArticolo.selezionaById(idArticolo);
                request.setAttribute("articoloSelezionato", articoloSelezionato);
                session.setAttribute("idArticolo",idArticolo); 
                              
                catalogoSelezionato = gestoreCatalogo.selezionaById(idCatalogo);
                request.setAttribute("catalogoSelezionato", catalogoSelezionato);
                session.setAttribute("idCatalogo",idCatalogo);       

                categoriaSelezionata = gestoreCategoria.selezionaById(idCategoria); 
                request.setAttribute("categoriaSelezionata", categoriaSelezionata);
                session.setAttribute("idCategoria", idCategoria);
            }
        pathUtente = "/view/articolo";

        }
        
        // use RequestDispatcher to forward request internally
        String url = pathUtente + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VetrinaServlet.class.getName()).log(Level.WARNING, null, ex);
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