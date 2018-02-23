/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
        try{
        JdbcTemplate jtm = new JdbcTemplate(
                DBConnection.getInstance().getDataSource());

        String SQL = "Select * from registro where nombre=? and pass=?";
        Usuario user = (Usuario) jtm.queryForObject(
                SQL, new Object[]{usuario.getUser(), usuario.getPass()}, new BeanPropertyRowMapper(Usuario.class));

        return user;
        }catch(Exception e){
                Logger.getLogger(ChatDao.class.getName()).log(Level.SEVERE, null, e);
                }
        return usuario;
    }

    public int nuevoUsuario(Usuario usuario) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("registro");
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("nombre", usuario.getUser());
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
/// hasta aqui
    
//    public int nuevoMensaje(Mensaje mensaje){
//        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName("MENSAJES");
//        Map<String,Object>  parametros=new HashMap<>();
//        
//        parametros.put(key, this);
//        parametros.put(key, this);
//        parametros.put(key, this);
//        parametros.put(key, this);
//        parametros.put(key, this);
//        parametros.put(key, this);
//        
//        
//        
//    }
    
    
    public Canal nuevoCanal(Canal canal) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnection.getInstance().getDataSource()).withTableName("CANALES").usingGeneratedKeyColumns("ID");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("Nombre", canal.getNombre());
        canal.setId(jdbcInsert.executeAndReturnKey(parameters).longValue());

        return canal;
    }
}
