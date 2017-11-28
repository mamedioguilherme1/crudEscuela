/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructural;

import bd.bdConection;
import escuela.Escuela;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vistas.IActualizableEscuela;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class ServicioEscuela implements IActualizableEscuela {
    private ArrayList <IActualizableEscuela> v =  new ArrayList<>();
    private static ArrayList <Escuela> arrayEsc = new ArrayList<>();
    private Escuela escuela;
    private String busca;
    public bdConection con = new bdConection();
    
    public ServicioEscuela() {v = new ArrayList<>();}
    public String getBusca() {return busca;}
    public void setBusca(String busca) {this.busca = busca;}
    public Escuela getEscuela() {return escuela;}
    public void addVista(IActualizableEscuela gui){v.add(gui);}

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
    @Override
    public void actualizable(){
        for(IActualizableEscuela gui :v){
            gui.actualizable();
        }
    }
    public void insertar(Escuela e)
    {   
        arrayEsc.add(e);
        insertarBD();
    }
    public void insertarBD()
    {
        String insert = "";
        insert = "INSERT INTO esc VALUES (" + "'"
            +arrayEsc.get(arrayEsc.size() - 1).getIdE()+"','"
            +arrayEsc.get(arrayEsc.size() - 1).getNombre()+"','"
            +arrayEsc.get(arrayEsc.size() - 1).getProfesor()+"','" 
            +arrayEsc.get(arrayEsc.size() - 1).getEstudiante() + "')";
        con.executeUpdateStatement(insert);
        
    }
    public ArrayList<Escuela> getArrayEsc() 
    {
        return arrayEsc; 
    }
    public void cargaDatosDB()
    {
        ResultSet res = null;
        String busca = "SELECT * FROM esc";
        res = con.executeQueryStatement(busca);
        try {
            while(res.next())
            {
               String id = res.getString("IDESCUELA");
               String nEscuela = res.getString("nombreEscuela");
               String profesor = res.getString("profesor");
               String estudiante = res.getString("estudiante");
               Escuela e = new Escuela(id, nEscuela, profesor, estudiante);
               //e.setId(res.getInt("idEscuela"));
               getArrayEsc().add(e);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarDB(String id, String nome, String profesor, String estudiante)
    {
        String busca = "UPDATE esc set NOMBREESCUELA='"+ nome
                +"', PROFESOR='" + profesor
                +"', ESTUDIANTE='" + estudiante
                +"' where IDESCUELA='" + id +"'";
        con.executeQueryStatement(busca);
    }
    public void eliminarDB(String id)
    {
        String busca = "DELETE from esc where IDESCUELA='"+ id +"'";
        con.executeQueryStatement(busca);
    }
    public int contador() 
    {
        int id = 0;
        ResultSet res = null;
        String busca = "SELECT * FROM esc";
        res = con.executeQueryStatement(busca);
        try {
            while(res.next())
            {
               id = res.getInt("idEscuela");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (id+1);
    }
    public Escuela buscar(String id)
    {
        Escuela auxEsc = new Escuela();
        for(int i =0; i < arrayEsc.size(); i++)
        {
            if(id.equals(arrayEsc.get(i).getIdE()))
            {
                auxEsc =  arrayEsc.get(i);
            }
        }
        return auxEsc;
    }
}
