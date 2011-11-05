<%-- 
    Document   : modificarUsuario
    Created on : 22/10/2011, 02:40:11 PM
    Author     : wendy
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
            
            <h2 class="title">Modificar Usuario:</h2>
            <br/>
        
             
              ${error}
              ${mensaje}

         <a href="eliminarUsuario.htm?id=${p.codusuario}" >Eliminar</a> 
         <br/><br/>
       
        <form:form action="modificarUsuario.htm?id=${p.codusuario}" commandName="mu" >
             <table>
                <tr>
                    <td>Nombre</td>
                    <td><form:input path="nombre"  value="${p.nombre}" /></td>
                    </td>
                </tr>
                <tr>
                    <td>Apellido</td>
                    <td><form:input path="apellido" value="${p.apellido}"/></td>
                </tr>
                <tr>
                     <td>Edad</td>
                    <td><form:input path="edad" value="${p.edad}"/></td>
                </tr>
                <tr>
                     <td>Email</td>
                    <td><form:input path="email" value="${p.email}"/></td>
                </tr>
                <tr>
               
            </table>
            
            <input type="submit" value="Modificar" />
            <input type="button" value="Regresar" onclick="window.location='usuario.htm'" />
            
        </form:form>
            <br/>
           
        
        </div>
			
    </div>
		
</div>
              
<%@include file="../../interfaz/sidebar-usuario.html" %>       
<%@include file="../../interfaz/footer.html" %>    