package com.example.fitnespro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.collections.transformation.FilteredList;

public class AllMembersController implements Initializable{
    @FXML
    private TextField nameFilter;
    @FXML
    private TableView<Members> AllMembersTable;

    @FXML
    private TableColumn<Members, String> start_ab;

    @FXML
    private TableColumn<Members, String> end_ab;


    @FXML
    private TableColumn<Members, String> membership;

    @FXML
    private TableColumn<Members, String> trainer;

    @FXML
    private TableColumn<Members, String> group_member;

    @FXML
    private TableColumn<Members, String> contact;

    @FXML
    private TableColumn<Members, String> lastname;
    @FXML
    private TableColumn<Members, String> name;

    @FXML
    private TableColumn<Members, String> id_mem;



    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Members members = null;

    ObservableList<Members> MembersList = FXCollections.observableArrayList();

    @FXML
    private void handleNameFilter() {
        // Получаем текст из поля фильтрации
        String filter = nameFilter.getText();

        FilteredList<Members> filteredData = new FilteredList<>(MembersList, p -> true);

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
        AllMembersTable.setItems(filteredData);
    }
    @FXML
    void refreshTable() {
        try {
            MembersList.clear();

            query = "SELECT * FROM members";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MembersList.add(new Members(
                        resultSet.getInt("id_mem"),
                        resultSet.getString("lastname"),
                        resultSet.getString("name"),
                        resultSet.getString("contact"),
                        resultSet.getString("group_member"),
                        resultSet.getString("membership"),
                        resultSet.getString("trainer"),
                        resultSet.getString("start_ab"),
                        resultSet.getString("end_ab")

                ));
                AllMembersTable.setItems(MembersList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDate() {
        connection = com.example.fitnespro.DataBaseHandler.getConnect();
        refreshTable();

        id_mem.setCellValueFactory(new PropertyValueFactory<>("id_mem"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        trainer.setCellValueFactory(new PropertyValueFactory<>("trainer"));
        start_ab.setCellValueFactory(new PropertyValueFactory<>("start_ab"));
        end_ab.setCellValueFactory(new PropertyValueFactory<>("end_ab"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        group_member.setCellValueFactory(new PropertyValueFactory<>("group_member"));
        membership.setCellValueFactory(new PropertyValueFactory<>("membership"));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> handleNameFilter());
    }

}
