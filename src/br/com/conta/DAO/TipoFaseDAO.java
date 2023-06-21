package br.com.conta.DAO;
import  br.com.conta.model.TipoFase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoFaseDAO extends ConexaoDB {

    private static final String INSERT_TIPO_FASE_SQL = "INSERT INTO tipo_fase (descricao, observacao) VALUES (?, ?);";
    private static final String SELECT_TIPO_FASE_BY_ID = "SELECT id, descricao, observacao FROM tipo_fase WHERE id = ?";
    private static final String SELECT_ALL_TIPO_FASE = "SELECT * FROM tipo_fase;";
    private static final String DELETE_TIPO_FASE_SQL = "DELETE FROM tipo_fase WHERE id = ?;";
    private static final String UPDATE_TIPO_FASE_SQL = "UPDATE tipo_fase SET descricao = ?, observacao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tipo_fase;";

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

    public void insertTipoFase(TipoFase entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPO_FASE_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setString(2, entidade.getObservacao());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TipoFase selectTipoFase(int id) {
        TipoFase entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPO_FASE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String observacao = rs.getString("observacao");
                entidade = new TipoFase(id, descricao, observacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TipoFase> selectAllTipoFase() {
        List<TipoFase> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPO_FASE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String observacao = rs.getString("observacao");
                entidades.add(new TipoFase(id, descricao, observacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipoFase(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TIPO_FASE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTipoFase(TipoFase entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TIPO_FASE_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setString(2, entidade.getObservacao());
            statement.setInt(3, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
