
package Toozafari;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Llamar {
    public static void main (String [] args){
        Zoologico zool = new Zoologico();
        try {
                zool.ani(zool);
            } catch (Zoologico.EdadNoValidaException | Zoologico.EspecieNoCoincideException | Zoologico.EdadNoValida | Zoologico.MadreNoEncontradaException ex) {
                Logger.getLogger(Reguistro.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
