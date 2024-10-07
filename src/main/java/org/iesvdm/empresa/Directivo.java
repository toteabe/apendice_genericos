package org.iesvdm.empresa;

import java.math.BigDecimal;

public class Directivo extends Encargado{

    private BigDecimal bonus;

    public Directivo(String nombre, String apellido, String correo, String direccion, String telefono, String dni, int codigoEmpleado, String sede, String departamento, BigDecimal bonus) {
        super(nombre, apellido, correo, direccion, telefono, dni, codigoEmpleado, sede, departamento);
        this.bonus = bonus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
}
