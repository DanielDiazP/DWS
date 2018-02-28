/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Canal;
import model.Usuario;
import model.Mensaje;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Dani
 */
public class ChatDao {

    public Usuario datosUsuario(Usuario usuario) {
        Usuario user=null;
        try {
            JdbcTemplate jtm = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());

            String SQL = "Select * from registro where nombre=?";
            user = (Usuario)jtm.queryForObject(
                SQL, new Object[]{usuario.getNombre()}, new BeanPropertyRowMapper(Usuario.class));
            

        } catch (Exception e) {
            Logger.getLogger(ChatDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;
    }
    

    public int nuevoUsuario(Usuario usuario) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("registro");
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("nombre", usuario.getNombre());
        parametros.put("pass", usuario.getPass());
        return jdbcInsert.execute(parametros);
    }

    public List<Canal> todosCanales() {

        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());
        List<Canal> listaCanales = jtm.query("Select * from canales",
                new BeanPropertyRowMapper(Canal.class));

        return listaCanales;
    }


    public void guardarMensaje(Mensaje mensaje){
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName("mensajes");
        Map<String,Object>  parametros=new HashMap<>();
        
        parametros.put("mensaje", mensaje.getMensaje());
        parametros.put("fecha", mensaje.getFecha());
        parametros.put("id_canal", mensaje.getId_canal());
        parametros.put("nombre_user", mensaje.getNombre_user());
        jdbcInsert.execute(parametros);
    }
    
    public List<Mensaje> cargarMensaje(Mensaje mensaje) {
        List<Mensaje> mensajes=null;
        try {
            JdbcTemplate jtm = new JdbcTemplate(
                    DBConnection.getInstance().getDataSource());

            String SQL = "Select * from mensajes right join canales where mensajes.fecha between CAST(? AS DATE) AND CAST(? AS DATE) on canales.id=?";
            List<Mensaje> peticion = (List<Mensaje>) jtm.queryForObject(
                SQL, new Object[]{mensaje.getFecha(),mensaje.getFecha2(),mensaje.getId_canal()}, new BeanPropertyRowMapper(Mensaje.class));
            mensajes=peticion;

        } catch (Exception e) {
            Logger.getLogger(ChatDao.class.getName()).log(Level.SEVERE, null, e);
        }
      return mensajes;
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
