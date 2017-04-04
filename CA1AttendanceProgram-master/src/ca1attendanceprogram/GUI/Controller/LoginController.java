/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.*;
import ca1attendanceprogram.BLL.SettingManager;
import ca1attendanceprogram.GUI.Model.LessonModel;
import ca1attendanceprogram.GUI.Model.LoginModel;
import ca1attendanceprogram.GUI.Model.StudentLessonModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Mecaa
 */
public class LoginController implements Initializable
  {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private CheckBox boxRemUsername;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnHiddenButton;
    @FXML
    private Label lblAttendenceAll;
    @FXML
    private Label lblCurrentClass;
    @FXML
    private Label lblClassAttendance;
    @FXML
    private Label lblConfirmation;
    @FXML
    private Label lblAttending;
    @FXML
    private Button btnChangePassword;
    @FXML
    private Label lblCurrentTeacher;
    @FXML
    private AnchorPane ancLogin;
    @FXML
    private AnchorPane ancStudentInfo;
    @FXML
    private ImageView imgLogo1;
    // 0 = not logged int // 1 = logged in // 2 = wrong password, not logged in
    private static final int NOT_LOGGED_IN = 1;
    private static final int LOGGED_IN = 2;
    private static final int WRONG_PASSWORD = 3;
    private static final int LOGGED_IN_TEACHER = 4;
    private int loginState = NOT_LOGGED_IN;
    private static final LoginModel LOGIN_MODEL = new LoginModel();
    private static final LessonModel LESSON_MODEL = new LessonModel();
    private static final StudentLessonModel STUD_LESS_MODEL = new StudentLessonModel();
    private static final SettingManager SETTING_MANAGER = new SettingManager();
    Lesson lesson;
    private Person person = null;
    @FXML
    private VBox ancPicture;

    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
        Image logo = new Image("file:DATA/BASYDVEST_negativ.png");
        imgLogo.setImage(logo);
        ArrayList<String> strings = SETTING_MANAGER.getSettings();
        if (!strings.isEmpty())
          {
            txtUsername.setText(strings.get(0));
            txtPassword.setText(strings.get(1));
            boxRemUsername.setSelected(true);
          }

      }

    @FXML
    private void loginEvent(ActionEvent event) throws IOException
      {
        if (person == null)
          {
            person = LOGIN_MODEL.LoginChecker(txtUsername.getText().trim(), txtPassword.getText().trim());
          }
        if (loginState != LOGGED_IN
                && person != null)
          {

            if (person.getClass() == Student.class)
              {
                rememberSettings(); //saveUsername();
                loginState = LOGGED_IN;
                activeState();
              }
            else if (person.getClass() == Teacher.class)
              {
                rememberSettings(); //saveUsername();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/TeacherOverview.fxml"));
                Parent root = loader.load();
                TeacherOverviewController tController = (TeacherOverviewController) loader.getController();
                tController.AltInitilizer((Teacher) person);

                Stage subStage = new Stage();
                subStage.setScene(new Scene(root));

                subStage.initStyle(StageStyle.UNDECORATED);

                subStage.show();
                Stage stage = (Stage) btnHiddenButton.getScene().getWindow();
                stage.close();

              }
          }
        else if (loginState != LOGGED_IN && person == null)
          {

            loginState = WRONG_PASSWORD;
            activeState();
          }
        else if (loginState == LOGGED_IN)
          {
            if (btnLogin.getText().equals("Attend Class"))
              {
                lblAttending.setVisible(true);
                btnLogin.setText("Leave Class");
                StudentLesson studLess = new StudentLesson((Student) person, lesson, 0);
                STUD_LESS_MODEL.setStudentAttendence(studLess, 1);
                lblAttendenceAll.setText(STUD_LESS_MODEL.getAllAbsenceAsPercentage((Student) person));
                lblClassAttendance.setText(STUD_LESS_MODEL.getAbsenceForCurrentCourse((Student) person, lesson));
              }
            else if (btnLogin.getText().equals("Leave Class"))
              {
                lblAttending.setVisible(false);
                btnLogin.setText("Attend Class");
                StudentLesson studLess = new StudentLesson((Student) person, lesson, 0);
                STUD_LESS_MODEL.setStudentAttendence(studLess, 0);
                lblAttendenceAll.setText(STUD_LESS_MODEL.getAllAbsenceAsPercentage((Student) person));
                lblClassAttendance.setText(STUD_LESS_MODEL.getAbsenceForCurrentCourse((Student) person, lesson));
              }

          }
      }

    public void rememberSettings()
      {
        if (boxRemUsername.isSelected())
          {
            SETTING_MANAGER.rememberUsername(txtUsername.getText(), txtPassword.getText());
          }
        else
          {
            SETTING_MANAGER.resetSettings();
          }
      }

    @FXML
    /**
     * @Param closeEvent is an event that triggers differently based on the
     * current login state
     *
     */
    private void closeEvent(ActionEvent event)
      {
        if (loginState != LOGGED_IN)
          {
            Platform.exit();
          }
        if (loginState == LOGGED_IN)
          {
            loginState = NOT_LOGGED_IN;
            activeState();
            lblAttending.setVisible(false);
            btnChangePassword.setVisible(false);
            person = null;
          }
      }

    public void activeState()
      {

        AnimateLogin aLogin = new AnimateLogin();
        switch (loginState)
          {
            case LOGGED_IN:
                ancPicture.getChildren().clear();
                Student stud = (Student) person;

                txtUsername.setDisable(true);
                txtPassword.setDisable(true);
                btnLogin.setText("Attend Class");
                btnClose.setText("Log Off");
                btnHiddenButton.getStyleClass().remove("button-small");
                btnHiddenButton.getStyleClass().add("button");
                btnHiddenButton.setVisible(true);
                btnHiddenButton.setText("See Absense");
                lblConfirmation.setVisible(false);
                boxRemUsername.setDisable(true);
                btnChangePassword.setVisible(true);
                lesson = getNewestLesson();
                if (lesson != null && lesson.getCal().DAY_OF_YEAR == Calendar.DAY_OF_YEAR)
                  {
                    lblCurrentClass.setText(lesson.getLessonName());
                    lblCurrentTeacher.setText(lesson.getTeacherName());
                    lblAttendenceAll.setText(STUD_LESS_MODEL.getAllAbsenceAsPercentage(stud));
                    lblClassAttendance.setText(STUD_LESS_MODEL.getAbsenceForCurrentCourse(stud, lesson));
                    StudentLesson studLess = STUD_LESS_MODEL.getOneStudentLessonFromLessonAndStudent(stud, lesson);
                    if (studLess.getAttendint() == 1)
                      {
                        lblAttending.setVisible(true);
                        btnLogin.setText("Leave Class");
                      }

                  }
                else
                  {

                    lblCurrentClass.setText("No Class is running");
                    lblCurrentTeacher.setText("N/A");
                    lblAttendenceAll.setText(STUD_LESS_MODEL.getAllAbsenceAsPercentage(stud));
                    lblClassAttendance.setText("N/");

                  }
                try
                  {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/ImageHolderView.fxml"));
                    Parent root;
                    root = loader.load();
                    ancPicture.getChildren().add(root);
                    ImageHolderViewController imgController = loader.getController();
                    imgController.altInitialize(stud);
                  }
                catch (IOException ex)
                  {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                  }

                aLogin.doAsTold(true).play();
                break;
            case NOT_LOGGED_IN:

                txtUsername.setDisable(false);
                txtPassword.setDisable(false);
                if (!boxRemUsername.isSelected())
                  {
                    txtUsername.clear();
                    txtPassword.clear();
                  }
                btnHiddenButton.setVisible(false);
                btnLogin.setText("Login");
                btnClose.setText("Quit");
                boxRemUsername.setDisable(false);
                aLogin.doAsTold(false).play();
                break;
            case WRONG_PASSWORD:
                btnHiddenButton.setVisible(true);
                btnHiddenButton.setText("Forgotten password?");
                btnHiddenButton.getStyleClass().remove("button");
                btnHiddenButton.getStyleClass().add("button-small");
                break;
            case LOGGED_IN_TEACHER:
                break;

          }
      }

    @FXML
    private void HiddenButtonEvent(ActionEvent event) throws IOException
      {
        if (loginState == LOGGED_IN)
          {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/AbsenceOverview.fxml"));

            Parent root = loader.load();
            AbsenceOverviewController AOController = (AbsenceOverviewController) loader.getController();
            AOController.altInitialize((Student) person);
            Stage subStage = new Stage();
            subStage.setScene(new Scene(root));
            subStage.initStyle(StageStyle.UNDECORATED);
            subStage.show();
            Stage stage = (Stage) btnHiddenButton.getScene().getWindow();
            stage.close();

          }
        else if (loginState == WRONG_PASSWORD)
          {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Forgotten Password");
            alert.setHeaderText("Email: " + txtUsername.getText() + "@easv365.dk");
            alert.setContentText("Send password to this email");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
              {
                lblConfirmation.setVisible(true);
                lblConfirmation.setText("A password has been send to your e-mail.");
              }
            else
              {
                // ... user chose CANCEL or closed the dialog
                //Do nothing
              }
          }
      }

