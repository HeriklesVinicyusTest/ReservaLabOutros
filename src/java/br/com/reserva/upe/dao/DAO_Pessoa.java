package br.com.reserva.upe.dao;

import br.com.reserva.upe.conexao.ConexaoBD;
import br.com.reserva.upe.modelo.Pessoa;
import br.com.reserva.upe.util.FacesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Pessoa {

    ConexaoBD conect = new ConexaoBD();

    public Pessoa autenticar(Pessoa p) {
        String sql = "select * from pessoa where email= '" + p.getEmail()
                + "' and senha = '" + p.getSenha() + "';";

        try {
            Connection conn = ConexaoBD.Conectar();
            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setTipo(rs.getString("tipo"));
                p.setNome(rs.getString("nome"));
            } else {
                p.setNome("");
                FacesUtil.MensagemErro("Usuário ou senha incorretas!");
            }

            pst.close();
            ConexaoBD.Desconectar();

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return p;
    }

}
