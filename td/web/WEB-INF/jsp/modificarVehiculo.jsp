<%-- 
    Document   : modificarVehiculo
    Created on : 22/10/2011, 03:06:52 AM
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
            <h1>Veh&iacute;culo</h1>
            
            <h2 class="title">Modificar veh&iacute;culo:</h2>
            <br/>

        
 <script>
             Mensaje("${mensaje}");
             Mensaje("${error}")
                </script>

        <a href="eliminarVehiculo.htm?id=${p.placa}" onclick="return confirmar('¿Está seguro que desea eliminar este modelo?')">Eliminar</a> 
        <br/></br>
        <form:form action="modificarVehiculo.htm?id=${p.placa}" commandName="mm" >
            <table>
                <tr>
                    <td>Placa</td>
                    <td><form:input path="placa" value="${p.placa}"/></td>
                    
                    </td>
                </tr>
                <tr>
                    <td>modelo</td>
                    
              
                    
                    <td>
                       <form:select path="codmodelo.codmodelo">
                           <form:option  value="${p.codmodelo.codmodelo}" label="${p.codmodelo.nombre}"/> 
                            <c:forEach items="${lista}" var="p" >
                                <form:option  value="${p.codmodelo}" label="${p.nombre}"/> 
                            </c:forEach>
                        </form:select>  
                    </td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td> <form:input path="precio" value="${p.precio}"/></td>
                </tr>
                 <tr>
                    <td>Motor</td>
                    <td> <form:input path="motor" value="${p.motor}"/></td>
                </tr>
                 <tr>
                    <td>Color</td>
                    <td> 
                         <form:select path="color">
                             <form:option value="${p.color}" label="${p.color}" />  
                             <form:option value="negro" label="negro" />  
                             <form:option value="azul" label="azul" />  
                             <form:option value="blanco" label="blanco" />  
                             <form:option value="rojo" label="rojo" />  
                             <form:option value="amarillo" label="amarillo" />  
                             <form:option value="verde" label="verde" />  
                          </form:select>
                     </td>
                </tr>
                
            </table>
            
            <input type="submit" value="Modificar" />
            <input type="button" value="Regresar" onclick="window.location='vehiculo.htm'" />
            
        </form:form>
            <br/>
            
              

        </div>
			
    </div>
		
</div>
       
<%@include file="../../interfaz/sidebar-vehiculo.html" %>       
<%@include file="../../interfaz/footer.html" %>   
