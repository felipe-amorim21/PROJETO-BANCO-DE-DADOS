package br.com.conta.util;

import br.com.conta.model.TipoFase;
import  br.com.conta.model.TipoPessoa;
import br.com.conta.DAO.TipoPessoaDAO;

import java.sql.SQLException;
public class TipoPessoaTeste {
    static TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();

    public static void main(String[] args) {

        //count
        System.out.println(tipoPessoaDAO.count());

        //salvar
        TipoPessoa tipoPessoa = new TipoPessoa("Teste");
        tipoPessoaDAO.insertTipoPessoa(tipoPessoa);

        System.out.println(tipoPessoaDAO.count());
    }

}
