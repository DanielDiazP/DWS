/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PasswordHash;

/**
 *
 * @author Dani
 */
public class PasswordServicios {

    public String crearHash(String pass) throws InvalidKeySpecException {
        String hash = "Error al hashear";
        try {

            hash = PasswordHash.getInstance().createHash(pass);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(PasswordServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;

    }
}
