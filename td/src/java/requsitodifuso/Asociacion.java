/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package requsitodifuso;

import Dao.conexion;
import java.util.HashMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author wendy
 */
public class Asociacion {
    
    public HashMap <String, String> Asociaciones() {
        
        HashMap<String,String> asociaciones = new HashMap<String,String>();
        
      conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       
       SqlRowSet srs =j.queryForRowSet("SELECT tc.constraint_name AS constraint_name ,"+
        "tc.table_name AS table_name , "+
        "kcu.column_name AS column_name , "+
        "ccu.table_name AS references_table, "+
        "ccu.column_name AS references_field "+
        "FROM information_schema.table_constraints tc "+
        "LEFT JOIN information_schema.key_column_usage kcu "+
        "ON tc.constraint_catalog = kcu.constraint_catalog "+
        "AND tc.constraint_schema = kcu.constraint_schema "+
        "AND tc.constraint_name = kcu.constraint_name "+
        "LEFT JOIN information_schema.referential_constraints rc "+
        "ON tc.constraint_catalog = rc.constraint_catalog "+
        "AND tc.constraint_schema = rc.constraint_schema "+
        "AND tc.constraint_name = rc.constraint_name "+
        "LEFT JOIN information_schema.constraint_column_usage ccu "+
        "ON rc.unique_constraint_catalog = ccu.constraint_catalog "+
        "AND rc.unique_constraint_schema = ccu.constraint_schema "+
        "AND rc.unique_constraint_name = ccu.constraint_name "+
            "WHERE "+ 
        "tc.constraint_type='FOREIGN KEY'");
      
       while (srs.next()) {
            System.out.println(srs.getString("table_name") + " - " + srs.getString("column_name"));
            String clave=srs.getString("constraint_name");
            String valor=srs.getString("table_name") +"."+srs.getString("references_table")+"."+srs.getString("column_name")+"."+srs.getString("references_field");
            
            asociaciones.put(clave, valor);
        }
       
       
        
        return asociaciones;       
}
    
      /*public static void main(String[] args) {
      Asociacion c= new Asociacion();
     
      
      HashMap<String,String> p=c.Asociaciones();
      
      for( Iterator it = p.keySet().iterator(); it.hasNext();) {
        String clave = (String)it.next(); 
        String valor = (String)p.get(clave); 
        System.out.println(clave + " : " + valor);}
      
      
      }*/
}
    
    
    
          
    
    

