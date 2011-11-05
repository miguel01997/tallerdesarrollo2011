/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import Dao.conexion;
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
