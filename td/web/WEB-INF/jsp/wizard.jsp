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
</head>
    
    <li><a href="index.htm" accesskey="5" title="">Volver</a></li>
  

  <body>
    
   

          
    
     
      <div id="v0" >   

      
    
    
      <select id="listatablas" onchange="llenarlista('uno',value)" >
        
                          <%-- <option value=""></option> --%>>
    
                            <c:forEach items="${hashMap}" var="p" >
                                 
                             <option value="${p.value}">${p.key}</option>
                             
                            </c:forEach>
                
                </select>
    
    <%--<select id="listatablas" onchange="llenarlista('uno',value)">
        <option value=""></option>
        <option value="tabla1">tabla1</option>
        <option value="tabla2">tabla2</option>
</select> --%>
    
    
    <p id ="uno"></p>
    
    
    <table>
        <tr>
            <th>Predicados</th>
            <td>
                
                            <select id="sel_pred" onchange="agregarElemento('table_pred',value)" >
        
                           <option value=""></option> 
    
                            <c:forEach items="${listapred}" var="p" >
                               <option value="${p.predname}">${p.predname}</option>
                            </c:forEach>
                
                </select>
                
              
            </td>
            
            <td>
                <table border="1" id="table_pred" ></table>
            </td>
        </tr>
        <tr>
            <th>Comparadores</th>
            <td>
                <select id="sel_comp" onchange="agregarElemento('table_comp',value)" >
        
                            <option value=""></option>
    
                            <c:forEach items="${listacomp}" var="p" >
                               <option value="${p.compname}">${p.compname}</option>
                            </c:forEach>
                
                </select>
            </td>
            
            <td>
                <table border="1" id="table_comp" ></table>
            </td>
        </tr>
        <tr>
            <th>Modificadores</th>
            <td>
                  <select id="sel_mod" onchange="agregarElemento('table_mod',value)" >
        
                       <option value=""></option> 
    
                            <c:forEach items="${listamod}" var="p" >
                               <option value="${p.modname}">${p.modname}</option>
                            </c:forEach>
                
                </select>
            </td>
           
            <td>
                <table border="1" id="table_mod"></table>
            </td>
        </tr>
        <tr>
             <th>Conectores</th>
            <td>
                
                            <select id="sel_conn" onchange="agregarElemento('table_conn',value)" >
        
                            <option value=""></option>
    
                            <c:forEach items="${listaconn}" var="p" >
                               <option value="${p.connname}">${p.connname}</option>
                            </c:forEach>
                
                </select>
                
              
            </td>
            
            <td>
                <table border="1" id="table_conn"></table>
            </td>
        </tr>
        <tr>
            <th>Cuantificadores</th>
            <td>
                <select id="sel_cuan" onchange="agregarElemento('table_cuant',value)" >
        
                            <option value=""></option>
    
                            <c:forEach items="${listaquan}" var="p" >
                               <option value="${p.quanname}">${p.quanname}</option>
                            </c:forEach>
                
                </select>
            </td>
        
            <td>
                <table border="1" id="table_cuant"></table>
            </td>
        </tr>
    </table>
    
    <button onclick="cambiarVentana('v0', 'marcoTextoOCL')">Siguiente</button>
    
</div>
    
    <br/>
    <hr/>
    
    <div id="marcoTextoOCL" hidden="true">
        
        
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
                 <option value="AND">AND</option>
                 <option value="OR">OR</option>
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
                    <button id="sig_v4_2_1" onclick="verificarRadioBotton('pred','cuanti')">Siguiente</button>
    </div>
    
    <!-- PREDICADO-->
        <div   id="v4_2_1" hidden="true" >
        <hr/>
        <h2>Predicado</h2>
                <!-- Atributos -->
                <select id="listaAtt" name="predS" >
                 <option value="ATT1">ATT1</option>
                 <option value="ATT2">ATT2</option>
                 </select>
                <!-- Comparadores -->
                <select id="listaAtt" name="predS" >
                 <option value="CMP1">CMP1</option>
                 <option value="CMP2">CMP2</option>
                 </select>
                
                <!-- Modificador -->
                 <select id="listaAtt" name="predS" >
                 <option value="MOD1">MOD1</option>
                 <option value="MOD2">MOD2</option>
                 </select>
                
                <!-- PREDICADOS -->
                <select id="listaAtt" name="predS" >
                 <option value="PRED1">PRED1</option>
                 <option value="PRED2">PRED2</option>
                 </select>
                <br/>
                
                
                
        
                     <br/>
                     <button id="atrasv4_2_1" onclick="cambiarVentana('v4_2_1','v4_1')('v4_1')">Atras</button>
                     <button id="agrePred" onclick="agregarTextoPred('textoOCL', 'predS','Tabla')">Agregar Predicado</button>
        
        </div>
  </body>
</html>