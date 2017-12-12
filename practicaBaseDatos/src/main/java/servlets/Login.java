/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import servicios.MandarEmail;
import servicios.PasswordServicios;
import servicios.UsuariosServicios;
import utils.Utils;

/**
 *
 * @author Dani
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.spec.InvalidKeySpecException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InvalidKeySpecException {
        String pagina = "loggin.jsp";
        UsuariosServicios us = new UsuariosServicios();
        PasswordServicios ps = new PasswordServicios();
        Usuario usuario = new Usuario();
        LocalDateTime ahora = LocalDateTime.now();
        String usuarioNombre;
        String usuarioPass;

        if (request.getParameter("opcion") != null) {
            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "registrar":
                    usuarioNombre = request.getParameter("nombre");
                    usuarioPass = request.getParameter("password");
                    String usuarioEmail = request.getParameter("email");
                    if (us.comprobacionNombre(usuarioNombre)) {
                        usuario.setNombre(usuarioNombre);
                        usuario.setEmail(usuarioEmail);
                        usuario.setPassword(ps.crearHash(usuarioPass));
                        usuario.setActivado(Boolean.FALSE);
                        usuario.setFechaActivacion(ahora);
                        usuario.setCodigoActivacion(Utils.randomAlphaNumeric(10));
                        usuario = us.insertarUser(usuario);
                        MandarEmail email = new MandarEmail();
                        email.mandarEmail(usuarioEmail, "http://localhost:8080/practicaBaseDatos/login?opcion=activar&codigoActivacion="
                                + usuario.getCodigoActivacion() + "&hora=" + usuario.getFechaActivacion(), "Email de activacion");

                        request.setAttribute("mensaje", "Codigo de activacion enviado, revise su correo");
                    } else {
                        request.setAttribute("mensaje", "Ese nombre ya esta siendo usado");
                    }
                    break;

                case "activar":
                    String codigoActivacion = request.getParameter("codigoActivacion");
                    String horaDeRegistro = request.getParameter("hora");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDateTime dateTime = LocalDateTime.parse(horaDeRegistro, formatter);

                    if (us.comprobacionCodigo(codigoActivacion) && dateTime.plusMinutes(30).isAfter(ahora)) {
                        us.activar(codigoActivacion);
                    } else {
                        request.setAttribute("mensaje", "Codigo de activacion no valido");
                    }
                    break;

                case "login":
                    usuarioNombre = request.getParameter("nombre");
                    usuarioPass = request.getParameter("password");
                    if (us.comprobacionPass(usuarioNombre, usuarioPass)) {
                        request.getSession().setAttribute("logeado", "si");
                        request.setAttribute("mensaje", "Bienvenid@");
                    } else {
                        request.setAttribute("mensaje", "Nombre de usuario y/o contraseña incorrectos, o cuenta no validada");
                    }
                    break;
                case "unLogin":
                    if (request.getSession() != null) {
                        request.getSession().invalidate();
                        request.setAttribute("mensaje", "Hasta la proxima");
                    } else {
                        request.setAttribute("mensaje", "Opcion no valida");
                    }

                    break;
            }

        }
        request.getRequestDispatcher(pagina).forward(request, response);
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
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
