package com.example.tramitec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.example.tramitec.util.MatriculaModel;
import com.example.tramitec.util.StageLoaderMatricula;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import com.example.tramitec.model.AlumnoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class LandingPageController implements Initializable {

    @FXML
    private Label LabelCarrera;

    @FXML
    private Label LabelCorreo;
    
    @FXML
    private Button btnCerrar;

    @FXML
    private Label LabelControl;

    @FXML
    private Label LabelNombre;

    @FXML
    private Button btnService;

    @FXML
    private Button btnTitle;

    @FXML
    private ImageView logoHome;

    private MatriculaModel matriculaModel;

    private AlumnoDAO ADAO = new AlumnoDAO();

    public void setMatriculaModel(MatriculaModel matriculaModel) {
        this.matriculaModel = matriculaModel;
    }


    @FXML
    void clickBtn(ActionEvent event) {
        if (event.getSource().equals(btnService)) {
            System.out.println(matriculaModel.getMatricula());
            try {
                StageLoaderMatricula.load("viewStatus.fxml", event, matriculaModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource().equals(btnCerrar)) {
                try {
                    StageLoaderMatricula.load("ViewLogin.fxml", event, null);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        } else {
            JOptionPane.showMessageDialog(null, "EN DESARROLLO, VUELVA MAS TARDE", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    void clickLogo(MouseEvent event) {
        try {
            StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            Platform.runLater(() -> {
                LabelNombre.setText(String.valueOf(ADAO.getNombre(matriculaModel.getMatricula())));
                LabelControl.setText(String.valueOf(matriculaModel.getMatricula()));
                LabelCarrera.setText(String.valueOf(ADAO.getCarrera(matriculaModel.getMatricula())));
                LabelCorreo.setText(String.valueOf(ADAO.getCorreo(matriculaModel.getMatricula())));
            });
        });
    
        hilo.start();
    }

}