<%-- 
    Document   : modelo
    Created on : 21/10/2011, 12:14:13 AM
    Author     : luis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>
<!DOCTYPE html>
<div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>
    <div class="post">
	
	<div class="entry">
            <h1>Modelo</h1>
            

         ${mensaje}
         ${error}
        <table class="tabla">
            <tr>
                <th>Nombre</th>
               <th>Marca</th>
               <th>Año</th>
               
            </tr>
            
           <c:forEach items="${lista}" var="p" >
               <tr>
                 <td>
                     <a href="modificarModelo.htm?id=${p.codmodelo}">${p.nombre}  </a></td>
               <td>${p.marca}</td>
               <td>${p.anio}</td>
               </tr> 
           </c:forEach>
        </table>      
       
      
        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-modelo.html" %>       
<%@include file="../../interfaz/footer.html" %>   
