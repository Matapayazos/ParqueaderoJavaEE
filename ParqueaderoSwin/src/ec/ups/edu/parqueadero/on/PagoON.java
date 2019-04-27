/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.parqueadero.on;

import ec.ups.edu.parqueadero.dao.TicketDAO;
import ec.ups.edu.parqueadero.en.Ticket;
import ec.ups.edu.parqueadero.en.Vehiculo;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author matapayazos
 */
public class PagoON {
         
    private TicketDAO tdao = new TicketDAO();
    private Vehiculo veh = new Vehiculo();
    private Ticket tic = new Ticket();
    
    public void RegistrarPago(String Codigo) throws SQLException{
           
          
    }

    public Double  CalcularMonto(){
     
        
        
        
        return null;        
    }
  
}
