/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.parqueadero.dao;

import ec.ups.edu.parqueadero.en.Ticket;
import ec.ups.edu.parqueadero.en.Vehiculo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matapayazos
 */
public class TicketDAO {
       
        
    public boolean InsertTicket(Vehiculo vehiculo) throws SQLException{
        Conectar con = new Conectar();
        Connection conn = con.getConnection();
        try {
            CallableStatement insertCliente = conn.prepareCall("{call insertTicket(?)}");
            insertCliente.setString(1, vehiculo.getPlaca());
            insertCliente.executeQuery();
            System.out.println("TicketDAO Registrado ");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al Registrar ClienteDAO \nTIPO ERROR:  "+e);
            return false;
        }
    }

    public int selectSiguienteNumero(){
        Conectar con = new Conectar();
        int siguienteNumero = 0;
        int obtenerNumero;
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'parqueadero' AND TABLE_NAME = 'ticket';";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                obtenerNumero = rs.getInt(1);
                siguienteNumero = obtenerNumero;
            }
            return siguienteNumero;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
   
   public ArrayList<Ticket> listTicket(){
        ArrayList<Ticket> listaTicket = new ArrayList<Ticket>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM ticket;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Ticket tic = new Ticket();
                tic.setCodigo(rs.getString(1));
                tic.setHoraEntrada(rs.getDate(2));
                tic.setEspacioAsignado(rs.getString(3));
                listaTicket.add(tic); 
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }finally{
            try{
                ps.close();
                rs.close();
                conec.desconectar();
            }catch(Exception ex){return null;}
        }
        return listaTicket;
    }
}
