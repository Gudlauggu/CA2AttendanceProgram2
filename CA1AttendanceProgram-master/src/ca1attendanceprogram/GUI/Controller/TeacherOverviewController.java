/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.GUI.Model.LessonModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class TeacherOverviewController implements Initializable
  {

    @FXML
    private TableView<Student> tblAllLessons;
    @FXML
    private TableColumn<Student, String> clmName;
    @FXML
    private TableColumn<Student, String> clmAbsence;
    @FXML
    private TableColumn<Student, String> clmAttending;
    @FXML
    private ComboBox<String> CBLesson;
    @FXML
    private Button btnLogOff;
    private static ObservableList<Student> students
            = FXCollections.observableArrayList();
    //private static final StudentManager STUDENT_MANAGER = new StudentManager();
    private Teacher teacher = null;
    private LessonModel lessonModel = new LessonModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {

        updateFields();

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
        clmName.setCellValueFactory(
                new PropertyValueFactory("name"));
        clmAbsence.setCellValueFactory(
                new PropertyValueFactory("absencePercentage"));
        clmAttending.setCellValueFactory(
                new PropertyValueFactory("attendingTest"));

      }

    public void AltInitilizer(Teacher teacher)
      {
        this.teacher = teacher;
        lessonModel.setCoursesForTeacher(teacher);
        lessonModel.LessonIntoObservable(teacher);
        cbChoicer(teacher);
        addListener();
      }

    public void addListener()
      {
        CBLesson.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
          {
            public void changed(ObservableValue ov, Number value, Number new_value)
              {
                CBLesson.getSelectionModel().select(new_value.intValue());
                tblAllLessons.getItems().clear();
                updateFields();
                tblAllLessons.refresh();
              }

          });
      }

    private void cbChoicer(Teacher teacher)//Sets the items in the choicebox
      {
        ArrayList<String> courseString = new ArrayList();
        courseString.add("All Courses");
          for (Course course : teacher.getCourses())
            {
              courseString.add(course.getName());
            }
        ObservableList<String> groups = FXCollections.observableArrayList(courseString);
        CBLesson.setItems(groups);
      }

    @FXML
    private void mercyButton(ActionEvent event)
      {
        Student student = tblAllLessons.getSelectionModel().getSelectedItem();
        if (student.getAttendingTest().equals("Absent(Mercy Requested)"))
          {
            student.setAttendingTest("Attending");
            tblAllLessons.refresh();
          }
      }

    @FXML
    private void smiteButton(ActionEvent event)
      {

        Student student = tblAllLessons.getSelectionModel().getSelectedItem();
        String status = student.getAttendingTest();
        if (status.equals("Attending") || status.equals("Absent(Mercy Requested)"))
          {
            student.setAttendingTest("Absent");
            tblAllLessons.refresh();
          }
      }

    @FXML
    private void startLesson(ActionEvent event)
      {
      }

  }
