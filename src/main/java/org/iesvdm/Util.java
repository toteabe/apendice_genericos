package org.iesvdm;

import java.util.List;

public class Util {

    public static <T> void llenaConT(List<? super T> listSuperT, T t) {
        listSuperT.add(t);
    }
}
