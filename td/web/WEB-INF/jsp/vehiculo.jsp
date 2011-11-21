<%-- 
    Document   : vehiculo
    Created on : 20/10/2011, 11:45:23 PM
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<script type="text/javascript" src="interfaz/verificaciones.js"></script>
<div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>
    <div class="post">
	
	<div class="entry">
            <h1>Veh&iacute;culos</h1>
        
        <br/>
               
 <script>
             Mensaje("${mensaje}");
             
                </script>
        <table class="tabla">
            <tr>
               <th>Placa</th>
               <th>Color</th>
               <th>Precio</th>
               <th>Motor</th>
               <th>Modelo</th>
               <th>Foto</th>
            </tr>
           <c:forEach items="${lista}" var="p" >
               <tr>
                   <td><a href="modificarVehiculo.htm?id=${p.placa}"> ${p.placa}</a></td>
               <td>${p.color}</td>
               <td>${p.precio}</td>
               <td>${p.motor}</td>
               <td><a href="modificarModelo.htm?id=${p.codmodelo.codmodelo}">${p.codmodelo.nombre}</a></td>
               <td>${p.foto}</td>
               
               </tr> 
           </c:forEach>
        </table>  
        
        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-vehiculo.html" %>       
<%@include file="../../interfaz/footer.html" %>   
