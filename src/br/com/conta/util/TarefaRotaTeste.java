package br.com.conta.util;

import br.com.conta.DAO.RotaDAO;
import br.com.conta.DAO.TarefaRotaDAO;
import br.com.conta.model.Medidor;
import br.com.conta.model.TarefaRota;

import java.util.Date;
import java.util.List;

public class TarefaRotaTeste {
    static TarefaRotaDAO tarefaRotaDAO = new TarefaRotaDAO();
    static RotaDAO rotaDAO = new RotaDAO();

    public static void main(String[] args) {

//        Date d1 = new Date();
//
//        //count
//        System.out.println(tarefaRotaDAO.count());
//
//        System.out.println(tarefaRotaDAO.selectTarefaRota(1));
//
//        //salvar
//        TarefaRota tarefaRota = new TarefaRota("Observacao", d1, d1, "tarefa", rotaDAO.selectRota(1));
//        tarefaRotaDAO.insertTarefaRota(tarefaRota);
//
//        System.out.println(tarefaRotaDAO.count());
        List<TarefaRota> medidores = tarefaRotaDAO.selectAllTarefaRota();
        medidores.forEach(System.out::println);


    }
}
