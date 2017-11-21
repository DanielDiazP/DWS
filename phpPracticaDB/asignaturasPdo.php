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
                    opcion = "asignaturasPdo.php?opcion=insert";
            break;
            case 2:
                    opcion = "asignaturasPdo.php?opcion=delete";
            break;
            case 3:
                    opcion = "asignaturasPdo.php?opcion=update";
            break;
            }
            document.forms.formulario1.action = opcion;
            document.forms.formulario1.submit();
            }
        </script>
    </head>
    <body>
        <?php
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";
        $controllerOpcion;
        $controllerCiclo;
        $controllerNombre;
        $controllerCurso;
        $controllerId;
        $sql;
        $statement;
        $parametro;
        $parametro1;
        $parametro2;
        $parametro3;


        //----------------------Controller Inicio----------------------
        if (isset($_REQUEST["opcion"])) {
            $controllerOpcion = $_REQUEST["opcion"];
            $controllerCiclo = $_REQUEST["ciclo"];
            $controllerNombre = $_REQUEST["nombre"];
            $controllerCurso = $_REQUEST["curso"];
            if (isset($_REQUEST["idasignatura"])) {
                $controllerId = $_REQUEST["idasignatura"];
            }
        }
        //----------------------Controller Fin----------------------
        //-----------------------DAO Inicio----------------------------
        //crear conexion
        try {
            $conn = new PDO("mysql:dbname=$database;host=$servername", $username, $password);
            //checkear conexion
        } catch (PDOException $ex) {
            echo "Error al conectar la BD " . $ex->getMessage() . "<br>";
        }



        switch ($controllerOpcion) {
            case "insert":
                $parametro = $controllerNombre;
                $parametro1 = $controllerFecha;
                $parametro2 = $controllerEdad;
                $statement = $conn->prepare("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD) VALUES(?,?,?)");
                $statement->bind_param('ssi', $parametro, $parametro1, $parametro2);
                $statement->execute();
                break;

            case "delete":
                $parametro = $controllerId;
                $statement = $conn->prepare("DELETE FROM ALUMNOS WHERE ID=?");
                $statement->bind_param('i', $parametro);
                $statement->execute();
                break;

            case "update":
                $parametro = $controllerNombre;
                $parametro1 = $controllerFecha;
                $parametro2 = $controllerEdad;
                $parametro3 = $controllerId;
                $statement = $conn->prepare("UPDATE ALUMNOS set NOMBRE=?,FECHA_NACIMIENTO=?,MAYOR_EDAD=? WHERE ID=?");
                $statement->bind_param('ssii', $parametro, $parametro1, $parametro2, $parametro3);
                $statement->execute();
                break;
        }
        $sql = "SELECT * FROM `ALUMNOS`";
        $result = $conn->query($sql);
        ?>
        //-----------------------DAO Fin----------------------------




        <!-----------------------Cliente Inicio-------------------->
        <table border=1 cellspacing=4 cellpadding=4>
            <?php
            while ($row = $result->fetch_assoc()) {
                $dateCliente = new DateTime($row["FECHA_NACIMIENTO"]);
                $fechaCliente = $dateCliente->format('d-m-Y')
                ?>

                <tr> 
                    <td><input type="button" value="cargar"  onclick="cargarAlumno('<?php echo $row["ID"] ?>', '<?php echo $row["NOMBRE"] ?>', '<?php echo $row["FECHA_NACIMIENTO"] ?>', '<?php echo $row["MAYOR_EDAD"] ?>')"/></td>
                    <td><?php echo $row["NOMBRE"] ?></td>
                    <td><?php echo $fechaCliente ?></td>
                    <td><input type="checkbox" name="mayor" <?php if ($row["MAYOR_EDAD"]) { ?> checked <?php } ?> /></td>
                </tr>
                <?php
            }
            ?>
        </table>
        <form action="asignaturasPdo.php" name="formulario1" method="POST" >
            <input type="hidden" id="idasignatura" name="idasignatura" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="ciclo" name="ciclo" size="12"/>
            <input type="text" id="curso" name="curso" size="12"/>
            <input type="button" value="insertar" onclick="boton(1);"/>
            <input type="button" value="borrar" onclick="boton(2);"/>
            <input type="button" value="cambiar" onclick="boton(3
            </form>
            <!-----------------------Cliente Fin-------------------->
                   <?php
                   $statement->close();
                   $result->free();
                   $conn->close();
                   ?>


                < /body>
        </html>
