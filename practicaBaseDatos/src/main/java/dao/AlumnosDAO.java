/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.invoke.MethodHandles;
import model.Alumno;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author daw
 */
public class AlumnosDAO {

    public List<Alumno> selectAllAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Alumno nuevo = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "SELECT * FROM ALUMNOS";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fn = rs.getDate("fecha_nacimiento");
                Boolean mayor = rs.getBoolean("mayor_edad");
                nuevo = new Alumno();
                nuevo.setFecha_nacimiento(fn);
                nuevo.setId(id);
                nuevo.setMayor_edad(mayor);
                nuevo.setNombre(nombre);
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;

    }

    public Alumno insertAlumno(Alumno alumno) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, alumno.getNombre());
            stmt.setDate(2, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, alumno.getMayor_edad());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);

        }
        return alumno;
    }

    public boolean updateAlumno(Alumno alumno) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        boolean error=true;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("UPDATE ALUMNOS set NOMBRE=?,FECHA_NACIMIENTO=?,MAYOR_EDAD=? WHERE ID=?");

            stmt.setString(1, alumno.getNombre());
            stmt.setDate(2, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, alumno.getMayor_edad());
            stmt.setLong(4, alumno.getId());
            stmt.executeUpdate();
            error=false;

        } catch (Exception e) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, e);
            }

            db.cerrarConexion(con);
        }
        return error;
    }

    public boolean deleteAlumno(Alumno alumno) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        boolean error=true;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("DELETE FROM ALUMNOS WHERE ID=?");

            stmt.setLong(1, alumno.getId());
            stmt.executeUpdate();
            error=false;

        } catch (Exception e) {
            Logger.getLogger(AlumnosDAO.class
                    .getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(AlumnosDAO.class
                        .getName()).log(Level.SEVERE, null, e);
            }
            db.cerrarConexion(con);
        }
        return error;
    }
}
