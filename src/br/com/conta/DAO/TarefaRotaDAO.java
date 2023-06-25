package br.com.conta.DAO;

import br.com.conta.model.Rota;
import br.com.conta.model.TarefaRota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TarefaRotaDAO extends ConexaoDB{
    private static final String INSERT_TAREFA_ROTA_SQL = "INSERT INTO tarefa_rota (observacao, data_inicio, data_fim, tarefa_rotacol, id_rota) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_TAREFA_ROTA_BY_ID = "SELECT * FROM tarefa_rota t inner join rota r on r.id = t.id_rota WHERE t.id = ?;";
    private static final String SELECT_ALL_TAREFA_ROTA = "SELECT * FROM tarefa_rota t inner join rota r on r.id = t.id_rota;";
    private static final String DELETE_TAREFA_ROTA_SQL = "DELETE FROM tarefa_rota WHERE id = ?;";
    private static final String UPDATE_TAREFA_ROTA_SQL = "UPDATE tarefa_rota SET observacao = ?, data_inicio, data_fim = ?, tarefa_rotacol = ?, id_rota = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tarefa_rota;";

    static RotaDAO rotaDAO = new RotaDAO();


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

    public void insertTarefaRota(TarefaRota entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TAREFA_ROTA_SQL)) {
            preparedStatement.setString(1, entidade.getObservacao());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataFim().getTime()));
            preparedStatement.setString(4, entidade.getTarefaRotacol());
            preparedStatement.setInt(5, entidade.getIdRota().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TarefaRota selectTarefaRota(int id) {
        TarefaRota entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TAREFA_ROTA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String observacao = rs.getString("observacao");
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                String tarefaRotacol = rs.getString("tarefa_rotacol");
                Rota rota = new Rota(rs.getInt("id"), rs.getString("descricao"));

                entidade = new TarefaRota(id, observacao, dataInicio, dataFim, tarefaRotacol, rota);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TarefaRota> selectAllTarefaRota() {
        List<TarefaRota> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TAREFA_ROTA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String observacao = rs.getString("observacao");
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                String tarefaRotacol = rs.getString("tarefa_rotacol");
                int idRota = rs.getInt("id_rota");
                Rota rota = new Rota(rs.getInt("id"), rs.getString("descricao"));
                entidades.add(new TarefaRota(id, observacao, dataInicio, dataFim, tarefaRotacol, rota));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTarefaRota(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TAREFA_ROTA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTarefaRota(TarefaRota entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TAREFA_ROTA_SQL)) {
            statement.setString(1, entidade.getObservacao());
            statement.setTimestamp(2, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            statement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataFim().getTime()));
            statement.setString(4, entidade.getTarefaRotacol());
            statement.setInt(5, entidade.getIdRota().getId());
            statement.setInt(6, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
