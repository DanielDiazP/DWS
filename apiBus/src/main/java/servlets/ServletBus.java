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
import java.io.PrintWriter;
import java.util.ArrayList;
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
            throws ServletException, IOException {

        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        final JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
                = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });

        GenericData data = new GenericData();

        GenericUrl genUrl;
        HttpRequest requestGoogle;
        GenericJson json;

        String paginaSalida = "inicio.html";

        data.put("idClient", "WEB.SERV.dany.8.2.91@gmail.com");
        data.put("passKey", "A56CBDF0-04A3-4DF7-9C3B-E1A1C3C410C0");

        if (request.getParameter("opcion") != null) {

            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "getListLines":
                    genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetListLines.php");

                    data.put("Lines", "");

                    requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                    json = requestGoogle.execute().parseAs(GenericJson.class);

                    ArrayList lineas = (ArrayList) json.get("resultValues");
                    request.setAttribute("lineasBus", lineas);
                    paginaSalida = "listaLineas.slt";
                    break;

                case "getInfoLine":
                    if (request.getParameter("numeroLinea") != null) {
                        genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetInfoLine.php");

                        data.put("line", request.getParameter("numeroLinea"));

                        requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                        json = requestGoogle.execute().parseAs(GenericJson.class);

                        ArrayList infoLinea = (ArrayList) json.get("Line");
                        request.setAttribute("infoLineasBus", infoLinea);
                        paginaSalida = "infoLineas.slt";
                    }
                    break;

                case "getRouteLines":
                    if (request.getParameter("numeroLinea") != null) {
                        genUrl = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetRouteLines.php");

                        data.put("Lines", request.getParameter("numeroLinea"));

                        requestGoogle = requestFactory.buildPostRequest(genUrl, new UrlEncodedContent(data));
                        json = requestGoogle.execute().parseAs(GenericJson.class);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
