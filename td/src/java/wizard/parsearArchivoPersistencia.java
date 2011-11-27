/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.persistence.descriptors.FieldsLockingPolicy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author luis
 */
public class parsearArchivoPersistencia {
    
    
    private String archivoPersistencia;

    public parsearArchivoPersistencia(String archivo) {
        if(archivo != null)
            archivoPersistencia = archivo;
    }
    
    
    
    
    
    
    
    
    
    private void parsear(String archivo ) throws ParserConfigurationException, SAXException, IOException{
           // Creamos el builder basado en SAX  
   
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        Document doc = docBuilder.parse (new File(archivo));
        
        doc.getDocumentElement ().normalize ();

       System.out.println ("El elemento raíz es " +

       doc.getDocumentElement().getNodeName());

       
       NodeList listaPersonas = doc.getElementsByTagName("class");

       int totalPersonas = listaPersonas.getLength();

       System.out.println("Número total de personas : " + totalPersonas);
       
       
       for (int i = 0; i < listaPersonas.getLength() ; i ++) {

           System.out.println(listaPersonas.item(i).getTextContent());
           //listaPersonas.item(i)
            //Node p = listaPersonas.item(i)

       }
    }
    
    
    
    
    
    /*Retorna una arraylist con los nombres de las clases*/
    public ArrayList clases() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        //Document doc = docBuilder.parse (new File("/home/wendy/NetBeansProjects/Taller/td/src/conf/persistence.xml"));
        Document doc = docBuilder.parse (new File("/home/luis/NetBeansProjects/tds/td/src/conf/persistence.xml"));
        doc.getDocumentElement ().normalize ();
        
        //Busca los tag class
        NodeList clasesL= doc.getElementsByTagName("class");
        ArrayList clases = new ArrayList();
        
        for (int i = 0; i < clasesL.getLength() ; i ++) {
            clases.add(clasesL.item(i).getTextContent());
       }
        
        return clases;
    }
    
    
    /*Retorna la lista de atributos de una clase*/
    public ArrayList<String> atributosDeClase(String clase) throws ClassNotFoundException{
    
        Class userClass = Class.forName(clase);
        Method[] userMethods = userClass.getDeclaredMethods();
        //System.out.println(clase + "  "+userFields.length);
        ArrayList atributos = new ArrayList();
        
        for(int i = 0; i<userMethods.length;i++){
           // System.out.println(userMethods[i].getName().substring(0, 3));
            String nombre = userMethods[i].getName();
            if(nombre.length()>3 
              && nombre.substring(0,3).equals("get")){
                String att = nombre.substring(3,nombre.length());
                atributos.add(att);
            }
        }
        
        /*for(int i = 0;i<userFields.length;i++){
            //System.out.println(userFields[i].getName());
            
            atributos.add(userFields[i].getName());
            
           // System.out.println(userFields[i].getType());
        }*/
        
    return atributos;
    }
    
    
    /*Busca para todas las clases en el archivo persistence los atributos
     Retorna un hashMap del tipo (<clase>, <listaAtributos>)
     **/
    public HashMap<String,ArrayList<String>> mapaClaseAtributos() 
            throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException{
    
       //Lista de clases
        ArrayList<String>clasesL;
        clasesL = clases();
        if(clasesL == null)
            return null;
        HashMap<String,ArrayList<String>> mapa = new HashMap<String, ArrayList<String>>();
        //busca los atribapaClaseAtributosutos
        for(String clase: clasesL){
           ArrayList<String> atributos = this.atributosDeClase(clase);
            ArrayList<String> put = mapa.put(clase, atributos);
        }
        return mapa;
        
    }
    
    public HashMap<String,ArrayList<String>> ClaseAtributo() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException{
    
    HashMap<String,ArrayList<String>> mapa=new HashMap<String, ArrayList<String>>();
    ArrayList<String>clasesL;
    clasesL = clases();
    
    for(String clase:clasesL){
       
        ArrayList<String> atributos = buscarAtributos(clase);
        mapa.put(clase, atributos);
    }
        
        return mapa;
        
    }
    
    
    
    
    
    /**Busca para una tabla las relaciones*/
    
    public ArrayList<String> buscarRelaciones(String clase) throws ClassNotFoundException{
         
        ArrayList<String> relaciones = new ArrayList();
         
           //trae la lista de atributos de la tabla y su tipo
        Class userClass = Class.forName(clase);
        Field[] userFields = userClass.getDeclaredFields();
        for(int i = 0; i< userFields.length;i++){
            
            String nClase = userFields[i].getType().getSimpleName();
            //System.out.println("++++++"+userFields[i].getType());
            if(nClase.equals("Collection")){
                //Busca clase entre <>
                String paquete = userFields[i].getGenericType().toString();
                int i2 = paquete.indexOf("<");
                int i3 = paquete.indexOf(">");
                String clase2 = paquete.substring(i2+1, i3);
                
                relaciones.add(clase2 + " "+userFields[i].getName());
            }
           
        }
        
        return relaciones;
    }
    
      public ArrayList<String> buscarAtributos(String clase) throws ClassNotFoundException{
         
        ArrayList<String> relaciones = new ArrayList();
         
           //trae la lista de atributos de la tabla y su tipo
        Class userClass = Class.forName(clase);
        Field[] userFields = userClass.getDeclaredFields();
        for(int i = 0; i< userFields.length;i++){
            String nClase = userFields[i].getType().getSimpleName();
            if(!nClase.equals("Collection")){
                relaciones.add(userFields[i].getName());
            }
           
        }
        
        return relaciones;
    }
    
      
    public HashMap<String,ArrayList<String>> mapaClaseRelacion()
            throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException{
        //Lista de clases
        ArrayList<String>clasesL;
        clasesL = clases();
        if(clasesL == null)
            return null;
        HashMap<String,ArrayList<String>> mapa = new HashMap<String, ArrayList<String>>();
        //busca los atribapaClaseAtributosutos
        for(String clase: clasesL){
           ArrayList<String> atributos = this.buscarRelaciones(clase);
            ArrayList<String> put = mapa.put(clase, atributos);
        }
        return mapa;
    
    } 
    
    
    
    
    
    
    
    
    
}
