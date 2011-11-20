/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Vehiculo;
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
public class cVehiculo extends SimpleFormController {
    
    conexion c;
    
    public cVehiculo(){
        setCommandClass(formUsuario.class);
        //setCommandName("consulta");
        setSuccessView("vehiculo");
       // setFormView("vehiculo");
         c = new conexion();
    }
    
    
    @Override
    protected ModelAndView onSubmit(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Object command, 
    BindException errors) throws Exception {
        //formUsuario consulta = (formUsuario) command; 
    ModelAndView mv = new ModelAndView(getSuccessView());
      //  List lista = consulta2(consulta.getConsultaquery());
       // mv.addObject("prueba",consulta.getConsultaquery());
       // mv.addObject("lista",lista);
       
    return mv;
    }
    
    
    ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        
        List vehiculos = (List)request.getAttribute("lista");     
        Map modelo = new HashMap();
        Vehiculo v= new Vehiculo();
        if(vehiculos == null || vehiculos.isEmpty()){
            vehiculos = v.buscarTodosVehiculos();
        }
        modelo.put("lista",vehiculos);
        modelo.put("mensaje", request.getParameter("mensaje"));
        
        return modelo;
    }
        
    
   
    
}
