/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package requsitodifuso;

/**
 *
 * @author wendy
 */
import Dao.conexion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.*;
import org.springframework.web.servlet.ModelAndView;
import wizard.wizardForm;

public class RequisitoDifuso {

    //El nombre del requisito
     String nombre;
    
    //Todos los terminos difusos involucrados con el requerimiento
     String[] terminos;
     String[] conectoresA;
    
    //La calibracion del resultado (Cuantitativa: > 1 | Cualitativa: < 1)
     double calibracion;
    
    //Los atributos de la tabla presentes en la operacion collect
     String[] columnas;
    
    //La tabla asociada al requisito
     String tabla;
    
    //La condicion difusa asociada al requisito
    public  String condicion;
    
    //Lista auxiliar para el manejo de conectores
     LinkedList<String> conectores = new LinkedList<String>();
    
    //Tabla con toda la informacion del modelo. Aqui deben estar las asociaciones (como "tiene")
    static HashMap <String, String> asociaciones;
    
    public RequisitoDifuso(String n, String[] term, String[] con, double cal, String[] col, String tab, String cond, HashMap<String,String> asoc) {
    
        nombre = n;
        terminos = term;
        conectoresA = con;
        calibracion = cal;
        columnas = col;
        tabla = tab;
        condicion = cond;
        asociaciones = asoc;
        
        for (int i = 0; i < conectoresA.length; i++) {
        
            String aux = conectoresA[i];
            conectores.add(aux);       
        }
    }
  
    public  List<String> reconstruir(String[] condicion, String sep) {
    
        for (int i = 0; i < condicion.length; i++) condicion[i] = condicion[i].trim();            
        List<String> lista = new LinkedList<String>(Arrays.asList(condicion));
        
        int i = 0, n = lista.size();
        while (i < n) {
        
            String aux = lista.get(i);
            
            String scont = new String(aux);
            int icont = scont.replaceAll("[^(]", "").length();
            
            if (icont > 0) {
            
                String contS = new String(aux);
                
                int abiertos = contS.replaceAll("[^(]", "").length();
                int cerrados = contS.replaceAll("[^)]", "").length();
            
                int j = i+1;
                while (abiertos != cerrados && j < lista.size()) {
                
                    String aux2 = lista.get(j);
                    aux += " " + sep + " " + aux2;
                    
                    contS = new String(aux);
                    abiertos = contS.replaceAll("[^(]", "").length();
                    cerrados = contS.replaceAll("[^)]", "").length();
                    
                    j++;                
                }
                for (int k = j-1; k >= i; k--) lista.remove(k);
                lista.add(i,aux);                        
                n = lista.size();
            }
            i++;                    
        }

        return lista;
    }
    
    public  String traducir() {
    
        return traducir(condicion);
    }
    
    public  String traducir(String condicion) {    
        
        //Variables auxiliares
        condicion = condicion.toLowerCase().trim();
        Pattern p;
        String[] auxArreglo;
        List<String> lista;
        
        if (condicion.startsWith("select(")) {
            
            //Se omiten los caracteres del select
            String aux = condicion.substring(7,condicion.length()-1);
            
            //Se busca la primera ocurrencia del separador |
            int barra = aux.indexOf('|');
            
            //Se construye la clausula SELECT
            String col = "";
            int it = 0;
            while (it < columnas.length-1) {
            
                col +=  aux.substring(0,barra).trim() + "." + columnas[it] + ", ";
                it++;            
            }
            col +=  aux.substring(0,barra).trim() + "." + columnas[it] + " "; 

            String cal = "";
            if (calibracion != 0) cal = " \nWITH CALIBRATION " + calibracion;
        
            //Se devuelve el resultado de la traduccion
            return "SELECT " + col + "\nFROM " + tabla + " AS " + aux.substring(0,barra).trim() + " \nWHERE " + traducir(aux.substring(barra+1).trim()) + cal;
        }
        
        //Se separa la condicion por los and
        p = Pattern.compile("\\band\\b");
        auxArreglo = p.split(condicion);
        
        //Se vuelven a armar las parentizaciones y los cuantificadores
        lista = reconstruir(auxArreglo, "and");  
        if (lista.size() > 1) {         
            
            //Se traduce cada sub condicion por separado y se unen nuevamente con el conetor AND
            int i;
            String result = "";            
            for(i = 0; i < lista.size()-1; i++) result += traducir(lista.get(i)) + " AND ";
            result += traducir(lista.get(i));
            
            return result;
        }
        else { 
        
            //Se separa la condicion por los or
            p = Pattern.compile("\\bor\\b");
            auxArreglo = p.split(condicion);
            
            //Se vuelven a armar las parentizaciones y los cuantificadores
            lista = reconstruir(auxArreglo, "or");
            if (lista.size() > 1) {        
            
                //Se traduce cada sub condicion por separado y se unen nuevamente con el conetor OR
                int i;
                String result = "";            
                for(i = 0; i < lista.size()-1; i++) result += traducir(lista.get(i)) + " OR ";
                result += traducir(lista.get(i));
                
                return result;
            }
            
            else{
            
                int j = 0;
                while (j < conectores.size()) {
                
                    //Se separa la condicion por cada uno de los conectores difusos
                    String fc = (conectores.get(j)).trim().toLowerCase();
                    p = Pattern.compile("\\b"+fc+"\\b");
                    auxArreglo = p.split(condicion);
                    
                    //Se vuelven a armar las parentizaciones y los cuantificadores
                    lista = reconstruir(auxArreglo, fc);
                    
                    if (lista.size() > 1) {        
            
                        //Se traduce cada sub condicion por separado y se unen nuevamente con el conetor difuso correspondiente
                        int i;
                        String result = "";            
                        for(i = 0; i < lista.size()-1; i++) result += traducir(lista.get(i)) +  " " + fc.toUpperCase() + " " ;
                        result += traducir(lista.get(i));
                        
                        return result;
                    }                     
                    j++;
                }                
                
                //Se revisa si la condicion esta parentizada, es un cuantificador o esta negada
                if (condicion.startsWith("(") && condicion.endsWith(")")) return "(" + traducir(condicion.substring(1,condicion.length()-1)) + ")";                
                if (condicion.startsWith("not")) return "NOT " + traducir(condicion.substring(3));                       
            
                //Si no se entra en ninguno de los casos anteriores, se procede a traducir la sub condicion
                return traducirSimple(condicion);            
            }
        }        
    }
    
