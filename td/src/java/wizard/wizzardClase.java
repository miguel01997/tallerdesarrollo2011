/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import Dao.conexion;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author wendy
 */
public class wizzardClase {
    
    public List buscarPredicado(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
        List  list= j.queryForList("select predname from pg_catalog.pg_fuzzypred");
        
        return list;
        
       
    }
    
    public List buscarModificador(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
        List  list= j.queryForList("select modname from pg_catalog.pg_fuzzymod");
        return list;
        
    }
    
    public List buscarComparador(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
        List  list= j.queryForList("select compname from pg_catalog.pg_fuzzycomp");
        return list;
        
    }
    
    public List buscarConector(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
        List  list= j.queryForList("select connname from pg_catalog.pg_fuzzyconn");
        return list;
        
    } 
    
    public List buscarCuantificador(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
        List  list= j.queryForList("select quanname from pg_catalog.pg_fuzzyquan");
        return list;
        
    } 
    
    
}
