package br.com.conta.util;

import br.com.conta.DAO.MedicaoDAO;
import br.com.conta.DAO.MedidorDAO;
import br.com.conta.DAO.TimeRotaDAO;
import br.com.conta.model.Funcionario;
import br.com.conta.model.Medicao;
import br.com.conta.model.TimeRota;

import java.util.Date;
import java.util.List;

public class MedicaoTeste {

    static MedicaoDAO medicaoDAO = new MedicaoDAO();
    static MedidorDAO medidorDAO = new MedidorDAO();
    static TimeRotaDAO timeRotaDAO = new TimeRotaDAO();

    public static void main(String[] args) {
//        Date d1 = new Date();
        //count
        System.out.println(medicaoDAO.count());

        //salvar
//        Medicao medicao = new Medicao("07", "2023", d1, "2000", medidorDAO.selectMedidor(1), timeRotaDAO.selectTimeRota(1));
//        medicaoDAO.insertMedicao(medicao);

        System.out.println(medicaoDAO.count());

        System.out.println(medicaoDAO.selectMedicao(1));

//        List<Medicao> medicoes = medicaoDAO.selectAllMedicao();
//        medicoes.forEach(System.out::println);
    }
}
