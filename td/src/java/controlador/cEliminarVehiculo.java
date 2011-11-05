/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Modelo;
import beans.Vehiculo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author luis
 */
public class cEliminarVehiculo extends AbstractController {
    
    
    conexion c;
    public cEliminarVehiculo() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            c = new conexion();
            JdbcTemplate j = c.getJdbcTemplate();
            Vehiculo v = new Vehiculo();
            v.setPlaca(request.getParameter("id"));
            v.buscarVehiculo();
            //elimina el modelo
            //System.out.println(v.getPlaca());
            String mensaje="";
            if(v.VerificarAnuncio()){
                mensaje="vehiculo.htm?mensaje=Vehiculo "+v.getPlaca()+
                     " eliminado ";    
                v.eliminarVehiculo();
            }
            
            else{
                mensaje= "vehiculo.htm?mensaje=No se puede eliminar este Vehiculo, "
                        + "Existe un anuncio que lo referencia";
            
            }
            ModelAndView m = new ModelAndView("vehiculo");
             //envia el mensaje de que fue eliminado
             response.sendRedirect(mensaje);
           return m;
    }
}
