<?php

namespace controller;

/**
 * Description of Candado3
 *
 * @author DAW
 */
class Candado3 {

    public function siguiente($param) {
        $paginaSalida = \Constantes::PAGINA_ERROR;
        $valorSession = 0;

        if (($param != NULL)) {
            if (strcmp($param, \Constantes::PASS_3) == 0) {
                $paginaSalida = \Constantes::PAGINA_FINAL;
                $valorSession = session_destroy();
            }
        }

        $_SESSION[\Constantes::SESSION_KEY] = $valorSession;
        include $paginaSalida;
    }

}
