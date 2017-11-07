/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import servicios.AlumnosServicios;

/**
 *
 * @author daw
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
public class Alumnos extends HttpServlet {

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

        AlumnosServicios as = new AlumnosServicios();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Alumno a = new Alumno();
        String opcion = request.getParameter("opcion");
        long id;
        String nombre = null;
        String fecha = null;
        boolean edad;
        boolean error;
        Date fch = null;

        switch (opcion) {
            case "select":
                request.setAttribute("alumnos", as.getAllAlumnos());
                break;

            case "insert":
                nombre = request.getParameter("nombre");

                fecha = request.getParameter("fecha");
                edad = Boolean.parseBoolean(request.getParameter("edad"));
                try {
                    fch = df.parse(fecha);
                } catch (ParseException e) {
                }
                a.setNombre(nombre);
                a.setFecha_nacimiento(fch);
                a.setMayor_edad(edad);
                a = as.addAlumno(a);
                List<Alumno> alumnos = new ArrayList();
                alumnos.add(a);
                request.setAttribute("alumnos", alumnos);
                break;

            case "delete":
                id = Long.parseLong(request.getParameter("id"));
                a.setId(id);
                error = as.delAlumno(a);
                request.setAttribute("alumnos", error);
                break;

            case "update":
                id = Long.parseLong(request.getParameter("id"));
                edad = Boolean.parseBoolean(request.getParameter("edad"));
                fecha = request.getParameter("fecha");
                try {
                    fch = df.parse(fecha);
                } catch (ParseException e) {
                }
                a.setId(id);
                a.setNombre(nombre);
                a.setFecha_nacimiento(fch);
                a.setMayor_edad(edad);
                error = as.updAlumno(a);
                request.setAttribute("alumnos", error);
                break;

            default:
                request.setAttribute("alumnos", as.getAllAlumnos());
                break;
        }
        request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);

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
