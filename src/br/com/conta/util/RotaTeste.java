package br.com.conta.util;

import br.com.conta.DAO.RotaDAO;
import br.com.conta.model.Rota;
import br.com.conta.model.TipoFase;

public class RotaTeste {
    static RotaDAO rotaDAO = new RotaDAO();

    public static void main(String[] args) {
        //count
        System.out.println(rotaDAO.count());

        System.out.println(rotaDAO.selectRota(2));

//        //salvar
//        Rota rota = new Rota("Bairro B");
//        rotaDAO.insertRota(rota);
//
//        System.out.println(rotaDAO.count());
    }
}
