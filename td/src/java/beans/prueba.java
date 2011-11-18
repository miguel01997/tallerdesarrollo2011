/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import beans.Vehiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jose
 */
public class prueba {
    
    public static void main(String[] args){
        String query = "select u from Usuario u ";
        //query = "select m from Modelo m";
        //query = "select u.nombre,u.edad from Usuario u";
        /*query = "SELECT v.placa "+
                "FROM vehiculo AS v "+
                "WHERE  (v.codmodelo IN (SELECT a.codmodelo FROM modelo AS a WHERE a.anio = viejo))";*/
        //query = "SELECT a FROM Modelo AS a WHERE a.anio = viejo";
        //query = "select predname from pg_catalog.pg_fuzzypred";
        conexion c = new conexion();
        EntityManager em = c.getEm();
        
        Vehiculo  v = new Vehiculo();
        String q = "select * from vehiculo where motor=joven";
        //System.+//out.println(v.ejecutarQuery(q));
        
        
        List<Vehiculo> l  = v.ejecutarQuery(q);
        System.out.println(l.get(0).getMembresia());
        //JdbcTemplate jdbc = c.getJdbcTemplate();
        //jdbc.query(query,  new BeanPropertyRowMapper(Usuario.class));
        
        //Query l =  em.createNativeQuery(query,Usuario.class);
        
        //System.out.println(l.getResultList());
        /*for(Object u : l){
            System.out.println(u);
           // System.out.println(((Object[])u)[2]);
            //System.out.println(((java.util.Vector)u).get(0).getClass().getCanonicalName());
        }*/
        
        
    }
}
