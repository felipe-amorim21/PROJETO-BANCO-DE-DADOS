package br.com.conta.util;

import br.com.conta.DAO.PessoaDAO;
import br.com.conta.DAO.TipoPessoaDAO;
import br.com.conta.model.Pessoa;

import java.util.List;

public class PessoaTeste {
    static PessoaDAO pessoaDAO = new PessoaDAO();
    static TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();

    public static void main(String[] args) {
//        //count
//        System.out.println(pessoaDAO.count());
//
//        //salvar
//        Pessoa pessoa = new Pessoa("Lucas", null, "37283947123" ,tipoPessoaDAO.selectTipoPessoa(2));
//        pessoaDAO.insertPessoa(pessoa);
//
//        System.out.println(pessoaDAO.count());

        System.out.println(pessoaDAO.selectPessoa(8));

        List<Pessoa> medidores = pessoaDAO.selectAllPessoa();
        medidores.forEach(System.out::println);
    }
}
