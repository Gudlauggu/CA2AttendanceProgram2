/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Person;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.GUI.Model.StudentModel;
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
            person = loginChecker();
            
          }
        if (loginState != LOGGED_IN
                && person != null)
          {
            
            if (checkIfSame() != true)
              {
                  
                STUDENT_MODEL.changePassword(txtNewPass.getText().trim(), txtUsername.getText().trim());
                showLoginWindow();
                System.out.println("password been changed!");
                
              }
          }
          
      }
    
    private boolean checkIfSame()
      {
        if (txtNewPass.getText().trim() == txtNewPassAgain.getText().trim())
          {
            return true;
          }
        else
            return false;
      }
    
    private Student loginChecker()
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
