/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import java.util.List;
import model.Alumno;

/**
 *
 * @author daw
 */
public class AlumnosServicios {

    public List<Alumno> getAllAlumnos() {
        AlumnosDAO dao = new AlumnosDAO();

        return dao.selectAllAlumnos();
    }

    public Alumno addAlumno(Alumno alumnoNuevo) {
        AlumnosDAO dao = new AlumnosDAO();

        return dao.insertAlumno(alumnoNuevo);
    }

    public boolean updAlumno(Alumno alumno) {
        AlumnosDAO dao = new AlumnosDAO();

        return dao.updateAlumno(alumno);
    }

    public boolean delAlumno(Alumno alumno) {
        AlumnosDAO dao = new AlumnosDAO();

        return dao.deleteAlumno(alumno);
    }

    public void total(Alumno alumno) {
        AlumnosDAO dao = new AlumnosDAO();
        dao.total(alumno);
    }

}
