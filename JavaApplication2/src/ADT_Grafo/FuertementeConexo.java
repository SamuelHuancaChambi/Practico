/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo;

/**
 *
 * @author Veronica
 */
public class FuertementeConexo {
    private Conexo grafoConexo;
    
    public FuertementeConexo(DiGrafo unDigrafo ){
        this.grafoConexo = new Conexo(unDigrafo);
    }
    
    public boolean esFuertementeConexo(){
        return this.grafoConexo.esConexo();
    }
}
