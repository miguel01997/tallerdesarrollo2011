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
            Usuario mm = new Usuario();
            mm.setCodusuario(new Integer(request.getParameter("id")));
            mm.buscarUsuario();
            String mensaje="";
            
            if(mm.VerificarAnuncio()){
                 mensaje="usuario.htm?mensaje=Usuario "+mm.getNombre()+
                     " eliminado ";
            
                //elimina el modelo
                mm.eliminarUsuario();
            }
            else{
                mensaje="usuario.htm?mensaje=No se puede eliminar este Usuario, "
                        + "Existe un anuncio que lo esta referenciando"; 
            }
           
           ModelAndView m = new ModelAndView("usuario");
           m.addObject("mensajeR",mensaje);
           //envia el mensaje de que fue eliminado
           response.sendRedirect(mensaje);
           
           return m;
        
    }
}
