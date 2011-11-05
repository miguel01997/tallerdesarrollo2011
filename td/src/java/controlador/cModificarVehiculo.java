/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Modelo;
import beans.Vehiculo;
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
public class cModificarVehiculo extends SimpleFormController {
    
    public cModificarVehiculo() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Vehiculo.class);
        setCommandName("mm");
        setSuccessView("modificarVehiculo");
        setFormView("modificarVehiculo");
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
    
    Vehiculo v = (Vehiculo)command;
    
    
    
    //carga la lista de modelos
    Modelo m = new Modelo();
    mv.addObject("lista",m.buscarTodosModelos());
    //validar
    String error;
    error = validar(v);
    mv.addObject("mm",v);
    mv.addObject("p",v);
    
    Vehiculo v2 = new Vehiculo();
    v2.setPlaca(request.getParameter("id"));
    v2.buscarVehiculo();
    if(!v2.getPlaca().equals(v.getPlaca())){
           mv.addObject("error","No se puede modificar la placa");
           v.setPlaca(request.getParameter("id"));
           
           return mv;
    }
    if(error!=null){
           mv.addObject("error",error);
         
           return mv;
        }
    mv.addObject("mensaje","Vehiculo "+v.getPlaca()+" modificado");
    v.actualizarVehiculo();
    
    
    //Do something...
    return mv;
    }
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        Modelo v = new Modelo();
        modelo.put("lista",v.buscarTodosModelos());
        
        Vehiculo vv = new Vehiculo();
        //Buscamos el vehiculo por su placa y lo enviamos a la vista
        vv.setPlaca(request.getParameter("id"));
        
        vv.buscarVehiculo();
        modelo.put("p", vv);
        
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
