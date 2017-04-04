/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Controller;

import ca1attendanceprogram.BE.Student;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * FXML Controller class
 *
 * @author Mecaa
 */
public class ImageHolderViewController implements Initializable
  {

    @FXML
    private ImageView imgImageHolder;
    @FXML
    private Label lblName;
    private Student student;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
      {
        // TODO
      }

    public void altInitialize(Student stud)
      {
        setStudent(stud);
        lblName.setText(student.getName());
        if (student.getStudentImage() != null)
          {
            BufferedImage bf = stud.getStudentImage();
            WritableImage wr = null;
            if (bf != null)
              {
                wr = new WritableImage(bf.getWidth(), bf.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < bf.getWidth(); x++)
                  {
                    for (int y = 0; y < bf.getHeight(); y++)
                      {
                        pw.setArgb(x, y, bf.getRGB(x, y));
                      }
                  }
              }
            imgImageHolder.setImage(wr);
          }
        else
          {
            Image defAvatar = new Image("file:DATA/defAvatar.png");

            imgImageHolder.setImage(defAvatar);
          }
      }

    public Student getStudent()
      {
        return student;
      }

    private void setStudent(Student student)
      {
        this.student = student;
      }

  }
