package br.com.conta.DAO;

import br.com.conta.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO extends  ConexaoDB{
    private static final String INSERT_CONTRATO_SQL = "INSERT INTO contrato (data_inicio, data_criacao, id_cliente, id_classe, id_medidor) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_CONTRATO_BY_ID = "select *, cls.descricao as classe_descricao, tf.descricao as tipo_fase_descricao, mr.descricao as medidor_descricao, r.descricao as rota_descricao from contrato c inner join cliente cl on cl.id=c.id_cliente inner join pessoa p on p.id = cl.id_pessoa inner join tipo_pessoa tp on tp.id = p.id_tipo_pessoa inner join classe cls on cls.id=c.id_classe inner join tipo_fase tf on tf.id=cls.id_tipo_fase inner join medidor mr on mr.id=c.id_medidor inner join poste ps on ps.id = mr.id_poste inner join rota r on r.id = mr.id_rota WHERE c.id = ?;";
    private static final String SELECT_ALL_CONTRATO = "select *, cls.descricao as classe_descricao, tf.descricao as tipo_fase_descricao, mr.descricao as medidor_descricao, r.descricao as rota_descricao from contrato c inner join cliente cl on cl.id=c.id_cliente inner join pessoa p on p.id = cl.id_pessoa inner join tipo_pessoa tp on tp.id = p.id_tipo_pessoa inner join classe cls on cls.id=c.id_classe inner join tipo_fase tf on tf.id=cls.id_tipo_fase inner join medidor mr on mr.id=c.id_medidor inner join poste ps on ps.id = mr.id_poste inner join rota r on r.id = mr.id_rota;";
    private static final String DELETE_CONTRATO_SQL = "DELETE FROM contrato WHERE id = ?;";
    private static final String UPDATE_CONTRATO_SQL = "UPDATE contrato SET data_inicio = ?, data_criacao = ?, id_cliente = ?, id_classe = ?, id_medidor = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM contrato;";



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

    public void insertContrato(Contrato entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONTRATO_SQL)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(entidade.getDataCriacao().getTime()));
            preparedStatement.setInt(3, entidade.getIdCliente().getId());
            preparedStatement.setInt(4, entidade.getIdClasse().getId());
            preparedStatement.setInt(5, entidade.getIdMedidor().getId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Contrato selectContrato(int id) {
        Contrato entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONTRATO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataCriacao = rs.getTimestamp("data_criacao");
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("numero_documento"), rs.getString("numero_cliente"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao"))));
                Classe classe = new Classe(rs.getInt("id_classe"), rs.getString("classe_descricao"), new TipoFase(rs.getInt("id_tipo_fase"), rs.getString("tipo_fase_descricao"), rs.getString("observacao")));;
                Medidor medidor = new Medidor(rs.getInt("id_medidor"), rs.getString("medidor_descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao")));
                entidade = new Contrato(id, dataInicio, dataCriacao, cliente, classe, medidor);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Contrato> selectAllContrato() {
        List<Contrato> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONTRATO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataCriacao = rs.getTimestamp("data_criacao");
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("numero_documento"), rs.getString("numero_cliente"), new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao"))));
                Classe classe = new Classe(rs.getInt("id_classe"), rs.getString("classe_descricao"), new TipoFase(rs.getInt("id_tipo_fase"), rs.getString("tipo_fase_descricao"), rs.getString("observacao")));;
                Medidor medidor = new Medidor(rs.getInt("id_medidor"), rs.getString("medidor_descricao"), new Poste(rs.getInt("id_poste"), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt("id_rota"), rs.getString("rota_descricao")));

                entidades.add(new Contrato(id, dataInicio, dataCriacao, cliente, classe, medidor));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteContrato(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_CONTRATO_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateContrato(Contrato entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CONTRATO_SQL)) {
            statement.setTimestamp(1, new java.sql.Timestamp(entidade.getDataInicio().getTime()));
            statement.setTimestamp(2, new java.sql.Timestamp(entidade.getDataCriacao().getTime()));
            statement.setInt(3, entidade.getIdCliente().getId());
            statement.setInt(4, entidade.getIdClasse().getId());
            statement.setInt(5, entidade.getIdMedidor().getId());
            statement.setInt(6, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
