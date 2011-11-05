/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Modelo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author luis
 */
public class cEliminarModelo extends AbstractController {
    
    conexion c;
    public cEliminarModelo() {
        c = new conexion();
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
           
            JdbcTemplate j = c.getJdbcTemplate();
            Modelo mm = new Modelo();
            mm.setCodmodelo(new Integer(request.getParameter("id")));
            mm.buscarModelo();
            String mensaje="";
            
            if(mm.VerificarVehiculo()){
            //elimina el modelo
            mensaje= "modelo.htm?mensaje=Modelo "+mm.getNombre()+" eliminado ";    
            mm.eliminarModelo();
            
            }
            else{
              mensaje="modelo.htm?mensaje=Existe un vehiculo que referencia este modelo."
                      + " No se puede eliminar este modelo";  
            }     
                      
            ModelAndView m = new ModelAndView("modelo");
             //envia el mensaje de que fue eliminado
            response.sendRedirect(mensaje);
            
            
             return m;
            
    }
}
