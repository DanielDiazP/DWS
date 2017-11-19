/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Nota;

import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author Dani
 */
public class NotasDAO {

    public Nota selectNota(Nota notas) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Nota> handler = new BeanHandler<Nota>(Nota.class);
            notas = qr.query(con, "select * from NOTAS where ID_ALUMNO=? AND ID_ASIGNATURA=?", handler, notas.getIdAlumno(), notas.getIdAsignatura());

        } catch (Exception ex) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return notas;
    }

    public Nota insertNota(Nota notas) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.insert(con, "INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)",
                    new ScalarHandler<>(), notas.getIdAlumno(), notas.getIdAsignatura(), notas.getNota());

        } catch (Exception e) {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.cerrarConexion(con);
        }
        return notas;
    }

    public boolean updateNota(Nota notas) {
        boolean error = true;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.update(con, "update NOTAS set NOTA=? where ID_ALUMNO=? AND ID_ASIGNATURA=?", notas.getNota(), notas.getIdAlumno(), notas.getIdAsignatura());
        } catch (Exception e) {
            error = false;
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.cerrarConexion(con);
        }
        return error;
    }

    public boolean deleteNota(Nota notas) {
        boolean error = false;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.update(con, "delete from NOTAS where ID_ALUMNO = ? AND ID_ASIGNATURA = ?", notas.getIdAlumno(), notas.getIdAsignatura());

        } catch (Exception ex) {
            error = true;
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return error;
    }

}
