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
    </head>
    <body>
        <?php
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";
        //crear conexion
        $conn = new mysqli($servername, $username, $password, $database);
        //checkear conexion
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $sql = "SELECT * FROM `ALUMNOS`";
        $result = $conn->query($sql);

      ?>
        <table border=1 cellspacing=4 cellpadding=4>
            <?php
        while ($row = $result->fetch_assoc()) {
        echo '
             <tr> 
             <td> ' . ' <input type="button" value="id"  onclick="cargarAlumno('.$row["ID"].','.$row["NOMBRE"].','.$row["FECHA_NACIMIENTO"].','.$row["MAYOR_EDAD"].');"/>'.'</td>
             <td> ' . $row["NOMBRE"] . '</td>
             <td> ' . $row["FECHA_NACIMIENTO"] . '</td>
             <td> ' . $row["MAYOR_EDAD"] . '</td>
             </tr>';
        }
        echo '</table>';



        $conn->close();
        ?>
    </body>
</html>
