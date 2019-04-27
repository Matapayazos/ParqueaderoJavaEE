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
public class TicketON {
        
    private TicketDAO tdao = new TicketDAO();
    private Vehiculo veh = new Vehiculo();
    private Ticket tic = new Ticket();
    
    public void RegistrarTicket(String placa) throws SQLException{
            veh.setPlaca(placa);
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
    
    public String[] ExtraerTicketParaComprobante(int numero) throws SQLException{ 
        String[] vector = tdao.selectTicketComprobante(numero);

        if(vector[0]==null){
            JOptionPane.showMessageDialog(null, "TICKET NO ENCONTRADO");
            return null;
        }else{
            System.out.println(vector[0]+" -- "+vector[1]+" -- "+vector[2]+" -- "+vector[3]+" -- "+vector[4]+" -- "+vector[5]+" -- "+vector[6]);
            return vector;
        }     
    }
    public String[] ExtraerTicketParaComprobantePagado(int numero) throws SQLException{ 
        String[] vector = tdao.selectTicketComprobantePagado(numero);

        if(vector[0]==null){
            JOptionPane.showMessageDialog(null, "TICKET NO ENCONTRADO");
            return null;
        }else{
            System.out.println(vector[0]+" -- "+vector[1]+" -- "+vector[2]+" -- "+vector[3]+" -- "+vector[4]+" -- "+vector[5]+" -- "+vector[6]);
            return vector;
        }     
    }

    public void EliminarTicketComprobante(int numero) throws SQLException{
            tic.setNumero(numero);
            if(tdao.DeleteTicketComprobante(numero)==true){
            }else{
                JOptionPane.showMessageDialog(null, "ERROR DE ELMINACION DE TICKET PARA PAGO DE COMPROBANTE");
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
                fila[0] = tic.getNumero()+"";
                fila[1] = tic.getFh_entrada();
                fila[2] = tic.getPlaca();
                //String a = fila[0]+"   "+fila[1]+"   "+fila[2]; 
                //System.out.println("\n"+a+"\n");
            }
        }
        return listaTickets;
    } 
    
    public ArrayList<Ticketpagado> ListarTicketsPagados(){
        tdao = new TicketDAO();
        Ticket tic= new Ticket();
        ArrayList<Ticketpagado> listaTicketsPagados = tdao.listTicketPagados();
        if(listaTicketsPagados.size() > 0){
            for(int i=0; i< listaTicketsPagados.size(); i++){
                String fila[] = new String[4];
                ticp = listaTicketsPagados.get(i);
                fila[0] = ticp.getNumero()+"";
                fila[1] = ticp.getFh_entrada();
                fila[2] = ticp.getPlaca();
                fila[3] = ticp.getFh_pago();
                //String a = fila[0]+"   "+fila[1]+"   "+fila[2]; 
                //System.out.println("\n"+a+"\n");
            }
        }
        return listaTicketsPagados;
    }
    
}
