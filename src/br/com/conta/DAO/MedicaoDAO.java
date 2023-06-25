package br.com.conta.DAO;

import br.com.conta.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MedicaoDAO extends ConexaoDB{

    private static final String INSERT_MEDICAO_SQL = "INSERT INTO medicao (mes, ano , data_medicao, valor, id_medidor, id_time_rota) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_MEDICAO_BY_ID = "select *, r.descricao as rota_descricao, tp.descricao as tipo_pessoa_descricao from medicao m inner join medidor mr on mr.id=m.id_medidor inner join poste p on p.id = mr.id_poste inner join rota r on r.id = mr.id_rota inner join time_rota tr on tr.id = m.id_time_rota inner join funcionario f on f.id = tr.id_funcionario inner join pessoa ps on ps.id = f.id_pessoa inner join tipo_pessoa tp on tp.id = ps.id_tipo_pessoa inner join tarefa_rota tfr on tfr.id = tr.id_tarefa_rota inner join rota r2 on r2.id = tfr.id_rota WHERE m.id = ?;";
    private static final String SELECT_ALL_MEDICAO = "select *, r.descricao as rota_descricao, tp.descricao as tipo_pessoa_descricao from medicao m inner join medidor mr on mr.id=m.id_medidor inner join poste p on p.id = mr.id_poste inner join rota r on r.id = mr.id_rota inner join time_rota tr on tr.id = m.id_time_rota inner join funcionario f on f.id = tr.id_funcionario inner join pessoa ps on ps.id = f.id_pessoa inner join tipo_pessoa tp on tp.id = ps.id_tipo_pessoa inner join tarefa_rota tfr on tfr.id = tr.id_tarefa_rota inner join rota r2 on r2.id = tfr.id_rota;";
    private static final String DELETE_MEDICAO_SQL = "DELETE FROM medicao WHERE id = ?;";
    private static final String UPDATE_MEDICAO_SQL = "UPDATE medicao SET taxa = ?, lei = ?, data_inicio, data_fim = ?, id_classe = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medicao;";



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

    public void insertMedicao(Medicao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICAO_SQL)) {
            preparedStatement.setString(1, entidade.getMes());
            preparedStatement.setString(2, entidade.getAno());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataMedicao().getTime()));
            preparedStatement.setString(4, entidade.getValor());
            preparedStatement.setInt(5, entidade.getIdMedidor().getId());
            preparedStatement.setInt(6, entidade.getIdTimeRota().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Medicao selectMedicao(int id) {
        Medicao entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICAO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String mes = rs.getString("mes");
                String ano = rs.getString("ano");
                Timestamp dataMedicao = rs.getTimestamp("data_medicao");
                String valor = rs.getString("valor");
                int idMedidor = rs.getInt("id_medidor");
                int idTimeRota = rs.getInt("id_time_rota");
                Medidor medidor = new Medidor(idMedidor, rs.getString("descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao")));
                TimeRota timeRota = new TimeRota(idTimeRota, new Funcionario(rs.getInt("id_funcionario"), rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("tipo_pessoa_descricao")))), new TarefaRota(rs.getInt("id_tarefa_rota"), rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao"))));


                entidade = new Medicao(id, mes, ano, dataMedicao, valor, medidor, timeRota);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medicao> selectAllMedicao() {
        List<Medicao> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICAO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mes = rs.getString("mes");
                String ano = rs.getString("ano");
                Timestamp dataMedicao = rs.getTimestamp("data_medicao");
                String valor = rs.getString("valor");
                Medidor medidor = new Medidor(rs.getInt("id_medidor"), rs.getString("descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("descricao")));
                TimeRota timeRota = new TimeRota(rs.getInt("id_time_rota"), new Funcionario(rs.getInt("id_funcionario"), rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao")))), new TarefaRota(rs.getInt("id_tarefa_rota"), rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("descricao"))));

                entidades.add(new Medicao(id, mes, ano, dataMedicao, valor, medidor, timeRota));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedicao(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICAO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedicao(Medicao entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDICAO_SQL)) {
            statement.setString(1, entidade.getMes());
            statement.setString(2, entidade.getAno());
            statement.setTimestamp(3, new java.sql.Timestamp(entidade.getDataMedicao().getTime()));
            statement.setString(4, entidade.getValor());
            statement.setInt(5, entidade.getIdMedidor().getId());
            statement.setInt(6, entidade.getIdTimeRota().getId());
            statement.setInt(7, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
