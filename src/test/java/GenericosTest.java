import org.iesvdm.Caja;
import org.iesvdm.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericosTest {

    @Test
    public void testInstanceOfClassCaja() {

        Caja<Integer> cajaInteger = new Caja<>(1);

        Caja<String> cajaString = new Caja<>("UNO");

        Assertions.assertTrue(cajaString instanceof Caja<?>);
        Assertions.assertTrue(cajaInteger instanceof Caja);
        Assertions.assertTrue(Caja.class.isInstance(cajaString));
        Assertions.assertTrue(cajaString.getClass().isInstance(cajaInteger));

        //Aunque sean instancias del mismo tipo para instanceof
        //No pueden asignarse entre sí, debido a la comprobación en tiempo de compilación
        //de los genéricos.
        //cajaString = cajaInteger;

    }

    @Test
    public void testMetodoGenericoCaja() {
        Caja<Integer> cajaInteger = new Caja<>(1);

        String junto = cajaInteger.<Double>escribirJunto(0.1d);

        Assertions.assertEquals("10.1", junto);
    }

    @Test
    public void testCOVARIANZAListExtendsNumber() {

        //No hay herencia en genericos
        //Produce error de compilación
        //NO EXISTE VARIANZA EN LOS TIPOS DE GENÉRICOS
        //List<Number> listNumber = new ArrayList<Integer>();

        //NOTA: LA VARIANZA ES EL MECANISMO DE POLIMORFISMO DE LA HERENCIA
        // C extends B extends A
        // C ------> B ------> A
        // A a = new B();
        // A a = new C();
        // B b = new C();

        // EN JAVA NO EXISTE VARIANZA DIRECTA EN LOS TIPOS GENÉRICOS
        // Z<C> x-------> Z<B> x------> Z<A>
        // SE HACE MEDIANTE LA COVARIANZA: ? extends T

        List<Number> listNumber = new ArrayList<>();

        Integer i = 1;
        listNumber.add(i);

        Assertions.assertEquals(1, listNumber.size());

        // COVARIANZA: ? extends T

        //No hay error de compilación por el uso de ? extends Number
        //SE DICE QUE EL PARÁMETRO DEL TIPO ES COVARIANTE
        //      COVARIANTE
        //         |
        //         V
        List<? extends Number> listExtNumber = new ArrayList<Integer>();
        //Error de compilación
        //listExtNumber.add(i);
        //Pero sólo se puede añadir null, PARA NO CORROMPER LA MEMORIA, LISTAS DE UN SÓLO TIPO EN GENÉRICOS
        listExtNumber.add(null);

        //Debe existir previamente
        List<Integer> listInteger = new ArrayList<>();
        listInteger.add(i);
        //Y asignando uno ya creado con elementos, sí se puede leer ? extends U (COVARIANTE)
        // , en este caso U = Number
        List<? extends Number> listExtNumber2 = listInteger;
        for (Number number: listExtNumber2
             ) {
            System.out.println(number);
            System.out.println((Integer)number);
        }

    }

    @Test
    public void testCONTRAVARIANZAListSuperNumber() {

        //CONTRAVARIANZA
        // ? super T ==>
        Integer i = 1;

        //CONTRAVARIANTE
        //       |
        //       V
        List<? super Number> listSupNumber = new ArrayList<Number>();


        //CONTRAVARIANTE
        //       |
        //       V
        List<? super Number> listSupNumber2 = new ArrayList<Object>();
        //Object es super de Number --------------------------^
        //SE DICE QUE EL PARÁMETRO DEL TIPO ES CONTRAVARIANTE


        //Puedo añadir Integer
        listSupNumber.add(i);
        //Object es super de Number
        //error de compilación al añadir object
        //Object obj = new Object();
        //listSupNumber.add(obj);

        listSupNumber2.add(i);
        //Object es super de Number
        //error de compilación al añadir object
        //Object obj = new Object();
        //listSupNumber2.add(obj);

        //Error de compilación
        //for (Number number: listSupNumber
        //     ) {
        //          System.out.println(number);
        //}

        //Sólo se puede iterar a Object
        for (Object obj: listSupNumber
        ) {
            System.out.println(obj);
        }

        Assertions.assertTrue(true);


        //CONTRAVARIANTE
        //       |
        //       V
        List<? super Integer> listSupInteger = new ArrayList<Number>();
        //Object es super de Number --------------------------^
        //SE DICE QUE EL PARÁMETRO DEL TIPO ES CONTRAVARIANTE

        //Puedo añadir Integer
        listSupInteger.add(i);
        //Object es super de Number
        //error de compilación al añadir object
        //Object obj = new Object();
        //listSupNumber.add(obj);

        listSupInteger.add(i);
        //Object es super de Number
        //error de compilación al añadir object
        //Object obj = new Object();
        //listSupNumber2.add(obj);

        //Error de compilación
        //for (Integer integer: listSupInteger
        //     ) {
        //          System.out.println(integer);
        //}

        //Sólo se puede iterar a Object
        for (Object obj: listSupInteger
        ) {
            System.out.println(obj);
        }

        Assertions.assertTrue(true);
    }

    @Test
    public void testProductor_extendsT() {
        Integer i = 1;
        Caja<Number> cajaNumber = new Caja<>(i);

        List<Short> listShort = new ArrayList<>();
        Short s = 2;
        listShort.add(s);

        //Firma de productor extends T
        //    public void reemplazaPorPrimeroEnColeccion(Collection<? extends T> coleccion) {
        cajaNumber.reemplazaPorPrimeroEnColeccion(listShort);

        Assertions.assertEquals(s, cajaNumber.getT());
    }

    @Test
    public void testConsumidor_superT() {
        List<Number> listNumber = new ArrayList<>();
        Integer i = 1;

        //                                      CONTRAVARIANTE
        //                                          |
        //                                          V
        //public static <T> void llenaConT(List<? super T> listSuperT, T t) {
        Util.llenaConT(listNumber, i);

        Assertions.assertEquals(i, listNumber.iterator().next());


    }

    @Test
    public void convertirATipoTTest() {

        Integer i = 1;
        Caja<Number> cajaNumber = new Caja<>(i);
        Number number = cajaNumber.getT();

        Assertions.assertTrue( cajaNumber.getT() instanceof Number);

        //                                              CONTRAVARIANTE
        //                                                  |
        //                                                  V
        //public static <T> Caja<T> convertirATipoT(Caja<? super T> cajaSuper) {
        Caja<Integer> cajaInteger = Caja.convertirATipoT(cajaNumber);

        Integer iNumber = cajaInteger.getT();

        Assertions.assertTrue(cajaInteger.getT() instanceof Integer);

    }

}
