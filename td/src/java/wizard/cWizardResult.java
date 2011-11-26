/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import Dao.conexion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import requsitodifuso.Asociacion;
import requsitodifuso.RequisitoDifuso;

/** 
 *
 * @author jose
 */
public class cWizardResult extends SimpleFormController {
    
    public cWizardResult() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(wizardForm.class);
        setCommandName("resultw");
        //setSuccessView("wizardResult");
        setFormView("wizard");
    }
    
    /*@Override
    protected void doSubmitAction(Object command) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
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
    wizardForm wf = (wizardForm)command; 
    String[] cols = wf.getColumnas();
    
    
   
    String paq_tabla = wf.getTabla();
    //Obtener el nombre de la tabla
    int dotindex = paq_tabla.lastIndexOf(".");
    String tabla = paq_tabla.substring(dotindex+1);
    
    String condicion = wf.getCondicion();
    
    String[] term = wf.getTerminos()[0].split(",");
    
   
    String[] conec = wf.getConectoresA()[0].split(",");
    
    Asociacion c= new Asociacion();
    HashMap<String,String> mapa = new HashMap<String,String>();
    mapa=c.Asociaciones();
    double cali=wf.getCalibracion();
    RequisitoDifuso rd = new RequisitoDifuso("FR", term, conec, cali, cols, tabla,condicion, mapa);
    String sql = rd.traducir();
    //System.out.println(sql);
    conexion conex = new conexion();
    if (wf.isComoHashMap()){
        List result = conex.ejecutarQuery(sql);
        mv.addObject("resultado", result);
        setSuccessView("wizardResult");
    } else {
        try{
            Class clase = Class.forName(paq_tabla);
            Object obj = clase.getConstructor(new Class[]{}).newInstance((Object[]) null);
         
            Class[] methodParameters = new Class[]{java.lang.String.class};
            Method m = clase.getDeclaredMethod("ejecutarQuery", methodParameters);
          
            Object o = m.invoke(obj, sql);
        
            mv.addObject("prueba", "Texto de prueba");
            mv.addObject("lista", o);
            mv.setViewName(tabla.toLowerCase());
            return mv;
          
        } catch(InvocationTargetException e){
            System.out.println("Error al ejecutar el metodo "+e.getCause().getLocalizedMessage()+"\n***");
            mv.addObject("error",e.getCause().getLocalizedMessage());
            e.printStackTrace();
        }
    }
 
    mv.addObject("tabla",tabla);
    mv.addObject("columnas", cols);
    
    
    return mv;
    }
     
}
