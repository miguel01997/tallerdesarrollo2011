<%-- 
    Document   : crearAnuncio
    Created on : 22/10/2011, 11:05:36 AM
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
            <h1>Anuncio</h1>
            
            <h2 class="title">Crear Anuncio:</h2>
            <br/>
            
             ${error}
             ${mensaje}

        <form:form action="crearAnuncio.htm" commandName="ca" >
            <table>
                <tr>
                    <td>Fecha</td>
                    <td><form:input path="fecha" /> (dd/mm/yyyy)</td>
                    
                    </td>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td><form:input path="descripcion" /></td>
                </tr>
                <tr>
               <td>Usuario</td>
                    
                    
                    <td>
                        <form:select path="codusuario.codusuario">
                            
                            <c:forEach items="${lista}" var="p" >
                                <form:option  value="${p.codusuario}" label="${p.nombre} ${p.apellido}"/> 
                            </c:forEach>
                        </form:select>    
                    </td>
                </tr>
                <tr>
                <td>Vehiculo</td>
                    
                    
                    <td>
                        <form:select path="codvehiculo.placa">
                            
                            <c:forEach items="${listaV}" var="p" >
                                <form:option  value="${p.placa}" label="${p.placa}"/> 
                            </c:forEach>
                        </form:select>    
                    </td>
                </tr>
                <tr>
            </table>
            
            
            <br/>
            <input type="submit" value="Insertar" />
            <input type="button" value="Regresar" onclick="window.location='anuncio.htm'" />
            
        </form:form>
            <br/>
             
                
       </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-anuncio.html" %>       
<%@include file="../../interfaz/footer.html" %>   