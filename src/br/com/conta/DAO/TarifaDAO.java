package br.com.conta.DAO;

import br.com.conta.model.TarefaRota;
import br.com.conta.model.Tarifa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO extends ConexaoDB{

    private static final String INSERT_TARIFA_SQL = "INSERT INTO tarifa (taxa, lei ,data_inicio, data_fim, id_classe) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_TARIFA_BY_ID = "SELECT id, taxa, lei, data_inicio, data_fim, id_classe FROM tarifa WHERE id = ?";
    private static final String SELECT_ALL_TARIFA = "SELECT * FROM tarifa;";
    private static final String DELETE_TARIFA_SQL = "DELETE FROM tarifa WHERE id = ?;";
    private static final String UPDATE_TARIFA_SQL = "UPDATE tarifa SET taxa = ?, lei = ?, data_inicio, data_fim = ?, id_classe = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tarifa;";

    static ClasseDAO classeDAO = new ClasseDAO();


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

    public void insertTarifa(Tarifa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TARIFA_SQL)) {
            preparedStatement.setString(1, entidade.getTaxa());
            preparedStatement.setString(2, entidade.getLei());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(entidade.getDataFim().getTime()));
            preparedStatement.setInt(5, entidade.getIdClasse().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarifa selectTarifa(int id) {
        Tarifa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TARIFA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String taxa = rs.getString("taxa");
                String lei = rs.getString("lei");
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                int idClasse = rs.getInt("id_classe");

                entidade = new Tarifa(id, taxa, lei, dataInicio, dataFim, classeDAO.selectClasse(idClasse));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Tarifa> selectAllTarifa() {
        List<Tarifa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TARIFA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String taxa = rs.getString("taxa");
                String lei = rs.getString("lei");
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                int idClasse = rs.getInt("id_classe");
                entidades.add(new Tarifa(id, taxa, lei, dataInicio, dataFim, classeDAO.selectClasse(idClasse)));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTarifa(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TARIFA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTarifa(Tarifa entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TARIFA_SQL)) {
            statement.setString(1, entidade.getTaxa());
            statement.setString(2, entidade.getLei());
            statement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            statement.setTimestamp(4, new java.sql.Timestamp(entidade.getDataFim().getTime()));
            statement.setInt(5, entidade.getIdClasse().getId());
            statement.setInt(6, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
