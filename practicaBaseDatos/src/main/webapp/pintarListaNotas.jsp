<%-- 
    Document   : pintarListaNotas
    Created on : 19-nov-2017, 20:15:17
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
        <script>
            function cargarAlumno(id, nombre) {
                document.getElementById("nombreAl").value = nombre;
                document.getElementById("idalumno").value = id;
            }
            function cargarAsignatura(id, nombre) {
                document.getElementById("idasignatura").value = id;
                document.getElementById("nombreAs").value = nombre;
            }

            function boton(num) {
                var opcion = null;
                switch (num) {
                    case 1:
                        opcion = "notas?opcion=insert";
                        break;
                    case 2:
                        opcion = "notas?opcion=delete";
                        break;
                    case 3:
                        opcion = "notas?opcion=update";
                        break;
                    case 4:
                        opcion = "notas?opcion=select";
                        break;
                }
                document.forms.formulario1.action = opcion;
                document.forms.formulario1.submit();
            }
        </script>
    </head>
    <body>
        <h1>Notas</h1>

        <span>Alumno: </span>
        <select id="alumno" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
            <option disabled selected>alumno</option>
            <c:forEach items="${alumnos}" var="alumno">
                <option value="${alumno.id}" name="${alumno.nombre}">${alumno.nombre}</option>
            </c:forEach>
        </select>

        <span>Asignatura: </span>
        <select id="asignatura" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
            <option disabled selected>asignatura</option>
            <c:forEach items="${asignaturas}" var="asignatura">
                <option value="${asignatura.id}">${asignatura.nombre}</option>
            </c:forEach>
        </select>

        <form action="notas" name="formulario1" method="POST" >
            <table>
                <tr>
                    <td>
                        ALUMNO
                        <br>
                        <input type="hidden" id="idalumno" name="idAlumno"  value="${idAlumno}">
                        <input type="text" id="nombreAl" name="nombreAlumno"  value="${nombreAlumno}">
                    </td>
                    <td>
                        ASIGNATURA
                        <br>
                        <input type="hidden" id="idasignatura" name="idAsignatura"  value="${idAsignatura}">
                        <input type="text" id="nombreAs" name="nombreAsignatura"  value="${nombreAsignatura}">
                    </td>
                    <td>


                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        NOTA <input type="text" value="${nota.nota}" id="nota" name="nota" size="1">
                    </td>
                    <td>
                        <br>
                        <input type="hidden" id="accion" name="accion" value="">
                        <input type="button" value="insertar" onclick="boton(1);"/>
                        <input type="button" value="borrar" onclick="boton(2);"/>
                        <input type="button" value="cambiar" onclick="boton(3);"/>
                        <input type="button" value="select" onclick="boton(4);"/>
                    </td>
                </tr>
            </table>
        </form>








    </body>
</html>
