package br.com.conta.DAO;
import br.com.conta.model.TipoPessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TipoPessoaDAO extends  ConexaoDB{
    private static final String INSERT_TIPO_PESSOA_SQL = "INSERT INTO tipo_pessoa (descricao) VALUES (?);";
    private static final String SELECT_TIPO_PESSOA_BY_ID = "SELECT id, descricao FROM tipo_pessoa WHERE id = ?";
    private static final String SELECT_ALL_TIPO_PESSOA = "SELECT * FROM tipo_pessoa;";
    private static final String DELETE_TIPO_PESSOA_SQL = "DELETE FROM tipo_pessoa WHERE id = ?;";
    private static final String UPDATE_TIPO_PESSOA_SQL = "UPDATE tipo_pessoa SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tipo_pessoa;";

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

    public void insertTipoPessoa(TipoPessoa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPO_PESSOA_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TipoPessoa selectTipoPessoa(int id) {
        TipoPessoa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPO_PESSOA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new TipoPessoa(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TipoPessoa> selectAllTipoPessoa() {
        List<TipoPessoa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPO_PESSOA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new TipoPessoa(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipoPessoa(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TIPO_PESSOA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTipoPessoa(TipoPessoa entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TIPO_PESSOA_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setInt(2, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
