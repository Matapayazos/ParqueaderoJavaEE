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
 
   public String[] selectTicketComprobante(int numero) throws SQLException{
        Conectar con = new Conectar();
        Connection conn = con.getConnection();
        
        String sql = "SELECT t.numero, t.fh_entrada, NOW(), MIN(TIMEDIFF(NOW(),t.fh_entrada)), ROUND(((TIMESTAMPDIFF(MINUTE,t.fh_entrada,NOW())))*0.01,2), ROUND((((TIMESTAMPDIFF(MINUTE,t.fh_entrada,NOW())))*0.01)*0.12,2), ROUND(((TIMESTAMPDIFF(MINUTE,t.fh_entrada,NOW()))*0.01)+(((TIMESTAMPDIFF(MINUTE,t.fh_entrada,NOW()))*0.01)*0.12),2),t.placa  FROM ticket t WHERE t.numero="+numero+";"; 
        ResultSet rs = null;
        PreparedStatement ps = null;
        String[] array = new String[8];
        
        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                array[0]= rs.getInt(1)+"";
                array[1]= rs.getString(2);
                array[2]= rs.getString(3);
                array[3]= rs.getString(4);
                array[4]= rs.getString(5);
                array[5]= rs.getString(6);
                array[6]= rs.getString(7);
                array[7]= rs.getString(8);
            } 
        } catch (SQLException e) {
            System.out.println("ERROR EN DAO  \n"+e);
            return null;
        }
      return array;      
    }
   
   public String[] selectTicketComprobantePagado(int numero) throws SQLException{
        Conectar con = new Conectar();
        Connection conn = con.getConnection();
        
        String sql = "SELECT t.numero, t.placa, DATE(c.fecha), t.fh_entrada, c.fh_salida, c.tiempo, c.total FROM ticketpagado t, comprobante c WHERE t.numero = c.numero and t.numero ="+numero+";"; 
        ResultSet rs = null;
        PreparedStatement ps = null;
        String[] array = new String[8];
        
        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                array[0]= rs.getInt(1)+"";
                array[1]= rs.getString(2);
                array[2]= rs.getString(3);
                array[3]= rs.getString(4);
                array[4]= rs.getString(5);
                array[5]= rs.getString(6);              
                array[6]= rs.getDouble(7)+"";
            } 
        } catch (SQLException e) {
            System.out.println("ERROR EN DAO  \n"+e);
            return null;
        }
      return array;      
    }

   public boolean DeleteTicketComprobante(String numero) throws SQLException{
        Conectar con = new Conectar();
        Connection conn = con.getConnection();
        try {
            CallableStatement deleteTicket = conn.prepareCall("{call deleteTicket(?)}");
            deleteTicket.setString(1, numero);
            deleteTicket.executeQuery();
            System.out.println("TicketDAO Eliminado ");
            return true; 
        } catch (SQLException e) {
            System.out.println("Error al Eliminar TicketDAO \nTIPO ERROR:  "+e);
            return false;
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
   
    public ArrayList<Ticketpagado> listTicketPagados(){
        ArrayList<Ticketpagado> listaTicketsPagados = new ArrayList<Ticketpagado>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM ticketpagado;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Ticketpagado ticp = new Ticketpagado();
                ticp.setNumero(rs.getInt(1));
                ticp.setFh_entrada(rs.getString(2));
                ticp.setPlaca(rs.getString(3));
                ticp.setFh_pago(rs.getString(4));
                listaTicketsPagados.add(ticp);
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
        return listaTicketsPagados;
    } 
    
}
