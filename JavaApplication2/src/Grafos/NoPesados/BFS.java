/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.NoPesados;

/**
 *
 * @author Novio
 */
import java.util.*;
import static java.util.Collections.sort;
public class BFS
{
    private UtilsRecorrido controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    public BFS(Grafo unGrafo)
    {
        this.grafo=unGrafo;
        //grafo.validarVertice(posVerticePartida);
        recorrido=new ArrayList<>();
        controlMarcados=new UtilsRecorrido(this.grafo.cantidadDeVertices());
        //ejecutarBFS(posVerticePartida);
    }
    public void ejecutarBFS(int posVertice)
    {
        grafo.validarVertice(posVertice);
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posVertice);
        controlMarcados.marcarVertice(posVertice);
        do{
            int posVerticeEnTurno=cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesDelVerticeEnTurno= grafo.adyacentesDeVertice(posVerticeEnTurno);
            for (Integer posVerticeAdyacente:adyacentesDelVerticeEnTurno) 
            {
                if(!controlMarcados.estaVerticeMarcado(posVerticeAdyacente))
                {
                    cola.offer(posVerticeAdyacente);
                    controlMarcados.marcarVertice(posVerticeAdyacente);
                }
            }
        }while(!cola.isEmpty());
    }
    public boolean hayCaminoAVertice(int posVertice)
    {
        grafo.validarVertice(posVertice);
        return controlMarcados.estaVerticeMarcado(posVertice);
    }
    public Iterable<Integer> elRecorrido()
    {
        if(this.controlMarcados.estanTodosMarcados())
        {
        Collections.sort(this.recorrido);
        return this.recorrido;
        }else
        {
            ejecutarBFS(0);
            Collections.sort(this.recorrido);
            return this.recorrido;
        }
    }
    ///////////////
    public boolean esGrafoConexo(int posVerticePartida)
    {
        ejecutarBFS(posVerticePartida);
        return this.controlMarcados.estanTodosMarcados();
    }
    ///////////////
    public boolean esDigrafoFuertementeConexo()
    {
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) 
        {
            ejecutarBFS(i);
            if(!this.controlMarcados.estanTodosMarcados())
            {
                return false;
            }
            this.controlMarcados.desmarcarTodos();
        }
        return true;
    }
    ////////////////////
    //EJERCICIO 7
    /////////////////////
    public boolean esDigrafoDebilmenteConexo()
    {
        Grafo grafoAuxiliar=new Grafo(this.grafo.cantidadDeVertices());
        for (int i = 0; i < grafo.cantidadDeVertices(); i++)
        {
            List<Integer> adyacentesDelVertice=grafo.listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentesDelVertice.size(); j++)
            {
                grafoAuxiliar.insertarArista(i, adyacentesDelVertice.get(j));
            }
        }
        BFS bfs=new BFS(grafoAuxiliar);
        return bfs.esGrafoConexo(0);
    }
}
