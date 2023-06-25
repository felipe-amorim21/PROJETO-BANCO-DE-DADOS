package br.com.conta.DAO;

import br.com.conta.model.Pessoa;
import br.com.conta.model.TipoPessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends ConexaoDB{
    private static final String INSERT_PESSOA_SQL = "INSERT INTO pessoa (nome, cpf, cnpj, id_tipo_pessoa) VALUES (?, ?, ?, ?);";
    private static final String SELECT_PESSOA_BY_ID = "SELECT * FROM pessoa p inner join tipo_pessoa t on t.id = p.id_tipo_pessoa WHERE p.id = ?;";
    private static final String SELECT_ALL_PESSOA = "SELECT * FROM pessoa p inner join tipo_pessoa t on t.id = p.id_tipo_pessoa;";
    private static final String DELETE_PESSOA_SQL = "DELETE FROM pessoa WHERE id = ?;";
    private static final String UPDATE_PESSOA_SQL = "UPDATE pessoa SET nome = ?, cpf = ?, cnpj = ?, id_tipo_pessoa = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM pessoa;";

    static TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();



    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void insertPessoa(Pessoa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_PESSOA_SQL)) {
            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getCpf());
            preparedStatement.setString(3, entidade.getCnpj());
            preparedStatement.setInt(4, entidade.getIdTipoPessoa().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Pessoa selectPessoa(int id) {
        Pessoa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_PESSOA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cnpj = rs.getString("cnpj");
                TipoPessoa tipoPessoa = new TipoPessoa(rs.getInt("id_tipo_pessoa"), rs.getString("descricao"));


                entidade = new Pessoa(id, nome, cpf, cnpj, tipoPessoa);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Pessoa> selectAllPessoa() {
        List<Pessoa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_PESSOA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cnpj = rs.getString("cnpj");
                TipoPessoa tipoPessoa = new TipoPessoa(rs.getInt("id_tipo_pessoa"), rs.getString("descricao"));

                entidades.add(new Pessoa(id, nome, cpf, cnpj, tipoPessoa));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deletePessoa(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_PESSOA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePessoa(Pessoa entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_PESSOA_SQL)) {
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getCpf());
            statement.setString(3, entidade.getCnpj());
            statement.setInt(4, entidade.getIdTipoPessoa().getId());
            statement.setInt(5, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
