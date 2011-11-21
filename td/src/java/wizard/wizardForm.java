/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

/**
 *
 * @author jose
 */
public class wizardForm {
    
    //El nombre del requisito
    private String nombre;
    
    //Todos los terminos difusos involucrados con el requerimiento
    private String[] terminos;
    private String[] conectoresA;
    
    //La calibracion del resultado (Cuantitativa: > 1 | Cualitativa: < 1)
    private String calibracion;

    public String getCalibracion() {
        return calibracion;
    }

    public void setCalibracion(String calibracion) {
        this.calibracion = calibracion;
    }
    
    //Los atributos de la tabla presentes en la operacion collect
    private String[] columnas;
    
    //La tabla asociada al requisito
    private String tabla;
    
    //La condicion difusa asociada al requisito
    private String condicion;
    
    
    //
    private boolean comoHashMap = false;

    public wizardForm() {
    }

    

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String[] getConectoresA() {
        return conectoresA;
    }

    public void setConectoresA(String[] conectoresA) {
        this.conectoresA = conectoresA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String[] getTerminos() {
        return terminos;
    }

    public void setTerminos(String[] terminos) {
        this.terminos = terminos;
    }

    public boolean isComoHashMap() {
        return comoHashMap;
    }

    public void setComoHashMap(boolean comoHashMap) {
        this.comoHashMap = comoHashMap;
    }
    
    
}
