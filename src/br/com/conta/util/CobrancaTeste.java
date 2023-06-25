package br.com.conta.util;

import br.com.conta.DAO.CobrancaDAO;
import br.com.conta.DAO.MedicaoDAO;
import br.com.conta.DAO.TarifaDAO;
import br.com.conta.model.Cobranca;
import br.com.conta.model.Medidor;

import java.sql.SQLException;
import java.util.List;

public class CobrancaTeste {

    static CobrancaDAO cobrancaDAO = new CobrancaDAO();
    static TarifaDAO tarifaDAO = new TarifaDAO();
    static MedicaoDAO medicaoDAO = new MedicaoDAO();

    public static void main(String[] args) throws SQLException {
        System.out.println(cobrancaDAO.count());


//        Cobranca cobranca = new Cobranca("07","2023", tarifaDAO.selectTarifa(1), medicaoDAO.selectMedicao(1));
//        cobrancaDAO.insertCobranca(cobranca);
//
//        //buscar por ID
//        medidor = medidorDAO.selectMedidor(1);
        System.out.println(cobrancaDAO.selectCobranca(2));
////
////
//
//        //Select all
        List<Cobranca> cobrancas = cobrancaDAO.selectAllCobranca();
        cobrancas.forEach(System.out::println);
//
//        //Delete
//        cobrancaDAO.deleteCobranca(1);
//        cobrancaDAO.selectAllCobranca().forEach(System.out::println);
    }
}
