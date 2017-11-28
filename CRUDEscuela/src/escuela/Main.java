/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import bd.bdConection;
import estructural.ServicioEscuela;
import vistas.GUIPrincipal;

/**
 *
 * @author Guilherme
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicioEscuela ser = new ServicioEscuela();
        ser.cargaDatosDB();
        GUIPrincipal gui = new GUIPrincipal(ser);
        gui.setVisible(true);
    }
    
}
