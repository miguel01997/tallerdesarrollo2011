<%-- 
    Document   : modificarModelo
    Created on : 21/10/2011, 06:47:59 PM
    Author     : luis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>
<script type="text/javascript" src="interfaz/verificaciones.js"></script>
<!DOCTYPE html>
<div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>
    <div class="post">
	
	<div class="entry">
            <h1>Modelo</h1>
            
            <h2 class="title">Modificar Modelo:</h2>
            <br/>
            
             <script>
             Mensaje("${mensaje}");
             Mensaje("${error}")
                </script>
        

        <a href="eliminarModelo.htm?id=${p.codmodelo}" onclick="return confirmar('¿Está seguro que desea eliminar este modelo?')">Eliminar</a>
        <br/></br>
        <form:form action="modificarModelo.htm?id=${p.codmodelo}" commandName="mm" >
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><form:input path="nombre"  value="${p.nombre}" /></td>
                    </td>
                </tr>
                <tr>
                    <td>Marca</td>
                    <td><form:input path="marca" value="${p.marca}"/></td>
                </tr>
                <tr>
                    <td>Año</td>
                    <td>
                        
            <form:select path="anio">
                <form:option value="${p.anio}" label="${p.anio}"/>
                <c:forEach var="i" begin="1945" end="2011">
                   
                   <form:option value="${i}" label="${i}" />
                </c:forEach>       
           </form:select>
                        
                        </td>
                </tr>
                
            </table>
            <br/>
            <input type="submit" value="Modificar" />
            <input type="button" value="Regresar" onclick="window.location='modelo.htm'" />
            
        </form:form>
            <br/>
            
        
        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-modelo.html" %>       
<%@include file="../../interfaz/footer.html" %>   