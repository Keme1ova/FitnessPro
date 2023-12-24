package com.example.fitnespro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberProfileController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField membershipField;

    @FXML
    private TextField trainerField;

    @FXML
    private TextField startdateField;

    @FXML
    private TextField enddateField;

    @FXML
    private TextField groupField;

    @FXML
    private TextField searchField;

    private void displayMemberProfileByName(String name) {
        String query = "SELECT * FROM members WHERE name = ?";
        try {
            Connection connection = DataBaseHandler.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idField.setText(resultSet.getString("id_mem"));
                nameField.setText(resultSet.getString("name"));
                surnameField.setText(resultSet.getString("lastname"));
                contactField.setText(resultSet.getString("contact"));
                genderField.setText(resultSet.getString("gender"));
                membershipField.setText(resultSet.getString("membership"));
                trainerField.setText(resultSet.getString("trainer"));
                startdateField.setText(resultSet.getString("start_ab"));
                enddateField.setText(resultSet.getString("end_ab"));
                groupField.setText(resultSet.getString("group_member"));
            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Метод, который будет вызываться при нажатии кнопки "Поиск" по имени
    @FXML
    private void searchByNameAction() {
        String name = searchField.getText().trim();
        if (!name.isEmpty()) {
            // Отображение данных участника по заданному имени
            displayMemberProfileByName(name);
        }
    }
    // Метод для обновления данных
    private void updateMemberProfile() {
        String updateQuery = "UPDATE members SET name=?, lastname=?, contact=?, gender=?, " +
                "membership=?, trainer=?, start_ab=?, end_ab=?, group_member=? WHERE id_mem=?";

        try {
            Connection connection = DataBaseHandler.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, surnameField.getText());
            preparedStatement.setString(3, contactField.getText());
            preparedStatement.setString(4, genderField.getText());
            preparedStatement.setString(5, membershipField.getText());
            preparedStatement.setString(6, trainerField.getText());
            preparedStatement.setString(7, startdateField.getText());
            preparedStatement.setString(8, enddateField.getText());
            preparedStatement.setString(9, groupField.getText());
            preparedStatement.setString(10, idField.getText());

            // Выполнение запроса UPDATE
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Успешное обновление
                showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Die Mitgliedsdaten wurden erfolgreich aktualisiert");
            } else {
                // Возможно, участник с указанным ID не был найден
                showAlert(Alert.AlertType.ERROR, "Fehler", "Der Teilnehmer wurde in der Datenbank nicht gefunden");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberProfileController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    private void updateMemberAction() {
        updateMemberProfile();
    }
    //Метод для удаления данных из таблицы
    @FXML
    private void deleteMemberAction() {
        String memId = idField.getText().trim();

        if (!memId.isEmpty()) {
            try {
                String deleteQuery = "DELETE FROM members WHERE id_mem = ?";

                Connection connection = DataBaseHandler.getConnect();

                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, memId);

                // Выполнение запроса
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(" Участник успешно удален из базы данных.");
                    clearFields();
                } else {
                    System.out.println(" Участник не найден в базе данных.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(MemberProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    private void clearFields() {
        idField.clear();
        nameField.clear();
        surnameField.clear();
        contactField.clear();
        genderField.clear();
        membershipField.clear();
        trainerField.clear();
        startdateField.clear();
        enddateField.clear();
        groupField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchByNameAction());

    }
}
