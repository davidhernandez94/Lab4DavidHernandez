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
 * @author David Hernandez
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
        // initialize labels
        Label title = new Label(" Welcome to Business Expense Calculator!! ");
        Label days = new Label("Number of days on the trip:");
        Label airfare = new Label("Amount of airfare, if any:");
        Label carRental = new Label("Car rental fees:");
        Label miles = new Label("Miles Driven:");
        Label parking = new Label("Parking fees:");
        Label taxi = new Label("Taxi charges:");
        Label conference = new Label("Conference/Seminar registration fees:");
        Label lodging = new Label("Lodging charges, per night:");
        Label expensesLabel = new Label("Total Expenses");
        Label allowableLabel = new Label("Total Allowable Expenses");
        Label excessLabel = new Label("Total Excess");

        // set styling (see styling.css)
        title.setId("title");
        expensesLabel.getStyleClass().add("result-button");
        allowableLabel.getStyleClass().add("result-button");
        excessLabel.getStyleClass().add("result-button");

        // initialize textFields
        TextField daysField = new TextField();
        TextField airfareField = new TextField();
        TextField carRentalField = new TextField();
        TextField milesField = new TextField();
        TextField parkingField = new TextField();
        TextField taxiField = new TextField();
        TextField conferenceField = new TextField();
        TextField lodgingField = new TextField();

        // initialize calculate button and set its default state to disabled
        Button calculate = new Button("Calculate");
        calculate.setDisable(true);

        // initialize gridPane and set up basic settings
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setVgap(15);
        gp.setPadding(new Insets(20, 40, 20, 40));

        // add nodes into gridPane
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
        gp.add(expensesLabel, 0, 10);
        gp.add(allowableLabel, 1, 10);
        gp.add(excessLabel, 0, 11);

        // handle when it is permissible to click the calculate button
        // based on what is contained in textFields
        EventHandler<KeyEvent> disableCalculateHandler = new EventHandler<KeyEvent>() {
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

        // handle when to disable certain textFields
        // carRentalField and milesDrivenField can't both contain data
        EventHandler<KeyEvent> disableTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!carRentalField.getText().isEmpty()) {
                    milesField.setDisable(true);
                } else {
                    milesField.setDisable(false);
                }
                if (!milesField.getText().isEmpty()) {
                    carRentalField.setDisable(true);
                } else {
                    carRentalField.setDisable(false);
                }
            }
        };

        // handle when the calculate button is clicked
        // calculate expenses, allowable expenses, and amount saved (or excess to be paid)
        EventHandler<MouseEvent> clickedCalculateHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                // changes empty textFields to 0
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

                // create variables to define what is contained in textFields
                int days = Integer.parseInt(daysField.getText());
                double airfare = Integer.parseInt(airfareField.getText());
                double carRental= Integer.parseInt(carRentalField.getText());
                double miles = Integer.parseInt(milesField.getText());
                double parking = Integer.parseInt(parkingField.getText());
                double taxi = Integer.parseInt(taxiField.getText());
                double conference = Integer.parseInt(conferenceField.getText());
                double lodging = Integer.parseInt(lodgingField.getText());

                // calculate total expenses and allowable expenses
                double expenses = airfare + carRental + parking + taxi + conference + lodging * days;
                double allowable = MEAL_ALLOWANCE * days;
                if (days * PARKING_ALLOWANCE > parking) {
                    allowable += parking;
                } else {
                    allowable += days * PARKING_ALLOWANCE;
                }
                if (days * TAXI_ALLOWANCE > taxi) {
                    allowable += taxi;
                } else {
                    allowable += days * TAXI_ALLOWANCE;
                }
                if (LODGING_ALLOWANCE > lodging) {
                    allowable += days * lodging;
                } else {
                    allowable += days * LODGING_ALLOWANCE;
                }
                if (carRental != 0) {
                    allowable += miles * MILEAGE_ALLOWANCE;
                }

                // change labels according to results of calculations
                expensesLabel.setText("total expenses: $" + Double.toString(expenses));
                allowableLabel.setText("total allowable expenses: $" + Double.toString(allowable));
                if (expenses > allowable) {
                    excessLabel.setText("Excess: $" + Double.toString(expenses - allowable));
                } else {
                    excessLabel.setText("Saved: $" + Double.toString(allowable - expenses));
                }
            }
        };

        // call all three handlers
        daysField.setOnKeyReleased(disableCalculateHandler);
        airfareField.setOnKeyReleased(disableCalculateHandler);
        carRentalField.setOnKeyReleased(disableCalculateHandler);
        milesField.setOnKeyReleased(disableCalculateHandler);
        parkingField.setOnKeyReleased(disableCalculateHandler);
        taxiField.setOnKeyReleased(disableCalculateHandler);
        conferenceField.setOnKeyReleased(disableCalculateHandler);
        lodgingField.setOnKeyReleased(disableCalculateHandler);

        carRentalField.setOnKeyReleased(disableTextField);
        milesField.setOnKeyReleased(disableTextField);

        calculate.setOnMouseReleased(clickedCalculateHandler);

        // reveal stage
        BorderPane root = new BorderPane(gp);
        gp.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styling.css");
        stage.setTitle("Business Expense Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
