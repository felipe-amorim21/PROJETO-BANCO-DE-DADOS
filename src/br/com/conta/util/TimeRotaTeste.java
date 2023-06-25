package br.com.conta.util;

import br.com.conta.DAO.FuncionarioDAO;
import br.com.conta.DAO.TarefaRotaDAO;
import br.com.conta.DAO.TimeRotaDAO;
import br.com.conta.model.Tarifa;
import br.com.conta.model.TimeRota;

import java.sql.Time;
import java.util.List;

public class TimeRotaTeste {

    static TimeRotaDAO timeRotaDAO = new TimeRotaDAO();
    static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    static TarefaRotaDAO tarefaRotaDAO = new TarefaRotaDAO();

    public static void main(String[] args) {

//        //count
//        System.out.println(timeRotaDAO.count());
//
        System.out.println(timeRotaDAO.selectTimeRota(1));
//
//        //salvar
//        TimeRota timeRota = new TimeRota(funcionarioDAO.selectFuncionario(1), tarefaRotaDAO.selectTarefaRota(1));
//        timeRotaDAO.insertTimeRota(timeRota);
//
//        System.out.println(timeRotaDAO.count());

//        List<TimeRota> medidores = timeRotaDAO.selectAllTimeRota();
//        medidores.forEach(System.out::println);
    }
}
