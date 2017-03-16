/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mechaa
 */
public class AbsenceOverviewController implements Initializable {

    @FXML
    private TableView<Lesson> tblAllAbsence;
    @FXML
    private TableColumn<Lesson, String> clmClass;
    @FXML
    private TableColumn<Lesson, String> clmTeacher;
    @FXML
    private Button btnLogOff;
    @FXML
    private TableColumn<Lesson, Date> clmDate;

//    LessonManager lessonManager = new LessonManager();
    private static ObservableList<Lesson> lessons
            = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateFields();

    }

    @FXML
    private void sendRequest(ActionEvent event) {
        tblAllAbsence.refresh();
        
    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/Login.fxml"));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));
        subStage.initStyle(StageStyle.UNDECORATED);
        subStage.show();
        Stage stage = (Stage) btnLogOff.getScene().getWindow();
        stage.close();
    }

    private void updateFields() {
        //lessons.addAll(lessonManager.getLessons());
        tblAllAbsence.setItems(lessons);
        clmTeacher.setCellValueFactory(
                new PropertyValueFactory("teacher"));
        clmDate.setCellValueFactory(
                new PropertyValueFactory("date"));
        clmClass.setCellValueFactory(
                new PropertyValueFactory("name"));
        
    }

}
