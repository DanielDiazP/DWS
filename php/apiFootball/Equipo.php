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
        <title>Partidos</title>

    </head>
    <body>
        <?php
        require 'vendor/autoload.php';

        use GuzzleHttp\Client;

        $client = new Client();
        $codigo=$_REQUEST["codigo"];
        $variable=5;

        $uri = "http://api.football-data.org/v1/teams/".$codigo."/fixtures/";

        $header = array('headers' => array('X-Auth-Token' => 'e424ba319fa4487faf60c7cbd01f2a8f'));
        $response = $client->get($uri, $header);
        $json = json_decode($response->getBody());
        
        ?>
        <h1>Partidos</h1>
        <table border=1 cellspacing=4 cellpadding=4>
            <th>FECHA</th>
            <th>ESTADO</th>
            <th>EQUIPO RESIDENTE</th>
            <th>EQUIPO VISITANTE</th>
            <?php
            foreach ($json->fixtures as $team) {
                ?>
                <tr>
                    <td><?php echo$team->date ?></td>
                    <td><?php echo$team->status ?></td>
                    <td><a href="http://localhost:8000/Jugadores.php?codigo=<?php echo$variable ?>"><?php echo$team->homeTeamName ?></a></td>
                    <?php $variable++ ?>
                    <td><a href="http://localhost:8000/Jugadores.php?codigo=<?php echo$variable ?>"><?php echo$team->awayTeamName ?></a></td>
                    <?php $variable++ ?>
                </tr>
                <?php
            }
            ?>
        </table>
</html>
