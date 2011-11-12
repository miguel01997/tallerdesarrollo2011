/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package requsitodifuso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import wizard.parsearArchivoPersistencia;

/**
 *
 * @author wendy
 */
public class Asociacion {
    
    public HashMap<String,ArrayList<String>> Asociaciones() {
        try {
            String archivoPersistencia = "/var/www/jsp/tds/td/src/conf/persistence.xml";
                 parsearArchivoPersistencia p =  new parsearArchivoPersistencia(archivoPersistencia);
                 HashMap<String,ArrayList<String>> parseo=p.mapaClaseAtributos();
                         
                      return parseo;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Asociacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Asociacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Asociacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Asociacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
}
    
    
    
          
    
    
}
