
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Novio
 */
public class ArbolBinarioBusqueda <K extends Comparable<K>,V> 
implements IArbolBusqueda<K,V>
{
    protected NodoBinario<K,V> raiz;
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) throws ExcepecionClaveYaExiste {
        if(this.esArbolVacio())
        {
            this.raiz=new NodoBinario<K,V>(claveAInsertar,valorAInsertar);
            return;
        }
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual))
        {
            K claveActual=nodoActual.getClave();
            if(claveAInsertar.compareTo(claveActual)<0)    
            {
                nodoActual=nodoActual.getHijoIzquierdo();
            } else if(claveAInsertar.compareTo(claveActual)>0)
            {
                nodoActual=nodoActual.getHijoDerecho();
            }else
            {
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        //si llego a este punto quiere decir que encontre donde insertar
        NodoBinario<K,V> nuevoNodo = new NodoBinario<K,V> (claveAInsertar,valorAInsertar);
        K claveAnterior=nodoAnterior.getClave();
        if(claveAInsertar.compareTo(claveAnterior)<0)
        {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else
        {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
        V valorAEliminar=this.buscar(claveAEliminar);
        if(valorAEliminar==null)
        {
            throw new ExcepcionClaveNoExiste();
        }
        this.raiz=eliminar(this.raiz, claveAEliminar);
        return valorAEliminar;
    }
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar)
    {
        K claveActual=nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual)<0)
        {
            NodoBinario<K,V> supuestoNuevoHijoIzq= eliminar(nodoActual.getHijoIzquierdo(),
                    claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzq);
            return nodoActual;
        }
        if(claveAEliminar.compareTo(claveActual)>0)
        {
            NodoBinario<K,V> supuestoNuevoHijoDer= eliminar(nodoActual.getHijoDerecho(),
                    claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDer);
            return nodoActual;
        }
        if(nodoActual.esHoja())
        {
            return NodoBinario.nodoVacio();
        }
        if(!nodoActual.esVacioHijoIzquierdo() &&
                nodoActual.esVacioHijoDerecho())
        {
            return nodoActual.getHijoIzquierdo();
        }
        if(!nodoActual.esVacioHijoDerecho() &&
                nodoActual.esVacioHijoIzquierdo())
        {
            return nodoActual.getHijoDerecho();
        }
        NodoBinario<K,V> nodoDelSucesor=buscarSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K,V> supuestoNuevoHijo=eliminar(nodoActual.getHijoDerecho(),nodoDelSucesor.getClave());
        nodoActual.setHijoDerecho(supuestoNuevoHijo);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());
        return nodoActual;
    }
    private NodoBinario<K,V> buscarSucesor(NodoBinario<K,V> nodoActual)
    {
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual))
        {
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
    @Override
    public V buscar(K claveBuscar) {
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual))
        {
            K claveActual=nodoActual.getClave();
            if(claveBuscar.compareTo(claveActual)<0)    
            {
                nodoActual=nodoActual.getHijoIzquierdo();
            } else if(claveBuscar.compareTo(claveActual)>0)
            {
                nodoActual=nodoActual.getHijoDerecho();
            }else
            {
                return nodoActual.getValor();
            }
        }
        return null;
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    @Override
    public int size()
    {
     if(this.esArbolVacio())
     {
         return 0;
     }
     Queue<NodoBinario<K,V>> coladeNodos= new LinkedList();
     coladeNodos.offer(this.raiz);
     int cantidadDeNodos=0;
     while(!coladeNodos.isEmpty())
     {
         NodoBinario<K,V> nodoActual=coladeNodos.poll();
         if(!nodoActual.esVacioHijoIzquierdo())
         {
             coladeNodos.offer(nodoActual.getHijoIzquierdo());
         }
         if(!nodoActual.esVacioHijoDerecho())
         {
             coladeNodos.offer(nodoActual.getHijoDerecho());
         }
         cantidadDeNodos++;
     }
     return cantidadDeNodos;
    }
    public int sizeRec()
    {
        return sizeRec(this.raiz);
    }
    public int sizeRec(NodoBinario<K,V> nodoActual)
    {
        if(NodoBinario.esNodoVacio(nodoActual))
        {
            return 0;
        }
        int cantidadDeNodosPorIzquierda=sizeRec(nodoActual.getHijoIzquierdo());
        int cantidadDeNodosPorDerecha=sizeRec(nodoActual.getHijoDerecho());
        return cantidadDeNodosPorIzquierda+cantidadDeNodosPorDerecha+1;
    }
    public int altura()
    {
        return altura(this.raiz);
    }
    public int altura(NodoBinario<K,V> nodoActual)
    {
        if(NodoBinario.esNodoVacio(nodoActual))
        {
            return 0;
        }
        int alturaPorIzquierda=altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha=altura(nodoActual.getHijoDerecho());
        return alturaPorIzquierda>alturaPorDerecha?alturaPorIzquierda+1:alturaPorDerecha+1;
    }
    
    public int alturaIt()
    {
        if(this.esArbolVacio())
        {
            return 0;
        }
        int alturaDelArbol=0;
        Queue<NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty())
        {
            int nroDeNodosDelNivel=colaDeNodos.size();
            int pos=0;
            while(pos<nroDeNodosDelNivel)
            {
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                if(!nodoActual.esVacioHijoIzquierdo())
                {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho())
                {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
            alturaDelArbol++;
        }
        return alturaDelArbol;
    }

    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vaciar() {
        this.raiz=NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }
    
    
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido=new LinkedList<>();
        if(this.esArbolVacio())
        {
            return recorrido;
        }
        Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
        colaDeNodos.offer(raiz);
        while(colaDeNodos.isEmpty())
        {
            NodoBinario<K,V> nodoActual=colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoIzquierdo())
            {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho())
            {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }
    public List<K> recorridoEnPreOrdenRec()
    {
        List<K> recorrido = new LinkedList<>();
        return recorrido;
    }
    private void recorridoEnPreOrdenRec(NodoBinario<K,V> nodoActual, List<K> recorrido)
    {
        if(NodoBinario.esNodoVacio(nodoActual))
        {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenRec(nodoActual.getHijoDerecho(), recorrido);
    }
    @Override
    public List<K> recorridoEnInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido=new LinkedList();
        if(this.esArbolVacio())
        {
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos=new Stack<>();
        NodoBinario<K,V> nodoActual=this.raiz;
        insertarEnPilaParaPostOrden(nodoActual, pilaDeNodos);
        //iniciando a sacar nodos de la pila
        while(!pilaDeNodos.isEmpty())
        {
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!pilaDeNodos.isEmpty())
            {
                NodoBinario<K,V> nodoDelTope= pilaDeNodos.peek();
                if(!nodoDelTope.esVacioHijoDerecho() &&
                        nodoDelTope.getHijoDerecho()!=nodoActual)
                {
                    insertarEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                }
            }
        }
        return recorrido;
    }

    private void insertarEnPilaParaPostOrden(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos) {
        while(!NodoBinario.esNodoVacio(nodoActual))
        {
            pilaDeNodos.push(nodoActual);
            if(!nodoActual.esVacioHijoIzquierdo())
            {
                nodoActual=nodoActual.getHijoIzquierdo();
            }else
            {
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
