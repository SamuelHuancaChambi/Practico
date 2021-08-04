package ADT_Grafo_Pesado;

/**
 *
 * @author Ronaldo Rivero
 */
public class Dijkstra {
    public final static int INFINITO = -1;
    
    private Grafos G;
    private int distancia[];
    private boolean visto[];
    
    public Dijkstra(Grafos G) {
        this.G = G;
        //distancia = new int[G.cantidadDeVertice()];
        //visto = new boolean[G.cantidadDeVertice()];
    }
    
    public void run(int s) {
        desmarcarTodo();
        limpiarDistancia();
        for(int w : G.adyacentesDeVertice(s)) {
            
        }
    }
    
    private int peso(int u, int v) {
        int p = INFINITO;
        //for(NodoPesado np : G.ListaDeAdyacencias2.get(u-1))
        return p;
    }
    
    private void desmarcarTodo() {
        for(int i = 0; i < visto.length; i++) {
            visto[i] = false;
        }
    }
    
    private void limpiarDistancia() {
        for(int i = 0; i < distancia.length; i++) {
            distancia[i] = INFINITO;
        }
    }    
}
