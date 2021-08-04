/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo;


import ADT_Grafo.excepciones.ExcepcionAristaNoExiste;
import ADT_Grafo.excepciones.ExcepcionAristaYaExiste;
import ADT_Grafo.excepciones.ExcepcionNroVerticesInvalidos;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Veronica
 */
public class DiGrafo extends Grafos {
    public DiGrafo(){
        super();
    }
    
    public DiGrafo(int nroInicialDelVertice) throws ExcepcionNroVerticesInvalidos {
        super(nroInicialDelVertice);
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
             if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacentesDelOrigen = this.ListaDeAdyacencias.get(posVerticeOrigen);
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino); 
    }

    @Override
    public int cantidadDeArista() {
        //return super.cantidadDeArista(); //To change body of generated methods, choose Tools | Templates.
        //Sumar las listas de adyacencias
        int cantArista=0;
       for( List<Integer> adyacentesDeUnVertice : super.ListaDeAdyacencias){
           for (Integer posDeAdyacente : adyacentesDeUnVertice){
               cantArista++;
           }
       }
        return cantArista;
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
          if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.ListaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("Metodo no soportado en grafos dirigidos");
    }
    
    public int gradoDeEntradaDeVertice(int posDeVertice){
        super.validarVertice(posDeVertice);
        int entradaDeVertice = 0 ;
        for(List<Integer> adyacentesDeUnVertice : super.ListaDeAdyacencias){
            for (Integer posDeAdyacente : adyacentesDeUnVertice){
                if(posDeAdyacente == posDeVertice){
                    entradaDeVertice++;
                }
            }
        }
        return entradaDeVertice;
    }
    
    public int gradoDeSalidaDeVertice(int posDeVertice){
        return super.gradoDeVertice(posDeVertice);
        
    }
}
