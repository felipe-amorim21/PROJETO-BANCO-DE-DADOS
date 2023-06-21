package br.com.conta.util;

import br.com.conta.DAO.PosteDAO;
import br.com.conta.model.Poste;

import java.sql.SQLException;
public class PosteTeste {
    static PosteDAO posteDAO = new PosteDAO();

    public static void main(String[] args) throws SQLException{
        //count
        System.out.println(posteDAO.count());

        //salvar
        Poste poste = new Poste("poste-04", "4.5130", "118.7552", "");
        posteDAO.insertPoste(poste);

        System.out.println(posteDAO.count());
    }
}
