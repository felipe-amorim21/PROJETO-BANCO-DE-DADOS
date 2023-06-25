package br.com.conta.DAO;

import br.com.conta.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeRotaDAO extends ConexaoDB{
    private static final String INSERT_TIME_ROTA_SQL = "INSERT INTO time_rota (id_funcionario, id_tarefa_rota) VALUES (?, ?);";
    private static final String SELECT_TIME_ROTA_BY_ID = "select *, tp.descricao as tipo_pessoa_descricao from time_rota tm inner join tarefa_rota tr on tr.id = tm.id_tarefa_rota inner join rota r on r.id = tr.id_rota inner join funcionario f on f.id=tm.id_funcionario inner join pessoa p on p.id=f.id_pessoa inner join tipo_pessoa tp on tp.id=p.id_tipo_pessoa WHERE tm.id = ?;";
    private static final String SELECT_ALL_TIME_ROTA = "select *, tp.descricao as tipo_pessoa_descricao from time_rota tm  inner join tarefa_rota tr on tr.id = tm.id_tarefa_rota inner join rota r on r.id = tr.id_rota inner join funcionario f on f.id=tm.id_funcionario inner join pessoa p on p.id=f.id_pessoa inner join tipo_pessoa tp on tp.id=p.id_tipo_pessoa;";
    private static final String DELETE_TIME_ROTA_SQL = "DELETE FROM time_rota WHERE id = ?;";
    private static final String UPDATE_TIME_ROTA_SQL = "UPDATE time_rota SET id_funcionario = ?, id_tarefa_rota = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM time_rota;";

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

    public void insertTimeRota(TimeRota entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIME_ROTA_SQL)) {
            preparedStatement.setInt(1, entidade.getIdFuncionario().getId());
            preparedStatement.setInt(2, entidade.getIdTarefaRota().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TimeRota selectTimeRota(int id) {
        TimeRota entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIME_ROTA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idFuncionario = rs.getInt("id_funcionario");
                int idTarefaRota = rs.getInt("id_tarefa_rota");
                TarefaRota tarefaRota = new TarefaRota(idTarefaRota, rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("descricao")));
                Funcionario funcionario = new Funcionario(idFuncionario, rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("tipo_pessoa_descricao"))));

                entidade = new TimeRota(id, funcionario, tarefaRota);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<TimeRota> selectAllTimeRota() {
        List<TimeRota> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIME_ROTA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String idFuncionario = rs.getString("id_funcionario");
                String idTarefaRota = rs.getString("id_tarefa_rota");
                TarefaRota tarefaRota = new TarefaRota(rs.getInt(id), rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("descricao")));
                Funcionario funcionario = new Funcionario(rs.getInt(id), rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("tipo_pessoa_descricao"))));

                entidades.add(new TimeRota(id, funcionario, tarefaRota));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTimeRota(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_TIME_ROTA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTimeRota(TimeRota entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_TIME_ROTA_SQL)) {
            statement.setInt(1, entidade.getIdFuncionario().getId());
            statement.setInt(2, entidade.getIdTarefaRota().getId());
            statement.setInt(3, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
