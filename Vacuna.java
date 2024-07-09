
package Toozafari;

public class Vacuna {
    private String nombre;
    private String fechaAplicacion;

    public Vacuna(String nombre, String fechaAplicacion) {
        this.nombre = nombre;
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }
}
