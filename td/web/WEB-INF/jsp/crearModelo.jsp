<%-- 
    Document   : crearModelo
    Created on : 21/10/2011, 02:41:39 PM
    Author     : luis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../interfaz/inicio.html" %>
<!DOCTYPE html>
<script type="text/javascript" src="interfaz/verificaciones.js"></script>
<div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div>
    <div class="post">
	
	<div class="entry">
            <h1>Modelo</h1>
            
            <h2 class="title">Crear Modelo:</h2>
            <br/>
            
           <script>
             Mensaje("${mensaje}");
             Mensaje("${error}")
                </script>
          
                <form:form   action="crearModelo.htm" commandName="mm"> 
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><form:input path="nombre" /></td>
                    
                </tr>
                <tr>
                    <td>Marca</td>
                    <td><form:input path="marca" /></td>
                </tr>
                <tr>
                    <td>Año</td>
                    <td>
                        
            <form:select path="anio">
                <c:forEach var="i" begin="1945" end="2011">
                   
                   <form:option value="${i}" label="${i}" />
                </c:forEach>       
           </form:select>
                        
                        </td>
                </tr>
                
            </table>
            
            <br />
            
            <input type="submit" value="Crear"  />
            <input type="button" value="Regresar" onclick="window.location='modelo.htm'" />
            
        </form:form>
            <br/>
              
                
       </div>
			
    </div>
		
</div>
       
             
<%@include file="../../interfaz/sidebar-modelo.html" %>       
<%@include file="../../interfaz/footer.html" %>   