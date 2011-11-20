/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Anuncio;
import beans.Usuario;
import beans.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


/** 
 *
 * @author wendy
 */
public class cModificarAnuncio extends SimpleFormController {
    
    private conexion c;

    public void setC(conexion c) {
        this.c = c;
    }
    public cModificarAnuncio() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Anuncio.class);
        setCommandName("ma");
        setSuccessView("modificarAnuncio");
        setFormView("modificarAnuncio");
        c=new conexion();
    }
    
   // @Override
   // protected void doSubmitAction(Object command) throws Exception {
    //    throw new UnsupportedOperationException("Not yet implemented");
    //}
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
        @Override
    protected ModelAndView onSubmit(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Object command, 
    BindException errors) throws Exception {
    ModelAndView mv = new ModelAndView(getSuccessView());
    Anuncio v = (Anuncio)command;
    v.setCodanuncio(new Integer(request.getParameter("id")));
    
    
    //carga la lista de modelos
    Usuario m = new Usuario();
    mv.addObject("lista",m.buscarTodosUsuarios());
    Vehiculo vv = new Vehiculo();
    mv.addObject("listaV",vv.buscarTodosVehiculos());
    //validar
    String error;
    //error = validar(v);
    mv.addObject("ma",v);
    mv.addObject("p",v);
    
    /*v = new Vehiculo();
    v.buscarVehiculo(request.getParameter("id"));
    mv.addObject("p",v);*/
    String mensaje="";
  
  
   
     v.actualizarAnuncio();
     mensaje= "Anuncio "+v.getDescripcion()+" modificado ";
    
    
    mv.addObject("mensaje", mensaje);
    
    //Do something...
    return mv;
    }
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        Usuario v = new Usuario();
        modelo.put("lista",v.buscarTodosUsuarios());
        Vehiculo vv=new Vehiculo();
        modelo.put("listaV",vv.buscarTodosVehiculos());
        Anuncio aa = new Anuncio();
        //Buscamos el vehiculo por su placa y lo enviamos a la vista
        aa.setCodanuncio(new Integer(request.getParameter("id")));
        
        System.out.println("\n\n\n"+new Integer(request.getParameter("id")));
        SimpleDateFormat fech=new SimpleDateFormat("dd/MM/yyyy");
        
        aa.setCodanuncio(aa.getCodanuncio());
        aa.buscarAnuncio();
        String fechaA= fech.format(aa.getFecha());
        modelo.put("fechaA",fechaA);
        modelo.put("p", aa);
        
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
