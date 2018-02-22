/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Canal;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


/**
 *
 * @author Dani
 */
public class ChatDao {

    public Usuario datosUsuario(Usuario usuario) {
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from USERS where NOMBRE=? and PASSWORD=?";
        Usuario user = (Usuario) jtm.queryForObject(
                SQL, new Object[]{usuario.getUser(), usuario.getPass()}, new BeanPropertyRowMapper(Usuario.class));

        return user;
    }

    public int nuevoUsuario(Usuario usuario) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("USERS");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("NOMBRE", usuario.getUser());
        parameters.put("PASSWORD", usuario.getPass());
        return jdbcInsert.execute(parameters);
    }

    public List<Canal> todosCanales() {

        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        List<Canal> listaCanales = jtm.query("Select * from canales",
                new BeanPropertyRowMapper(Canal.class));

        return listaCanales;
    }
/// hasta aqui
    public Canal nuevoCanal(Canal canal) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("CANALES").usingGeneratedKeyColumns("ID");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("Nombre", canal.getNombre());
        canal.setId(jdbcInsert.executeAndReturnKey(parameters).longValue());

        return canal;
    }
}
