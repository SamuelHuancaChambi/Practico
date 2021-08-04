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
public class Grafo 
{
    protected List<List<Integer>> listaDeAdyacencias;
    private BFS recorridoBTS;
    public Grafo()
    {
        this.listaDeAdyacencias = new ArrayList<>();
    }
    
    public Grafo(int nroInicialDeVertices)
    {
        if(nroInicialDeVertices<=0)
        {
            System.out.println("No se pueden generar esa cantidad"
                    + "de Vertices");
            return;
        }
        this.listaDeAdyacencias= new ArrayList();
        for (int i = 0; i < nroInicialDeVertices; i++)
        {
            this.insertarVertice();
        }
    }
    public void insertarVertice()
    { 
        List<Integer> adyacentesDeNuevoVertice= new ArrayList();
        this.listaDeAdyacencias.add(adyacentesDeNuevoVertice);
    }
    public int cantidadDeVertices()
    {
        return this.listaDeAdyacencias.size();
    }
    public int gradoDeVertice(int posDeVertice)
    {
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        return adyacentesDelVertice.size();
    }
    public void validarVertice(int posicionDeVertice)
    {
        if(posicionDeVertice<0 || posicionDeVertice>=this.cantidadDeVertices())
        {
            System.out.println("No existe la posicion en este vertice");
        }
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
        if(posVerticeOrigen!=posVerticeDestino)
        {
            List<Integer> adyacenciaDelDestino=this.listaDeAdyacencias.get(posVerticeDestino);
            adyacenciaDelDestino.add(posVerticeOrigen);
            Collections.sort(adyacenciaDelDestino);
        }
    }
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino)
    {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacentesDelOrigen.contains(posVerticeDestino);
    }
    public Iterable<Integer> adyacentesDeVertice(int posDeVertice)
    {
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice=this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> IterableDeAdyacente=adyacentesDelVertice;
        return IterableDeAdyacente;
    }
    public int cantidadDeAristas()
    {
        int cantAristas=0;
        int cantLazos=0;
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++)
        {
            List<Integer> adyacentesDelVertice=this.listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentesDelVertice.size(); j++) 
            {
                if(i==adyacentesDelVertice.get(j))
                {
                    cantLazos++;
                }else
                {
                    cantAristas++;
                }
            }
        }
        return cantLazos + (cantAristas / 2);
    }
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino)
    {
        if(!existeAdyacencia(posVerticeOrigen, posVerticeDestino))
        {
            System.out.println("No existe arista entre estos vertices");
        }
        List<Integer> adyacentesDelOrigen=this.listaDeAdyacencias.get(posVerticeOrigen);
        int pos=adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(pos);
        if(posVerticeOrigen!=posVerticeDestino)
        {
            List<Integer> adyacentesDelDestino=this.listaDeAdyacencias.get(posVerticeDestino);
            pos=adyacentesDelDestino.indexOf(posVerticeOrigen);
            adyacentesDelDestino.remove(pos);
        }
    }
    //////////////////////////////////////////
    //EJERCICIO 1 
    //////////////////////////////////////////
    public void eliminarVertice(int posVertice)
    {
        validarVertice(posVertice);
        this.listaDeAdyacencias.remove(posVertice);
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++)
        {
            List<Integer> adyacentesDelVertice=this.listaDeAdyacencias.get(i);
            if(adyacentesDelVertice.contains(posVertice))
            {
                int pos=adyacentesDelVertice.indexOf(posVertice);
                adyacentesDelVertice.remove(pos);
            }
            for (int j = 0; j < adyacentesDelVertice.size(); j++)
            {
                if(adyacentesDelVertice.get(j)>posVertice)
                {
                    adyacentesDelVertice.set(j, adyacentesDelVertice.get(j)-1);
                }
            }
        }
       
    }
}
