package com.example.portal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminValidLoginController {

    @FXML
    HBox logout,dashBoaredBox,studentBox,teacherBox,coursesBox,feesBox,salaryBox;
    @FXML
    BorderPane borderPane;
    @FXML
    Label nameLabel,studentCount;
    @FXML
    AnchorPane dashboredPane,studentPane,studentAddPane,studentDeletePane,studentUpdatePane;
    @FXML
    VBox addStVbox,delStVbox,updateStVbox;
    @FXML
    TextField stFirstName,stUserName,stPass,stSem,stFees,delStRegField,delStUserField,updateStID,updateDelTextField;

    List<AnchorPane> panes = new ArrayList<>();
    List<HBox> hboxes = new ArrayList<>();
    List<Student> students = new ArrayList<>();


    File file = new File("studentsData.ser");
    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
    ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));

    {
        students.add(new Student("Hamza","Hamz1086","1000",1,125000));
        students.add(new Student("Shehroz","Malik1100","1040",1,125000));
        students.add(new Student("Muzamil","Muzamil106","1600",1,125000));
        students.add(new Student("Ahmed","Ahmed186","1002",1,125000));
    }

    public AdminValidLoginController() throws IOException {
    }

    @FXML
    public void initialize() {

        panes.add(dashboredPane);
        panes.add(studentPane);
        panes.add(studentAddPane);
        panes.add(studentDeletePane);
        panes.add(studentUpdatePane);

        hboxes.add(logout);
        hboxes.add(dashBoaredBox);
        hboxes.add(studentBox);
        hboxes.add(teacherBox);
        hboxes.add(coursesBox);
        hboxes.add(feesBox);
        hboxes.add(salaryBox);

        byDefaultBlank();
    }

    public void clickOnDashBoard(MouseEvent event) throws IOException {
       visiblitityAndStyleStatus(dashboredPane,dashBoaredBox);
    }

    public void clickOnStudents(MouseEvent event) throws IOException {
        visiblitityAndStyleStatus(studentPane,studentBox);
    }


    public void addStudent() throws IOException {
        boolean addFlag = false;
        studentCount.setText(String.valueOf(Integer.parseInt(studentCount.getText()) + 1));
        students.add(new Student(stFirstName.getText(),stUserName.getText(),stPass.getText(),
                Integer.parseInt(stSem.getText()),Integer.parseInt(stFees.getText())));
        addFlag = true;

        if(addFlag){
            os.writeObject(students);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Student");
            alert.setHeaderText(null);
            alert.setContentText("Student Added");
            alert.showAndWait();

            stFirstName.clear();
            stUserName.clear();
            stPass.clear();
            stSem.clear();
            stFees.clear();
            delStRegField.clear();
            delStUserField.clear();
        }
    }


    public void deleteStudent() throws IOException {

        boolean flag = false;

        Iterator<Student> iterator = students.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            if(student.getRegNo().equals(delStRegField.getText())
                    && student.getUsername().equals(delStUserField.getText())){
                flag = true;
                iterator.remove();
                break;
            }
        }

        if(flag){
            os.writeObject(students);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Student");
            alert.setHeaderText(null);
            alert.setContentText("Student Deleted");
            alert.showAndWait();
            delStRegField.clear();
            delStUserField.clear();
        }

        if(!flag){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Student");
            alert.setHeaderText(null);
            alert.setContentText("Re-Check Student ID and User Name");
            alert.showAndWait();
        }

    }

    public void stBackBtn(ActionEvent event) throws IOException {
        visiblitityAndStyleStatus(studentPane,studentBox);
    }


    public void clickOnAddSt(MouseEvent event) throws IOException {
        visiblitityAndStyleStatus(studentAddPane,studentBox);
    }

    public void clickOndelSt(MouseEvent event) throws IOException {
        visiblitityAndStyleStatus(studentDeletePane,studentBox);
        System.out.println(students);
    }

    public void clickOnUpdateSt(MouseEvent event) throws IOException {
        visiblitityAndStyleStatus(studentUpdatePane,studentBox);
    }

    public void dataByID(ActionEvent event) throws IOException {
        Iterator<Student> iterator = students.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            if(student.getRegNo().equals(updateStID.getText())){
                System.out.println(updateStID.getText());
                updateDelTextField.setText("Hi");
            }
        }
    }



    public void clickOnTeachers(MouseEvent event) throws IOException {
//        visiblitityAndStyleStatus(teacherPane,teacherBox);
    }
    public void clickOnCourses(MouseEvent event) throws IOException {
//        visiblitityAndStyleStatus(coursesPane,coursesBox);
    }
    public void clickOnFees(MouseEvent event) throws IOException {
//        visiblitityAndStyleStatus(feesPane,feesBox);
    }
    public void clickOnSalary(MouseEvent event) throws IOException {
//        visiblitityAndStyleStatus(salaryPane,salaryBox);
    }

    private void visiblitityAndStyleStatus(AnchorPane activePane, HBox activeBox) {

        panes.forEach(pane -> pane.setVisible(pane.equals(activePane)));

        hboxes.forEach(hbox -> {
            if (hbox.equals(activeBox)) {
                hbox.setStyle("-fx-background-color: #b2b2d6; -fx-text-fill: white; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 1.5px; ");
            } else {
                hbox.setStyle(null);
            }

        });

    }

    public void doLogout(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if(alert.showAndWait().get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
            Scene scene = new Scene(loader.load());
//          Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Stage stage = (Stage)borderPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            event.consume();
        }

    }

    public void byDefaultBlank(){
        panes.forEach((pane)->{
            pane.setVisible(false);
        });
    }

    public void userName(String name){
        nameLabel.setText(name);
    }



}
