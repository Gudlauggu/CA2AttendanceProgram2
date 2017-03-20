/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

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
    private TextField txtEmailConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnChange;

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
