/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import Dao.conexion;
import beans.Anuncio;

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
public class cAnuncio extends SimpleFormController {
    
    conexion c;
    
    public cAnuncio() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(formUsuario.class);
        //setCommandName("MyCommandName");
        setSuccessView("anuncio");
        //setFormView("formView");
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
    //Do something...
    return mv;
    }
    
    
     ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        Anuncio a = new Anuncio();
        List anuncios = a.buscarTodosAnuncios();
        modelo.put("lista",anuncios);
        
        return modelo;
    }
    
    
}
