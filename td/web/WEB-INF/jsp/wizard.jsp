<%-- 
    Document   : wizard
    Created on : 05/11/2011, 01:38:09 PM
    Author     : wendy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
   <script type="text/javascript" src="wizard.js"></script>
   
    <script type="text/javascript">
        var map = new Array();
        <c:forEach items="${hashMapAtri}" var="p" >
                                 
          
             var subarr = new Array();
             <c:forEach items="${p.value}" var="atr" >
                subarr.push("${atr}");                 
                
                             

             </c:forEach> 
             map["${p.key}"] = subarr;
             
        </c:forEach>
        
        var mapA = new Array();
        <c:forEach items="${hashMap}" var="p" >
                                 
          
             var subarr = new Array();
             <c:forEach items="${p.value}" var="atr" >
                subarr.push("${atr}");                 
                
                             

             </c:forEach> 
             mapA["${p.key}"] = subarr;
             
        </c:forEach>
    </script>
</head>
    
    <li><a href="index.htm" accesskey="5" title="">Volver</a></li>
  

  <body>
   
      <div id="v0" >   

      <select id="listatablas" onchange="llenarlista('uno', value)" >
                            <option value=""></option>
                            <c:forEach items="${hashMap}" var="p" >
                                 
                             <option value="${p.key}">${p.key}</option>
                             

                            </c:forEach>
                
                </select>
    
    
          <form id="form1">
    
    <p id ="uno"></p>
    
          </form>
    
    <button onclick="atributosSeleccionado('v0', 'marcoTextoOCL')">Siguiente</button>

</div>



    
</div>

    
    <br/>
    <hr/>
    

    <div id="marcoTextoOCL" hidden="true" >


        
        
        <div id ="listaPredicado" style="border-width: 2px; border-style: dotted; border-color: red;
            position:relative;top:0px;right:30%;
            float: right; height: 300px; width: 400px ;
            ">
            <table id="listaPredicados">
               
            </table>
           
       </div> 
        
        <h2>OCL</h2>
        <!-- AREA DONDE SE COLOCA EL TEXTO OCL-->
       <textarea rows="10" cols="60%" id="textoOCL"   disabled="true"    >SELECT ()</textarea>
        <br/>

       <button id="agregarOCL" onclick="necesitaConector('v4_2_1','v4_1','v4_0')">Agregar elemento</button>
        
       
            
         

       
       
    </div>
    
    
    
    <div id="v4_0" hidden="true">
        <hr/>
        <h2>CONECTOR</h2>
          
          <select id="conector"  >
                <option >AND</option>
                <option >OR</option>
    
                            <c:forEach items="${listaconn}" var="p" >
                               <option value="${p.connname}">${p.connname}</option>
                            </c:forEach>
          </select>
          <br/>
          <button id="sig_v4_2_1v1" onclick="ocultarVentana('v4_0')" >Atras</button>
          <button id="sig_v4_2_1v2" onclick="cambiarVentana('v4_0','v4_1')" >Siguiente</button>
        
    </div>
    
    <div   id="v4_1" hidden="true" >
        <hr/>
        <h2>Insertarr</h2>
        <input id="pred" checked="true" type=radio name="selec" value="Predicado"  >Predicado</input>
        <input id ="cuanti" type=radio name="selec" value="Cuantificador">Cuantificador</input>
                    <br/>
                    <button id="Atrasv4_1" onclick="ocultarVentana('v4_1')">Atras</button>
                    <button id="sig_v4_2_1" onclick="verificarRadioBotton('pred','cuanti')">Siguiente</button>
    </div>
    
    <!-- PREDICADO-->
        <div   id="v4_2_1" hidden="true" >
        <hr/>
        <h2>Predicado</h2>
        <table>
            <tr>
                <th>Atributos</th>
                <th>Comparadores</th>
                <th>Modificadores</th>
                <th>Predicados</th>
            </tr>
            <tr>
                <td>
                <!-- Atributos -->
                <select id="listaAtt" name="predS" >
                         
                            
                 </select>
                </td>
                <td>
                <!-- Comparadores -->
                <select id="listaAtt" name="predS" >
             
    
                            <c:forEach items="${listacomp}" var="p" >
                      
                               <option value="${p.compname}">${p.compname}</option>
                            </c:forEach>
                 </select>
                </td>
                <td>
                <!-- Modificador -->
                 <select id="listaAtt" name="predS" >
                
                     <option value=" "></option>
                            <c:forEach items="${listamod}" var="p" >
                                
                               <option value="${p.modname}">${p.modname}</option>
                            </c:forEach>
                 </select>
                </td>
                <td>
                <!-- PREDICADOS -->
                <select id="listaAtt" name="predS" >
              
                                   
                            <c:forEach items="${listapred}" var="p" >
                               
                               <option value="${p.predname}">${p.predname}</option>
                            </c:forEach>
                 </select>
                </td>
            </tr>
        </table>
                <br/>
        
                     <br/>
                     <button id="atrasv4_2_1" onclick="cambiarVentana('v4_2_1','v4_1')('v4_1')">Atras</button>
                     <button id="agrePred" onclick="agregarTextoPred('textoOCL', 'predS','listatablas')">Agregar Predicado</button>
        
        </div>
  </body>
</html>