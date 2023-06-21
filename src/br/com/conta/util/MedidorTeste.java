package br.com.conta.util;

import br.com.conta.DAO.MedidorDAO;
import br.com.conta.DAO.PosteDAO;
import br.com.conta.DAO.RotaDAO;
import br.com.conta.model.Medidor;
import br.com.conta.model.Poste;

public class MedidorTeste {
    static MedidorDAO medidorDAO = new MedidorDAO();
    static PosteDAO posteDAO = new PosteDAO();
    static RotaDAO rotaDAO = new RotaDAO();

    public static void main(String[] args) {

        System.out.println(medidorDAO.count());

        System.out.println(medidorDAO.selectMedidor(1));

        //salvar
//        Medidor medidor = new Medidor("Descricao do medidor teste1", posteDAO.selectPoste(1), rotaDAO.selectRota(1));
//        medidorDAO.insertMedidor(medidor);
//
//        System.out.println(medidorDAO.count());
    }
}
