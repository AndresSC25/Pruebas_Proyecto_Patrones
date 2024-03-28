package com.laca.service;

import com.laca.entity.Transporter;
import com.laca.factory_method.abstractProduct.Transport;
import com.laca.factory_method.concreteProduct.APieLogistics;
import com.laca.factory_method.contreteCreator.APie;
import com.laca.factory_method.abstractCreator.LogisticsCompany;
import com.laca.singleton.DataBaseConnection;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransporterService {
    public TransporterService() throws SQLException {

    }
    @Transactional
    public List<Transport> getAllTransporters() {
        List<Transport> transportUnity = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM tbl_unidad_transporte";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LogisticsCompany aPieLogistics = new APieLogistics();
                Transport apie = aPieLogistics.createTransport();
                apie.setId_unidad(resultSet.getInt("id_unidad"));
                apie.setId_usuario(resultSet.getInt("id_usuario"));
                apie.setTipo_unidad(resultSet.getString("tipo_unidad"));
                apie.setCapacidad(resultSet.getInt("capacidad"));
                apie.setPrecio(resultSet.getDouble("precio"));
                apie.setEstado(resultSet.getString("estado"));
                transportUnity.add(apie);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        return transportUnity;
    }

    @Transactional
    public APie saveTransporter(APie aPie) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {
            String query = "INSERT INTO tbl_unidad_transporte (id_usuario,tipo_unidad,capacidad,precio,estado) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, aPie.getId_usuario());
            statement.setString(2, aPie.getTipo_unidad());
            statement.setInt(3, aPie.getCapacidad());
            statement.setDouble(4, aPie.getPrecio());
            statement.setString(5, aPie.getEstado());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    aPie.setId_unidad(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new transporter unity");
        }
        return aPie;
    }

    @Transactional
    public Transporter updateTransporter(Long transporterId, Transporter updatedTransporter) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {
            String storedProcedureCall = "{call update_transporter(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storedProcedureCall);

            statement.setLong(1, transporterId);
            statement.setString(2, updatedTransporter.getName());
            statement.setString(3, updatedTransporter.getCompany());

            boolean hasResults = statement.execute();

            if (!hasResults) {
                throw new RuntimeException("Error updating transporter: No results from the stored procedure.");
            }

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int updatedId = resultSet.getInt("id");
                String updatedName = resultSet.getString("name");
                String updatedCompany = resultSet.getString("company");

                // Crea un nuevo Transporter con los datos actualizados y devuélvelo
                updatedTransporter.setId((long) updatedId);
                updatedTransporter.setName(updatedName);
                updatedTransporter.setCompany(updatedCompany);

                return updatedTransporter;
            } else {
                throw new RuntimeException("Transporter not found by ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating transporter: " + e.getMessage(), e);
        }
    }
    @Transactional
    public Transporter getTransporterById(Long transporterId) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {
            String query = "SELECT id, name, company FROM transporters WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, transporterId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Transporter transporter = new Transporter();
                transporter.setId(resultSet.getLong("id"));
                transporter.setName(resultSet.getString("name"));
                transporter.setCompany(resultSet.getString("company"));
                return transporter;
            } else {
                throw new RuntimeException("Transporter not found with ID: " + transporterId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving transporter: " + e.getMessage(), e);
        }
    }
    @Transactional
    public Boolean deleteTransporter(Long transporterId) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {
            String query = "DELETE FROM transporters where transporters.id  = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, transporterId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transporter: " + e.getMessage(), e);
        }
    }

}