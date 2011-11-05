package controlador;

import Dao.conexion;
import beans.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 * @author wendy
 */
public class cCrearUsuario extends SimpleFormController {
    
        
    private conexion c;

    public void setC(conexion c) {
        this.c = c;
    }

    
    public cCrearUsuario() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Usuario.class);
        setCommandName("cu");
        setSuccessView("crearUsuario");
        setFormView("crearUsuario");
        c=new conexion();
    }
    
   // @Override
    //protected void doSubmitAction(Object command) throws Exception {
    //    throw new UnsupportedOperationException("Not yet implemented");
   // }
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Object command, 
    BindException errors) throws Exception {
    ModelAndView mv = new ModelAndView(getSuccessView());
 //   formRegUsuario registro= (formRegUsuario) command;
    Usuario registro= (Usuario) command;
     mv.addObject("cu",registro);
     
     registro.crearUsuario();
     mv.addObject("mensaje","Usuario "+ registro.getNombre()+" creado");
     registro.setNombre("");
     registro.setApellido("");
     registro.setEdad(0);
     registro.setEmail("");
     
     
   // Usuario u=new Usuario()
   // u.setNombre(registro.getNombre());
   // u.setApellido(registro.getApellido());
   // u.setEdad(registro.getEdad());
//    u.setEmail(registro.getEmail());
    //insertar(registro);
    
    //Do something...
    return mv;
    }
   
     
}