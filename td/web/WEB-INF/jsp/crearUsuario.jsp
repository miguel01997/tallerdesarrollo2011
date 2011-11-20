<%-- 
    Document   : ingUsuarioView
    Created on : 20/10/2011, 10:08:09 PM
    Author     : wendy
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
            <h1>Usuario</h1>
            
            <h2 class="title">Crear Usuario:</h2>
            <br/>
        
             <script>
             Mensaje("${mensaje}");
                </script>
            <form:form action="crearUsuario.htm" commandName="cu" >
                <table>
                    <tr>
                        <td>Nombre:</td>
                        <td><form:input path="nombre" /></td>

                        </td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td><form:input path="apellido" /></td>
                    </tr>
                    <tr>
                        <td>Edad:</td>
                        <td><form:input path="edad" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email" /></td>
                    </tr>
                    <tr>


                </table>


                <br/>
                <input type="submit" value="Enviar" />
                <input type="button" value="Regresar" onclick="window.location='usuario.htm'" />

            </form:form>
            <br/>
             
             <script>
             Mensaje("${correcto}");
                </script>
                
        </div>
			
    </div>
		
</div>
              
<%@include file="../../interfaz/sidebar-usuario.html" %>       
<%@include file="../../interfaz/footer.html" %>    