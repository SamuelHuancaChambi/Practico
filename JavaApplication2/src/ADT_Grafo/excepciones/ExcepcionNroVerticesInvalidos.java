/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo.excepciones;

/**
 *
 * @author Veronica
 */
public class ExcepcionNroVerticesInvalidos extends Exception{
        /**
     * Creates a new instance of <code>ExcepcionClaveNoExiste</code> without
     * detail message.
     */
    public ExcepcionNroVerticesInvalidos() {
        super ("No se aceptan cantidad de vertices inferior a 1");
    }

    /**
     * Constructs an instance of <code>ExcepcionClaveNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionNroVerticesInvalidos(String msg) {
        super(msg);
    }
    
    
}
