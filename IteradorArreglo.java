package sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterador para arreglos.
 * @author Alexander Perelman
 * @author Axel Flores
 * @author Santiago Liceaga
 * @author Pablo Landa
 * @author Mario Marin
 */
public class IteradorArreglo<T> implements Iterator<T> {
    
    private T[] colec;
    private int total;
    private int actual;
    
    public IteradorArreglo(T[] arre, int n){
        colec = arre;
        total = n;
        actual = 0;
    }

    @Override
    public boolean hasNext() {
        return actual<total;
    }

    @Override
    public T next() {
        if(hasNext()){
            T res = colec[actual];
            actual++;
            return res;
        }
        else
            throw new NoSuchElementException();   
    }
    
}
