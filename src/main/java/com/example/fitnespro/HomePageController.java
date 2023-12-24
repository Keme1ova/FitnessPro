package com.example.fitnespro;

import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private TreeView menu;
    @FXML
    private Button exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> menuRoot = new TreeItem<>("root");

        TreeItem<String> rootItemHome = new TreeItem<>("Home", new ImageView(new Image(getClass().getResourceAsStream("images/home.png"))));
        TreeItem<String> rootItemMembers = new TreeItem<>("Members", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> rootItemStaff = new TreeItem<>("Staff", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> rootItemSchedule = new TreeItem<>("Schedule", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> rootItemPayments = new TreeItem<>("Payments", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> rootItemPrice = new TreeItem<>("Price", new ImageView(new Image(getClass().getResourceAsStream("images/pay.png"))));

        TreeItem<String> itemMembersAll = new TreeItem<>("All members", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> itemMembersAdd = new TreeItem<>("Add a member", new ImageView(new Image(getClass().getResourceAsStream("images/person_add.png"))));
        TreeItem<String> itemMembersProfile = new TreeItem<>("Member's profile", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));

        TreeItem<String> itemScheduleClass = new TreeItem<>("Classes", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));
        TreeItem<String> itemSchedulePersonal = new TreeItem<>("Personal classes", new ImageView(new Image(getClass().getResourceAsStream("images/person.png"))));

        menuRoot.getChildren().addAll(rootItemHome, rootItemMembers, rootItemStaff, rootItemSchedule, rootItemPayments, rootItemPrice);
        rootItemMembers.getChildren().addAll(itemMembersAll, itemMembersAdd, itemMembersProfile);
        rootItemSchedule.getChildren().addAll(itemScheduleClass, itemSchedulePersonal);


        menu.setShowRoot(false);
        menu.setRoot(menuRoot);
    }

    public void treeMenu() throws IOException {
        TreeItem<String> item = (TreeItem<String>) menu.getSelectionModel().getSelectedItem();
        if (item != null) {
            System.out.printf(item.getValue());
        }
        if (item != null && "Personal classes".equals(item.getValue())) {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("personal-classes.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item != null && "Add a member".equals(item.getValue())) {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-member.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "All members") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("all-members.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Payments") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("payments.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Classes") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("schedule.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Home") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Staff") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Member's profile") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("member-profile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
        if (item.getValue() == "Price") {
            Stage stage = (Stage) item.getGraphic().getScene().getWindow();
            stage.close();
            Stage stageNew = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("price.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1480, 1000);
            String css = this.getClass().getResource("base1.css").toExternalForm();
            scene.getStylesheets().add(css);
            stageNew.setScene(scene);
            stageNew.show();
        }
    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}