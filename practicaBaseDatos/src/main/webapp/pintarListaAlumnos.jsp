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
                var nombre=document.getElementById("nombre").value;
                var id=document.getElementById("idalumno").value;
                var fecha=document.getElementById("fecha").value;
                var edad=document.getElementById("edad").value;
                var opcion=null;
                switch(num){
                    case 1:
                        opcion="insert";
                        break;
                    case 2:
                        opcion="delete";
                        break;
                    case 3:
                        opcion="update";
                        break;
                    case 4:
                        opcion="select";
                        break;
                }
                action(opcion,id,nombre,fecha,edad);
            }
            function action(opcion,id,nombre,fecha,edad){
                
                document.forms.formulario1.action="alumnos?opcion="+opcion+"&id="+id+
                        "&nombre="+nombre+"&fecha="+fecha+"&edad="+edad;
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
        <form action="" name="formulario1" >
            <input type="hidden" id="idalumno" />
            <input type="text" id="nombre" size="12"/>
            <input type="text" id="fecha" size="12"/>
            <input type="text" id="edad" size="12"/>
            <input type="submit" value="insertar" onclick="boton(1);"/>
            <input type="submit" value="borrar" onclick="boton(2);"/>
            <input type="submit" value="cambiar" onclick="boton(3);"/>
        </form>

    </body>
</html>
