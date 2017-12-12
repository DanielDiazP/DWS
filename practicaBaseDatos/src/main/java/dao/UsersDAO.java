/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Dani
 */
//----------JDBCTemplate--------
public class UsersDAO {

//    public List<Usuario> getAllUsersJDBCTemplate() {
//
//        JdbcTemplate jtm = new JdbcTemplate(
//                DBConnection.getInstance().getDataSource());
//        List<Usuario> usuario = jtm.query("Select * from USERS",
//                new BeanPropertyRowMapper(Usuario.class));
//
//        return usuario;
//    }
    public Usuario existenciaNombre(String nombre) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from USERS where NOMBRE=?";
        Usuario usuario = (Usuario) jtm.queryForObject(
                SQL, new Object[]{nombre}, new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario existenciaCodigo(String codigo) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from USERS where CODIGO_ACTIVACION=?";
        Usuario usuario = (Usuario) jtm.queryForObject(
                SQL, new Object[]{codigo}, new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario comprobarPass(final String nombre, final String pass) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from USERS where NOMBRE=? and PASSWORD=? and ACTIVO=1";
        Usuario usuario = (Usuario) jtm.query(
                SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setNString(1, nombre);
                preparedStatement.setNString(2, pass);
            }
        }, new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario activar(String codigo) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "INSERT INTO USERS (ACTIVO) VALUES(1) where CODIGO_ACTIVACION=?";
        Usuario usuario = (Usuario) jtm.queryForObject(
                SQL, new Object[]{codigo}, new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario addUser(Usuario usuario) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("USERS").usingGeneratedKeyColumns("ID");
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("NOMBRE", usuario.getNombre());
        parameters.put("PASSWORD", usuario.getPassword());
        parameters.put("ACTIVO", usuario.getActivado());
        parameters.put("CODIGO_ACTIVACION", usuario.getCodigoActivacion());
        parameters.put("FECHA_ACTIVACION", usuario.getFechaActivacion());
        parameters.put("EMAIL", usuario.getEmail());
        usuario.setId(jdbcInsert.executeAndReturnKey(parameters).longValue());

        return usuario;
    }

}
