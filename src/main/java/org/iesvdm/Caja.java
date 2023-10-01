package org.iesvdm;

import java.util.Collection;
import java.util.List;

public class Caja<T>{

    private T t;

    public Caja(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void reemplazaPorPrimeroEnColeccion(Collection<? extends T> coleccion) {

        if (coleccion.iterator().hasNext()) {
            T t1 = coleccion.iterator().next();
            this.t = t1;
        }

    }

    public static <T> Caja<T> convertirATipoT(Caja<? super T> cajaSuper) {

        Caja<T> cajaT = (Caja<T>)cajaSuper;

        return cajaT;
    }



    public <U> String escribirJunto(U u) {
        return "" + this.t + u;
    }


    @Override
    public String toString() {
        return "Caja{" +
                "t=" + t +
                '}';
    }
}
