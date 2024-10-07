package org.iesvdm.empresa;

public class Encargado extends Empleado{

    protected String departamento;

    public Encargado() {}

    public Encargado(String nombre, String apellido, String correo, String direccion, String telefono, String dni, int codigoEmpleado, String sede, String departamento) {
        super(nombre, apellido, correo, direccion, telefono, dni, codigoEmpleado, sede);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
