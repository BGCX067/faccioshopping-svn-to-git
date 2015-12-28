/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import carrello.Carrello;
import carrello.ElementoCarrello;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.checkout.sdk.commands.ApiContext;
import com.google.checkout.sdk.commands.CartPoster.CheckoutShoppingCartBuilder;
import com.google.checkout.sdk.commands.Environment;
import com.google.checkout.sdk.domain.CheckoutRedirect;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

/**
 * Servlet che gestisce il checkout di un ordine
 * @author Davide
 */
@WebServlet(name = "OrdineServlet", urlPatterns = {"/googleCheckout","/checkout","/acquisto"})
public class OrdineServlet extends HttpServlet {

    String idNegoziante = "",keyNegoziante = "",codValuta = "";
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        // initialize servlet with configuration information
        idNegoziante = servletConfig.getServletContext().getInitParameter("idNegoziante");
        keyNegoziante = servletConfig.getServletContext().getInitParameter("keyNegoziante");
        codValuta = servletConfig.getServletContext().getInitParameter("codValuta");
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
        response.setContentType("text/html;charset=UTF-8");
        
        if (pathUtente.equals("/googleCheckout"))
        {
            PrintWriter out = response.getWriter();
            String url = "";
            HttpSession session = request.getSession();
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            try {
                if (carrello!=null)
                {
                    ApiContext clientproxy = new ApiContext(Environment.SANDBOX, idNegoziante, keyNegoziante, codValuta);
                    CheckoutShoppingCartBuilder builder = clientproxy.cartPoster().makeCart();

                    for (ElementoCarrello elemento : carrello.getElementiCarrello())
                    {                                       
                        builder.addItem(elemento.getArticolo().getNome(), 
                                elemento.getArticolo().getDescrizione(), 
                                elemento.getArticolo().getPrezzo(), 
                                elemento.getQuantita()); 
                    }

                    CheckoutRedirect responseCheckout= builder.buildAndPost();                      
                    url=responseCheckout.getRedirectUrl();


                }            
                /* TODO output your page here
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet GoogleCheckoutServlet</title>");  
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet GoogleCheckoutServlet at " + request.getContextPath () + "</h1>");
                out.println("</body>");
                out.println("</html>");
                 */
            } catch (Exception ex) {
                    url="/index.jsp";
                    out.println("Errore durante il checkout su google." + ex.getMessage());
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
            } finally {            
                /*out.close();*/
                try {
                response.sendRedirect(url);
                return;
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
                }
            }
        }
        else
        {
            if (pathUtente.equals("/acquisto"))
            {
                request.getSession().setAttribute("carrello", null);                
                /**
                 * TODO gestione di evasione dell'ordine
                 */
                request.setAttribute("ok", "Ordine evaso con successo.Grazie!");
                pathUtente = "/index";
            }
            else if (pathUtente.equals("/checkout")) {
                pathUtente = "/view/checkout";
            }

            String url = pathUtente + ".jsp";

            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(OrdineServlet.class.getName()).log(Level.WARNING, null, ex);
            }
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
