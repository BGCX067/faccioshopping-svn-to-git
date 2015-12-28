package servlet;

import gestori.GestoreUtenteLocal;
import entity.Utente;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;




/**
 * Questa servlet viene utilizzata per la gestione utente
 * 
 * @author Michael Liao (askxuefeng@gmail.com)
 */
@WebServlet(name = "UtenteServlet", urlPatterns = {"/openid",
    "/login",
    "/logout",
    "/registra",
    "/modifica"})
public class UtenteServlet extends HttpServlet {

@EJB
private GestoreUtenteLocal gestoreUtente;

static final long ONE_HOUR = 3600000L;
    static final long TWO_HOUR = ONE_HOUR * 2L;
    static final String ATTR_MAC = "openid_mac";
    static final String ATTR_ALIAS = "openid_alias";

    private OpenIdManager manager;

    @Override
    public void init() throws ServletException {
        super.init();
        manager = new OpenIdManager();        
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pathUtente = request.getServletPath();
        /**
         * Gestione login OpenID
         */
        if (pathUtente.equals("/openid")) {
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/faccioshopping-war";
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/faccioshopping-war/openid";
            String redirectUrl = "";
            manager.setRealm(baseUrl);
            manager.setReturnTo(returnUrl);

            String op = request.getParameter("op");        
            String oresponse = request.getParameter("openid.mode");

            if (op==null && !oresponse.equals("cancel")) {
                // check sign on result from Google or Yahoo:
                checkNonce(request.getParameter("openid.response_nonce"));
                // get authentication:
                byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
                String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
                Authentication authentication = manager.getAuthentication(request, mac_key, alias);                                  
                redirectUrl = checkAuthentication(authentication,baseUrl,request);              
            }
            else if (op!=null && (op.equals("Google") || op.equals("Yahoo"))) {
                // redirect to Google or Yahoo sign on page:
                Endpoint endpoint = manager.lookupEndpoint(op);
                Association association = manager.lookupAssociation(endpoint);
                request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
                request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
                redirectUrl = manager.getAuthenticationUrl(endpoint, association);            
            }
            else if (oresponse.equals("cancel")) {
                request.setAttribute("err", "Processo di autenticazione annullato.");
                request.getRequestDispatcher("/home").forward(request, response);
                return;
            }
            else {throw new ServletException("Unsupported OP: " + op);}
            try {response.sendRedirect(redirectUrl);}
            catch(Exception ex) {Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);}
        }
        else
        {
            /**
             * Gestione login dal sito
             */
            if (pathUtente.equals("/login"))
            {
                    Utente _utente = gestoreUtente.login(request.getParameter("email"),request.getParameter("password"));
                    String action= (request.getParameter("action")!= null) ? request.getParameter("action") : ""; 
                    pathUtente= "/view/login";
                    if (action.equals("entra"))
                    {
                        if (_utente == null) {        
                            request.setAttribute("err", "Errore nel processo di login.");     
                        }            
                        else {
                            request.getSession().setAttribute("utentefaccioshopping", _utente);  
                            pathUtente = "index";
                        }  
                    }
            }
            else if (pathUtente.equals("/logout")) {
                    HttpSession session = request.getSession();
                    if (session != null) {
                            session.invalidate();
                            pathUtente= "index";
                 }
            }
            else if (pathUtente.equals("/registra")) {                
                pathUtente= "/view/registra";
                String action= (request.getParameter("action")!= null) ? request.getParameter("action") : "";                 
                if (action.equals("inserisci")) {
                    boolean isInterno = (Integer.parseInt(request.getParameter("isInterno")) != 0);
                    Utente _nuovoUtente = gestoreUtente.registrazione(request.getParameter("lastname"),request.getParameter("firstname"),
                            request.getParameter("email"),request.getParameter("password"),isInterno);
                    if (_nuovoUtente != null) {
                        request.setAttribute("ok", "Utente registrato con successo.");
                        request.getSession().setAttribute("utentefaccioshopping", _nuovoUtente);
                        request.getSession().setAttribute("authentication", null);                        
                        pathUtente= "index";            
                    }
                    else {
                        request.setAttribute("err", "Errore durante la registrazione. Email gi&agrave; utilizzato!");
                        pathUtente= "/view/registra";  
                    }
                }
                else if (action.equals("openID")) {	
                    request.setAttribute("ok", "Utente riconosciuto! Controlla i dati e conferma.");	
                    pathUtente= "/view/registra";
	        }
            }
            else if (pathUtente.equals("/modifica")) {
                pathUtente= "/view/modificautente";  
                String action= (request.getParameter("action")!= null) ? request.getParameter("action") : "";     
                if (action.equals("aggiorna"))
                {
                    Utente _utente = (Utente) request.getSession().getAttribute("utentefaccioshopping");
                    Utente _nuovoUtente = gestoreUtente.modifica(_utente.getId(),request.getParameter("lastname"),request.getParameter("firstname"),request.getParameter("email"),
                            request.getParameter("opassword"),request.getParameter("npassword"),_utente.getIsInterno());
                    if (_nuovoUtente != null)
                    {
                        request.getSession().setAttribute("utentefaccioshopping",_nuovoUtente);
                        request.setAttribute("ok", "Utente modificato con successo.");
                    }
                    else
                    {
                        request.setAttribute("err", "Errore durante la modifica dell'utente."); 
                    }
                    pathUtente= "index";
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
        processRequest(request,response);        
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
    
    // <editor-fold defaultstate="collapsed" desc="Login OpenID methods. Click on the + sign on the left to edit the code.">
    String checkAuthentication(Authentication auth,String baseUrl,HttpServletRequest request) {
        
        String valueUrl = "";
        Utente _utente = gestoreUtente.loginOpenID(auth.getEmail());    
        
        if (_utente == null) 
        {
           valueUrl = baseUrl + "/registra?action=openID"; 
           request.getSession().setAttribute("authentication", auth);
        }
        else
        {
            valueUrl = baseUrl + "/home";
            request.getSession().setAttribute("utentefaccioshopping", _utente);
        }
        
        return valueUrl; 
    }

    void checkNonce(String nonce) {
        // check response_nonce to prevent replay-attack:
        if (nonce==null || nonce.length()<20)
            throw new OpenIdException("Verify failed.");
        // make sure the time of server is correct:
        long nonceTime = getNonceTime(nonce);
        long diff = Math.abs(System.currentTimeMillis() - nonceTime);
        if (diff > ONE_HOUR)
            throw new OpenIdException("Bad nonce time.");
        if (isNonceExist(nonce))
            throw new OpenIdException("Verify nonce failed.");
        storeNonce(nonce, nonceTime + TWO_HOUR);
    }

    // simulate a database that store all nonce:
    private Set<String> nonceDb = new HashSet<String>();

    // check if nonce is exist in database:
    boolean isNonceExist(String nonce) {
        return nonceDb.contains(nonce);
    }

    // store nonce in database:
    void storeNonce(String nonce, long expires) {
        nonceDb.add(nonce);
    }

    long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .parse(nonce.substring(0, 19) + "+0000")
                    .getTime();
        }
        catch(ParseException e) {
            throw new OpenIdException("Bad nonce time.");
        }
    }
    // </editor-fold>
}