    public  String traducirSimple(String condicion) {
    
        //Variables auxiliares
        condicion = condicion.toLowerCase().trim();
        String[] auxArreglo;
        Pattern p;

        //Se busca la existencia de ->
        p = Pattern.compile("->");
        auxArreglo = p.split(condicion); 
        
        //Si habia alguna ->, estamos en presencia de un collectionRole
        if (auxArreglo.length > 1) {
        
            //Se separa el string
            String izquierda = new String(auxArreglo[0]);
            String derecha = new String(auxArreglo[1]);
            
            char variable = extraerVariable(derecha);
            
            //Se separa la parte izquierda
            p = Pattern.compile("\\.");
            auxArreglo = p.split(izquierda);
        
            //Se busca toda la informacion de la asociacion en la tabla
            String a = asociaciones.get(auxArreglo[1]);
            String[] asoc = p.split(a);
        
            //Se devuelve el resultado de la traduccion
            return auxArreglo[0] + "." + asoc[2] + " IN (SELECT " + variable + "." + asoc[3] + " FROM " + asoc[1] + " AS " + variable + " GROUP BY " + variable + "." + asoc[3] + " HAVING " + traducirCuantificador(derecha, asoc[1]) + ")";
        }
        
        //Si no hay ->, se separa la condicion por todos los espacios en blanco
        p = Pattern.compile("\\s");
        auxArreglo = p.split(condicion);
        
        //Se cuenta la cantidad de puntos en la primera palabra de la condicion
        String primero = new String(auxArreglo[0]);        
        int puntos = primero.replaceAll("[^\\.]", "").length();
        
        //Si hay uno solo, estamos en presencia del caso mas simple, no hay que modificarlo
        if (puntos == 1) return condicion;

        //Si hay dos puntos, estamos en presencia de un instanceRole
        else if (puntos == 2) {
                
            primero = auxArreglo[0];            
            String resto = condicion.substring(primero.length());
            
            //Se separa la parte izquierda de la condicion
            p = Pattern.compile("\\.");
            auxArreglo = p.split(primero);
            
            //Se busca la infromacion de la asociacion en la tabla
            String a = asociaciones.get(auxArreglo[1]);
            String[] asoc = p.split(a);
            
            //Se devuelve el resultado de la traduccion
            return auxArreglo[0] + "." + asoc[2] + " IN (SELECT a."  + asoc[3] + " FROM " + asoc[1] + " AS a WHERE a." + auxArreglo[2] + " " + resto.trim() + ")";
        }
        
        //Si no se entro en ninguno de los casos, ocurrio un error
        return "ERROR";
    } 

    public  char extraerVariable(String condicion) {
    
        int barra = condicion.indexOf('|');
        String aux = condicion.substring(0,barra).trim();
        
        return aux.charAt(aux.length()-1);
    }
    
    public  String traducirCuantificador(String condicion, String tablaAux) {
    
        if (tablaAux.equals("")) tablaAux = tabla;
    
        int parentesis = condicion.indexOf('(');
        
        String cuantificador = condicion.substring(0,parentesis).trim().toLowerCase();
        String cuerpo = condicion.substring(parentesis).trim().toLowerCase();        
        
        if (cuantificador.equals("exists")) {
        
            int barra = cuerpo.indexOf('|'); 
            char variable = extraerVariable(cuerpo);
        
            return "FOR SOME " + variable + " IN " + tablaAux + " : " + traducir(cuerpo.substring(barra+1,cuerpo.length()-1));        
        }
        else if (cuantificador.equals("forall")) {
        
            int barra = cuerpo.indexOf('|'); 
            char variable = extraerVariable(cuerpo);
        
            return "FOR ALL " + variable + " IN " + tablaAux + " : " + traducir(cuerpo.substring(barra+1,cuerpo.length()-1));       
        }
        else {
            
            int barra = cuerpo.indexOf('|');        
            return cuantificador + " ARE " + traducir(cuerpo.substring(barra+1,cuerpo.length()-1));       
        }
    }
    
    public ModelAndView ejecutarQuery(ModelAndView mv,wizardForm wf ,String sql,
            String paq_tabla) 
            throws NoSuchMethodException, InstantiationException, ClassNotFoundException, IllegalAccessException{
        conexion conex = new conexion();
    if (wf.isComoHashMap()){
        List result = conex.ejecutarQuery(sql);
        mv.addObject("resultado", result);
        mv.setViewName("wizardResult");
        //setSuccessView("wizardResult");
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
        
        return null;
    
    }
    
    
    
}