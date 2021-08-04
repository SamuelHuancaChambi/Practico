/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo_Pesado;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ronaldo Rivero
 */
public class Kruskal {
    public static final double INF = 63000.00;
    private List<List<AdyacentesConPeso>> adyacencia;
    private List<List<AdyacentesConPeso>> arbol;
    private List<Integer> pertenece;
    private int N;

    public Kruskal(Grafos G) {
        N = G.cantidadDeVertices();
        adyacencia = G.listaDeAdyacencia;
        arbol = new ArrayList<>();
        pertenece = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            arbol.add(new ArrayList<AdyacentesConPeso>());
            pertenece.add(i);
        }
    }
    
    public void run() {
        int verticeA = 0, verticeB = 0;
        int arcos = 1;
        while(arcos < N) {
            double min = INF;
            
            for(int i = 0; i < adyacencia.size(); i++) {
                for(int j = 0; j < adyacencia.get(i).size(); j++) {
                    int verticeJ = adyacencia.get(i).get(j).getIndiceVertice();
                    double pesoIJ = adyacencia.get(i).get(j).getPeso();
                    if(pesoIJ < min && pertenece.get(i) != pertenece.get(verticeJ)) {
                        min = pesoIJ;
                        verticeA = i;
                        verticeB = verticeJ;
                    }
                }
            }
         
            if(pertenece.get(verticeA) != pertenece.get(verticeB)) {
                arbol.get(verticeA).add(new AdyacentesConPeso(verticeB, min));
                arbol.get(verticeB).add(new AdyacentesConPeso(verticeA, min));
                
                int temp = pertenece.get(verticeB);
                pertenece.set(verticeB, pertenece.get(verticeA));
                for(int k = 0; k < N; k++) {
                    if(pertenece.get(k) == temp)
                        pertenece.set(k, pertenece.get(verticeA));
                }
                arcos++;
            }
        }        
    }

    public Grafos getGrafoKruskal() {
        Grafos gk = new Grafos();
        gk.listaDeAdyacencia = arbol;
        return gk;
    }
    
    
    public String mostrarArbol() {
        String s = "";
        for(int i = 0; i < arbol.size(); i++) {
            for(int j = 0; j < arbol.get(i).size(); j++) {
                s = s + i + " - " + arbol.get(i).get(j).getIndiceVertice()
                        + " = " + arbol.get(i).get(j).getPeso() + "\n";
            }
        }
        return s;
    }
    
    
}
