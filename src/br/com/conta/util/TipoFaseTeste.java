package br.com.conta.util;

import br.com.conta.DAO.TipoFaseDAO;
import br.com.conta.model.Medidor;
import  br.com.conta.model.TipoFase;

import java.sql.SQLException;
import java.util.List;

public class TipoFaseTeste {
    static TipoFaseDAO tipoFaseDAO = new TipoFaseDAO();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(tipoFaseDAO.count());

        System.out.println(tipoFaseDAO.selectTipoFase(2));

//        //salvar
//        TipoFase tipoFase = new TipoFase("teste");
//        tipoFaseDAO.insertTipoFase(tipoFase);
//
//        System.out.println(tipoFaseDAO.count());

        List<TipoFase> medidores = tipoFaseDAO.selectAllTipoFase();
        medidores.forEach(System.out::println);
    }
}
