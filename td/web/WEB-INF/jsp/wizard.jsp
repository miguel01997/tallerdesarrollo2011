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
  
  <body>
    <script language="Javascript">

      var map = new Array();
      var arr1 = ['atr1','atr2'];
      var arr2 = ['atr3','atr4'];
      map['tabla1'] = arr1;
      map['tabla2'] = arr2;
      
      
      function llenarlista(id,tabla){
          var elem = document.getElementById(id);
          var newhtml = "";
          var arr = map[tabla];
          for (var i in arr){
              alert(arr[i]);
              var atr = arr[i];
              newhtml += "<input type='checkbox' name=atr"+i+" value='"+atr+"'>"+atr+"<br>";
          }
          elem.innerHTML = newhtml;
          
      }
      
      function agregarElemento(idtabla,elem){
          var table = document.getElementById(idtabla);
          var row = table.insertRow(-1);
          var cell1 = row.insertCell(0);
          cell1.innerHTML = elem;
          var cell2 = row.insertCell(1);
          cell2.innerHTML = "<input type='button' value='Borrar'>";
      }m.addObject("listaconn",w.buscarConector());
      
      
    </script>
    
    <select id="listatablas" onchange="llenarlista('uno',value)">
        <option value=""></option>
        <option value="tabla1">tabla1</option>
        <option value="tabla2">tabla2</option>
    </select>
    
    <p id ="uno"></p>
    
    
    <table>
        <tr>
            <th>Predicados</th>
            <td>
                
                            <select id="listapred" >
        
                            <option value=""></option>
    
                            <c:forEach items="${listapred}" var="p" >
                               <option value="${p.predname}">${p.predname}</option>
                            </c:forEach>
                
                </select>
                
              
            </td>
            <td><input type="button" value="+"/></td>
            <td>
                <table border="1" id="table_pred"></table>
            </td>
        </tr>
        <tr>
            <th>Comparadores</th>
            <td>
                <select id="sel_comp" onchange="agregarElemento('table_comp',value)">
                    <option value=""> </option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                </select>
            </td>
            <td><input type="button" value="+"/></td>
            <td>
                <table border="1" id="table_comp"></table>
            </td>
        </tr>
        <tr>
            <th>Modificadores</th>
            <td>
                <select id="sel_mod" onchange="agregarElemento('table_mod',value)">
                    <option value=""> </option>
                </select>
            </td>
            <td><input type="button" value="+"/></td>
            <td>
                <table border="1" id="table_mod"></table>
            </td>
        </tr>
        <tr>
            <th>Cuantificadores</th>
            <td>
                <select id="sel_cuant" onchange="agregarElemento('table_cuant',value)">
                    <option value=""> </option>
                </select>
            </td>
            <td><input type="button" value="+"/></td>
            <td>
                <table border="1" id="table_cuant"></table>
            </td>
        </tr>
    </table>
