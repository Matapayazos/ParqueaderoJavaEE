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
            veh.setPlaca(Codigo);
            if(tdao.InsertTicket(veh)==true){
                JOptionPane.showMessageDialog(null, "TICKET GENERADO");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR DE GENERACION DE TICKET");
            }
    }

    public int SiguienteIdTicket(){
        int siguienteNumero = 0;
        if(tdao.selectSiguienteNumero()!=0){
            return siguienteNumero = tdao.selectSiguienteNumero();
        }else{
            return siguienteNumero;
        }        
    }
     
    public ArrayList<Ticket> ListarTickets(){
        tdao = new TicketDAO();
        Ticket tic= new Ticket();
        ArrayList<Ticket> listaTickets = tdao.listTicket();
        if(listaTickets.size() > 0){
            for(int i=0; i< listaTickets.size(); i++){
                String fila[] = new String[3];
                tic = listaTickets.get(i);
                fila[0] = tic.getCodigo()+"";
                fila[1] = tic.getHoraEntrada()+"";
                fila[2] = tic.getEspacioAsignado();
            }
        }
        return listaTickets;
    } 
    
  
}
