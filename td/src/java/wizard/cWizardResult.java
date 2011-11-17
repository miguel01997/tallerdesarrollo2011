/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

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
        setSuccessView("wizardResult");
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
    System.out.println(wf.getTabla());
    System.out.println("Terminos: "+wf.getTerminos().length);
    String[] term = wf.getTerminos();
    for (int i =0; i<term.length;i++){
        System.out.println(term[i]);
    }
    System.out.println("Conectores: "+wf.getConectoresA().length);
    String[] conec = wf.getConectoresA()[0].split(",");
    for (int i =0; i<conec.length;i++){
        System.out.println(conec[i]);
    }
    return mv;
    }
     
}
