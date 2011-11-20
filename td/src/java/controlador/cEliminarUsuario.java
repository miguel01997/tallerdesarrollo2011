/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
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
public class cEliminarUsuario extends AbstractController {
    
    conexion c;
    public cEliminarUsuario() {
        c = new conexion();
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
           
            JdbcTemplate j = c.getJdbcTemplate();
            Usuario v = new Usuario();
            v.setCodusuario(new Integer(request.getParameter("id")));
            v.buscarUsuario();
            //elimina el modelo
            //System.out.println(v.getPlaca());
            String mensaje="";
            if(v.VerificarAnuncio()){
                mensaje="usuario.htm?mensaje=Usuario "+v.getNombre()+
                     " eliminado ";    
                v.eliminarUsuario();
            }
            
            else{
                mensaje= "usuario.htm?mensaje=No se puede eliminar este usuario, "
                        + "Existe un anuncio que lo referencia";
            
            }
            ModelAndView m = new ModelAndView("usuario");
             //envia el mensaje de que fue eliminado
             response.sendRedirect(mensaje);
           return m;
        
    }
}
