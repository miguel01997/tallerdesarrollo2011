/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import oracle.toplink.essentials.internal.ejb.cmp3.EntityManagerImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author luis
 */
public class conexion {
    
    
    
    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource ds;
    
    
    
    EntityManagerFactory emf;
    EntityManager em;

    public DriverManagerDataSource getDs() {
        return ds;
    }

    public void setDs(DriverManagerDataSource ds) {
        this.ds = ds;
    }
    
      
    public conexion() {
        // Datos de conexion con la base de datos
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/concesionario");
        dataSource.setUsername("usuario");
        dataSource.setPassword("usuario"); //*/

        // La clase Spring con la Connection
           
      jdbcTemplate = new JdbcTemplate(dataSource);
      
      
      
      //Persistencia de entidades
        
          emf = Persistence.createEntityManagerFactory("tallerDesarrolloPU");
          em = emf.createEntityManager();
          
          
         
          
        
          
          
    }
    
      
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    
     public EntityManager getEm() {
        return em;
    }

    
}
