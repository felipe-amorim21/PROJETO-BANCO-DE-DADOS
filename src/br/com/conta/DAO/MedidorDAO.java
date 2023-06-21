package br.com.conta.DAO;

import br.com.conta.model.Medidor;
import br.com.conta.model.Poste;
import br.com.conta.model.Rota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class MedidorDAO extends ConexaoDB{

    private static final String INSERT_MEDIDOR_SQL = "INSERT INTO medidor (descricao, id_poste, id_rota) VALUES (?, ?, ?);";
    private static final String SELECT_MEDIDOR_BY_ID = "select * from medidor m inner join poste p on p.id = m.id_poste inner join rota r on r.id = m.id_poste WHERE m.id = ?;";
    private static final String SELECT_ALL_MEDIDOR = "select * from medidor m inner join poste p on p.id = m.id_poste inner join rota r on r.id = m.id_poste;";
    private static final String DELETE_MEDIDOR_SQL = "DELETE FROM medidor WHERE id = ?;";
    private static final String UPDATE_MEDIDOR_SQL = "UPDATE medidor SET descricao = ?, id_poste, id_rota = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medidor;";

    static PosteDAO posteDAO = new PosteDAO();
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

    public void insertMedidor(Medidor entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDIDOR_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getIdPoste().getId());
            preparedStatement.setInt(3, entidade.getIdRota().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Medidor selectMedidor(int id) {
        Medidor entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDIDOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int idPoste = rs.getInt("id_poste");
                int idRota = rs.getInt("id_rota");

                entidade = new Medidor(descricao, new Poste(rs.getInt(id), rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao")), new Rota(rs.getInt(id), rs.getString("descricao")));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medidor> selectAllMedidor() {
        List<Medidor> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDIDOR)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int idPoste = rs.getInt("id_poste");
                int idRota = rs.getInt("id_rota");
                Poste poste = new Poste(idPoste, rs.getString("codigo"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("observacao"));
                Rota rota = new Rota(idRota, rs.getString("descricao"));
                entidades.add(new Medidor(id, descricao, poste, rota));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedidor(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDIDOR_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMedidor(Medidor entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDIDOR_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setInt(2, entidade.getIdPoste().getId());
            statement.setInt(3, entidade.getIdRota().getId());
            statement.setInt(4, entidade.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
