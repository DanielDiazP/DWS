<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
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
                        opcion = "notasMysqliDB.php?opcion=insert";
                        break;
                    case 2:
                        opcion = "notasMysqliDB.php?opcion=delete";
                        break;
                    case 3:
                        opcion = "notasMysqliDB.php?opcion=update";
                        break;
                    case 4:
                        opcion = "notasMysqliDB.php?opcion=select";
                        break;
                }
                document.forms.formulario1.action = opcion;
                document.forms.formulario1.submit();
            }
        </script>
    </head>
    <body>
        <?php
        require_once 'vendor/autoload.php';
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";
        $controllerOpcion;
        $controllerNombreAlumno;
        $controllerNombreAsignatura;
        $controllerIdAlumno;
        $controllerIdAsignatura;
        $controllerNota;
        $sql;
        $statement;



        //----------------------Controller Inicio----------------------
        if (isset($_REQUEST["opcion"])) {
            $controllerOpcion = $_REQUEST["opcion"];
            $controllerNombreAlumno = $_REQUEST["nombreAlumno"];
            $controllerNombreAsignatura = $_REQUEST["nombreAsignatura"];
            $controllerIdAlumno = $_REQUEST["idAlumno"];
            $controllerIdAsignatura = $_REQUEST["idAsignatura"];
            $controllerNota = $_REQUEST["nota"];
        }
        //----------------------Controller Fin----------------------
        //-----------------------DAO Inicio----------------------------
        //crear conexion
        try {
            $conn = new MysqliDb($servername, $username, $password, $database);
            $conn->autoReconnect = false;
            //checkear conexion
        } catch (Exception $ex) {
            echo "Error al conectar la BD " . $ex->getMessage() . "<br>"; //// mirar este apartado!!
            die();
        }



        switch ($controllerOpcion) {
            case "insert":
                $parametros = Array($controllerIdAlumno, $controllerIdAsignatura, $controllerNota);
                $statement = $conn->rawQuery("INSERT INTO NOTAS (ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)", $parametros);
                if ($statement->getLastErrno() === 0) {
                    $statement->commit();
                } else {
                    echo 'Fallo al insertar ' . $statement->getLastError() . "<br>";
                    $statement->rollback();
                    die();
                }
                break;

            case "delete":
                $parametros = Array($controllerIdAlumno, $controllerIdAsignatura);
                $statement = $conn->prepare("delete from NOTAS where ID_ALUMNO = ? AND ID_ASIGNATURA = ?", $parametros);
                if ($statement->getLastErrno() === 0) {
                    $statement->commit();
                } else {
                    echo 'Fallo al borrar ' . $statement->getLastError() . "<br>";
                    $statement->rollback();
                    die();
                }
                break;

            case "update":
                $parametros = Array($controllerNota, $controllerIdAlumno, $controllerIdAsignatura);
                $statement = $conn->prepare("update NOTAS set NOTA=? where ID_ALUMNO=? AND ID_ASIGNATURA=?", $parametros);
                if ($statement->getLastErrno() === 0) {
                    $statement->commit();
                } else {
                    echo 'Fallo al actualizar ' . $statement->getLastError() . "<br>";
                    $statement->rollback();
                    die();
                }
                break;
            case "select":
                $parametros = Array($controllerIdAlumno, $controllerIdAsignatura);
                $statement = $conn->prepare("select * from NOTAS where ID_ALUMNO=? AND ID_ASIGNATURA=?", $parametros);
                if ($statement->getLastErrno() === 0) {
                    $statement->commit();
                } else {
                    echo 'Fallo al actualizar ' . $statement->getLastError() . "<br>";
                    $statement->rollback();
                    die();
                }
                break;
        }
        $alumnos = $conn->get("ALUMNOS");
        $asignaturas = $conn->get("ASIGNATURAS");
        if ($alumnos->getLastErrno() === 0 || $asignaturas->getLastErrno() === 0) {
            
        } else {
            echo 'Fallo al extraer alumnos o asignaturas ' . $conn->getLastError() . "<br>";
            die();
        }
        //-----------------------DAO Fin----------------------------
        ?>





        <!-----------------------Cliente Inicio-------------------->
        
        
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
        
        
        
        <table border=1 cellspacing=4 cellpadding=4>
            <?php
            while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
                $nombre = $row["NOMBRE"];
                $curso = $row["CURSO"];
                $ciclo = $row["CICLO"];
                ?>

                <tr> 
                    <td><input type="button" value="cargar"  onclick="cargarAsignatura('<?php echo $row["id"] ?>', '<?php echo htmlspecialchars($nombre, ENT_QUOTES, 'UTF-8') ?>', '<?php echo htmlspecialchars($curso, ENT_QUOTES, 'UTF-8') ?>', '<?php echo htmlspecialchars($ciclo, ENT_QUOTES, 'UTF-8') ?>')"/></td>
                    <td><?php echo $nombre ?></td>
                    <td><?php echo $curso ?></td>
                    <td><?php echo $ciclo ?></td>
                </tr>
                <?php
            }
            ?>
        </table>
        
        
        
        <form action="notasMysqliDB.php" name="formulario1" method="POST" >
            <table>
                <tr>
                    <td>
                        ALUMNO
                        <br>
                        <input type="hidden" id="idalumno" name="idAlumno"/>
                        <input type="text" id="nombreAl" name="nombreAlumno" />
                    </td>
                    <td>
                        ASIGNATURA
                        <br>
                        <input type="hidden" id="idasignatura" name="idAsignatura"/>
                        <input type="text" id="nombreAs" name="nombreAsignatura" />
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
                        <input type="button" value="actualizar" onclick="boton(4);"/>
                    </td>
                </tr>
            </table>
        </form>
        <!-----------------------Cliente Fin-------------------->
        <?php
        $statement->null;
        $alumnos->null;
        $asignaturas->null;
        $notas->null;
        $conn->disconnect();
        ?>


    </body>
</html>
