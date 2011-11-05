/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Anuncio;
import beans.Usuario;
import beans.Vehiculo;
import java.util.Date;
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
public class cCrearAnuncio extends SimpleFormController {
         
    private conexion c;

    public void setC(conexion c) {
        this.c = c;
    }

    public cCrearAnuncio() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Anuncio.class);
        setCommandName("ca");
        setSuccessView("crearAnuncio");
        setFormView("crearAnuncio");
        c=new conexion();
    }
    
   // @Override
   // protected void doSubmitAction(Object command) throws Exception {
   //     throw new UnsupportedOperationException("Not yet implemented");
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
        Anuncio m = (Anuncio)command;
        Usuario mm  = new Usuario();
        mv.addObject("lista",mm.buscarTodosUsuarios());
  
        Vehiculo vv  = new Vehiculo();
        mv.addObject("listaV",vv.buscarTodosVehiculos());
        mv.addObject("ca",m);  
        //m.setCodmodelo(new Modelo());
        //validar
      String mensaje="";
  
  
    if (m.VerificarPlaca()){
     m.crearAnuncio();
     mensaje= "Anuncio "+m.getDescripcion()+" creado ";}
    else{
     mensaje= "No se puede crear 2 anuncios de un mismo vehiculo";}
        
     mv.addObject("mensaje", mensaje);   
        //Borra los valores del objeto
        m.setDescripcion("");
        m.setFecha(null);
        
        
        return mv;
    }
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        Usuario v = new Usuario();
        Vehiculo y = new Vehiculo();
        
        modelo.put("lista",v.buscarTodosUsuarios());
        modelo.put("listaV",y.buscarTodosVehiculos());
        Anuncio vv = new Anuncio();
        vv.setCodusuario(new Usuario());
        vv.setCodvehiculo(new Vehiculo());
        return modelo;
    }
    
        
        //Metodo usado para validar
       
     
}
