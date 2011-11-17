/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jose
 */
public class prueba {
    
    public static void main(String[] args){
        String query = "select u.nombre,u.edad,m from Modelo m, Usuario u";
        //query = "select m from Modelo m";
        //query = "select u.nombre,u.edad from Usuario u";
        /*query = "SELECT v.placa "+
                "FROM vehiculo AS v "+
                "WHERE  (v.codmodelo IN (SELECT a.codmodelo FROM modelo AS a WHERE a.anio = viejo))";*/
        //query = "SELECT a FROM Modelo AS a WHERE a.anio = viejo";
        //query = "select predname from pg_catalog.pg_fuzzypred";
        conexion c = new conexion();
        EntityManager em = c.getEm();
        //JdbcTemplate jdbc = c.getJdbcTemplate();
        //jdbc.query(query,  new BeanPropertyRowMapper(Usuario.class));
        
        List l = em.createQuery(query).getResultList();
        
        for(Object u : l){
            System.out.println(u.getClass().getName());
            System.out.println(((Object[])u)[2]);
            //System.out.println(((java.util.Vector)u).get(0).getClass().getCanonicalName());
            
        }
    }
}
