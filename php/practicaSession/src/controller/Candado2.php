<?php

namespace candados;

/**
 * Description of Candado2
 *
 * @author DAW
 */
class Candado2 {

    public function siguiente($param) {
        $paginaSalida = \Constantes::PAGINA_ERROR;
        $valorSession = "1";

        if (($param != NULL)) {

            //nivel 2.1
            if ($_SESSION[\Constantes::SESSION_KEY] == "2.1") {
                if (strcmp($param, \Constantes::PASS_2_1) == 0) {
                    $paginaSalida = \Constantes::PAGINA_INTERMEDIA;
                    $valorSession = "2.2";
                }
            }

            //nivel 2.2
            if ($_SESSION[\Constantes::SESSION_KEY] == "2.2") {
                if (strcmp($param, \Constantes::PASS_2_2) == 0) {
                    $paginaSalida = \Constantes::PAGINA_INTERMEDIA;
                    $valorSession = "2.3";
                }
            }

            //nivel 2.3
            if ($_SESSION[\Constantes::SESSION_KEY] == "2.3") {
                if (strcmp($param, \Constantes::PASS_2_3) == 0) {
                    $paginaSalida = \Constantes::PAGINA_INTERMEDIA;
                    $valorSession = "3";
                }
            }
        }

        $_SESSION[\Constantes::SESSION_KEY] = $valorSession;
        include $paginaSalida;
    }

}
