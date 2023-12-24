package com.example.fitnespro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class StaffController implements Initializable{

    @FXML
    private TableView<Staff> StaffTable;
    @FXML
    private TextField nameFilter;
    @FXML
    private TableColumn<Members, String> fullname;

    @FXML
    private TableColumn<Members, String> phone_number;


    @FXML
    private TableColumn<Members, String> email;

    @FXML
    private TableColumn<Members, String> job;





    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Staff staff = null;

    ObservableList<Staff> StaffList = FXCollections.observableArrayList();

    @FXML
    private void handleNameFilter() {
        // Получаем текст из поля фильтрации
        String filter = nameFilter.getText();

        FilteredList<Staff> filteredData = new FilteredList<>(StaffList, p -> true);

        // Применяем фильтр (в данном случае, фильтрация по началу имени)
        filteredData.setPredicate(member -> {
            if (filter == null || filter.isEmpty()) {
                return true; // Отображаем все, если фильтр не установлен
            }
            // Приводим к нижнему регистру для регистронезависимого сравнения
            String lowerCaseFilter = filter.toLowerCase();

            // Сравниваем с началом имени
            return member.getFullname().toLowerCase().startsWith(lowerCaseFilter);

        });

        // Обновляем TableView
        StaffTable.setItems(filteredData);
    }

    @FXML
    void refreshTable() {
        try {
            StaffList.clear();

            query = "SELECT * FROM staff";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StaffList.add(new Staff(
                        resultSet.getString("fullname"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("job")

                ));
                StaffTable.setItems(StaffList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDate() {
        connection = com.example.fitnespro.DataBaseHandler.getConnect();
        refreshTable();

        fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        job.setCellValueFactory(new PropertyValueFactory<>("job"));


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> handleNameFilter());

    }

}
