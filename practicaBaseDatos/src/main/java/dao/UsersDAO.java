/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Alumno;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author dani
 */
public class UsersDAO {

    public List<Usuario> getAllUsersJDBCTemplate() {

        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        List<Usuario> usuario = jtm.query("Select * from USERS",
                new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario existenciaNombre(String nombre) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from USERS where NOMBRE=?";
        Usuario usuario =(Usuario) jtm.queryForObject(
                SQL, new Object[] {nombre}, new BeanPropertyRowMapper(Usuario.class));

        return usuario;
    }

    public Usuario addUserJDBCTemplate(Usuario usuario) {
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
