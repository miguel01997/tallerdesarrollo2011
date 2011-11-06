/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
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
        Document doc = docBuilder.parse (new File(archivoPersistencia));
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
        Field[] userFields = userClass.getDeclaredFields();
        Method[] userMethods = userClass.getDeclaredMethods();
        //System.out.println(clase + "  "+userFields.length);
        ArrayList atributos = new ArrayList();
        
        for(int i = 0; i<userMethods.length;i++){
            System.out.println(userMethods[i].getName().substring(0, 3));
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
        //busca los atributos
        for(String clase: clasesL){
           ArrayList<String> atributos = this.atributosDeClase(clase);
            ArrayList<String> put = mapa.put(clase, atributos);
        }
        return mapa;
        
    }
    
    
    
    
    
    
    
    
    
    
    
}