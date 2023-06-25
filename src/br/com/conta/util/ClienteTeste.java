package br.com.conta.util;

import br.com.conta.DAO.ClienteDAO;
import br.com.conta.DAO.PessoaDAO;
import br.com.conta.model.Cliente;
import br.com.conta.model.Pessoa;

import java.util.List;

public class ClienteTeste {

    static ClienteDAO clienteDAO = new ClienteDAO();
    static PessoaDAO pessoaDAO = new PessoaDAO();

    public static void main(String[] args) {
//        //count
//        System.out.println(clienteDAO.count());
//
//        //salvar
//        Cliente cliente = new Cliente("123232", "213213", pessoaDAO.selectPessoa(2));
//        clienteDAO.insertCliente(cliente);
//
//        System.out.println(clienteDAO.count());

        System.out.println(clienteDAO.selectCliente(1));

        List<Cliente> medidores = clienteDAO.selectAllCliente();
        medidores.forEach(System.out::println);
    }
}
