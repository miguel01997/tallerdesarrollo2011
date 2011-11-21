/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import beans.Modelo;
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
 * @author luis
 */
public class cmodificarModelo extends SimpleFormController {
    
    public cmodificarModelo() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(Modelo.class);
        setCommandName("mm");
        setSuccessView("modificarModelo");
        setFormView("modificarModelo");
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
    Modelo m = (Modelo) command;
    mv.addObject("mm",m);
    mv.addObject("p", m);
    //le asigna el id al objeto que lee de la url
    m.setCodmodelo(new Integer(request.getParameter("id")));
    //System.out.println(m.getNombre()+m.getMarca()+m.getAnio()+m.getCodmodelo());
    
    //se verifica el modelo
    
    //Para expresiones regulares
    /*    String soloLetras = "^[a-zA-Z]+$";
        String soloEspacios = "^\\s+$";
        String nombre = "^\\w+(\\s\\w*)*$";
        
        
        Pattern patron = Pattern.compile(soloEspacios);
        
        
        //Se busca si nombre no es vacio
        Matcher ma = patron.matcher(m.getNombre());
        if(m.getNombre().equals("") ||  ma.find()){
            mv.addObject("error","Error: Nombre vacio");
            return mv;
        }
        //verifica si nombre contiene letras
        ma = Pattern.compile(nombre).matcher(m.getNombre());
        if(!ma.find()){
            mv.addObject("error","Error: Nombre no válido");
            return mv;
        }
        
        ma = Pattern.compile(soloEspacios).matcher(m.getMarca());
        //Se busca si nombre no es vacio
        if(m.getMarca().equals("") || ma.find()){
            mv.addObject("error","Error: Marca vacia");
            return mv;
        }
        
        mv.addObject("mensaje","Modelo "+ m.getNombre()+" creado");
        //Borra los valores del objeto
        m.setNombre("");
        m.setMarca("");*/
    
    
    
    m.actualizarModelo();
    
    //mv.addObject("mm",m);
    mv.addObject("mensaje","Modelo "+m.getNombre()+" modificado.");
    
    return mv;
    }
    
    
      ///Metodo que se ejecuta antes de cargar la pagina
        @Override
    public Map referenceData(HttpServletRequest request) {
        Map modelo = new HashMap();
        //saca el parametro pasado por url
        String id = request.getParameter("id");
        //System.out.println(id);
        Modelo m = new Modelo();
        //instancia el modelo por su id
        m.setCodmodelo(new Integer(id));
        m.buscarModelo();
        //System.out.println(m.getCodmodelo());
        modelo.put("p", m);
       // modelo.put("lista",anuncios);
        return modelo;
    }
     
}
