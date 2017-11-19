/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Asignatura;

import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author daw
 */
public class AsignaturasDAO {

    public List<Asignatura> selectAllAsignaturas() {
        List<Asignatura> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> handler
                    = new BeanListHandler<Asignatura>(Asignatura.class);
            lista = qr.query(con, "select * FROM ASIGNATURAS", handler);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public Asignatura insertAsignatura(Asignatura asignatura) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            long id = qr.insert(con, "insert into ASIGNATURAS (NOMBRE,CICLO,CURSO) values (?,?,?)", new ScalarHandler<Long>(),
                    asignatura.getNombre(), asignatura.getCiclo(), asignatura.getCurso());
            asignatura.setId(id);
        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }

        return asignatura;
    }

    public boolean updateAsignatura(Asignatura asignatura) {
        boolean error = false;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.update(con, "UPDATE ASIGNATURAS set NOMBRE=?, CICLO=?, CURSO=? WHERE ID=?", asignatura.getNombre(), asignatura.getCiclo(), asignatura.getCurso(), asignatura.getId());

        } catch (Exception ex) {
            error = true;
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return error;
    }

    public boolean deleteAsignatura(Asignatura asignatura) {
        boolean error = false;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.update(con, "DELETE FROM ASIGNATURAS WHERE ID=?", asignatura.getId());

        } catch (Exception ex) {
            error = true;
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return error;
    }
}
