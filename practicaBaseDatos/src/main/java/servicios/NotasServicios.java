/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import model.Nota;

/**
 *
 * @author Dani
 */
public class NotasServicios {

    public Nota getNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.selectNota(notas);
    }

    public Nota addNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.insertNota(notas);
    }

    public boolean updNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.updateNota(notas);
    }

    public boolean delNotas(Nota notas) {
        NotasDAO dao = new NotasDAO();

        return dao.deleteNota(notas);
    }
}