//    public void saveUsername() {
//        if (boxRemUsername.isSelected()) {
//            USER_MANAGER.saveUsername(txtUsername.getText().trim(), txtPassword.getText().trim());
//        } else {
//            USER_MANAGER.resetFile();
//        }
//    }
    public void onKeyPressed(KeyCode code) throws IOException
      {
        if (code.equals(KeyCode.ENTER))
          {
            loginEvent(new ActionEvent());
          }
      }

    @FXML
    private void ChangePassword(ActionEvent event) throws IOException
      {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/ChangePasswordView.fxml"));
        Parent root = loader.load();
        ChangePasswordViewController passController = (ChangePasswordViewController) loader.getController();
        passController.getStudent((Student) person);

        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));

        subStage.initStyle(StageStyle.UNDECORATED);

        subStage.show();
        Stage stage = (Stage) btnChangePassword.getScene().getWindow();
        stage.close();

      }

    private Lesson getNewestLesson()
      {
        return LESSON_MODEL.getNewestLesson();
      }

    private class AnimateLogin
      {

        final double startWidth = ancLogin.getWidth();
        final double studStartWidth = ancStudentInfo.getWidth();

        private Animation doAsTold(Boolean bool)
          {

            final Animation hideLogin = new Transition()
              {
                  {
                    setCycleDuration(Duration.millis(1_000));
                  }

                protected void interpolate(double frac)
                  {
                    //look here
                    final double curWidth = startWidth * (1.0 - frac);
                    ancLogin.setTranslateX(-startWidth + curWidth);
                    final double studCurWidth = studStartWidth * (1.0 - frac);
                    ancStudentInfo.setTranslateX(-studStartWidth + studCurWidth);
                  }
              };
            final Animation showLogin = new Transition()
              {
                  {
                    setCycleDuration(Duration.millis(1_000));
                  }

                protected void interpolate(double frac)
                  {
                    //look here
                    final double curWidth = startWidth * frac;
                    ancLogin.setTranslateX(-startWidth + curWidth);
                    final double studCurWidth = studStartWidth * frac;
                    ancStudentInfo.setTranslateX(-studStartWidth + studCurWidth);
                  }
              };
            if (bool)
              {
                return hideLogin;
              }
            else
              {
                return showLogin;
              }
          }
      }

  }
