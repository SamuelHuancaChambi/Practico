
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Novio
 */
public interface IArbolBusqueda <K extends Comparable<K>,V>
{
    void insertar(K claveAInsertar, V valorAInsertar) throws ExcepecionClaveYaExiste;
    V eliminar(K claveaEliminar) throws ExcepcionClaveNoExiste;
    V buscar (K claveBuscar);
    boolean contiene(K claveABuscar);
    int size();
    int altura();
    int nivel();
    void vaciar();
    boolean esArbolVacio();
    List<K> recorridoPorNiveles();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPostOrden();


}
