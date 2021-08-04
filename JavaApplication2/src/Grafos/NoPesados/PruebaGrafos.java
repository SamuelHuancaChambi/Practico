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
import ADT_Grafo_Pesado.Grafos;
import ADT_Grafo_Pesado.Kruskal;
import ADT_Grafo_Pesado.excepciones.ExcepcionAristaYaExiste;
import java.util.*;
public class PruebaGrafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionAristaYaExiste {
        /*System.out.println("hola mundo");
       Grafo grafo=new Grafo(8);
       grafo.insertarArista(0, 1);
       grafo.insertarArista(0, 2);
       grafo.insertarArista(1, 2);
       grafo.insertarArista(1, 3);
       grafo.insertarArista(2, 4);
       grafo.insertarArista(2, 7);
       grafo.insertarArista(3, 4);
       grafo.insertarArista(5, 4);
       grafo.insertarArista(5, 6);
        for (int i = 0; i < grafo.cantidadDeVertices(); i++)
        {
            Iterable<Integer> ady=grafo.adyacentesDeVertice(i);
            System.out.println(i + ":"+ ady);
        }
        System.out.println("Cantidad de vertices:"+grafo.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + grafo.cantidadDeAristas());/*
        //EJERCICIO 1
        grafo.eliminarVertice(4);
        for (int i = 0; i < grafo.cantidadDeVertices(); i++)
        {
            Iterable<Integer> ady=grafo.adyacentesDeVertice(i);
            System.out.println(i + ":"+ ady);
        }
           System.out.println("Cantidad de aristas: " + grafo.cantidadDeAristas());
           System.out.println("Cantidad de vertices:"+grafo.cantidadDeVertices());*/
        DiGrafo digrafo= new DiGrafo(5);
        digrafo.insertarArista(0, 1);
        digrafo.insertarArista(1, 2);
        digrafo.insertarArista(2, 0);
        digrafo.insertarArista(3, 4);
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++)
        {
            Iterable<Integer> ady=digrafo.adyacentesDeVertice(i);
            System.out.println(i + ":"+ ady);
        }
        
        //digrafo.invertirDigrafo();
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++)
        {
            Iterable<Integer> ady=digrafo.adyacentesDeVertice(i);
            System.out.println(i + ":"+ ady);
        }
        BFS bfs=new BFS(digrafo);
        //EJERCICIO 7
        System.out.println(bfs.esDigrafoDebilmenteConexo());
        DFS dfs= new DFS(digrafo);
        //EJERCICIO3
        List<Integer> compIslas=dfs.componentesDeIslas();
        System.out.println(compIslas);
        //EJERCICIO 9 Y 10
        System.out.println(dfs.grafoCantIslas());
        //Ejercicio 6
        //Implementar un algoritmo para encontrar si hay ciclos en un digrafo
        System.out.println("--------------------------------------------------");
        MatrizDeAdyacencia matrizDeAdyacencia=new MatrizDeAdyacencia(digrafo);
        for (int i = 0; i < matrizDeAdyacencia.cantidadDeVertices(); i++)
        {
            System.out.print(i+" ");
            int [] adyacentesDelVertice=new int[matrizDeAdyacencia.cantidadVertices];
            adyacentesDelVertice=matrizDeAdyacencia.adyacentesDelVertice(i);
            for (int j = 0; j < matrizDeAdyacencia.cantidadVertices; j++)
            {
                System.out.print(adyacentesDelVertice[j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------");
        
        System.out.println(Boolean.toString(matrizDeAdyacencia.hayCiclos()));
       // Ejercicio 11. Para un grafo dirigido implementar el algoritmo de Wharsall,
        //que luego muestre entre que vértices hay camino
        matrizDeAdyacencia.mostrarCaminosDeVertices();
        //Ejercicio 16
        //Algoritmo de Kruskal
        Grafos g = new Grafos() ;
            //A, B, C, D, E ,F ,G
            g.insertarVertice();
            g.insertarVertice();
            g.insertarVertice();
            g.insertarVertice();
            g.insertarVertice();
            g.insertarVertice();
            g.insertarVertice();
            
            g.insertarArista(0, 7, 1);
            g.insertarArista(0, 5, 3);
            g.insertarArista(1, 8, 2);
            g.insertarArista(1, 9, 3);
            g.insertarArista(1, 7, 4);
            g.insertarArista(2, 5, 4);
            g.insertarArista(3, 15, 4);
            g.insertarArista(3, 6, 5);
            g.insertarArista(4, 8, 5);
            g.insertarArista(4, 9, 6);
            g.insertarArista(5, 11, 6);
            
            System.out.println(g.mostraElGrafo());
            
            Kruskal K = new Kruskal(g);
            K.run();
            System.out.println("----------- KRUSKAL -----------");
            g=K.getGrafoKruskal();
            System.out.println(g); 
    }
    
}
