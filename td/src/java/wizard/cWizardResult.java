/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import Dao.conexion;
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
    System.out.println("Columnas: "+cols.length);
    for (int i =0; i<cols.length;i++){
        System.out.println(cols[i]);
    }
    
    System.out.println(wf.getCondicion());
    String paq_tabla = wf.getTabla();
    //Obtener el nombre de la tabla
    int dotindex = paq_tabla.lastIndexOf(".");
    String tabla = paq_tabla.substring(dotindex+1);
    
    String condicion = wf.getCondicion();
    System.out.println(wf.getTabla());
    System.out.println("Terminos: "+wf.getTerminos().length);
    String[] term = wf.getTerminos()[0].split(",");
    for (int i =0; i<term.length;i++){
        System.out.println(term[i]);
    }
    System.out.println("Conectores: "+wf.getConectoresA().length);
    String[] conec = wf.getConectoresA()[0].split(",");
    for (int i =0; i<conec.length;i++){
        System.out.println(conec[i]);
    }
    
    System.out.println("Mostrar como un hashmap? "+wf.isComoHashMap());
    
    
    Asociacion c= new Asociacion();
    HashMap<String,String> mapa = new HashMap<String,String>();
    mapa=c.Asociaciones();
    System.out.println("CAAALIBRACCCIOOONNN"+wf.getCalibracion());
    RequisitoDifuso rd = new RequisitoDifuso("FR", term, conec, 0.5, cols, tabla,condicion, mapa);
    String sql = rd.traducir();
    System.out.println(sql);
    conexion conex = new conexion();
    if (wf.isComoHashMap()){
        List result = conex.ejecutarQuery(sql);
        mv.addObject("resultado", result);
        setSuccessView("wizardResult");
    } else {
        try{
            Class clase = Class.forName(paq_tabla);
            Object obj = clase.getConstructor(new Class[]{}).newInstance((Object[]) null);
            System.out.println("Objeto creado dinam : "+obj.getClass().getCanonicalName());
            Class[] methodParameters = new Class[]{java.lang.String.class};
            Method m = clase.getDeclaredMethod("ejecutarQuery", methodParameters);
            System.out.println("Metodo cargado dinam.:"+m.toGenericString());
            Object o = m.invoke(obj, sql);
            //mv.addObject("lista", o);
            //this.setSuccessView(tabla.toLowerCase());
            //response.sendRedirect(tabla.toLowerCase()+".htm");
            mv.addObject("prueba", "Texto de prueba");
            mv.addObject("lista", o);
            mv.setViewName(tabla.toLowerCase());
            return mv;
            //System.out.println(o.getClass().getCanonicalName());
        } catch(Exception e){
            System.out.println("Error al ejecutar el metodo ");
            e.printStackTrace();
        }
    }
    System.out.println("\n\n\n***SuccessView del controlador: "+this.getSuccessView());
    mv.addObject("tabla",tabla);
    mv.addObject("columnas", cols);
    
    
    return mv;
    }
     
}
