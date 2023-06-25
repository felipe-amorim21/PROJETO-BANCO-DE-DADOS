package br.com.conta.DAO;

import br.com.conta.model.Cliente;
import br.com.conta.model.Pessoa;
import br.com.conta.model.TipoPessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConexaoDB{

    private static final String INSERT_CLIENTE_SQL = "INSERT INTO cliente (numero_documento, numero_cliente, id_pessoa) VALUES (?, ?, ?);";
    private static final String SELECT_CLIENTE_BY_ID = "SELECT * FROM cliente c inner join pessoa p on p.id = c.id_pessoa inner join tipo_pessoa t on t.id=p.id_tipo_pessoa  WHERE c.id = ?;";
    private static final String SELECT_ALL_CLIENTE = "select * from cliente c inner join pessoa p on p.id=c.id_pessoa inner join tipo_pessoa t on t.id=p.id_tipo_pessoa;";
    private static final String DELETE_CLIENTE_SQL = "DELETE FROM cliente WHERE id = ?;";
    private static final String UPDATE_CLIENTE_SQL = "UPDATE cliente SET numero_documento = ?, numero_cliente = ?, id_pessoa = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM cliente;";




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

    public void insertCliente(Cliente entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CLIENTE_SQL)) {
            preparedStatement.setString(1, entidade.getNumeroDocumento());
            preparedStatement.setString(2, entidade.getNumeroCliente());
            preparedStatement.setInt(3, entidade.getIdPessoa().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente selectCliente(int id) {
        Cliente entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CLIENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String numeroDocumento = rs.getString("numero_documento");
                String numeroCliente = rs.getString("numero_cliente");
                Pessoa pessoa = new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa((rs.getInt("id_tipo_pessoa")),rs.getString("descricao")));


                entidade = new Cliente(id, numeroDocumento, numeroCliente, pessoa);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Cliente> selectAllCliente() {
        List<Cliente> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CLIENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String numeroDocumento = rs.getString("numero_documento");
                String numeroCliente = rs.getString("numero_cliente");
                Pessoa pessoa = new Pessoa(rs.getInt("id_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"), new TipoPessoa(rs.getInt("id_tipo_pessoa"), rs.getString("descricao")));

                entidades.add(new Cliente(id, numeroDocumento, numeroCliente, pessoa));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteCliente(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_CLIENTE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateCliente(Cliente entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CLIENTE_SQL)) {
            statement.setString(1, entidade.getNumeroDocumento());
            statement.setString(2, entidade.getNumeroCliente());
            statement.setInt(3, entidade.getIdPessoa().getId());
            statement.setInt(4, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
