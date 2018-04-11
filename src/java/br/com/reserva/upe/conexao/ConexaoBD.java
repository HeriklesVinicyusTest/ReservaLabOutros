package br.com.reserva.upe.conexao;

import java.sql.*;

public class ConexaoBD {

    private static Connection con = null;

    public static Connection Conectar() {
        System.out.println("Conectando ao banco...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reserva_upe?useSSL=false", "root", "");
            System.out.println("Conectado.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
        } catch (SQLException e) {
            System.out.println(">>> [Error Driver!]");
            System.out.println(e);
        }
        return con;
    }

    public static void Desconectar() {
        try {
            con.close();
            System.out.println(">>> Conexão Encerrada com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
