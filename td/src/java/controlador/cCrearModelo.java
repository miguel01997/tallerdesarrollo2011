/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Modelo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
/** 
 *
 * @author luis
 */
public class cCrearModelo extends SimpleFormController {
    
    
    
    private conexion c;
    public cCrearModelo() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Modelo.class);
        setCommandName("mm");
       
        setSuccessView("crearModelo");
        setFormView("crearModelo");
        c = new conexion();
    }
    
   
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Object command, 
        BindException errors) throws Exception {
        ModelAndView mv = new ModelAndView(getSuccessView());
        Modelo m = (Modelo)command;
        
        //Esto es porque regresa a la página
        mv.addObject("mm",m);
        
        //Se busca si nombre no es vacio
        if(m.getNombre().equals("")){
            mv.addObject("error","Error: Nombre vacio");
            return mv;
        }
        
         //Se busca si nombre no es vacio
        if(m.getMarca().equals("")){
            mv.addObject("error","Error: Marca vacia");
            return mv;
        }
        
        
        //Crea el modelo
        m.crearModelo();
        
        mv.addObject("mensaje","Modelo "+ m.getNombre()+" creado");
        //Borra los valores del objeto
        m.setNombre("");
        m.setMarca("");
        
        return mv;
    }
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        
        //modelo.put("lista",anuncios);
        return modelo;
    }
    
     
}
