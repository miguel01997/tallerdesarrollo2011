/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package requsitodifuso;

/**
 *
 * @author wendy
 */
import java.util.*;

public class prueba {

    public static void main(String[] args) {
    
        try {
            
             Asociacion c= new Asociacion();
             HashMap<String,String> mapa = new HashMap<String,String>();
             mapa=c.Asociaciones();
                    
            //
             
             //mapa.put("publica", "usuario.anuncio.codusuario.codusuario");  
            //mapa.put("tienemv", "modelo.vehiculo.codmodelo.codmodelo");
            
           /* int i = Integer.parseInt(args[0]);
            switch (i) {
            
                case 0:*/
                
                   /* String c1 = "select(v | v.precio is bajo and v.color is oscuro)";
                    RequisitoDifuso r1 = new RequisitoDifuso("FR1",new String[] { "bajo", "oscuro" }, new String[] { }, 0.5, new String[] { "placa", "precio" }, "vehiculo", c1, mapa); 
                    System.out.println("\n" + r1.condicion);
                    System.out.println("\n" + r1.traducir()); 
                //    break;*/
                    
               // case 1:
                
                 //   String c2 = "select(v | not v.tienevm.anio is nuevo)";
                   // RequisitoDifuso r2 = new RequisitoDifuso("FR2",new String[] { "nuevo" }, new String[] { }, 0.75, new String[] { "placa" }, "vehiculo", c2, mapa);
                    //System.out.println("\n" + r2.condicion);
                    //System.out.println("\n" + r2.traducir()); 
                  //  break; 
            
           // case 1:
                
                    String c2 = "select( U|U.publica->a_lo_sumo_2(a|  a.codusuario =   cerca_5) )";
                    RequisitoDifuso r2 = new RequisitoDifuso("FR2",new String[] { "viejo, cerca_5" }, new String[] { "AND" }, 0.75, new String[] { "edad" }, "usuario", c2, mapa);
                    System.out.println("\n" + r2.condicion);
                    System.out.println("\n" + r2.traducir()); 
                   // break; 
                    /*
                //case 2:
                
                    String c3 = "select(m | m.tienemv->laMayoria(v | v.color is claro))";
                    RequisitoDifuso r3 = new RequisitoDifuso("FR3",new String[] { "claro", "laMayoria" }, new String[] { }, 10, new String[] { "marca" }, "modelo", c3, mapa);
                    System.out.println("\n" + r3.condicion);
                    System.out.println("\n" + r3.traducir()); 
               //     break;    
                
              //  case 3:
                /*
                    String c4 = "select(m | m.marca is favorita and m.tienemv->laMayoria(v | v.precio is muy bajo fc1 v.color similarA gris))";
                    RequisitoDifuso r4 = new RequisitoDifuso("FR4",new String[] { "favorita", "laMayoria", "muy", "bajo", "similarA", "fc1" }, new String[] { "fc1" }, 0.8, new String[] { "nombre", "marca" }, "modelo", c4, mapa);
                    System.out.println("\n" + r4.condicion);
                    System.out.println("\n" + r4.traducir()); 
                    break;

                default:
                
                    System.out.println("\nSe debe introducir un numero del 0 al 3");
            }*/       
        }
        catch (Exception e) {
        
            System.out.println("\nOcurrio un error. Posiblemente falto indicar el requisito");
            e.printStackTrace();
        }        
    }
}
