/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Articolo;
import carrello.Carrello;
import entity.Catalogo;
import entity.Categoria;
import gestori.GestoreArticoloLocal;
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
 * Servlet per la gestione del carrello della spesa
 * @author Davide
 */
@WebServlet(name = "Carrello", urlPatterns = {"/getCarrello",
    "/addCarrello",
    "/addRegaloAmico",
    "/updateCarrello",
    "/deleteCarrello"})
public class CarrelloServlet extends HttpServlet {

    @EJB
    private GestoreArticoloLocal gestoreArticolo;

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
        Carrello carrello = (Carrello) session.getAttribute("carrello");


        // if addToCart action is called
        if (pathUtente.equals("/addCarrello")) {

            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (carrello == null) {

                carrello = new Carrello();
                session.setAttribute("carrello", carrello);
            }

            // get user input from request
            String idArticolo = request.getParameter("idArticolo");
            
            if (!idArticolo.isEmpty()) {

                Articolo articolo = gestoreArticolo.selezionaById(Integer.parseInt(idArticolo));
                request.setAttribute("articoloSelezionato", articolo);
                Categoria categoriaSelezionata = articolo.getIdCategoria();
                request.setAttribute("categoriaSelezionata", categoriaSelezionata);
                Catalogo catalogoSelezionato= articolo.getIdCategoria().getCatalogo();
                request.setAttribute("catalogoSelezionato", catalogoSelezionato);
                //TODO gestione delle caratteristiche articolo per ora
                //viene gestita solo una singola caratteristica di un articolo 'u'
                carrello.addArticolo(articolo,"u");
            }

            pathUtente = "/view/articolo";
        }
         else if (pathUtente.equals("/addRegaloAmico")) {

            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (carrello == null) {
                carrello = new Carrello();
                session.setAttribute("carrello", carrello);
            }

            // get user input from request
            String nomeArticolo = request.getParameter("nomeArticolo");   
            Articolo articolo = gestoreArticolo.selezionaByNome(nomeArticolo);
            if (articolo != null)
            {          
                request.setAttribute("ok", "Articolo aggiunto correttamente al carrello.");
                carrello.addArticolo(articolo,"u");                
            }
            else request.setAttribute("err", "Articolo non disponibile su faccioshopping.");
            pathUtente = "/view/amico";


            // if updateCart action is called
        } 
        else if (pathUtente.equals("/updateCarrello")) {

            // get input from request
            int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));            
            short quantita = Short.parseShort(request.getParameter("quantita"));

            Articolo articolo = gestoreArticolo.selezionaById(idArticolo);
            carrello.aggiorna(articolo,"u", quantita);

            pathUtente = "/view/carrello";


            // if purchase action is called
        } 
        
        else if (pathUtente.equals("/getCarrello")) {

            String svuota = request.getParameter("svuota");

            if ((svuota != null) && svuota.equals("true")) {
                carrello.svuota();
            }
            pathUtente = "/view/carrello";
        } 
        
        else if (pathUtente.equals ("/deleteCarrello")) {

        // get input from request
        int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));

        Articolo articolo = gestoreArticolo.selezionaById(idArticolo);
        carrello.delArticolo(articolo,"u");


        pathUtente = "/view/carrello";


        // if purchase action is called
        }

        // use RequestDispatcher to forward request internally
        String url = pathUtente + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Non è possibile redirigere verso la risorsa richiesta.", ex);
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
