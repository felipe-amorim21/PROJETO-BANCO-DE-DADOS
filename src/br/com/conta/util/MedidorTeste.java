package br.com.conta.util;

import br.com.conta.DAO.MedidorDAO;
import br.com.conta.DAO.PosteDAO;
import br.com.conta.DAO.RotaDAO;
import br.com.conta.model.Medidor;
import br.com.conta.model.Poste;

import java.sql.SQLException;
import java.util.List;

public class MedidorTeste {
    static MedidorDAO medidorDAO = new MedidorDAO();
    static PosteDAO posteDAO = new PosteDAO();
    static RotaDAO rotaDAO = new RotaDAO();

    public static void main(String[] args) throws SQLException {

//        System.out.println(medidorDAO.count());
//
//        System.out.println(medidorDAO.selectMedidor(4));
//
//        Medidor medidor = new Medidor(1,"medidor 01", posteDAO.selectPoste(1), rotaDAO.selectRota(2));
//        medidorDAO.insertMedidor(medidor);
//
//        //buscar por ID
//        medidor = medidorDAO.selectMedidor(1);
        System.out.println(medidorDAO.selectMedidor(1));
//
//
//        //Update
//        medidor.setDescricao("Medidor-7");
//        medidorDAO.updateMedidor(medidor);
//        medidor = medidorDAO.selectMedidor(2);
//        System.out.println(medidorDAO.selectMedidor(2));

        //Select all
        List<Medidor> medidores = medidorDAO.selectAllMedidor();
        medidores.forEach(System.out::println);

        //Delete
//        medidorDAO.deleteMedidor(24);
//        medidorDAO.selectAllMedidor().forEach(System.out::println);
    }
}
