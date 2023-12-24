package com.example.fitnespro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
public class PaymentsController implements Initializable {

    @FXML
    private TableView<Payments> PaymentsTable;

    @FXML
    private TableColumn<Payments, String> amount;

    @FXML
    private TableColumn<Payments, String> id_payment;

    @FXML
    private TableColumn<Payments, String> lastname;

    @FXML
    private TableColumn<Payments, String> membership;

    @FXML
    private TableColumn<Payments, String> name;

    @FXML
    private TextField nameFilter;

    @FXML
    private TableColumn<Payments, String> paid;

    @FXML
    private TableColumn<Payments, String> status;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Payments payments = null;

    ObservableList<Payments> PaymentsList = FXCollections.observableArrayList();

    @FXML
    private void handleNameFilter() {
        // Получаем текст из поля фильтрации
        String filter = nameFilter.getText();

        FilteredList<Payments> filteredData = new FilteredList<>(PaymentsList, p -> true);

        // Применяем фильтр (в данном случае, фильтрация по началу имени)
        filteredData.setPredicate(member -> {
            if (filter == null || filter.isEmpty()) {
                return true; // Отображаем все, если фильтр не установлен
            }
            // Приводим к нижнему регистру для регистронезависимого сравнения
            String lowerCaseFilter = filter.toLowerCase();

            // Сравниваем с началом имени
            return member.getName().toLowerCase().startsWith(lowerCaseFilter) ||
                    member.getLastname().toLowerCase().startsWith(lowerCaseFilter)
                    ;

        });

        // Обновляем TableView
        PaymentsTable.setItems(filteredData);
    }
    @FXML
    void refreshTable() {
        try {
            PaymentsList.clear();

            query = "SELECT * FROM payments";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentsList.add(new Payments(
                        resultSet.getInt("id_payment"),
                        resultSet.getString("lastname"),
                        resultSet.getString("name"),
                        resultSet.getString("membership"),
                        resultSet.getString("amount"),
                        resultSet.getString("paid"),
                        resultSet.getString("status")

                ));
                PaymentsTable.setItems(PaymentsList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDate() {
        connection = com.example.fitnespro.DataBaseHandler.getConnect();
        refreshTable();

        id_payment.setCellValueFactory(new PropertyValueFactory<>("id_payment"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        membership.setCellValueFactory(new PropertyValueFactory<>("membership"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> handleNameFilter());

    }
}
