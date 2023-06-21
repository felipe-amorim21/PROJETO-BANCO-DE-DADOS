package br.com.conta.util;

import br.com.conta.DAO.TipoFaseDAO;
import  br.com.conta.model.TipoFase;

import java.sql.SQLException;
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
    }
}
