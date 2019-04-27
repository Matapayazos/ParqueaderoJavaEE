/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.parqueadero.en;

/**
 *
 * @author matapayazos
 */
public class Vehiculo {
    private String Placa;

    public Vehiculo() {
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getPlaca() {
        return Placa;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "Placa=" + Placa + '}';
    }
    
    
}
