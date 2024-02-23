/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Libros;
import Modelo.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorLibros {
    
    private Libros libro;
    Conexion conectar = new Conexion();
    Connection conectado = (Connection) conectar.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public ControladorLibros() {
    }

    public Libros getVehiculo() {
        return libro;
    }

    public void setLibros(Libros libro) {
        this.libro = libro;
    }

    public void insertarLibros(Libros lib, Personas per) {
        String sql = "call InsertarLibro('" + lib.getTitulo()+ "'," + per.getCedula()+ ",'" + lib.getISBN()+ "','" + lib.getPaginas()
                + "','" + lib.getEdicion()+ "'," + lib.getEditorial() + "," + lib.getCiudad()+ "," + lib.getPais()+ "," + lib.getFechaEdicion()+ " )";

        try {

            ejecutar = (PreparedStatement) conectado.prepareCall(sql);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Libro Registrado");
            } else {
                JOptionPane.showMessageDialog(null, "Resvise la información ingresada");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos" + e);
        }
    }

    public ArrayList listarLibros(String titulo) {
        String sql = "call BuscarLibroPorTitulo(" + titulo + ")";
        ArrayList<Libros> listaLibros = new ArrayList<>();
        try {
            ejecutar = (PreparedStatement) conectado.prepareCall(sql);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                while (resultado.next()) {
                    libro=new Libros();
                    libro.setTitulo(resultado.getString(1));
                    libro.setISBN(resultado.getString(2));
                    libro.setPaginas(resultado.getInt(3));
                    libro.setEdicion(resultado.getInt(4));
                    libro.setEditorial(resultado.getString(5));
                    libro.setCiudad(resultado.getString(6));
                    libro.setPais(resultado.getString(7));
                    libro.setFechaEdicion(resultado.getDate(8));
                    libro.add(listaLibros);
                }
            }
            return listaLibros;
        } catch (SQLException e) {
            System.out.println("Error en la base de datos " + e);
            return null;
        }
    }
    
    public void ModificarLibros (Libros lib, Personas per) {
        String sql = "call ModificarLibro('" + lib.getTitulo()+ "'," + per.getCedula()+ ",'" + lib.getISBN()+ "','" + lib.getPaginas()
                + "','" + lib.getEdicion()+ "'," + lib.getEditorial() + "," + lib.getCiudad()+ "," + lib.getPais()+ "," + lib.getFechaEdicion()+ " )";

        try {

            ejecutar = (PreparedStatement) conectado.prepareCall(sql);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Libro Modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Resvise la información ingresada");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos" + e);
        }
    
    }

        
        public void EliminarLibro(String titulo){
            String sql = "call EliminarLibroPorTitulo(" + titulo + ")";
        ArrayList<Libros> listaLibros = new ArrayList<>();
        try {
            ejecutar = (PreparedStatement) conectado.prepareCall(sql);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                while (resultado.next()) {
                    libro=new Libros();
                    libro.setTitulo(resultado.getString(1));
                    libro.setISBN(resultado.getString(2));
                    libro.setPaginas(resultado.getInt(3));
                    libro.setEdicion(resultado.getInt(4));
                    libro.setEditorial(resultado.getString(5));
                    libro.setCiudad(resultado.getString(6));
                    libro.setPais(resultado.getString(7));
                    libro.setFechaEdicion(resultado.getDate(8));
                    libro.add(listaLibros);
                }
            }
            return listarLibros;
        } catch (SQLException e) {
            System.out.println("Error en la base de datos " + e);
            return null;
        }
        }
            
        
            
     
    
}
