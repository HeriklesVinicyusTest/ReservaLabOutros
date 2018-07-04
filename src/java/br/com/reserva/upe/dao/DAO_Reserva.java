package br.com.reserva.upe.dao;

import br.com.reserva.upe.conexao.ConexaoBD;
import br.com.reserva.upe.modelo.Reserva;
import br.com.reserva.upe.util.FacesUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hilton
 */
public class DAO_Reserva {

    ConexaoBD conect = new ConexaoBD();

    public void Atualizar2(Reserva r, Integer id) throws SQLException {

        String sql = "UPDATE reserva SET dataDaReserva= ?, turno= ?, horario= ?, laboratorio=?, descricao=? WHERE id = '" + id + "';";

        try {
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;

            pst = conn.prepareStatement(sql);

            pst.setString(1, r.getData());
            pst.setString(2, r.getTurno());
            pst.setString(3, r.getHorario());
            pst.setString(4, r.getLaboratorio());
            pst.setString(5, r.getDescricao());

            pst.execute();

            pst.close();
            ConexaoBD.Desconectar();
            //FacesUtil.MensagemIformativa("A reserva atualizada com sucesso!");

        } catch (SQLException a) {
            a.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível atualizar a reserva! :/");
        }

    }
    
    public List<Reserva> listarMinhasReservas(Integer idPessoa) {
        String sql = "SELECT *from reserva where idPessoa = " + idPessoa + ";";
        List<Reserva> lista = new ArrayList<Reserva>();

        try {
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("dataDaReserva");
                String Turno = rs.getString("turno");
                String Horario = rs.getString("horario");
                String Laboratorio = rs.getString("laboratorio");
                String Descricao = rs.getString("descricao");

                Reserva re = new Reserva();

                re.setId(id);
                re.setData(data);
                re.setTurno(Turno);
                re.setHorario(Horario);
                re.setLaboratorio(Laboratorio);
                re.setDescricao(Descricao);

                lista.add(re);
            }
            pst.close();
            ConexaoBD.Desconectar();
            return lista;

        } catch (Exception e) {
            // System.out.print("Não foi possível fazer a conexão com o banco");
            e.printStackTrace();
            FacesUtil.MensagemErro("Não foi possível fazer a listagem das suas reservas! :/");
            return null;
        }
    }

}
