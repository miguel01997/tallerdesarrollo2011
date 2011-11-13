/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author wendy
 */
public class pru {
     public static void main(String[] args) throws ClassNotFoundException {
        try {
            // TODO code application logic 
            String archivoPersistencia = "src/conf/persistence.xml";
            parsearArchivoPersistencia p =  new parsearArchivoPersistencia(archivoPersistencia);
           
            HashMap parseo=p.mapaClaseAtributos();
            Set clavesSet=parseo.keySet();
            List<String> claves = new ArrayList<String>(clavesSet);
            
            Iterator iter = claves.iterator();
                while (iter.hasNext())
                System.out.println(iter.next());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(pru.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(pru.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pru.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        
     
     
        
    
}