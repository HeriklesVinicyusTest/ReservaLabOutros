package br.com.reserva.upe.dao;

import br.com.reserva.upe.conexao.ConexaoBD;
import br.com.reserva.upe.modelo.Horario;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.FacesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO_Horario {
    
    public List<Horario> horariosSemReserva(String data, String laboratorio){
        String sql = "SELECT hora from horario where hora not in (SELECT horario from reserva where dataDaReserva = '"+ data +"' and laboratorio = '"+laboratorio+"' )order by horario.hora;";
        List<Horario> horasSemReserva = new ArrayList<Horario>();
        
        try {
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);

           while (rs.next()) {

                String hora = rs.getString("hora");

                Horario ho = new Horario();

                ho.setHora(hora);

                horasSemReserva.add(ho);
            }
            pst.close();
            ConexaoBD.Desconectar();
            

        } catch (Exception e) {
            // System.out.print("Não foi possível fazer a conexão com o banco");
            e.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível fazer a listagem das horas indisponiveis! :/");
            
        }
        return horasSemReserva;
    }

}
