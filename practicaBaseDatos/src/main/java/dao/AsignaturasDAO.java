/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Asignatura;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 *
 * @author daw
 */
public class AsignaturasDAO {
    
    public List<Asignatura>selectAllAsignaturas(){
        List<Asignatura> lista=null;
        DBConnection db=new DBConnection();
        Connection con =null;
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
    
    public Asignatura insertAsignatura(Asignatura asignatura){
        DBConnection db=new DBConnection();
        Connection con =null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Asignatura> handler
              = new BeanHandler<Asignatura>(Asignatura.class);
            asignatura = qr.query(con, "select * FROM ASIGNATURAS", handler);

        } catch (Exception ex) {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        
        return asignatura;
    }
    public boolean updateAsignatura(Asignatura asignatura){
        boolean error=false;
        return error;
    }
    public boolean deleteAsignatura(Asignatura asignatura){
         boolean error=false;
        return error;
    }
}
