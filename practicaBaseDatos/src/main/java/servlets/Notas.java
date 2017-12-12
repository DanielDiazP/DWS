/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nota;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;
import servicios.NotasServicios;

/**
 *
 * @author Dani
 */
@WebServlet(name = "Notas", urlPatterns = {"/secure/notas"})
public class Notas extends HttpServlet {

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

        NotasServicios ns = new NotasServicios();
        AlumnosServicios als = new AlumnosServicios();
        AsignaturasServicios ass = new AsignaturasServicios();
        Nota n = new Nota();
        String nombreAlumno = request.getParameter("nombreAlumno");
        String idAlumno = request.getParameter("idAlumno");
        String nombreAsignatura = request.getParameter("nombreAsignatura");
        String idAsignatura = request.getParameter("idAsignatura");
        String nota = request.getParameter("nota");
        if (request.getParameter("opcion") != null) {
            String opcion = request.getParameter("opcion");

            boolean error;

            switch (opcion) {
                case "select":
                    n.setIdAlumno(Long.parseLong(idAlumno));
                    n.setIdAsignatura(Long.parseLong(idAsignatura));
                    n = ns.getNotas(n);
                    request.setAttribute("nota", n);
                    break;
                case "insert":
                    n.setIdAlumno(Long.parseLong(idAlumno));
                    n.setIdAsignatura(Long.parseLong(idAsignatura));
                    n.setNota(Integer.parseInt(nota));
                    ns.addNotas(n);
                    break;
                case "delete":
                    n.setIdAlumno(Long.parseLong(idAlumno));
                    n.setIdAsignatura(Long.parseLong(idAsignatura));
                    error = ns.delNotas(n);
                    request.setAttribute("hecho", error);
                    break;
                case "update":
                    n.setIdAlumno(Long.parseLong(idAlumno));
                    n.setIdAsignatura(Long.parseLong(idAsignatura));
                    n.setNota(Integer.parseInt(nota));
                    error = ns.updNotas(n);
                    request.setAttribute("hecho", error);
                    break;
            }

        }
        request.setAttribute("nombreAlumno", nombreAlumno);
        request.setAttribute("idAlumno", idAlumno);
        request.setAttribute("nombreAsignatura", nombreAsignatura);
        request.setAttribute("idAsignatura", idAsignatura);
        request.setAttribute("alumnos", als.getAllAlumnos());
        request.setAttribute("asignaturas", ass.getAllAsignaturas());
        request.getRequestDispatcher("pintarListaNotas.jsp").forward(request, response);
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
