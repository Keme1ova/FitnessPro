package com.example.fitnespro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    @FXML
    private Button signIn;


    @FXML
    void initialize() {

//        DataBaseHandler dbHandler = new DataBaseHandler();

//       Вход в систему
        signIn.setOnAction (event -> {
            String loginText = login_field.getText().trim() ;
            String loginPassword = password_field.getText().trim() ;
            if(!loginText.equals ("") && !loginPassword.equals (""))
                    loginUser(loginText, loginPassword);
            else
                System.out.println("Fields are empty");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while(true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1){
            signIn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/fitnespro/homePage.fxml"));


            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}