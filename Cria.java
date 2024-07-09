
package Toozafari;

public class Cria extends Animal {
    private Animal madre;

    public Cria(String nombre, int edad, String especie, Animal madre) {
        super(nombre, edad, especie);
        this.madre = madre;
    }

    public Animal getMadre() {
        return madre;
    }
}