/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
        boolean error = false;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("UPDATE ALUMNOS set NOMBRE=?,FECHA_NACIMIENTO=?,MAYOR_EDAD=? WHERE ID=?");

            stmt.setString(1, alumno.getNombre());
            stmt.setDate(2, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, alumno.getMayor_edad());
            stmt.setLong(4, alumno.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            error = true;
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
        boolean error = false;
        try {
            con = db.getConnection();
            stmt = con.prepareStatement("DELETE FROM ALUMNOS WHERE ID=?");
            stmt.setLong(1, alumno.getId());
            stmt.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            error = true;

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

    public void total(Alumno alumno) {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = db.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement("DELETE FROM NOTAS WHERE ID_ALUMNO=?");
            stmt.setLong(1, alumno.getId());
            stmt.executeUpdate();

            stmt = con.prepareStatement("DELETE FROM ALUMNOS WHERE ID=?");
            stmt.setLong(1, alumno.getId());
            stmt.executeUpdate();

            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            db.cerrarConexion(con);
        }

    }
}
