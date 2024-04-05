/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Edgar Chajón
 */
public class ProyectoUMG extends Application {
    
    @Override
    public void start (Stage primaryStage){
        
        
        Label label=new Label("Hola prueba");
        StackPane root=new StackPane();
        root.getChildren().add(label);
        
         Scene scene = new Scene(root, 700, 700);
         
         primaryStage.setScene(scene);
         primaryStage.setTitle("JAVAFX");
         primaryStage.show();
        
        
       
        
        
        
        
        
          
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
