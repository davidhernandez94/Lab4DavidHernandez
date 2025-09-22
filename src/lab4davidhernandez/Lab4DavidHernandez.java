/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4davidhernandez;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author 6309110
 */
public class Lab4DavidHernandez extends Application {
    private static final double MILEAGE_ALLOWANCE = 0.27;
    private static final double MEAL_ALLOWANCE = 37;
    private static final double PARKING_ALLOWANCE = 10;
    private static final double LODGING_ALLOWANCE = 95;
    private static final double TAXI_ALLOWANCE = 20;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label title = new Label("Welcome to business expense calculator");
        title.setId("title");
        Label days = new Label("Number of days on the trip:");
        Label airfare = new Label("Amount of airfare, if any:");
        Label carRental = new Label("Car rental fees:");
        Label miles = new Label("Miles Driven:");
        Label parking = new Label("Parking fees:");
        Label taxi = new Label("Taxi charges:");
        Label conference = new Label("Conference/Seminar registration fees:");
        Label lodging = new Label("Lodging charges, per night:");
        Label total = new Label();
        
        TextField daysField = new TextField();
        TextField airfareField = new TextField();
        TextField carRentalField = new TextField();
        TextField milesField = new TextField();
        TextField parkingField = new TextField();
        TextField taxiField = new TextField();
        TextField conferenceField = new TextField();
        TextField lodgingField = new TextField();
        
        Button calculate = new Button("Calculate");
        calculate.setDisable(true);
        
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setVgap(15);
        gp.setPadding(new Insets(20, 40, 20, 40));
        gp.add(title, 0, 0, 2, 1);
        gp.add(days, 0, 1);
        gp.add(daysField, 1, 1);
        gp.add(airfare, 0, 2);
        gp.add(airfareField, 1, 2);
        gp.add(carRental, 0, 3);
        gp.add(carRentalField, 1, 3);
        gp.add(miles, 0, 4);
        gp.add(milesField, 1, 4);
        gp.add(parking, 0, 5);
        gp.add(parkingField, 1, 5);
        gp.add(taxi, 0, 6);
        gp.add(taxiField, 1, 6);
        gp.add(conference, 0, 7);
        gp.add(conferenceField, 1, 7);
        gp.add(lodging, 0, 8);
        gp.add(lodgingField, 1, 8);
        gp.add(calculate, 0, 9);
        gp.add(total, 0, 10);
        
        EventHandler disableCalculateHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                calculate.setDisable(
                        !daysField.getText().matches("^\\d+$") ||
                        !airfareField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !carRentalField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !milesField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !parkingField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !taxiField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !conferenceField.getText().matches("^\\d*[.]?\\d?\\d?$") ||
                        !lodgingField.getText().matches("^\\d+[.]?\\d?\\d?$"));
            }
        };
        
        EventHandler clickedCalculateHandler;
        clickedCalculateHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (airfareField.getText().isEmpty()) {
                    airfareField.setText("0");
                }
                if (carRentalField.getText().isEmpty()) {
                    carRentalField.setText("0");
                }
                if (milesField.getText().isEmpty()) {
                    milesField.setText("0");
                }
                if (parkingField.getText().isEmpty()) {
                    parkingField.setText("0");
                }
                if (taxiField.getText().isEmpty()) {
                    taxiField.setText("0");
                }
                if (conferenceField.getText().isEmpty()) {
                    conferenceField.setText("0");
                }
                double result = 0;
                result += Double.parseDouble(airfareField.getText());
                result += Double.parseDouble(carRentalField.getText());
                if (Double.parseDouble(parkingField.getText()) > 
                        (PARKING_ALLOWANCE * Double.parseDouble(daysField.getText()))) {
                    result += (Double.parseDouble(parkingField.getText()) - PARKING_ALLOWANCE * Double.parseDouble(daysField.getText()));
                }
                if (Double.parseDouble(taxiField.getText()) > 
                        (TAXI_ALLOWANCE * Double.parseDouble(taxiField.getText()))) {
                    result += (Double.parseDouble(taxiField.getText()) - TAXI_ALLOWANCE * Double.parseDouble(taxiField.getText()));
                }
                if (Double.parseDouble(lodgingField.getText()) > 
                        (LODGING_ALLOWANCE * Double.parseDouble(lodgingField.getText()))) {
                    result += (Double.parseDouble(lodgingField.getText()) - LODGING_ALLOWANCE * Double.parseDouble(lodgingField.getText()));
                }
                if (carRentalField.getText() == "0") {
                    result -= Double.parseDouble(milesField.getText()) * MILEAGE_ALLOWANCE;
                }
                result += Double.parseDouble(conferenceField.getText());
                result -= Double.parseDouble(conferenceField.getText()) * MEAL_ALLOWANCE;
                
                total.setText("total expenses: $" + Double.toString(result));
            }
        };
        
        daysField.setOnKeyReleased(disableCalculateHandler);
        airfareField.setOnKeyReleased(disableCalculateHandler);
        carRentalField.setOnKeyReleased(disableCalculateHandler);
        milesField.setOnKeyReleased(disableCalculateHandler);
        parkingField.setOnKeyReleased(disableCalculateHandler);
        taxiField.setOnKeyReleased(disableCalculateHandler);
        conferenceField.setOnKeyReleased(disableCalculateHandler);
        lodgingField.setOnKeyReleased(disableCalculateHandler);
        calculate.setOnMouseReleased(clickedCalculateHandler);
        
        BorderPane root = new BorderPane(gp);
        gp.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styling.css");
        stage.setScene(scene);
        stage.show();
    }
}
