/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT_Grafo;

import ADT_Grafo.excepciones.ExcepcionAristaYaExiste;

/**
 *
 * @author Veronica
 */
public class TestGrafo {
     public static void main(String argumentos[]) throws  ExcepcionAristaYaExiste{
      Grafos g = new Grafos() ;
     
      g.insertarVertice();
      g.insertarVertice();
      g.insertarVertice();
      g.insertarVertice();
      g.insertarVertice();
      g.insertarVertice();
      g.insertarArista(1, 3);
      g.insertarArista(1, 6);
     // g.insertarArista(2, 4);
     // g.insertarArista(3, 1);
      g.insertarArista(4, 2);
     // g.insertarArista(3, 4);
     // g.insertarArista(6, 1);
      System.out.println(g.toString());
     }
}
