/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo_Pesado;

import java.util.List;

/**
 *
 * @author Ronaldo Rivero
 */
public class FloydWarshall {
    static final double INF = 63000.00;
    double[][] path;
    int N;

    public FloydWarshall(Grafos G) {
        N = G.cantidadDeVertices();
        path = new double[N][N];
        llenarInf();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < G.listaDeAdyacencia.get(i).size(); j++) {
                AdyacentesConPeso v = G.listaDeAdyacencia.get(i).get(j);
                path[i][v.getIndiceVertice()] = v.getPeso();
            }
        }
        for(int i = 0; i < N; i++) {
            path[i][i] = 0;
        }
    }
    
    public void run() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++){
                    if(path[i][k] != INF && path[k][j] != INF) {
                        double dt = path[i][k] + path[k][j];
                        if(path[i][j] > dt){
                            path[i][j] = dt;
                        }
                    }
                }
            }
        }
    }
    
    public String mostrarMatriz() {
        String s = "   ";
        for(int k = 0; k < N; k++) {
            s = s + " ["+k+"] ";
        }
        s = s + "\n";
        for(int i = 0; i < N; i++) {
            s = s + "["+i+"]";
            for(int j = 0; j < N; j++){                    
                s = s + " " + path[i][j] + " ";
            }
            s = s + "\n";
        }
        return s;
    }
    
    private void llenarInf() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){                    
                path[i][j] = INF;
            }
        }
    }
}
