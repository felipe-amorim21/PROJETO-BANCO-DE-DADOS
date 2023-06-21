package br.com.conta.util;

import br.com.conta.DAO.ClasseDAO;
import br.com.conta.DAO.TarifaDAO;
import br.com.conta.model.TarefaRota;
import br.com.conta.model.Tarifa;

import java.util.Date;

public class TarifaTeste {

    static TarifaDAO tarifaDAO = new TarifaDAO();
    static ClasseDAO classeDAO = new ClasseDAO();

    public static void main(String[] args) {

        Date d1 = new Date();

        //count
        System.out.println(tarifaDAO.count());

        System.out.println(classeDAO.selectClasse(1));

        //salvar
        Tarifa tarifa = new Tarifa("0.23%", "2023.2", d1, d1, classeDAO.selectClasse(2));
        tarifaDAO.insertTarifa(tarifa);

        System.out.println(tarifaDAO.count());
    }
}
