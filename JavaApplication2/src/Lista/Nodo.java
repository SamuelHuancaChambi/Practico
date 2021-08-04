/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista;

/**
 *
 * @author Novio
 */
public class Nodo {
   private int data; 
   private Nodo link;

    public Nodo()
    {
        this.data = -1;
        this.link=null;
    }

    public Nodo(int data) 
    {
        this.data = data;
        this.link=null;
    }

    public int getData() {
        return data;
    }

    public Nodo getLink() {
        return link;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }
    public Nodo sigNodo()
    {
        return link;
    }
}
