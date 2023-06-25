package br.com.conta.DAO;

import br.com.conta.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CobrancaDAO extends ConexaoDB{

    private static final String INSERT_COBRANCA_SQL = "INSERT INTO cobranca (mes_referencia, ano_referencia, id_tarifa, id_medicao) VALUES (?, ?, ?, ?);";
    private static final String SELECT_COBRANCA_BY_ID = "select *, tf.descricao as tipo_fase_descricao, mr.descricao as medidor_descricao, r.descricao as rota_descricao, tp.descricao as tipo_pessoa_descricao, r2.descricao as rota2_descricao from cobranca c inner join tarifa t on t.id=c.id_tarifa inner join classe cl on cl.id = t.id_classe inner join tipo_fase tf on tf.id=cl.id_tipo_fase inner join medicao m on m.id=c.id_medicao inner join medidor mr on mr.id=m.id_medidor inner join poste p on p.id = mr.id_poste inner join rota r on r.id = mr.id_rota inner join time_rota tr on tr.id = m.id_time_rota inner join funcionario f on f.id = tr.id_funcionario inner join pessoa ps on ps.id = f.id_pessoa inner join tipo_pessoa tp on tp.id = ps.id_tipo_pessoa inner join tarefa_rota tfr on tfr.id = tr.id_tarefa_rota inner join rota r2 on r2.id = tfr.id_rota WHERE c.id = ?;";
    private static final String SELECT_ALL_COBRANCA = "select *, tf.descricao as tipo_fase_descricao, mr.descricao as medidor_descricao, r.descricao as rota_descricao, tp.descricao as tipo_pessoa_descricao, r2.descricao as rota2_descricao from cobranca c inner join tarifa t on t.id=c.id_tarifa inner join classe cl on cl.id = t.id_classe inner join tipo_fase tf on tf.id=cl.id_tipo_fase inner join medicao m on m.id=c.id_medicao inner join medidor mr on mr.id=m.id_medidor inner join poste p on p.id = mr.id_poste inner join rota r on r.id = mr.id_rota inner join time_rota tr on tr.id = m.id_time_rota inner join funcionario f on f.id = tr.id_funcionario inner join pessoa ps on ps.id = f.id_pessoa inner join tipo_pessoa tp on tp.id = ps.id_tipo_pessoa inner join tarefa_rota tfr on tfr.id = tr.id_tarefa_rota inner join rota r2 on r2.id = tfr.id_rota;";
    private static final String DELETE_COBRANCA_SQL = "DELETE FROM cobranca WHERE id = ?;";
    private static final String UPDATE_COBRANCA_SQL = "UPDATE cobranca SET mes_referencia = ?, ano_referencia = ?, id_tarifa = ?, id_medicao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM cobranca;";



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

    public void insertCobranca(Cobranca entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_COBRANCA_SQL)) {
            preparedStatement.setString(1, entidade.getMesReferencia());
            preparedStatement.setString(2, entidade.getAnoReferencia());
            preparedStatement.setInt(3, entidade.getIdTarifa().getId());
            preparedStatement.setInt(4, entidade.getIdMedicao().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Cobranca selectCobranca(int id) {
        Cobranca entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_COBRANCA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String mesCobranca = rs.getString("mes_referencia");
                String anoCobranca = rs.getString("ano_referencia");
                Tarifa tarifa = new Tarifa(rs.getInt("id_tarifa"), rs.getString("taxa"), rs.getString("lei"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), new Classe(rs.getInt("id_classe"), rs.getString("descricao") ,new TipoFase(rs.getInt("id_tipo_fase"), rs.getString("tipo_fase_descricao"), rs.getString("observacao"))));
                Medicao medicao = new Medicao(rs.getInt("id_medicao"), rs.getString("mes"), rs.getString("ano"), rs.getTimestamp("data_medicao"), rs.getString("valor"), new Medidor(rs.getInt("id_medidor"), rs.getString("medidor_descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao"))), new TimeRota(rs.getInt("id_time_rota"), new Funcionario(rs.getInt("id_funcionario"), rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("tipo_pessoa_descricao")))), new TarefaRota(rs.getInt("id_tarefa_rota"), rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("rota2_descricao")))));


                entidade = new Cobranca(id, mesCobranca, anoCobranca, tarifa, medicao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Cobranca> selectAllCobranca() {
        List<Cobranca> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_COBRANCA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mesCobranca = rs.getString("mes_referencia");
                String anoCobranca = rs.getString("ano_referencia");
                Tarifa tarifa = new Tarifa(rs.getInt("id_tarifa"), rs.getString("taxa"), rs.getString("lei"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), new Classe(rs.getInt("id_classe"), rs.getString("descricao") ,new TipoFase(rs.getInt("id_tipo_fase"), rs.getString("tipo_fase_descricao"), rs.getString("observacao"))));
                Medicao medicao = new Medicao(rs.getInt("id_medicao"), rs.getString("mes"), rs.getString("ano"), rs.getTimestamp("data_medicao"), rs.getString("valor"), new Medidor(rs.getInt("id_medidor"), rs.getString("medidor_descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao"))), new TimeRota(rs.getInt("id_time_rota"), new Funcionario(rs.getInt("id_funcionario"), rs.getString("codigo_funcional"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("tipo_pessoa_descricao")))), new TarefaRota(rs.getInt("id_tarefa_rota"), rs.getString("observacao"), rs.getTimestamp("data_inicio"), rs.getTimestamp("data_fim"), rs.getString("tarefa_rotacol"), new Rota(rs.getInt("id_rota"), rs.getString("rota2_descricao")))));

                entidades.add(new Cobranca(id, mesCobranca, anoCobranca, tarifa, medicao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteCobranca(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_COBRANCA_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateCobranca(Cobranca entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_COBRANCA_SQL)) {
            statement.setString(1, entidade.getMesReferencia());
            statement.setString(2, entidade.getAnoReferencia());
            statement.setInt(3, entidade.getIdTarifa().getId());
            statement.setInt(4, entidade.getIdMedicao().getId());
            statement.setInt(5, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
