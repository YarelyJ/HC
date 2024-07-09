package Toozafari;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Zoologico {

    private ArrayList<Animal> animales;
    private ArrayList<Cria> crias;
    private ArrayList<Vacuna> vacunas;
    
    public Zoologico() {
        animales = new ArrayList<>();
        crias = new ArrayList<>();
        vacunas = new ArrayList<>();
    }

    public void agregarAnimal(Animal animal) {
        animales.add(animal);
    }

    public void agregarCria(Cria cria) {
        crias.add(cria);
    }

    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
    }

    public void mostrarAnimales() {
        for (Animal animal : animales) {
            System.out.println("Nombre: " + animal.getNombre() + ", Edad: " + animal.getEdad() + ", Especie: " + animal.getEspecie());
        }
    }

    public void mostrarCriass() {
        for (Cria cria : crias) {
            System.out.println("Nombre: " + cria.getNombre() + ", Edad: " + cria.getEdad() + ", Especie: " + cria.getEspecie() + ", Madre: " + cria.getMadre().getNombre());
        }
    }

    public void mostrarVacunas() {
        for (Vacuna vacuna : vacunas) {
            System.out.println("Nombre: " + vacuna.getNombre() + ", Fecha de Aplicación: " + vacuna.getFechaAplicacion());
        }
    }
    
    public static class EdadNoValidaException extends Exception {
        public EdadNoValidaException(String message) {
            super(message);
        }
    }

    public class EdadNoValida extends Exception{
        public EdadNoValida(String message) {
            super(message);
       } 
    }
    
    public static class MadreNoEncontradaException extends Exception {
        public MadreNoEncontradaException(String message) {
            super(message);
        }
    }
    
    public static class EspecieNoCoincideException extends Exception {
        public EspecieNoCoincideException(String message) {
            super(message);
        }
    }

    public void ani (Zoologico zoologico) throws MadreNoEncontradaException, EdadNoValidaException, EspecieNoCoincideException, EdadNoValida {
        //Zoologico zoo = new Zoologico();
        //zoo.ani(zoo);
        //Scanner scanner = new Scanner(System.in);

        while (true) {
            try{
                int opcion=Byte.parseByte(JOptionPane.showInputDialog("1. Agregar Animal\n"+"2. Agregar Cria\n"+"3. Agregar Vacuna\n"+"4. Mostrar Animales\n"+
                "5. Mostrar Crias\n"+"6. Mostrar Vacunas\n"+"7. Salir"));
            
            switch (opcion) {
                case 1:
                    String nombreAnimal=JOptionPane.showInputDialog("Ingrese nombre del animal:");
                    //nombreAnimal = scanner.next();
                    int edadAnimal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad del animal:"));
                    //edadAnimal = scanner.nextInt();
                    if (edadAnimal > 10) {
                    throw new EdadNoValida("Edad no valida");
                    }
                    String especieAnimal = JOptionPane.showInputDialog("Ingrese especie del animal:");
                    //especieAnimal = scanner.next();
                    Animal animal = new Animal(nombreAnimal, edadAnimal, especieAnimal);
                    zoologico.agregarAnimal(animal);
                    break;
                    case 2:
                    String nombreCria = JOptionPane.showInputDialog("Ingrese nombre de la cria:");
                    //nombreCria = scanner.next();
                    int edadCria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad de la cria:"));
                    //edadCria = scanner.nextInt();
                    if (edadCria < 0) {
                    throw new EdadNoValidaException("La edad de la cria no puede ser negativa");
                    }
                    if (edadCria > 8) {
                    throw new EdadNoValida("Edad no valida");
                    }
                    String especieCria = JOptionPane.showInputDialog("Ingrese especie de la cria:");
                    //especieCria = scanner.next();
                    String nombreMadre = JOptionPane.showInputDialog("Ingrese nombre de la madre:");
                    //nombreMadre = scanner.next();
                    Animal madre = null;
                    for (Animal animal2 : zoologico.animales) {
                        if (animal2.getNombre().equals(nombreMadre)) {
                            madre = animal2;
                            break;
                        }
                    }
                    if (madre == null) {
                        throw new MadreNoEncontradaException("La madre no se encuentra en el zoológico");
                    }
                    if (madre.getEspecie().equals(especieCria)) {
                    } else {
                       throw new EspecieNoCoincideException("La especie de la madre y la cria deben ser la misma");
                    }
                    Cria cria = new Cria(nombreCria, edadCria, especieCria, madre);
                    zoologico.agregarCria(cria);
                    break;
                case 3:
                    String nombreVacuna = JOptionPane.showInputDialog("Ingrese nombre de la vacuna:");
                    //nombreVacuna = scanner.next();
                    String fechaAplicacion = JOptionPane.showInputDialog("Ingrese fecha de aplicación de la vacuna:");
                    //fechaAplicacion = scanner.next();
                    Vacuna vacuna = new Vacuna(nombreVacuna, fechaAplicacion);
                    zoologico.agregarVacuna(vacuna);
                    break;
                case 4:
                    zoologico.mostrarAnimales();
                    break;
                case 5:
                    zoologico.mostrarCriass();
                    break;
                case 6:
                    zoologico.mostrarVacunas();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Adiós!");
                    zoologico.guardarDatos("datos.txt"); 
                    return;
                default:
                    System.out.println("Opción inválida");
            }
            }catch(NumberFormatException nfe){
                System.out.print("Formato erroneo");
            }catch (MadreNoEncontradaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public void guardarDatos(String filename) {
    try {
        FileWriter writer = new FileWriter(filename);
        for (Animal animal : animales) {
            writer.write("Animal: " + animal.getNombre() + "\t Edad: " + animal.getEdad() + "\t Especie: " + animal.getEspecie() + "\n");
        }
        for (Cria cria : crias) {
            writer.write("Cria: " + cria.getNombre() + "\t Edad: " + cria.getEdad() + "\t Especie: " + cria.getEspecie() + "\t Madre: " + cria.getMadre().getNombre() + "\n");
        }
        for (Vacuna vacuna : vacunas) {
            writer.write("Vacuna: " + vacuna.getNombre() + "\t Fecha de aplicación: " + vacuna.getFechaAplicacion() + "\n");
        }
        writer.close();
        System.out.println("Datos guardados en " + filename);
    } catch (IOException e) {
        System.out.println("Error al guardar datos: " + e.getMessage());
    }
}
}
