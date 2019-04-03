package Affichage;

import com.sun.deploy.util.StringUtils;
import com.sun.rowset.internal.Row;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private final ObservableList<row> data =
            FXCollections.observableArrayList(

            );
    public static void main(String[] args) {
    launch(args);
}

    @Override
    public void start(Stage primaryStage) throws Exception {

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);




        LoadFile loadFile = new LoadFile(selectedFile.getPath());
        ArrayList<ArrayList<String>> dataNoFilter = loadFile.getDataValuesGlobal();
        for (ArrayList<String> arrayRow  : dataNoFilter
             ) {
                data.add(new row(arrayRow.get(0),
                        arrayRow.get(1),
                        round(Double.parseDouble(arrayRow.get(2)),2)
                        ,round(Double.parseDouble(arrayRow.get(3)),2),
                        round(Double.parseDouble(arrayRow.get(4)),2)));
        }


        primaryStage.setTitle("TwitMiner");
        VBox vBoxStruct = new VBox();
        vBoxStruct.setSpacing(10);
        Scene scene=new Scene(vBoxStruct,1000,1000);
        primaryStage.setScene(scene);
        Tableau table= new Tableau(primaryStage);
        table.setPrefHeight(800.0);
        TextField textMinFreq = new TextField();
        textMinFreq.setPromptText("minFreq");
        TextField textMinLift = new TextField();
        textMinLift.setPromptText("minLift");
        TextField textMinConf = new TextField();
        textMinConf.setPromptText("minConf");
        Button button = new Button("Ok");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Double minLift;
                Double minFreq;
                Double minConf;
                if("".equals(textMinConf.getText())) {
                    minConf = 0.0;
                }else {
                    minConf = Double.parseDouble(textMinConf.getText());
                }
                if(textMinFreq.getText().equals("")){
                    minFreq = 0.0;
                }else {
                    minFreq = Double.parseDouble(textMinFreq.getText());
                }
                if(textMinLift.getText().equals("")){
                    minLift = 0.0;
                }else {
                    minLift = Double.parseDouble(textMinLift.getText());
                }


                for (int i = data.size()-1; i > -1 ; i--) {
                    if(data.get(i).getLift() < minLift
                            || data.get(i).getConf() < minConf
                            || data.get(i).getFreq() < minFreq)
                        data.remove(data.get(i));
                }
                table.setItems(data);
            }
        });

        HBox config = new HBox();
        config.getChildren().addAll(textMinFreq,textMinLift,textMinConf,button);
        config.setSpacing(10);
        table.setItems(data);
        vBoxStruct.getChildren().addAll(config,table);

        primaryStage.show();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
