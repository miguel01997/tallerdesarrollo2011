<%-- 
    Document   : modificarAnuncio
    Created on : 22/10/2011, 05:38:56 PM
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
            
            <h2 class="title">Modificar Anuncio:</h2>
            <br/>
        
             ${error}
             ${mensaje}

        
         <a href="eliminarAnuncio.htm?id=${p.codanuncio}" >Eliminar</a> 
        <form:form action="modificarAnuncio.htm?id=${p.codanuncio}" commandName="ma" >
            <table>
                <tr>
                    <td>Fecha</td>
                    <td><form:input path="fecha" value="${fechaA}"/> (dd/mm/yyyy)</td>
                    
                    </td>
                </tr>
                <tr>
                     <td>Descripcion</td>
                    <td><form:input path="descripcion" value="${p.descripcion}"/></td>
                    
                    </td>
                </tr>
                <tr>
                    <td>Usuario</td>
                    
                    
                    <td>
                        <form:select path="codusuario.codusuario" id="codigoUsuario" >
                            <%--<form:option  value="${p.codusuario.codusuario}" label="${p.codusuario.nombre}"/> --%>
                            <c:forEach items="${lista}" var="l" >
                                <form:option  value="${l.codusuario}" label="${l.nombre}"/> 
                            </c:forEach>
                        </form:select>  
                    </td>
                </tr>
                <tr>
                       <td>Vehiculo</td>
                    
                    
                    <td>
                        <form:select path="codvehiculo.placa" id="codigoVehiculo">
                           <%--  <form:option  value="${pp.codvehiculo.placa}" label="${pp.codvehiculo.placa}"/> --%>
                            <c:forEach items="${listaV}" var="ll" >
                                <form:option  value="${ll.placa}" label="${ll.placa}"/> 
                            </c:forEach>
                        </form:select>  
                    </td>
                </tr>
                <tr>
            </table>
            
            <input type="submit" value="Modificar" />
            <input type="button" value="Regresar" onclick="window.location='anuncio.htm'" />
            
        </form:form>
            <br/>
            
            
              
              <script>
                  document.getElementById("codigoUsuario").value = "${p.codusuario.codusuario}";
                  document.getElementById("codigoVehiculo").value = "${p.codvehiculo.placa}";
              </script>
       </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-anuncio.html" %>       
<%@include file="../../interfaz/footer.html" %>   