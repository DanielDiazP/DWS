<?php

namespace controller;


/**
 * Description of Candado1
 *
 * @author DAW
 */
class Candado1 {

    public function siguiente($param) {
        $paginaSalida = \Constantes::PAGINA_ERROR;
        $valorSession = "1";

        if (($_SESSION[\Constantes::SESSION_KEY] == "0") && ($param != NULL)) {
            if (strcmp($param, \Constantes::PASS_1) == 0) {
                $paginaSalida = \Constantes::PAGINA_INTERMEDIA;
                $valorSession = "2.1";
            }
        } else {
            $valorSession = "0";
        }

        $_SESSION[\Constantes::SESSION_KEY] = $valorSession;
        include $paginaSalida;
    }

}
