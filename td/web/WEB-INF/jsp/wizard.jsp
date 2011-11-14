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
         
         //RELACIONES
         var mapR = new Array();
        <c:forEach items="${mapaRelaciones}" var="p" >
             var subarr = new Array();
             <c:forEach items="${p.value}" var="atr" >
                subarr.push("${atr}");                 
             </c:forEach> 
             mapR["${p.key}"] = subarr;
             
        </c:forEach>    


        //CONECTORES
        var mapConec = new Array();
        <c:forEach items="${listaconn}" var="p" >
            mapConec.push(p)
        </c:forEach>
            mapConec.push("AND","OR");


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
        <h2>Insertar</h2>
        <input id="pred" checked="true" type=radio name="selec" value="Predicado"  >Predicado</input>
        <input id ="cuanti" type=radio name="selec" value="Cuantificador">Cuantificador</input>
                    <br/>
                    <button id="Atrasv4_1" onclick="ocultarVentana('v4_1')">Atras</button>
                    <button id="sig_v4_2_1" onclick="verificarRadioBotton('pred','cuanti','v4_1','v4_2_1','v4_2_2')">Siguiente</button>
    </div>
    
    <!-- PREDICADO-->
        <div   id="v4_2_1" hidden="true" >
        <hr/>
        <h2>Predicado</h2>
        
        <form id="form2">
        <table>
            <tr>
                <th>(</th>
                <th>Atributos</th>
                <th>Comparadores</th>
                <th>Modificadores</th>
                <th>Predicados</th>
                <th>)</th>
            </tr>
            <tr>
            
                <td>
                    
                    <input type='checkbox' id="abrepred" name="abrepred" />
                    
                </td>
                
                <td>
                <!-- Atributos -->
                <select id="listaAtt" name="predS" >
                         
                            
                 </select>
                </td>
                <td>
                <!-- Comparadores -->
                <select id="listaAttC" name="predS" >
             
    
                            <c:forEach items="${listacomp}" var="p" >
                               <option value="${p.compname}">${p.compname}</option>
                            </c:forEach>
                 </select>
                </td>
                <td>
                <!-- Modificador -->
                 <select id="listaAttM" name="predS" >
                
                     <option value=" "></option>
                            <c:forEach items="${listamod}" var="p" >
                                
                               <option value="${p.modname}">${p.modname}</option>
                            </c:forEach>
                 </select>
                </td>
                <td>
                <!-- PREDICADOS -->
                <select id="listaAttP" name="predS" >
              
                                   
                            <c:forEach items="${listapred}" var="p" >
                               
                               <option value="${p.predname}">${p.predname}</option>
                            </c:forEach>
                 </select>
                </td>
                <td>
                    
                    <input type='checkbox' id="cierrapred" name="cierrapred" />
                    
                </td>
                
            </tr>
            
            
                
                
          
        </table>
        </form>

                <br/>
        
                     <br/>
                     <button id="atrasv4_2_1" onclick="cambiarVentana('v4_2_1','v4_1')">Atras</button>
                     <button id="agrePred" onclick="agregarTextoPred('textoOCL', 'predS','listatablas')">Agregar Predicado</button>
                     
        </div>
    
    
    <!-- CUANTIFICADOR-->
    <div id="v4_2_2" hidden="true"  >
     <hr/>
    <h2>CUANTIFICADOR</h2>    
        
    
    
    <table id="tablaCuantificador">
        <tr>
            <th>Relaciones</th>
            <th>Cuantificadores</th>
            <th>Variable del cuantificador</th>
            <th>Expresión</th>
        </tr>
        <tr>
            <td><!-- Relaciones -->
                <select id="listaRelCuan" name="lCuan0" onchange ="cambiaCuanti('textCuanti0','lCuan0')" >
                    
               </select>
            </td>
            <td>
                <select id="listaCuan" name="lCuan0" onchange ="cambiaCuanti('textCuanti0','lCuan0')" >
                      <c:forEach items="${listaquan}" var="p" >
                               <option value="${p}">${p}</option>
                      </c:forEach>
                     
               </select>
              
            </td>
            <td><!-- Cuantificadores-->
                <input id="varCuantificador0"  name="lCuan0" type="text" onchange ="cambiaCuanti('textCuanti0','lCuan0')" onkeyup="variableNoNumerica(value,'vCuantificador')"></input>
            </td>
            <td><!-- Texto Cuantificadores-->
                <textarea id="textCuanti0" type="text" disabled="true" cols="60%"> </textarea>
            </td>
        </tr> 
    </table>
        
   
    <div hidden="true" id="vCuantificador" style="color:#FF0000;" >Variable del cuantificador no válida</div>
     
    
    
    <!-- TEXTO QUE SE COPIARA A LA EXPRESION DENTRO DEL CUANTIFICADOR-->
    <input type="text" id="texCuanti0" name="lCuan0" onchange="cambiaCuanti('textCuanti0','lCuan0')"></input>
    <!-- NOMBRE DE LA CLASE DEL ATRIBUTO DEL CUANTIFICADOR -->
    <input type="text" id="classCuan0" ></input>
    
    <!-- VARIABLE DUMMY -->
    <input type="text" id="varDummy0" name="lCuan0" onchange="cambiaCuanti('textCuanti0','lCuan0')"></input>
    
     <button id="atrasv4_2_2" onclick="cambiarVentana('v4_2_2','v4_1')">Atras</button>
     <button id="agreExpreCuan" onclick="crearVentanasRec('recuCuanti')" >Agregar Expresion al cuantificador</button>
     <button id="agreCuan0" disabled="true" onclick="addContenidoCuan('textCuanti0')">Agregar Cuantificador</button>
    </div>
    
    
    <!-- AQUI SE AGREGARAN RECURSIVAMENTE LOS CUANTIFICADORES -->
    <div id="recuCuanti" >
        
    </div>
    
    
    
    
    
    
    
    <button onclick="habilitar('varCuantificador0')" >prueba</button>
    
  </body>
</html>