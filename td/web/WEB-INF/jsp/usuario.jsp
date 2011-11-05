<%-- 
    Document   : usuario
    Created on : 07/10/2011, 09:05:27 PM
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
            <h1>Usuario</h1>   
                
               ${mensaje}
         
            <table class="tabla">
                <tr>
                   <th>Nombre</th>
                   <th>Apellido</th>
                   <th>Edad</th>
                   <th>Email</th>
                   <th>Tipo</th>
                </tr>
                  <c:forEach items="${lista}" var="p" >
                   <tr>
                     <td>
                         <a href="modificarUsuario.htm?id=${p.codusuario}">${p.nombre}  </a></td>

                   <td>${p.apellido}</td>
                   <td>${p.edad}</td>
                   <td>${p.email}</td>
                   </tr> 
               </c:forEach>
            </table>
                
        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-usuario.html" %>       
<%@include file="../../interfaz/footer.html" %>    
        
    

