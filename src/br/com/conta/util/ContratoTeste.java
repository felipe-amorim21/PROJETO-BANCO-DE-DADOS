package br.com.conta.util;

import br.com.conta.DAO.*;
import br.com.conta.model.Cobranca;
import br.com.conta.model.Contrato;

import java.util.Date;
import java.util.List;

public class ContratoTeste {

    static ContratoDAO contratoDAO = new ContratoDAO();
    static ClienteDAO clienteDAO = new ClienteDAO();
    static ClasseDAO classeDAO = new ClasseDAO();
    static MedidorDAO medidorDAO = new MedidorDAO();

    public static void main(String[] args) {
        System.out.println(contratoDAO.count());

        Date d1 = new Date();

//        Contrato cobranca = new Contrato(d1, d1, clienteDAO.selectCliente(1), classeDAO.selectClasse(1), medidorDAO.selectMedidor(1));
//        contratoDAO.insertContrato(cobranca);
//
//        //buscar por ID
//        medidor = medidorDAO.selectMedidor(1);
        System.out.println(contratoDAO.selectContrato(1));
////
////
//
//        //Select all
        List<Contrato> contratos = contratoDAO.selectAllContrato();
        contratos.forEach(System.out::println);
//
//        //Delete
//        cobrancaDAO.deleteCobranca(1);
//        cobrancaDAO.selectAllCobranca().forEach(System.out::println);
    }
}
