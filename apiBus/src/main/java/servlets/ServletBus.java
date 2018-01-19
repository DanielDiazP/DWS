/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dani
 */
@WebServlet(name = "ServletBus", urlPatterns = {"/servletBus"})
public class ServletBus extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException, ExecutionException {

        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        final JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
                = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                        request.setConnectTimeout(100000);
                    }
                });
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime ahora = LocalDateTime.now();
        String formatDateTime = ahora.format(formatter);
        
        GenericData data = new GenericData();
        
        GenericUrl genUrl;
        HttpRequest requestGoogle;
        GenericJson json;

        String paginaSalida = "inicio.html";

        data.put("idClient", "WEB.SERV.dany.8.2.91@gmail.com");
        data.put("passKey", "A56CBDF0-04A3-4DF7-9C3B-E1A1C3C410C0");
        data.put("SelectDate",formatDateTime);

        if (request.getParameter("opcion") != null) {

            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "getListLines":
                    genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetListLines.php");
                    
                    data.put("Lines", "");//put post / set get

                    requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                    json = requestGoogle.executeAsync().get().parseAs(GenericJson.class);

                    ArrayList lineas = (ArrayList) json.get("resultValues");
                    request.setAttribute("lineasBus", lineas);
                    paginaSalida = "inicio.jsp";
                    break;

                case "getInfoLine":
                    if (request.getParameter("numeroLinea") != null) {
                        genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetInfoLine.php");

                        data.put("line", request.getParameter("numeroLinea"));
                        data.put("cultureInfo", "ES");

                        requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                        json = requestGoogle.executeAsync().get().parseAs(GenericJson.class);
                        GenericJson js =(GenericJson) json.get("Line");
                        js.remove("dayType");
                        ArrayList infoLinea = (ArrayList) json.get("Line");
                        request.setAttribute("infoLineasBus", infoLinea);
                        paginaSalida = "infoLineas.jsp";
                    }
                    break;

                case "getRouteLines":
                    if (request.getParameter("numeroLinea") != null) {
                        genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetRouteLines.php");

                        data.put("Lines", request.getParameter("numeroLinea"));

                        requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                        json = requestGoogle.executeAsync().get().parseAs(GenericJson.class);

                        ArrayList rutaLinea = (ArrayList) json.get("resultValues");
                        request.setAttribute("infoRutaLinea", rutaLinea);
                        paginaSalida = "infoRutas.slt";
                    }
                    break;
            }

        }
        request.getRequestDispatcher(paginaSalida).forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServletBus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ServletBus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServletBus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ServletBus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
