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
        <title>Premier League 2017/18</title>

    </head>
    <body>
        <?php
        require 'vendor/autoload.php';

        use GuzzleHttp\Client;

        $client = new Client();
        $equipo=10;

        $uri = 'http://api.football-data.org/v1/competitions/445/leagueTable';

        $header = array('headers' => array('X-Auth-Token' => 'e424ba319fa4487faf60c7cbd01f2a8f'));
        $response = $client->get($uri, $header);
        $json = json_decode($response->getBody());
        
        ?>
        <h1>Premier League 2017/18</h1>
        <table border=1 cellspacing=4 cellpadding=4>
            <th>EQUIPO</th>
            <th>RANKING</th>
            <th>PARTIDOS JUGADOS</th>
            <th>GOLES</th>
            <?php
            foreach ($json->standing as $team) {
                ?>
                <tr>
                    <td><a href="http://localhost:8000/Equipo.php?codigo=<?php echo$equipo ?>"><?php echo$team->teamName ?></a></td>
                    <td><?php echo$team->position ?></td>
                    <td><?php echo$team->playedGames ?></td>
                    <td><?php echo$team->goals ?></td>
                    <?php $equipo++ ?>
                </tr>
                <?php
            }
            ?>
        </table>

</html>