package com.example.portal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class AdminLoginController {
    @FXML
    TextField textField;
    @FXML
    PasswordField passField;
    @FXML
    Label invalidLabel;


    File file = new File("AdminLoginUserPass.txt");

    public void clickOnFields() {

        textField.setOnMouseClicked(event -> {
            invalidLabel.setText("");
        });
        passField.setOnMouseClicked(event -> {
            invalidLabel.setText("");
        });

    }

    public void signIn(ActionEvent event) throws IOException {

        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            boolean flag = false;


            while ((line = br.readLine()) != null) {
                String[] userPassArray = line.split(" ");

                if (userPassArray[0].equals(textField.getText()) &&
                        userPassArray[1].equals(passField.getText())) {
                    flag = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("adminValidLogIn.fxml"));
                    Parent root = loader.load();
                    AdminValidLoginController controller = loader.getController();
                    controller.userName(userPassArray[0]);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            }

            if (!flag) {
                textField.clear();
                passField.clear();
                invalidLabel.setStyle("-fx-text-fill: red");
                invalidLabel.setText("Invalid User or Password");
                blinkingLabel(invalidLabel);

            }

        } else {
            System.out.println("File not found");
        }

    }

    public void backBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("landingPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void blinkingLabel(Label label) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), event -> {
                    if(label.getOpacity()==1.0){
                        label.setOpacity(0);
                    }
                    else{
                        label.setOpacity(1);
                    }
        })
        );
        timeline.setCycleCount(10);
        timeline.play();
    }

}
