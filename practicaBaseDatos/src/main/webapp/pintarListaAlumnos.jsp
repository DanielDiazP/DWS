<%-- 
    Document   : verAlumnos
    Created on : 31-oct-2017, 10:30:10
    Author     : daw
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            function cargarAlumno(id, nombre, fecha, mayor) {
            document.getElementById("nombre").value = nombre;
            document.getElementById("idalumno").value = id;
            document.getElementById("fecha").value = fecha;
            document.getElementById("edad").value = mayor;
            }
            function boton(num){
                var opcion=null;
                switch(num){
                    case 1:
                        opcion="alumnos?opcion=insert";
                        break;
                    case 2:
                        opcion="alumnos?opcion=delete";
                        break;
                    case 3:
                        opcion="alumnos?opcion=update";
                        break;
                }
                 document.forms.formulario1.action=opcion;
                 document.forms.formulario1.submit();
            }
           
        </script>
    </head>
    <body>
        <h1>ALUMNOS</h1>

        <table border="1">
            <c:forEach items="${alumnos}" var="alumno">  
                <tr>
                    <td>
                        <input type="button" value="cargar ${alumno.id}" 
                               onclick="cargarAlumno('${alumno.id}',
                                                   '${alumno.nombre}',
                                                   '<fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>',
                               ${alumno.mayor_edad});"/>
                    </td> 
                    <td>
                        ${alumno.nombre}
                    </td>

                    <td>
                        <fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>
                    </td>

                    <td>
                        <input type="checkbox" <c:if test="${alumno.mayor_edad}" >checked</c:if> />
                        </td>
                    </tr>


            </c:forEach> 

        </table>
        <form action="alumnos" name="formulario1" method="POST" >
            <input type="hidden" id="idalumno" name="id" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="fecha" name="fecha" size="12"/>
            <input type="text" id="edad" name="edad" size="12"/>
            <input type="button" value="insertar" onclick="boton(1);"/>
            <input type="button" value="borrar" onclick="boton(2);"/>
            <input type="button" value="cambiar" onclick="boton(3);"/>
           
        </form>

    </body>
</html>
