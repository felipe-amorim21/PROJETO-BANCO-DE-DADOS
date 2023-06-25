package br.com.conta.util;

import br.com.conta.DAO.FuncionarioDAO;
import br.com.conta.DAO.PessoaDAO;
import br.com.conta.model.Cliente;
import br.com.conta.model.Funcionario;

import java.util.List;

public class FuncionarioTeste {

    static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    static PessoaDAO pessoaDAO = new PessoaDAO();

    public static void main(String[] args) {
//        //count
//        System.out.println(funcionarioDAO.count());
//
//        //salvar
//        Funcionario funcionario = new Funcionario("213213", pessoaDAO.selectPessoa(8));
//        funcionarioDAO.insertFuncionario(funcionario);
//
//        System.out.println(funcionarioDAO.count());

        System.out.println(funcionarioDAO.selectFuncionario(1));

        List<Funcionario> medidores = funcionarioDAO.selectAllFuncionario();
        medidores.forEach(System.out::println);
    }
}
