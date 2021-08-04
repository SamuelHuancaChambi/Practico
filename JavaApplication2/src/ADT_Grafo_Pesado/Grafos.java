/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo_Pesado;

import ADT_Grafo_Pesado.excepciones.ExcepcionAristaNoExiste;
import ADT_Grafo_Pesado.excepciones.ExcepcionAristaYaExiste;
import ADT_Grafo_Pesado.excepciones.ExcepcionNroVerticesInvalidos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Veronica
 */
public class Grafos {
    protected List<List<AdyacentesConPeso>> listaDeAdyacencia;

    public Grafos() {
        listaDeAdyacencia=new ArrayList<>();
    }

    public Grafos(int nroVerticeInicial) throws ExcepcionNroVerticesInvalidos {
        if(nroVerticeInicial<0){
            throw new ExcepcionNroVerticesInvalidos();
        }
        this.listaDeAdyacencia=new ArrayList<>();

        for(int i=0;i<nroVerticeInicial;i++){
            this.insertarVertice();
        } 
    }

    public void insertarVertice(){
       List<AdyacentesConPeso>adyacentes = new ArrayList<>();
       listaDeAdyacencia.add(adyacentes);    
    }
    
    public int cantidadDeVertices(){
        return listaDeAdyacencia.size();
    }

    public int gradoDelVertice(int posicionDelVertice){
        this.validarVertice(posicionDelVertice);
        List<AdyacentesConPeso> listaDeAdyacentes = listaDeAdyacencia.get(posicionDelVertice);
        return listaDeAdyacentes.size();
    }
    
    public void validarVertice(int posicion){
        if(posicion < 0 || posicion > this.cantidadDeVertices()){
            throw new IllegalArgumentException("La posicion del vertice es invalida. El vertic no existe");
        }
    }
    
