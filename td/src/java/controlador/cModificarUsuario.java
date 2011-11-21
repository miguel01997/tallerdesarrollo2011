/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 * @author wendy
 */
public class cModificarUsuario extends SimpleFormController {
       private conexion c;

    public void setC(conexion c) {
        this.c = c;
    }
    
    public cModificarUsuario() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Usuario.class);
        setCommandName("mu");
        setSuccessView("modificarUsuario");
        setFormView("modificarUsuario");
        c=new conexion();
    }
    
   // @Override
   // protected void doSubmitAction(Object command) throws Exception {
    //    throw new UnsupportedOperationException("Not yet implemented");
    
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
    
     mv.addObject("mu",registro);
     mv.addObject("p",registro);
     
     String idd= request.getParameter("id");
  
     request.setAttribute("id", idd);   
    registro.setCodusuario(new Integer(idd));
    
    //Validaciones
    
    String nombre=registro.getNombre();
     Pattern p = Pattern.compile("[a-zA-Z]");
     Matcher m = p.matcher(nombre);
     if (!m.find()){
            mv.addObject("mensaje","El nombre debe ser una cadena de letras");
     return mv;}
     
     //Verifica apellido
     String apellido=registro.getApellido();
     p = Pattern.compile("[a-zA-Z]");
     m = p.matcher(apellido);
     if (!m.find()){
            mv.addObject("mensaje","El apellido debe ser una cadena de letras");
     return mv;}
     //Verifica edad
     
     
     // verifica correo
     String correo=registro.getEmail();
     p = Pattern.compile("^[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}");
     m=p.matcher(correo);
     if(!m.matches()){
         mv.addObject("mensaje","El formato del email es incorrecto");
     return mv;
     
     }
    
    
   // System.out.println(registro.getCodusuario()+registro.getNombre()+registro.getApellido()+registro.getEdad()+registro.getEmail());
    registro.actualizarUsuario();
    
    //mv.addObject("mm",m);
    mv.addObject("mensaje","Usuario "+registro.getNombre()+" modificado.");
    
    return mv;}
     
     
     
   // Usuario u=new Usuario()
   // u.setNombre(registro.getNombre());
   // u.setApellido(registro.getApellido());
   // u.setEdad(registro.getEdad());
//    u.setEmail(registro.getEmail());
    //insertar(registro);
    
    //Do something...

    
    
    
       @Override
    public Map referenceData(HttpServletRequest request) {
        Map usuario = new HashMap();
        //saca el parametro pasado por url
        String id = request.getParameter("id");
        //System.out.println(id);
        Usuario m = new Usuario();
        //instancia el modelo por su id
        m.setCodusuario(new Integer(id));
        m.buscarUsuario();
        //System.out.println(m.getCodmodelo());
        usuario.put("p", m);
       // modelo.put("lista",anuncios);
        return usuario;
    }
   
     
}