/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.GUI.Model.StudentLessonModel;

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
public class AbsenceOverviewController implements Initializable
  {

    @FXML
    private TableView<StudentLesson> tblAllAbsence;
    @FXML
    private TableColumn<StudentLesson, String> clmClass;
    @FXML
    private TableColumn<StudentLesson, String> clmStatus;
    @FXML
    private Button btnLogOff;
    @FXML
    private TableColumn<StudentLesson, Date> clmDate;

//    LessonManager lessonManager = new LessonManager();
    private ObservableList<StudentLesson> studentLessons
            = FXCollections.observableArrayList();
    Student student;
    private static final StudentLessonModel STUD_LESS_MODEL = new StudentLessonModel();

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb)
      {

        tblAllAbsence.setItems(studentLessons);
      }

    public void altInitialize(Student student)
      {
        this.student = student;
        
        updateFields();

      }

    @FXML
    private void sendRequest(ActionEvent event)
      {
          for (StudentLesson studentLesson : tblAllAbsence.getSelectionModel().getSelectedItems())
            {
              if(studentLesson.getAttendint()==0){
                 STUD_LESS_MODEL.setStudentAttendence(studentLesson, 2);
                 }
            }
          studentLessons.clear();
           studentLessons.addAll(STUD_LESS_MODEL.getStudentLessonBasedOnStudent(student));
        
      }

    @FXML
    private void logOff(ActionEvent event) throws IOException
      {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/Login.fxml"));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));
        subStage.initStyle(StageStyle.UNDECORATED);
        subStage.show();
        Stage stage = (Stage) btnLogOff.getScene().getWindow();
        stage.close();
      }

    private void updateFields()
      {
        studentLessons.addAll(STUD_LESS_MODEL.getStudentLessonBasedOnStudent(student));
        clmStatus.setCellValueFactory(
                new PropertyValueFactory("attendence"));
//        clmTeacher.setCellValueFactory(
//                cellData-> cellData.getValue().getLessonId());
        clmDate.setCellValueFactory(
                new PropertyValueFactory("cal"));
        clmClass.setCellValueFactory(
                new PropertyValueFactory("lessonName"));

      }

  }
