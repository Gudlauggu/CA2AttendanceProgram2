/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.GUI.Model.LessonModel;
import ca1attendanceprogram.GUI.Model.StudentLessonModel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
    private TableView<StudentLesson> tblAllLessons;
    @FXML
    private TableColumn<StudentLesson, String> clmName;
    @FXML
    private TableColumn<StudentLesson, String> clmAttending;
    @FXML
    private TableColumn<StudentLesson, String> clmLesson;
    @FXML
    private TableColumn<StudentLesson, String> clmDate;

    @FXML
    private ComboBox<String> CBLesson;
    @FXML
    private Button btnLogOff;
    private static ObservableList<StudentLesson> studLessons
            = FXCollections.observableArrayList();
    //private static final StudentManager STUDENT_MANAGER = new StudentManager();
    private Teacher teacher = null;
    private LessonModel lessonModel = new LessonModel();
    private String allCourses = "All Courses";
    @FXML
    private Button btnChangePass;
    @FXML
    private Button btnLesson;

    //
    private static final StudentLessonModel STUD_LESS_MODEL = new StudentLessonModel();
    //
    @FXML
    private DatePicker datePicker;
    @FXML
    private GridPane grdPane;

    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        datePicker.setValue(LocalDate.now());

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
        clmName.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
        clmLesson.setCellValueFactory(cellData -> cellData.getValue().lessonNameProperty());
        clmAttending.setCellValueFactory(cellData -> cellData.getValue().attendenceProperty());
        clmDate.setCellValueFactory(cellData -> cellData.getValue().dateStringProperty());

      }

    public void AltInitilizer(Teacher teacher)
      {
        STUD_LESS_MODEL.getStudLessonForView().clear();
        this.teacher = teacher;
        lessonModel.setCoursesForTeacher(teacher);
        lessonModel.LessonIntoObservable(teacher);
        cbChoicer(teacher);
        addListener();
        updateFields();
        tblAllLessons.setItems(STUD_LESS_MODEL.getStudLessonForView());
        for (Course course : teacher.getCourses())
          {
            STUD_LESS_MODEL.getStudentLessonBasedOnCourse(course);
          }
        tblAllLessons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

      }

    public void addListener()
      {
        CBLesson.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
          {
            public void changed(ObservableValue ov, Number value, Number new_value)
              {
                CBLesson.getSelectionModel().select(new_value.intValue());
                studLessons.clear();
                tableUpdater();
                if (checkNewestLesson())
                  {
                    btnLesson.setText("Lesson has Started");
                    btnLesson.setDisable(true);

                  }
                else
                  {
                    btnLesson.setText("Start Lesson");
                    btnLesson.setDisable(false);
                  }
              }

          });
      }

    private void tableUpdater()
      {

        String crntCourse = CBLesson.getSelectionModel().getSelectedItem();
        STUD_LESS_MODEL.getStudLessonForView().clear();
        if (crntCourse != null)
          {
            if (crntCourse.equals(allCourses))
              {
                grdPane.getChildren().clear();
                STUD_LESS_MODEL.dontSortforView();
                btnLesson.setDisable(true);
              }
            else
              {
                for (Course course : teacher.getCourses())
                  {
                    if (course.getName().equals(crntCourse))
                      {
                        STUD_LESS_MODEL.sortByDate(datePicker.getValue(), course);

                      }
                  }
                imgHolderSetter();
              }
            clmDate.setSortType(TableColumn.SortType.DESCENDING);
            tblAllLessons.getSortOrder().add(clmDate);
          }
      }

    private void cbChoicer(Teacher teacher)//Sets the items in the choicebox
      {
        ArrayList<String> courseString = new ArrayList();
        courseString.add(allCourses);
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
        if (tblAllLessons.getSelectionModel().getSelectedItems().size() > 1)
          {
            for (StudentLesson studLesson : tblAllLessons.getSelectionModel().getSelectedItems())
              {
                if (studLesson.getAttendint() == 2)
                  {
                    STUD_LESS_MODEL.setStudentAttendence(studLesson, 1);
                  }
              }
          }
        else if (tblAllLessons.getSelectionModel().getSelectedItems().size() == 1)
          {
            StudentLesson studLess = tblAllLessons.getSelectionModel().getSelectedItem();
            STUD_LESS_MODEL.setStudentAttendence(studLess, 1);
          }
        tableUpdater();
      }

    @FXML
    private void smiteButton(ActionEvent event)
      {
        if (tblAllLessons.getSelectionModel().getSelectedItems().size() > 1)
          {
            for (StudentLesson studLesson : tblAllLessons.getSelectionModel().getSelectedItems())
              {
                if (studLesson.getAttendint() == 2)
                  {
                    STUD_LESS_MODEL.setStudentAttendence(studLesson, 0);
                  }
              }
          }
        else if (tblAllLessons.getSelectionModel().getSelectedItems().size() == 1)
          {
            StudentLesson studLess = tblAllLessons.getSelectionModel().getSelectedItem();
            STUD_LESS_MODEL.setStudentAttendence(studLess, 0);
          }
        tableUpdater();

      }

    @FXML
    private void startLesson(ActionEvent event)
      {
        Lesson crntLesson = lessonModel.getNewestLesson();
        if (!checkNewestLesson())
          {
            String courseName = CBLesson.getSelectionModel().getSelectedItem();
            lessonModel.createLesson(teacher, courseName);
            datePicker.setValue(LocalDate.now());
            STUD_LESS_MODEL.getStudLessonForView().clear();
            for (Course course : teacher.getCourses())
              {
                STUD_LESS_MODEL.getStudentLessonBasedOnCourse(course);
              }
            tableUpdater();
            btnLesson.setText("Lesson has Started");
            btnLesson.setDisable(true);
          }
      }

    @FXML
    private void openChangePass(ActionEvent event) throws IOException
      {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/ChangePasswordView.fxml"));

        Parent root = loader.load();

        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initStyle(StageStyle.UNDECORATED);

        subStage.show();
        Stage stage = (Stage) btnChangePass.getScene().getWindow();
        stage.close();
      }

    private void imgHolderSetter()
      {
        grdPane.getChildren().clear();
        RowConstraints con = grdPane.getRowConstraints().get(0);
        con.setMinHeight(180);

        int clm = 0;
        int row = 0;
        for (StudentLesson studLesson : STUD_LESS_MODEL.getStudLessonForView())
          {

            Student student = studLesson.getStudent();
            if (studLesson.getAttendint() == 1)
              {
                try
                  {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/ImageHolderView.fxml"));
                    Parent root;
                    root = loader.load();
                    grdPane.add(root, clm, row);
                    ImageHolderViewController imgController = loader.getController();
                    imgController.altInitialize(student);
                  }
                catch (IOException ex)
                  {
                    Logger.getLogger(TeacherOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                clm++;
                if (clm >= grdPane.getColumnConstraints().size())
                  {
                    row++;
                    if (grdPane.getRowConstraints().size() - 1 >= row)
                      {
                        con = new RowConstraints();
                        con.setMinHeight(180);
                        grdPane.getRowConstraints().add(con);
                      }
                    clm = 0;
                  }
              }
          }

      }

    @FXML
    private void dateRefresher(ActionEvent event)
      {

        tableUpdater();
      }

    @FXML
    private void refreshEvent(ActionEvent event)
      {

        STUD_LESS_MODEL.getStudLessonForView().clear();
        for (Course course : teacher.getCourses())
          {
            STUD_LESS_MODEL.getStudentLessonBasedOnCourse(course);
          }
        tableUpdater();
        if (checkNewestLesson())
          {
            btnLesson.setText("Lesson has Started");
            btnLesson.setDisable(true);

          }
        else
          {
            btnLesson.setText("Start Lesson");
            btnLesson.setDisable(false);
          }

      }

    private Boolean checkNewestLesson()
      {
        Lesson crntLesson = lessonModel.getNewestLesson();

        if (crntLesson == null || (crntLesson.getCal().get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR) && crntLesson.getLessonName().equals(CBLesson.getSelectionModel().getSelectedItem())))

          {
            return true;
          }
        return false;
      }
  }
