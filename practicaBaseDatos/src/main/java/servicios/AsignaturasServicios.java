/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import model.Asignatura;
import dao.AsignaturasDAO;

/**
 *
 * @author daw
 */
public class AsignaturasServicios {

    public List<Asignatura> getAllAsignaturas() {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.selectAllAsignaturas();
    }

    public Asignatura addAsignaturas(Asignatura asignaturaNueva) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.insertAsignatura(asignaturaNueva);
    }

    public boolean updAsignaturas(Asignatura asignatura) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.updateAsignatura(asignatura);
    }

    public boolean delAsignaturas(Asignatura asignatura) {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.deleteAsignatura(asignatura);
    }

    public void total(Asignatura asignatura) {
        AsignaturasDAO dao = new AsignaturasDAO();
        dao.total(asignatura);
    }
}
