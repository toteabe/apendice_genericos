package org.iesvdm.empresa;

public class Empleado extends Persona {

    protected int codigoEmpleado;
    protected String sede;

    public Empleado() {}

    public Empleado(String nombre, String apellido, String correo, String direccion, String telefono, String dni, int codigoEmpleado, String sede) {
        super(nombre, apellido, correo, direccion, telefono, dni);
        this.codigoEmpleado = codigoEmpleado;
        this.sede = sede;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
