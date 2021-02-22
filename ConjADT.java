package sudoku;

import java.util.Iterator;

/**
 * Esta interfaz define la funcionalidad mínima que deben tener los conjuntos 
 * @author Alexander Perelman
 * @author Axel Flores
 * @author Santiago Liceaga
 * @author Pablo Landa
 * @author Mario Marin
 */
public interface ConjADT<T> extends Iterable<T> {
    public boolean estaVacio();
    public boolean contiene(T dato);
    public boolean agregaElemento(T dato);
    public boolean elimina(T dato);
    public int getCardinalidad();
    public ConjADT <T> union(ConjADT otro);
    public ConjADT <T> interseccion(ConjADT<T> otro);
    public String toString();
    public Iterator<T> iterator();
    public ConjADT <T> diferencia(ConjADT otro);
    
}
