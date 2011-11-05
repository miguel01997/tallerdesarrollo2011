/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wendy
 */
public class Prueba {
    
    public static void main(String [] args ){
         EntityManagerFactory emf;
	 EntityManager em;
        
          emf = Persistence.createEntityManagerFactory("TestPU");
          em = emf.createEntityManager();
          
          Iterator it = em.createNamedQuery("Usuario.findAll").getResultList().iterator();
	    while(it.hasNext()) {
	        //Agrego el resultado al Combobox
                Usuario u= (Usuario)it.next();
	       System.out.println(u.getNombre());
	    }
          
            
            Usuario u= new Usuario();
            u.setNombre("Luiss");
            u.setApellido("Lopez");
            u.setEdad(14);
            u.setEmail("a@h.com");
           // u.setCodusuario();
           
            //em.persist(u); 
            em.getTransaction().begin();
            Usuario uu= em.find(Usuario.class, 22);
            //em.remove(uu);
            em.persist(u); //em.merge(u); for updates
            em.getTransaction().commit();
            em.close();
    }
    
}
