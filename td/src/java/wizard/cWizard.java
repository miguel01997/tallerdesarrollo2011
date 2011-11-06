/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import Dao.conexion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author wendy
 */
public class cWizard extends AbstractController {
    
    conexion c;
    public cWizard() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            c = new conexion();
            JdbcTemplate j = c.getJdbcTemplate();
            wizzardClase w = new wizzardClase();
            ModelAndView m = new ModelAndView("wizard");
            
              // TODO code application logic 
            String archivoPersistencia = "/var/www/jsp/tds/td/src/conf/persistence.xml";
            parsearArchivoPersistencia p =  new parsearArchivoPersistencia(archivoPersistencia);
           
            HashMap parseo=p.mapaClaseAtributos();
            Set clavesSet=parseo.keySet();
            List<String> claves = new ArrayList<String>(clavesSet);
            
            
       
            m.addObject("hashMap",parseo);
            
            m.addObject("listatablas",claves);
            
            m.addObject("listapred",w.buscarPredicado());
            
            m.addObject("listamod",w.buscarModificador());
            
            m.addObject("listaconn",w.buscarConector());
             
            m.addObject("listacomp",w.buscarComparador());
            
            m.addObject("listaquan",w.buscarCuantificador());
             //envia el mensaje de que fue eliminado
          //   response.sendRedirect(mensaje);
           return m;
    }
    
    
}
