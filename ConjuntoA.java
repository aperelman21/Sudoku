package sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Clase para modelar un conjunto con arreglos.
 * @author Alexander Perelman
 * @author Axel Flores
 * @author Santiago Liceaga
 * @author Pablo Landa
 * @author Mario Marin
 * 
 */
public class ConjuntoA<T> implements ConjADT<T> {
    private T[] conj;
    private int cardin; //cardinalidad
    private final int MAX = 50;
    
    public ConjuntoA(){
        conj = (T[])new Object[MAX];
        cardin =0;
    }
    
    public ConjuntoA(int max){
        conj = (T[])new Object[max];
        cardin =0;
    }
    
    @Override
    public Iterator<T> iterator(){
        return new IteradorArreglo(conj,cardin);
    }
    public int getCardinalidad(){
        return cardin;
    }
    @Override
    public boolean contiene(T dato){
        boolean resp;
        Iterator<T> it;
        resp = false;
        it = iterator();
        while(it.hasNext() && !resp)
            resp = it.next().equals(dato);
        return resp;
    }
    
    public boolean agregaElemento(T dato){
        boolean resp;
        resp = contiene(dato);
        if(!resp){
            if(cardin==conj.length)
                expande();
            conj[cardin] = dato;
            cardin++;
        }
        return !resp;
    }
    
    public void expande(){
        T[] grande = (T[])new Object[conj.length*2];
        for(int i =0;i<cardin;i++){
            grande[i]=conj[i];
        }
        conj = grande;
    }
    public int buscaDato(T dato){
        int i = 0;
        while(i<cardin && !conj[i].equals(dato))
            i++;
        if(i==-1)
            i=-1;
        return i;
    }
    
    public boolean elimina(T dato){
        boolean resp;
        int pos;
        pos = buscaDato(dato);
        if(pos>=0){
            resp = true;
            conj[pos] = conj[cardin-1];
            cardin--;
            conj[cardin]=null;   
        }
        else
            resp = false;
        return resp;
    }
    
    public String toString(){
        return toString(iterator(), new StringBuilder());
    }
    
    private String toString(Iterator<T> it, StringBuilder cad){
        if(!it.hasNext())
            return cad.toString();
        else{
            cad.append(it.next()).append("");
            return toString(it,cad);
        }
    }
    
    public boolean estaVacio(){
        return cardin==0;
    }
    
    public ConjADT <T> interseccion(ConjADT<T> otro){
        Iterator<T> ir1 = iterator();
        Iterator<T> ir2;
        T valor;
        boolean bool;
        ConjADT <T> inter = new ConjuntoA<>();
        while(ir1.hasNext()){
            valor = ir1.next();
            ir2 = otro.iterator();
            bool = false;
            while(ir2.hasNext() && !bool)
                if(valor.equals(ir2.next())){
                    inter.agregaElemento(valor);
                    bool = true;
                }  
        }
        return inter;
    }
    
    public ConjADT<T> interR(ConjADT<T> otro){
        if(otro == null)
            throw new NullPointerException();
        Iterator it = iterator();
        ConjADT<T> inter = new ConjuntoA<>();
        return interR(it,otro,inter,null);
    }
    
    private ConjADT<T> interR(Iterator<T> it, ConjADT<T> otro, ConjADT<T> inter, T valor){
        if(it.hasNext()){
            valor = it.next();
            if(otro.contiene(valor))
                inter.agregaElemento(valor);
            return interR(it,otro,inter,null);
        }
        return inter;
        
    }
    
    
   
    
    @Override
    public ConjADT <T> union(ConjADT otro){
        T dato;
        Iterator <T> it = otro.iterator();
        ConjADT<T> resp = new ConjuntoA();
        for(int i=0;i<cardin;i++){
            resp.agregaElemento(conj[i]);
        }
        while(it.hasNext())
            resp.agregaElemento(it.next());
        return resp;
    }
    
    public ConjADT<T> unionR(ConjADT<T> otro){
        if(otro==null)
            return null;
        else{
            Iterator it = otro.iterator();
            ConjADT<T> resp = new ConjuntoA<>();
            return unionR(it,0,resp);
        }
    }
    
    private ConjADT<T> unionR(Iterator it, int i, ConjADT<T> resp){
        if(i==cardin && !it.hasNext())
            return resp;
        else{
            if(i<cardin){
                resp.agregaElemento(conj[i]);
                i++;
            }
            if(it.hasNext()){
                resp.agregaElemento((T) it.next());
            }
            return unionR(it,i,resp);
        }    
        
    }
    
    public ConjADT <T> diferencia(ConjADT otro){
        T dato;
        ConjADT<T> resp = new ConjuntoA();
        if(otro != null){
            for(int i=0; i<cardin; i++){
              dato = conj[i];
              if(!otro.contiene(dato))
                  resp.agregaElemento(dato);
          }  
        }
        else{
            throw new NoSuchElementException();
        }
        
        return resp;
    }
    
    public ConjADT <T> diferenciaR(ConjADT <T> otro){
        ConjADT<T> dif = new ConjuntoA();
        if(otro!= null){
            Iterator<T> it =iterator();
            dif = diferenciaR(otro,dif,it,null);
        }
        return dif;
    }
    
    private ConjADT<T> diferenciaR(ConjADT<T> otro, ConjADT<T> dif, Iterator<T> it, T valor){
        if(!it.hasNext())
            return dif;
        else{
            valor = it.next();
            if(!otro.contiene(valor))
                dif.agregaElemento(valor);
            return diferenciaR(otro,dif,it,valor);
                        
        }
    }
    
    
    
    
    public static void main(String[] args) {
        
        ConjADT<Double> ca = new ConjuntoA<>();
        ConjADT<Double> cb = new ConjuntoA<>();
        ConjADT<Double> cc = new ConjuntoA<>();
        ca.agregaElemento(2.0);
        ca.agregaElemento(4.0);
        cb.agregaElemento(3.0);
        cb.agregaElemento(2.0);
        cc = ca.union(cb);
        System.out.println(cc.toString());
        
    }
    
    
    
}
