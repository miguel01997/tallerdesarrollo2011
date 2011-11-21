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
    <body style="background:#909EB4">
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
            mapConec.push("${p.connname}")
        </c:forEach>
            mapConec.push("AND","OR");
            
        //Comparadores
        var mapComp = new Array();
        <c:forEach items="${listacomp}" var="p" >
            mapComp.push("${p.compname}");
        </c:forEach>
            
        //Modificadores
        var mapMod = new Array();
        <c:forEach items="${listamod}" var="p" >
            mapMod.push("${p.modname}");
        </c:forEach>
            
        //Predicados
        var mapPred = new Array();
        <c:forEach items="${listapred}" var="p" >
            mapPred.push("${p.predname}");
        </c:forEach>
            
        var mapTerm = mapComp.concat(mapMod, mapPred);
        //alert(mapTerm);
    </script>
</head>
     <input type="button" value="Regresar" onclick="window.location='index.htm'" />
    
  

  <body>
      <form:form action="wizardResult.htm" commandName="resultw" id="form">
          <input type="hidden" name="conectoresA" id="conectoresA" />
          <input type="hidden" name="terminos" id="terminos" />
      <div id="v0" >   

      <select id="listatablas" onchange="llenarlista('uno', value)" >
                            <option value=""></option>
                            <c:forEach items="${hashMap}" var="p" >
                             <option value="${p.key}">${p.key}</option>
                            </c:forEach>
                
      </select>
          <input type="hidden" name="tabla" id="tabla" value="" />
    
<!--          <form id="form1">-->
    
    <p id ="uno"></p>
    
<!--          </form> -->
    
    <button onclick="atributosSeleccionado('v0', 'marcoTextoOCL');return false;">Siguiente</button>

</div>



    
</div>

    
    <br/>
    <hr/>
    

    <div id="marcoTextoOCL" hidden="true" >


        
    <!-- border-width: 2px; border-style: dotted; border-color: red;-->    
        <div id ="listaPredicado" style="
            position:relative;top:0px;right:30%;
            float: right; height: 300px; width: 400px ;
            ">
            <table id="listaPredicados">
               
            </table>
           
       </div> 
  
        
        <h2>OCL</h2>
        <!-- AREA DONDE SE COLOCA EL TEXTO OCL-->
        <textarea rows="10" cols="60%" id="textoOCL"   disabled="true"    >SELECT ()</textarea>
        <input type="hidden" name="condicion" id="condicion" value="" />
        <br/>

       <button id="agregarOCL" onclick="necesitaConector('v4_2_1','v4_1','v4_0'); return false;">Agregar elemento</button>
        
       
            
         

       
       
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
          <button id="sig_v4_2_1v1" onclick="ocultarVentana('v4_0');return false;" >Atras</button>
          <button id="sig_v4_2_1v2" onclick="cambiarVentana('v4_0','v4_1');return false;" >Siguiente</button>
        
    </div>
    
    <div   id="v4_1" hidden="true" >
        <hr/>
        <h2>Insertar</h2>
        <input id="pred" checked="true" type=radio name="selec" value="Predicado"  >Predicado</input>
        <input id ="cuanti" type=radio name="selec" value="Cuantificador">Cuantificador</input>
                    <br/>
                    <button id="Atrasv4_1" onclick="ocultarVentana('v4_1');return false;">Atras</button>
                    <button id="sig_v4_2_1" onclick="verificarRadioBotton('pred','cuanti','v4_1','v4_2_1','v4_2_2');return false;">Siguiente</button>
    </div>
    
    <!-- PREDICADO-->
        <div   id="v4_2_1" hidden="true" >
        <hr/>
        <h2>Predicado</h2>
        
<!--        <form id="form2">-->
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
                <select id="listaAttC" name="predS" 
                        onchange="verifComp(value,'listaAttM')" >
             
                    <option value=" "></option>
                    <option value="=">=</option>
                            <c:forEach items="${listacomp}" var="p" >
                               <option value="${p.compname}">${p.compname}</option>
                               
                            </c:forEach>
                 </select>
                </td>
                <td>
                <!-- Modificador -->
                <select id="listaAttM" name="predS" 
                        onchange="verifMod(value,'listaAttC')" >
                 
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
<!--        </form>-->

                <br/>
        
                     <br/>
                     <button id="atrasv4_2_1" onclick="cambiarVentana('v4_2_1','v4_1');return false;">Atras</button>
                     <button id="agrePred" onclick="agregarTextoPred('textoOCL', 'predS','listatablas');return false;">Agregar Predicado</button>
                     
        </div>
    
    
    <!-- CUANTIFICADOR-->
    <div id="v4_2_2" hidden="true"  >
     <hr/>
    <h2>CUANTIFICADOR</h2>    
        
    
<!--    <form id="form3">-->
    <table id="tablaCuantificador">
        <tr>
            <th>(</th>
            <th>Relaciones</th>
            <th>Cuantificadores</th>
            <th>Variable del cuantificador</th>
            <th>Expresión</th>
           
        
            <th>)</th>
        </tr>
        <tr>
            <td>  <input type='checkbox' id="abrepredi" name="abrepredi" /> </td> 
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
            
              <td>  <input type='checkbox' id="predicierra" name="predicierra" /> </td> 
        </tr> 
        
           
    </table>
<!--           </form>-->
        
   
    <div hidden="true" id="vCuantificador" style="color:#FF0000;" >Variable del cuantificador no válida</div>
     
    
    
    <!-- TEXTO QUE SE COPIARA A LA EXPRESION DENTRO DEL CUANTIFICADOR-->
    <input hidden="true" type="text" id="texCuanti0" name="lCuan0" onchange="cambiaCuanti('textCuanti0','lCuan0')"></input>
    <!-- NOMBRE DE LA CLASE DEL ATRIBUTO DEL CUANTIFICADOR -->
    <input hidden="true" type="text" id="classCuan0" ></input>
    
    <!-- VARIABLE DUMMY -->
    <input hidden="true" type="text" id="varDummy0" name="lCuan0" onchange="cambiaCuanti('textCuanti0','lCuan0')"></input>
    
     <button id="atrasv4_2_2" onclick="cambiarVentana('v4_2_2','v4_1');return false;">Atras</button>
     <button id="agreExpreCuan" onclick="crearVentanasRec('recuCuanti');return false;" >Agregar Expresion al cuantificador</button>
     <button id="agreCuan0" disabled="true" onclick="addContenidoCuan('textCuanti0');return false;">Agregar Cuantificador</button>
    </div>
    
    
    <!-- AQUI SE AGREGARAN RECURSIVAMENTE LOS CUANTIFICADORES -->
    <div hydden="true" id="recuCuanti" >
        
    </div>
    
    
    
    <script language="Javascript">
       // alert(document.getElementById("listaPredicado"));
    </script>
    
    
    
    <button hidden="true" onclick="habilitar('varCuantificador0');return false;" >prueba</button>
    
    <input type="submit" value="Consultar" onclick="pasarValores()" />
    </form:form> 
  </body>
</html>