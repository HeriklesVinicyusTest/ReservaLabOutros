
package br.com.reserva.upe.modelo;

public class Horario {
    
    private String hora;

    public Horario(String hora) {
        this.hora = hora;
    }

    public Horario() {
    }
    
    

    @Override
    public String toString() {
        return this.hora;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
}
