<!DOCTYPE html>

<!--
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Equipos
 *
 * @author Dani
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>JUGADORES</title>

    </head>
    <body>
        <?php
        require 'vendor/autoload.php';

        use GuzzleHttp\Client;

        $client = new Client();
        $codigo=$_REQUEST["codigo"];

        $uri = "http://api.football-data.org/v1/teams/".$codigo."/players";
       

        $header = array('headers' => array('X-Auth-Token' => 'e424ba319fa4487faf60c7cbd01f2a8f'));
        $response = $client->get($uri, $header);
        $json = json_decode($response->getBody());
        
        ?>
        <h1>JUGADORES</h1>
        <table border=1 cellspacing=4 cellpadding=4>
            <th>NOMBRE</th>
            <th>POSICION</th>
            <th>NUMERO DE CAMISETA</th>
            <th>FECHA DE NACIMIENTO</th>
            <th>NACIONALIDAD</th>
            <th>DURACION DEL CONTRATO</th>
            <?php
            foreach ($json->players as $team) {
                ?>
                <tr>
                    <td><?php echo$team->name ?></td>
                    <td><?php echo$team->position ?></td>
                    <td><?php echo$team->jerseyNumber ?></td>
                    <td><?php echo$team->dateOfBirth ?></td>
                    <td><?php echo$team->nationality ?></td>
                    <td><?php echo$team->contractUntil ?></td>
                </tr>
                <?php
            }
            ?>
        </table>
</html>
