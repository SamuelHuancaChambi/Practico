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
public class MatrizDeAdyacencia 
{
    protected int [][] matrizDeAdyacencia;
    protected int cantidadVertices;
    public MatrizDeAdyacencia(DiGrafo digrafo)
    {
        //inicializo la matriz
        this.cantidadVertices=digrafo.listaDeAdyacencias.size();
        this.matrizDeAdyacencia=new int[100][100];
        for (int i = 0; i < this.cantidadVertices; i++)
        {
            for (int j = 0; j < this.cantidadVertices; j++)
            {
                this.matrizDeAdyacencia[i][j]=0;
            }
        }
        //cargo las aristas
        for (int i = 0; i < this.cantidadDeVertices(); i++)
        {
            List<Integer> listaDeAdyacentes=digrafo.listaDeAdyacencias.get(i);
            for (int j = 0; j < listaDeAdyacentes.size(); j++)
            {
                    insertarArista(i, listaDeAdyacentes.get(j));
            }
        }
    }
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino)
    {
        this.matrizDeAdyacencia[posVerticeOrigen][posVerticeDestino]=1;
    }
    public void insertarVertice()
    {
        this.cantidadVertices++;
    }
    public int cantidadDeAristas()
    {
        int c=0;
        for (int i = 0; i < this.cantidadVertices; i++) 
        {
            for (int j = 0; j < this.cantidadVertices; j++) 
            {
                if(this.matrizDeAdyacencia[i][j]==1)
                {
                    c++;
                }
            }
        }
        return c;
    }
    public int cantidadDeVertices()
    {
        return this.cantidadVertices;
    }
    public int[] adyacentesDelVertice(int posVertice)
    {
        int [] adyacentesDelVertice=new int[this.cantidadVertices];
        for (int i = 0; i < this.cantidadVertices; i++)
        {
            adyacentesDelVertice[i]=this.matrizDeAdyacencia[posVertice][i];
        }
        return adyacentesDelVertice;
    }
    public int [][] algoritmoDeWharshall()
    {
        int [][] matrizP=new int[this.cantidadVertices][this.cantidadVertices];
        //copiamos la matriz de Adyacencia
        for (int i = 0; i < this.cantidadVertices; i++)
        {
            for (int j = 0; j < this.cantidadVertices; j++)
            {
                matrizP[i][j]=this.matrizDeAdyacencia[i][j];
            }
        }
        //aplicamos el algoritmo de Wharshall
        for (int k = 0; k < this.cantidadVertices; k++)
        {
            for (int i = 0; i < this.cantidadVertices; i++)
            {
                for (int j = 0; j < this.cantidadVertices; j++)
                {
                    matrizP[i][j]=Math.min(matrizP[i][j] + matrizP[i][k] * matrizP[k][j], 1);
                }
            }
        }
        return matrizP;
    }
    public boolean hayCiclos()
    {
        int [][] matrizDeCaminos=new int[this.cantidadVertices][this.cantidadVertices];
        matrizDeCaminos=this.algoritmoDeWharshall();
        for (int i = 0; i < this.cantidadVertices; i++)
        {
            for (int j = 0; j < this.cantidadVertices; j++)
            {
                if(i==j)
                {
                    if(matrizDeCaminos[i][j]==1)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void mostrarCaminosDeVertices()
    {
        int [][] matrizDeCaminos=new int[this.cantidadVertices][this.cantidadVertices];
        matrizDeCaminos=this.algoritmoDeWharshall();
        System.out.print("Existe caminos entre los vertices:");
        for (int i = 0; i < this.cantidadVertices; i++)
        {
            for (int j = 0; j < this.cantidadVertices; j++)
            {
                if(matrizDeCaminos[i][j]==1)
                {
                    System.out.print("{" +i + "," + j + "}"+ " ");
                }
                //System.out.println("");
            }
        }
    }
}
