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
public class DiGrafo extends Grafo
{
      public DiGrafo()
      {
          super();
      }
      public DiGrafo(int nroInicialDeVertices)
      {
          super(nroInicialDeVertices);
      }
      public int cantidadDeAristas()
      {
         int c=0;
          for (int i = 0; i < super.listaDeAdyacencias.size(); i++) 
          {
            List<Integer> listaDeAdyacencias=super.listaDeAdyacencias.get(i);
            c+=listaDeAdyacencias.size();
          }
          return c;
      }
      public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
      {
          if(!existeAdyacencia(posVerticeOrigen, posVerticeDestino))
        {
            System.out.println("No existe arista entre estos vertices");
            return;
        }
        List<Integer> adyacentesDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        int pos=adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(pos);
      }
      public void insertarArista(int posVerticeOrigen, int posVerticeDestino)
      {
          if(existeAdyacencia(posVerticeOrigen, posVerticeDestino))
        {
            System.out.println("Arista ya existe");
            return;
        }
        List<Integer> adyacenciaDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacenciaDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacenciaDelOrigen);
      }
      public int gradoDeEntradaDeVertice(int posDeVertice)
      {
        int c=0;
          for (int i = 0; i < super.listaDeAdyacencias.size(); i++)
          {
              List<Integer> adyacentesDelVertice=super.listaDeAdyacencias.get(i);
              for (int j = 0; j < adyacentesDelVertice.size(); j++) 
              {
                  if(adyacentesDelVertice.get(j)==posDeVertice)
                  {
                      c++;
                  }
              }
          }
          return c;
      }
      public int gradoDeSalidaDeVertice(int posDeVertice)
      {
          return super.gradoDeVertice(posDeVertice);
      }
      public Iterable<Integer> adyacentesDeVertice(int posDeVertice)
    {
        super.validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice=super.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> IterableDeAdyacente=adyacentesDelVertice;
        return IterableDeAdyacente;
    }
      public void eliminarVertice(int posDeVertice)
      {
          super.eliminarVertice(posDeVertice);
      }
      public void invertirDigrafo()
      {
          DiGrafo digrafoAuxiliar=new DiGrafo(this.cantidadDeVertices());
          for (int i = 0; i < this.cantidadDeVertices(); i++)
          {
            List<Integer> adyacentesDelVertice=this.listaDeAdyacencias.get(i);
              for (int j = 0; j < adyacentesDelVertice.size(); j++) 
              {
                  digrafoAuxiliar.insertarArista(adyacentesDelVertice.get(j), i);
              }
              adyacentesDelVertice.clear();
          }
          for (int i = 0; i < digrafoAuxiliar.cantidadDeVertices(); i++)
          {
              List<Integer> adyacentesDelVertice=digrafoAuxiliar.listaDeAdyacencias.get(i);
              for (int j = 0; j < adyacentesDelVertice.size(); j++)
              {
                  this.insertarArista(i, adyacentesDelVertice.get(j));
              }
          }
      }
}
