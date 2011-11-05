/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Anuncio;
import beans.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author wendy
 */
public class cEliminarAnuncio extends AbstractController {
    
    
    conexion c;
    public cEliminarAnuncio() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            c = new conexion();
            JdbcTemplate j = c.getJdbcTemplate();
            Anuncio v = new Anuncio();
            v.setCodanuncio(new Integer(request.getParameter("id")));
            v.buscarAnuncio();
            //elimina el modelo
            //System.out.println(v.getPlaca());
            v.eliminarAnuncio();
           
            ModelAndView m = new ModelAndView("anuncio");
             //envia el mensaje de que fue eliminado
           
             response.sendRedirect("anuncio.htm?mensaje=El anuncio del vehiculo con la placa "
                     +v.getCodvehiculo().getPlaca()+ " fue eliminado");
                    
           return m;
    }
    
    
}
