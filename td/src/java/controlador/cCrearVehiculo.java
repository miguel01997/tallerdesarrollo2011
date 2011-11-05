/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Modelo;
import beans.Vehiculo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 * @author luis
 */
public class cCrearVehiculo extends SimpleFormController {
    
    public cCrearVehiculo() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Vehiculo.class);
        setCommandName("mm");
        setSuccessView("crearVehiculo");
        setFormView("crearVehiculo");
    }
  
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Object command, 
        BindException errors) throws Exception {
        ModelAndView mv = new ModelAndView(getSuccessView());
        Vehiculo m = (Vehiculo)command;
        Modelo mm  = new Modelo();
        mv.addObject("lista",mm.buscarTodosModelos());
        mv.addObject("mm",m);    
        //m.setCodmodelo(new Modelo());
        //validar
       String error = validar(m);
        if(error!=null){
           
           
           mv.addObject("error",error);
           //response.encodeRedirectURL("crearVehiculo.htm");
           //Esto es porque regresa a la página el objeto pero con los valore vacios
           
           return mv;
        }
        
      
      
        response.encodeRedirectURL("crearVehicuslo.htm");
        
        m.crearVehiculo();
        
        mv.addObject("mensaje","Vehículo "+ m.getPlaca()+" creado");
        //Borra los valores del objeto
        m.setPlaca("");
        m.setMotor(0);
        m.setPrecio(new BigDecimal(0));
        
        return mv;
    }
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        Modelo v = new Modelo();
        
        modelo.put("lista",v.buscarTodosModelos());
        Vehiculo vv = new Vehiculo();
        vv.setCodmodelo(new Modelo());
       
        return modelo;
    }
    
        
        //Metodo usado para validar
        private String validar(Vehiculo m ){
           String mensaje = null;
            //Se busca si nombre no es vacio
        if(m.getPlaca().equals("")){
            mensaje = "Error: Placa vacia";
            return mensaje;
        }
        
        if(m.getCodmodelo().getCodmodelo() == null){
            mensaje = "Error: Modelo vacio";
            return mensaje;
        }
        
        
         //Se busca si nombre no es vacio
        if(m.getPrecio() == null){
            mensaje = "Error: Precio vacio";
            return mensaje;
        }
        
        //Se busca si nombre no es vacio
        if(m.getMotor()== null){
            mensaje = "Error: Motor vacio";
            return mensaje;
        }
        
        return mensaje;
           
        }
     
}
