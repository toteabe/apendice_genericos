package org.iesvdm;

import org.iesvdm.empresa.Directivo;
import org.iesvdm.empresa.Empleado;
import org.iesvdm.empresa.Encargado;

import java.util.List;

public class UtilCoContraVarianza {

    //                       ArrayList<Encargado>() ----V
    public static void producir(List<? extends Empleado> empleados) {
        // Por qué covarianza en genéricos es sólo READ-ONLY o PRODUCER
        // DADO UN ESQUEMA DE HERENCIA COMO EL SIGUIENTE
        //                              List<? extends Empleado>
        // Persona
        //    |
        //    V                         ¿Qué ocurre si permitimos escribir?
        // Empleado                     empleados.add(new Empleado) => FALLA empleados es ArrayList<Encargado>
        //    |                                 ^                      POR TANTO EN PREVENCIÓN
        //    V                                 |                      NO SE PUEDE ESCRIBIR
        // Encargado                    empleados = ArrayList<Encargado>();
        //    |
        //    V
        // Directivo

        //SOLO ITERABLE POR EL TIPO DEL EXTENDS (? extends Empleado) => Empleado
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }

        //See reference:
        //https://stackoverflow.com/questions/50380642/incompatible-types-capture-of-extends-is-not-convertible-to-capture-of
//FALLA por capture of extends
        for (Encargado encargado: empleados) {
            System.out.println(encargado);
        }
//FALLA por capture of extends
        for (Directivo directivo: empleados) {
            System.out.println(encargado);
        }

    }

    public static void consumir(List<? super Encargado> encargados) {
        // Por qué contravarianza en genéricos es sólo WRITE-ONLY o CONSUMER
        // DADO UN ESQUEMA DE HERENCIA COMO EL SIGUIENTE
        //                              List<? super Encargado>
        // Persona
        //    |
        //    V                         ¿Qué ocurre si permitimos leer?
        // Empleado                     encargados = ArrayList<Empleado>();
        //    |                                 |__________________
        //    V                                                   V
        // Encargado                    for (Encargado empleado: encargados) => FALLA encargados es ArrayList<Empleado>
        //    |                                                                 POR TANTO EN PREVENCIÓN
        //    V                                                                 NO SE PUEDE LEER
        // Directivo


        //FALLA a una referencia subclase no puedes asignarle una referencia superclase
//        Empleado empleado = new Empleado();
//        Encargado encargado = empleado;

        //FUNCIONA
        Encargado encargado = new Encargado();
        Empleado empleado = encargado;

        //FUNCIONA
        encargados.add(new Encargado());

        //FALLA por capture of super
        encargados.add(new Empleado());


    }

}
