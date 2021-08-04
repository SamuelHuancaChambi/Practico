/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Novio
 */
class ExcepecionClaveYaExiste extends Exception {
     public ExcepecionClaveYaExiste() {
        super("Clave No Existe");
    }

    public ExcepecionClaveYaExiste(String message) {
        super(message);
    }
    
}
