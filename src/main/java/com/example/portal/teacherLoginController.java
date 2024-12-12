package com.example.portal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class teacherLoginController {
    @FXML
    TextField textField;
    @FXML
    PasswordField passField;

    File file = new File("TeacherLoginUserPass.txt");


    public void signIn(ActionEvent event) throws IOException {
        if(file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ( (line = br.readLine()) != null){
                String [] userPass = line.split(" ");
                if(userPass[0].equals(textField.getText()) && userPass[1].equals(passField.getText())){
                    System.out.println("Login Success");
                }
            }

        }
        else{
            System.out.println("File does not exist");
            System.out.println(file.getAbsolutePath());
        }
    }



}
