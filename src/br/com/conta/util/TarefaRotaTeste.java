package br.com.conta.util;

import br.com.conta.DAO.RotaDAO;
import br.com.conta.DAO.TarefaRotaDAO;
import br.com.conta.model.TarefaRota;
import br.com.conta.model.TipoFase;

import java.util.Date;

public class TarefaRotaTeste {
    static TarefaRotaDAO tarefaRotaDAO = new TarefaRotaDAO();
    static RotaDAO rotaDAO = new RotaDAO();

    public static void main(String[] args) {

        Date d1 = new Date();

        //count
        System.out.println(tarefaRotaDAO.count());

        //salvar
        TarefaRota tarefaRota = new TarefaRota("Observacao", d1, d1, "tarefa", rotaDAO.selectRota(1));
        tarefaRotaDAO.insertTarefaRota(tarefaRota);

        System.out.println(tarefaRotaDAO.count());

    }
}
