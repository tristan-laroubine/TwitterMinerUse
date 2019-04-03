package Affichage;

import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Tableau extends TableView<row> {

    public Tableau(Stage primaryStage){

        //Col
        TableColumn attrMots1 = new TableColumn("---------");
        TableColumn attrMots2 = new TableColumn("---------");
        TableColumn conf = new TableColumn("Conf");
        TableColumn lift  = new TableColumn("Lift");
        TableColumn freq = new TableColumn("Freq");

        //size
        attrMots1.prefWidthProperty().bind(this.widthProperty().multiply(0.4));
        attrMots2.prefWidthProperty().bind(this.widthProperty().multiply(0.4));
        freq.prefWidthProperty().bind(this.widthProperty().multiply(0.06));
        conf.prefWidthProperty().bind(this.widthProperty().multiply(0.06));
        lift.prefWidthProperty().bind(this.widthProperty().multiply(0.06));

        //CellValues

        attrMots1.setCellValueFactory(new PropertyValueFactory<row,String>("str1"));
        attrMots2.setCellValueFactory(new PropertyValueFactory<row,String>("str2"));
        freq.setCellValueFactory(new PropertyValueFactory<row,String>("freq"));
        conf.setCellValueFactory(new PropertyValueFactory<row,Double>("conf"));
        lift.setCellValueFactory(new PropertyValueFactory<row,Double>("lift"));

        this.getColumns().addAll(attrMots1,attrMots2,conf,lift,freq);

    }
}
