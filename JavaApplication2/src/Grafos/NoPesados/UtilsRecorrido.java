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
public class UtilsRecorrido
{
    private List<Boolean> marcados;
    public UtilsRecorrido(int numVertices)
    {
        marcados=new ArrayList<>();
        for (int i = 0; i < numVertices; i++)
        {
            marcados.add(Boolean.FALSE);
        }
    }
    public void marcarVertice(int posVertice)
    {
        marcados.set(posVertice, Boolean.TRUE);
    }
    public boolean estaVerticeMarcado(int posVertice)
    {
        return marcados.get(posVertice);
    }
    public void desmarcarTodos()
    {
        for (int i = 0; i < marcados.size(); i++)
        {
            marcados.set(i,Boolean.FALSE);
        }
    }
    public boolean estanTodosMarcados()
    {
        boolean b=true;
        for (int i = 0; i < marcados.size(); i++)
        {
            if(!estaVerticeMarcado(i))
            {
                b=false;
            }
        }
        return b;
    }
}
