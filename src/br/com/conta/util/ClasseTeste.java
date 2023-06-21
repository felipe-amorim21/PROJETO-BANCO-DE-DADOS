package br.com.conta.util;

import br.com.conta.DAO.ClasseDAO;
import br.com.conta.DAO.TipoFaseDAO;
import br.com.conta.model.Classe;

import java.util.List;

public class ClasseTeste {
    static ClasseDAO classeDAO = new ClasseDAO();
    static TipoFaseDAO tipoFaseDAO = new TipoFaseDAO();

    public static void main(String[] args) {

        System.out.println(classeDAO.count());

        System.out.println(classeDAO.selectClasse(1));

//        //salvar
//        Classe classe = new Classe("interior", tipoFaseDAO.selectTipoFase(1));
//        classeDAO.insertClasse(classe);
//
//        System.out.println(classeDAO.count());

        List<Classe> classe = classeDAO.selectAllClasse();
        classe.forEach(System.out::println);
    }
}
