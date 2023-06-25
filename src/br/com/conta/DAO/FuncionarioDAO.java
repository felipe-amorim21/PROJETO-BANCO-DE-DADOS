package br.com.conta.DAO;

import br.com.conta.model.Funcionario;
import br.com.conta.model.Pessoa;
import br.com.conta.model.TipoPessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends ConexaoDB{

    private static final String INSERT_FUNCIONARIO_SQL = "INSERT INTO funcionario (codigo_funcional, id_pessoa) VALUES (?, ?);";
    private static final String SELECT_FUNCIONARIO_BY_ID = "select * from funcionario f inner join pessoa p on p.id = f.id_pessoa inner join tipo_pessoa t on t.id = p.id_tipo_pessoa WHERE f.id = ?;";
    private static final String SELECT_ALL_FUNCIONARIO = "select * from funcionario f inner join pessoa p on p.id = f.id_pessoa inner join tipo_pessoa t on t.id = p.id_tipo_pessoa;";
    private static final String DELETE_FUNCIONARIO_SQL = "DELETE FROM funcionario WHERE id = ?;";
    private static final String UPDATE_FUNCIONARIO_SQL = "UPDATE funcionario SET condigo_funcional = ?, id_pessoa = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM funcionario;";

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

    public void insertFuncionario(Funcionario entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_FUNCIONARIO_SQL)) {
            preparedStatement.setString(1, entidade.getCodigoFuncional());
            preparedStatement.setInt(2, entidade.getIdPessoa().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Funcionario selectFuncionario(int id) {
        Funcionario entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_FUNCIONARIO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String condigoFuncional = rs.getString("codigo_funcional");
                Pessoa pessoa = new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao")));

                entidade = new Funcionario(id, condigoFuncional, pessoa);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Funcionario> selectAllFuncionario() {
        List<Funcionario> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_FUNCIONARIO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String condigoFuncional = rs.getString("codigo_funcional");
                Pessoa pessoa = new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao")));
                entidades.add(new Funcionario(id, condigoFuncional, pessoa));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteFuncionario(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_FUNCIONARIO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateFuncional(Funcionario entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_FUNCIONARIO_SQL)) {
            statement.setString(1, entidade.getCodigoFuncional());
            statement.setInt(2, entidade.getIdPessoa().getId());
            statement.setInt(3, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
