/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.NoPesados;

import java.util.*;

/**
 *
 * @author Novio
 */
public class DFS
{
    private UtilsRecorrido controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    public DFS(Grafo unGrafo)
    {
        this.grafo=unGrafo;
      //  grafo.validarVertice(posVerticePartida);
        recorrido=new ArrayList<>();
        controlMarcados=new UtilsRecorrido(this.grafo.cantidadDeVertices());
        //procesarDFS(posVerticePartida);
    }
    public void procesarDFS(int posVertice)
    {   
       grafo.validarVertice(posVertice);
       controlMarcados.marcarVertice(posVertice);
       this.recorrido.add(posVertice);
       Iterable<Integer> adyacentesDelVerticeEnTurno= grafo.adyacentesDeVertice(posVertice);
            for (Integer posVerticeAdyacente:adyacentesDelVerticeEnTurno) 
            {
                if(!controlMarcados.estaVerticeMarcado(posVerticeAdyacente))
                {
                    procesarDFS(posVerticeAdyacente);
                }
            }
    }
    public boolean hayCaminoAVertice(int posVertice)
    {
        grafo.validarVertice(posVertice);
        return controlMarcados.estaVerticeMarcado(posVertice);
    }
    public Iterable<Integer> elRecorrido()
    {
        return this.recorrido;
    }
    public boolean hayCaminosAtodos()
    {
        return controlMarcados.estanTodosMarcados();
    }
    //////////////////////
    //EJERCICIO 9
    ////////////////////////
    //DISCULPE INGENIERO ESTE CODIGO ME SIRVE PARA EL 9 Y 10
    //PERO NO CREO QUE ME LO HAGA VALER
    // DE TODAS FORMAS GRACIAS Y QUE TENGA BUEN DIA
    public int grafoCantIslas()
    {
        int cantIslas=0;
        int verticeEnTurno=0;
        DFS dfsAuxiliar=new DFS(grafo);
        dfsAuxiliar.procesarDFS(verticeEnTurno);
        if(dfsAuxiliar.hayCaminosAtodos())
        {
            return cantIslas=1;
        }else{
            cantIslas++;
            while(!dfsAuxiliar.hayCaminosAtodos() && verticeEnTurno<grafo.cantidadDeVertices())
            {
                while(dfsAuxiliar.hayCaminoAVertice(verticeEnTurno))
                {
                    verticeEnTurno++;
                }
                dfsAuxiliar.procesarDFS(verticeEnTurno);
                cantIslas++;
            }
        }
        return cantIslas;
    }
    ////////////////////////////////
    //EJERCICIO 3
    ///////////////////////////////
    public List<Integer> componentesDeIslas()
    {
        int verticeEnTurno=0;
        DFS dfsAuxiliar=new DFS(grafo);
        dfsAuxiliar.procesarDFS(verticeEnTurno);
        if(dfsAuxiliar.hayCaminosAtodos())
        {
            return dfsAuxiliar.recorrido;
        }else
        {
            while(!dfsAuxiliar.hayCaminosAtodos() && verticeEnTurno<grafo.cantidadDeVertices())
            {
                while(dfsAuxiliar.hayCaminoAVertice(verticeEnTurno))
                {
                    verticeEnTurno++;
                }
                dfsAuxiliar.procesarDFS(verticeEnTurno);
            }
        }
        return dfsAuxiliar.recorrido;
    }
    public boolean grafohayCiclos(int posVertice)
    {
        grafo.validarVertice(posVertice);
        Grafo grafoAuxiliar=new Grafo(grafo.cantidadDeVertices());
        controlMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDelVerticeEnTurno= grafo.adyacentesDeVertice(posVertice);
        for (Integer posVerticeAdyacente:adyacentesDelVerticeEnTurno) 
        {
            if(!controlMarcados.estaVerticeMarcado(posVerticeAdyacente))
            {
                grafoAuxiliar.insertarArista(posVertice, posVerticeAdyacente);
                grafohayCiclos(posVerticeAdyacente);
            }else if(!grafoAuxiliar.existeAdyacencia(posVertice, posVerticeAdyacente))
            {
                return true;
            }
        }
        if(!this.hayCaminosAtodos())
        {
            while(!hayCaminoAVertice(posVertice))
            {
                posVertice++;
            }
            grafohayCiclos(posVertice);
        }
        return false;
    }
}
