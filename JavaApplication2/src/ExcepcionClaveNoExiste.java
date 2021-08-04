/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Novio
 */
public class ExcepcionClaveNoExiste extends Exception {

    public ExcepcionClaveNoExiste() {
        super("Clave No Existe");
    }

    public ExcepcionClaveNoExiste(String message) {
        super(message);
    }
    
}
