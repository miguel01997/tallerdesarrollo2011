/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.conexion;
import beans.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 * @author luis
 */
public class resulUsuarios extends SimpleFormController {
    
    private conexion c;

    public void setC(conexion c) {
        this.c = c;
    }

    
    
    
    
    public resulUsuarios() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(formUsuario.class);
        setCommandName("consulta");
        setSuccessView("usuarioquery");
        setFormView("usuario");
        c = new conexion();
    }
    
   
    @Override
    protected ModelAndView onSubmit(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Object command, 
    BindException errors) throws Exception {
        formUsuario consulta = (formUsuario) command; 
    ModelAndView mv = new ModelAndView(getSuccessView());
        List lista = consulta2(consulta.getConsultaquery());
        mv.addObject("prueba",consulta.getConsultaquery());
        mv.addObject("lista",lista);
       
    return mv;
    }
    
    private List buscarUsuarios(){
      JdbcTemplate select = c.getJdbcTemplate();
      return select.query("select * from usuario", new Usuario());
      
      
    }
    
    
   /* private List consulta(String consulta){
      JdbcTemplate select = c.getJdbcTemplate();
      return select.query(consulta, new Usuario());
      
    }*/
    
    private List consulta2(String consulta){
            JdbcTemplate s = c.getJdbcTemplate();
            List <Usuario> u;
            u = s.query(consulta, new BeanPropertyRowMapper(Usuario.class));
            return u;
    }
    
    
    
        ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        List usuarios = buscarUsuarios();
        modelo.put("lista",usuarios);
        modelo.put("mensaje",request.getParameter("mensaje"));        
        
        return modelo;
    }

}