    public void insertarArista(int posVerticeOrigen, int peso, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if(existeAdyacencia(posVerticeOrigen,posVerticeDestino)){
            throw new ExcepcionAristaYaExiste();
        }
        
        List<AdyacentesConPeso> listaOrigen = listaDeAdyacencia.get(posVerticeOrigen);
        AdyacentesConPeso nodo = new AdyacentesConPeso(posVerticeDestino, peso);
        listaOrigen.add(nodo);        
        Collections.sort(listaOrigen);
        
        if(posVerticeOrigen != posVerticeDestino){
            List<AdyacentesConPeso>listaDestino = listaDeAdyacencia.get(posVerticeDestino);
            AdyacentesConPeso nodo1 = new AdyacentesConPeso(posVerticeOrigen, peso);
            listaDestino.add(nodo1);
            Collections.sort(listaDestino);
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        List<AdyacentesConPeso>listaDeAycentes = listaDeAdyacencia.get(posVerticeOrigen);
        AdyacentesConPeso nuevo=new AdyacentesConPeso(posVerticeDestino);
        return listaDeAycentes.contains(nuevo);
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        this.validarVertice(posDeVertice);
        List<AdyacentesConPeso>listaDeAdyacentes = listaDeAdyacencia.get(posDeVertice);
        List<Integer>soloVertices=new ArrayList<>();
        for(AdyacentesConPeso vertice : listaDeAdyacentes){
            soloVertices.add(vertice.getIndiceVertice());
        }
        Iterable<Integer>iteradorDeAdyacentes = soloVertices;
        return iteradorDeAdyacentes;
    }
    
    public Iterable<AdyacentesConPeso> adyacentesDelVerticeConPeso(int posicionDelVertice){
        this.validarVertice(posicionDelVertice);
        List<AdyacentesConPeso>lista = this.listaDeAdyacencia.get(posicionDelVertice);
        return lista;
    }

    public int cantidadDeArista() {
        int lazos=0;
        int aristas=0;
            for(int i=0;i<this.listaDeAdyacencia.size();i++){
                List<AdyacentesConPeso> adyacentes=listaDeAdyacencia.get(i);
                    for(AdyacentesConPeso elemento : adyacentes){
                        if(i == elemento.getIndiceVertice()){
                         lazos++;
                        }else{
                         aristas++;
                        }
                    }//fin for each
            }//fin for
            return lazos+(aristas/2);
    }

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        this.validarVertice(posVerticeOrigen);
        this.validarVertice(posVerticeDestino);
        if(!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacentesConPeso>listaOrigen = listaDeAdyacencia.get(posVerticeOrigen);
        AdyacentesConPeso aBorrar = new AdyacentesConPeso(posVerticeDestino,0);

        int posEliminar = listaOrigen.indexOf(aBorrar);
        listaOrigen.remove(posEliminar);
            if(posVerticeOrigen != posVerticeDestino){
                  List<AdyacentesConPeso>listaDestino = listaDeAdyacencia.get(posVerticeDestino);
                  AdyacentesConPeso aBorrar1 = new AdyacentesConPeso(posVerticeOrigen,0);
                  int posAEliminar = listaDestino.indexOf(aBorrar1);
                  listaDestino.remove(posAEliminar);
            }
    }

    public void eliminarVertice(int posVerticeAEliminar) {
        this.validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencia.remove(posVerticeAEliminar);
        AdyacentesConPeso eliminar = new AdyacentesConPeso(posVerticeAEliminar,0);
        for(List<AdyacentesConPeso> listaAdya : this.listaDeAdyacencia){
            int posicionAEliminarDeAdyacencia = listaAdya.indexOf(eliminar);
            if(posicionAEliminarDeAdyacencia >= 0){
                listaAdya.remove(posicionAEliminarDeAdyacencia);
               
            }
            for(int i=0;i < listaAdya.size(); i++){    //por que el for este si el remove hace todo el trabajo
                int posicionAdyacente = listaAdya.get(i).getIndiceVertice();
                    if(posicionAdyacente > posVerticeAEliminar){
                        AdyacentesConPeso poner = listaAdya.get(i);
                        poner.setIndiceVertice(posicionAdyacente-1);
                        listaAdya.set(i,poner);
                    }
            }
        }
    }
    
    public double peso(int verticeOrigen,int verticeDestino) throws ExcepcionAristaNoExiste{
        validarVertice(verticeOrigen);
        validarVertice(verticeDestino);
        if(!existeAdyacencia(verticeOrigen, verticeDestino)){
            throw new ExcepcionAristaNoExiste();
        }
        
        List<AdyacentesConPeso>lista = this.listaDeAdyacencia.get(verticeOrigen);
        AdyacentesConPeso nodo = new AdyacentesConPeso(verticeDestino);
        int posicion = lista.indexOf(nodo);
        return lista.get(posicion).getPeso();
    }
    
    public String mostraElGrafo(){
       String s="|  ";
       
        Double [][]matriz=new Double[this.cantidadDeVertices()][this.cantidadDeVertices()];
        for(int i=0;i<this.cantidadDeVertices();i++){
            s=s+i+" | ";
        }
        s=s+"\n";
       for(int i=0;i<this.cantidadDeVertices();i++){
           for(int j=0;j<this.cantidadDeVertices();j++){
               matriz[i][j]=(double)0;
           }
       }
       
        for(int i=0;i<this.listaDeAdyacencia.size();i++){
            List<AdyacentesConPeso>adyacentes=listaDeAdyacencia.get(i);
                for(AdyacentesConPeso elemento : adyacentes){
                    matriz[i][elemento.getIndiceVertice()]=elemento.getPeso();
                }
        }
        for(int i=0;i<this.cantidadDeVertices();i++){
            s=s+i+"|";
           for(int j=0;j<this.cantidadDeVertices();j++){
               s=s+matriz[i][j]+" ";
           }
           s=s+"\n";
       }
       return s;
    }
}
