/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.parqueadero.en;

import java.util.Date;

/**
 *
 * @author matapayazos
 */
public class Ticket {
    private String Codigo;
    private Date HoraEntrada;
    private String EspacioAsignado;

    public Ticket() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public Date getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(Date HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public String getEspacioAsignado() {
        return EspacioAsignado;
    }

    public void setEspacioAsignado(String EspacioAsignado) {
        this.EspacioAsignado = EspacioAsignado;
    }

    @Override
    public String toString() {
        return "Ticket{" + "Codigo=" + Codigo + ", HoraEntrada=" + HoraEntrada + ", EspacioAsignado=" + EspacioAsignado + '}';
    }
    
    
    
}
