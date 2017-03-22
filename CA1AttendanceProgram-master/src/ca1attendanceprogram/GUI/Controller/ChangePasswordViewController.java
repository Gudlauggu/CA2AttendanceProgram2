/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Person;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.GUI.Model.LoginModel;
import ca1attendanceprogram.GUI.Model.StudentModel;
import ca1attendanceprogram.GUI.Model.TeacherModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gudla
 */
public class ChangePasswordViewController implements Initializable
{

    @FXML
    private TextField txtOldPass;
    @FXML
    private TextField txtNewPass;
    @FXML
    private TextField txtNewPassAgain;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnChange;

    @FXML
    private TextField txtUsername;

    // 0 = not logged int // 1 = logged in // 2 = wrong password, not logged in
    private static final int NOT_LOGGED_IN = 1;
    private static final int LOGGED_IN = 2;
    private static final int WRONG_PASSWORD = 3;
    private int loginState = NOT_LOGGED_IN;
    private static final StudentModel STUDENT_MODEL = new StudentModel();
    private static final TeacherModel TEACHER_MODEL = new TeacherModel();
    private static final LoginModel LOGIN_MODEL = new LoginModel();
    private Person person = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }

    @FXML
    private void cancelEvent(ActionEvent event) throws IOException
      {
        showLoginWindow();
      }

    @FXML
    private void changePass(ActionEvent event) throws IOException
      {
        if (person == null)
          {
            person = LOGIN_MODEL.LoginChecker(txtUsername.getText().trim(), txtOldPass.getText().trim());

          }
        if (loginState != LOGGED_IN
                && person != null)
          {

            if (checkIfSame() != true)
              {
                if (person.getClass() == Student.class)
                  {
                    STUDENT_MODEL.changePassword(txtNewPass.getText().trim(), txtUsername.getText().trim());
                    loginState = LOGGED_IN;
                    System.out.println("Student password has been changed!");
                    showLoginWindow();

                  } else if (person.getClass() == Teacher.class)
                  {
                    TEACHER_MODEL.changePassword(txtNewPass.getText().trim(), txtUsername.getText().trim());
                    loginState = LOGGED_IN;
                    System.out.println("Teacher password has been changed!");
                    showLoginWindow();
                  }
              } else if (checkIfSame() != false)
              {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Wrong Password");
                alert.setHeaderText(null);
                alert.setContentText("Oops seems that the new password does not match! Try again");

                alert.showAndWait();

              }
          } else if (loginState != LOGGED_IN && person == null)
          {

            loginState = WRONG_PASSWORD;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Wrong Password");
            alert.setHeaderText(null);
            alert.setContentText("Oops seem your old password is incorrect! Try again!");

            alert.showAndWait();

          }

      }

    private boolean checkIfSame()
      {
        if (txtNewPass.getText().trim() == txtNewPassAgain.getText().trim())
          {
            return true;
          } else
          {
            return false;
          }
      }

    private Student studLoginChecker()
      {
        return STUDENT_MODEL.LoginChecker(txtUsername.getText().trim(), txtOldPass.getText().trim());

      }

    private void showLoginWindow() throws IOException
      {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca1attendanceprogram/GUI/View/Login.fxml"));
        Parent root = loader.load();
        Stage subStage = new Stage();
        subStage.setScene(new Scene(root));
        subStage.initStyle(StageStyle.UNDECORATED);
        subStage.show();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
      }

}
