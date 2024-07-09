package Toozafari;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Reguistro extends JFrame implements ActionListener{
    JLabel lbuser, lbpass, lberr;
    JTextField txuser;
    JPasswordField txpass;
    JButton btnace,btnsal;
    private int intentos = 0;
    private String usuarioGuardado,  contraseñaGuardada;
    public Reguistro (){
        lbuser=new JLabel ("User");
        lbuser.setBounds(50,50,100,30);
        add(lbuser);
        lbpass=new JLabel ("Password");
        lbpass.setBounds(50,100,100,30);
        add(lbpass);
        txuser=new JTextField ();
        txuser.setBounds(130,50,100,30);
        add(txuser); 
        txpass=new JPasswordField ();
        txpass.setBounds(130,100,100,30);
        add(txpass);
        btnace=new JButton("Aceptar");
        btnace.setBounds(60,150,100,30);
        btnace.addActionListener(this);
        add (btnace);
        btnsal=new JButton("Cancelar");
        btnsal.setBounds(200,150,100,30);
        btnsal.addActionListener(this);
        add (btnsal);
        lberr=new JLabel ();
        lberr.setBounds(50,200,500,30);
        add (lberr);
        
        try {
            FileReader lect = new FileReader("Usuario.txt");
            BufferedReader jd = new BufferedReader(lect);
            usuarioGuardado = jd.readLine();
            if (usuarioGuardado == null) {
                usuarioGuardado = "";
            }
            jd.close();

            FileReader lectu = new FileReader("Contraseña.txt");
            BufferedReader bl = new BufferedReader(lectu);
            contraseñaGuardada = bl.readLine();
            if (contraseñaGuardada == null) {
                contraseñaGuardada = "";
            }
            lectu.close();
            bl.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    
    private boolean validar(String usuario, String contraseña) {
        return usuario.equals(usuarioGuardado) && contraseña.equals(contraseñaGuardada);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnace) {
        String usuario = txuser.getText();
        String contraseña = new String(txpass.getPassword());
        if (validar(usuario, contraseña)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            dispose();
            Zoologico zool = new Zoologico();
            try {
                zool.ani(zool);
            } catch (Zoologico.EdadNoValidaException | Zoologico.EspecieNoCoincideException | Zoologico.EdadNoValida | Zoologico.MadreNoEncontradaException ex) {
                Logger.getLogger(Reguistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            intentos++;
            if (intentos < 3) {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intentos restantes: " + (3 - intentos));
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. La cuenta ha sido bloqueada.");
                dispose();
            }
        }
    } else if (e.getSource() == btnsal) {
        dispose();
    }
}

    public JButton botonCancelar(){
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(50, 125, 100, 25);
        Reguistro.EventoBoton2 evento2 = new Reguistro.EventoBoton2();
        botonCancelar.addActionListener(evento2); 
        return botonCancelar;
    }

    private class EventoBoton2 implements ActionListener{
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    }
    public static void main(String[] args) {
        Reguistro ventana = new Reguistro();
        ventana.setLayout(null);
        ventana.setTitle("Iniciar Seción");
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(350,250); 
        ventana.setVisible(true);
    }    
}
