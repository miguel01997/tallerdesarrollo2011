<%-- 
    Document   : anuncio
    Created on : 21/10/2011, 12:45:17 AM
    Author     : luis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>
<!DOCTYPE html>
<script type="text/javascript" src="interfaz/verificaciones.js"></script>
<div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>
    <div class="post">
	
	<div class="entry">
            <h1>Anuncio</h1>
            
           <script>
             Mensaje("${mensaje}");
                </script>
         <br></br>
        <table class="tabla">
            <tr>
                <th >C&oacute;digo</th>
               <th>Fecha</th>
               <th>Descripci&oacute;n</th>
               <th>Usuario</th>
               <th>Veh&iacute;culo</th>
               
            </tr>
           <c:forEach items="${lista}" var="p" >
               <tr>
               <td><a href="modificarAnuncio.htm?id=${p.codanuncio}">${p.codanuncio}</a></td>
               <td>${p.fecha}</td>
               <td>${p.descripcion}</td>
               <td><a href="modificarUsuario.htm?id=${p.codusuario.codusuario}">${p.codusuario.nombre} ${p.codusuario.apellido}</a></td>
               <td><a href="modificarVehiculo.htm?id=${p.codvehiculo.placa}">${p.codvehiculo.placa}</a></td>
               </tr> 
           </c:forEach>
        </table>    
        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-anuncio.html" %>       
<%@include file="../../interfaz/footer.html" %>  
