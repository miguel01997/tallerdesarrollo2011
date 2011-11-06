/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author luis
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic 
        String archivoPersistencia = "src/conf/persistence.xml";
        parsearArchivoPersistencia p =  new parsearArchivoPersistencia(archivoPersistencia);
        try {
            System.out.println(p.clases());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        /*Atributos de clases*/
        for(String i:p.atributosDeClase("beans.Vehiculo")){
            System.out.println(i);
        }
        
        
            System.out.println("Imprime El Mapa del archivo de Persistencia");
        try {
            //Buscar Mapa
            System.out.println(p.mapaClaseAtributos());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        System.out.println("Relaciones");
        System.out.println(p.buscarRelaciones("beans.Vehiculo"));
        
        
        
    }
}
