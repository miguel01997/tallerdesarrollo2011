/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import beans.Vehiculo;
import java.util.HashMap;
import java.util.Iterator;
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
        query = "select placa,precio from vehiculo where motor=joven";
        List lis = em.createNativeQuery(query, Vehiculo.class).getResultList();
        
        Vehiculo  v = new Vehiculo();
        String q = "select placa,codmodelo,precio from vehiculo where motor=joven";
        //System.+//out.println(v.ejecutarQuery(q));
        
        
        List<Vehiculo> l  = v.ejecutarQuery(q);
        System.out.println(l.get(0).getMembresia());
        //JdbcTemplate jdbc = c.getJdbcTemplate();
        //jdbc.query(query,  new BeanPropertyRowMapper(Usuario.class));
        
        //Query l =  em.createNativeQuery(query,Usuario.class);
        
        System.out.println("\n\n\n**** Lista Vehiculos:"+l.size());
        for(Object u : l){
            System.out.println(u + " "+ ((Vehiculo)u).getPrecio());
           // System.out.println(((Object[])u)[2]);
            //System.out.println(((java.util.Vector)u).get(0).getClass().getCanonicalName());
        }
        
        JdbcTemplate jt = c.getJdbcTemplate();
        List m = jt.queryForList(query);
        for (Object u: m){
            HashMap map = (HashMap) u;
            Iterator it = map.keySet().iterator();
            String str = "";
            while(it.hasNext()){
                String elem = (String) it.next();
                str += elem+" "+map.get(elem);
            }
            System.out.println(">>"+str);
        }
        
    }
}
