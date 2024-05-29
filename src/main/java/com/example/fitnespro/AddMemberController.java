package com.example.fitnespro;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {
    @FXML
    private Button addBtn;

    @FXML
    private TextField membershipField;

    @FXML
    private Label resultLabel;


    @FXML
    private DatePicker endField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField groupField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField numField;

    @FXML
    private DatePicker startField;

    @FXML
    private TextField trainerField;

    @FXML
    private void clearFields() {
        nameField.clear();
        numField.clear();
        lastNameField.clear();
        membershipField.clear();
        groupField.clear();
        trainerField.clear();
        genderField.clear();
    }

    @FXML
    private void addMember(ActionEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FitnessPro", "root", "KAK2004.");

            if (connection != null) {
                String sql = "INSERT INTO members (name, lastname, contact,membership, group_member,  trainer, gender,start_ab, end_ab) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, lastNameField.getText());
                preparedStatement.setString(3, numField.getText());
                preparedStatement.setString(4, membershipField.getText());
                preparedStatement.setString(5, groupField.getText());
                preparedStatement.setString(6, trainerField.getText());
                preparedStatement.setString(7, genderField.getText());
                preparedStatement.setDate(8,(Date) startField.getDayCellFactory());
                preparedStatement.setDate(9,(Date) endField.getDayCellFactory());


                // Выполняем запрос
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    resultLabel.setText("Mitglied erfolgreich hinzugefügt!");
                } else {
                    resultLabel.setText("Fehler beim Hinzufügen des Mitglieds.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resultLabel.setText("Datenbankfehler.");

        } finally {
            // Закрываем ресурсы в блоке finally
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
