<%-- 
    Document   : pintarListaAsignaturas
    Created on : 13-nov-2017, 10:28:17
    Author     : daw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignaturas</title>
        <script>
            function cargarAsignatura(id, nombre, ciclo, curso) {
                document.getElementById("idasignatura").value = id;
                document.getElementById("nombre").value = nombre;
                document.getElementById("ciclo").value = ciclo;
                document.getElementById("curso").value = curso;
            }

            function boton(num) {
                var opcion = null;
                switch (num) {
                    case 1:
                        opcion = "asignaturas?opcion=insert";
                        break;
                    case 2:
                        opcion = "asignaturas?opcion=delete";
                        break;
                    case 3:
                        opcion = "asignaturas?opcion=update";
                        break;
                }
                document.forms.formulario1.action = opcion;
                document.forms.formulario1.submit();
            }
        </script>
    </head>
    <body>
        <h1>Asignaturas</h1>

        <table border="1">
            <c:forEach items="${asignaturas}" var="asignatura">
                <tr>
                    <td><input type="button" value="cargar ${asignatura.id}" onclick="cargarAsignatura('${asignatura.id}', '${asignatura.nombre}', '${asignatura.ciclo}', '${asignatura.curso}')"></td>
                    <td>${asignatura.nombre}</td>
                    <td>${asignatura.ciclo}</td>
                    <td>${asignatura.curso}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="asignaturas" name="formulario1" method="POST" >
            <input type="hidden" id="idasignatura" name="id" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="ciclo" name="ciclo" size="12"/>
            <input type="text" id="curso" name="curso" size="12"/>
            <input type="button" value="insertar" onclick="boton(1);"/>
            <input type="button" value="borrar" onclick="boton(2);"/>
            <input type="button" value="cambiar" onclick="boton(3);"/>

        </form>
        <c:if test="${foreign}">
            <script>
                var seguir = null;
                seguir = confirm("La asignatura tiene nota \n¿Borrar?");
                if (seguir === true) {
                    document.getElementById("nombre").value = "${asignatura.nombre}";
                    document.getElementById("idasignatura").value = ${asignatura.id};
                    document.getElementById("ciclo").value = '${asignatura.ciclo}';
                    document.getElementById("curso").value = '${asignatura.curso}';
                    document.forms.formulario1.action = "asignaturas?opcion=total";
                    document.forms.formulario1.submit();
                }
            </script>
        </c:if>
    </body>
</html>
