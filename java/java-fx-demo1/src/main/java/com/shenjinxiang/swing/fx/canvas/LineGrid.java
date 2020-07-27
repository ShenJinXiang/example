package com.shenjinxiang.swing.fx.canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/27 13:09
 */
public class LineGrid extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int width = 50;
        int num = 3;
        int padding = 50;
        VBox vBox = new VBox();
//        Scene scene = new Scene(vBox, 2 * padding + num * width, 2 * padding + num * width);
        Scene scene = new Scene(vBox, 300, 250);
        Line line = new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);

        vBox.getChildren().add(line);
//        ObservableList<Node> list = vBox.getChildren();
//        for (int i = 0; i < num; i++) {
//            Line lineX = new Line(padding, padding + i * width, padding + width * num, padding + i * width);
//            Line lineY = new Line(padding + i * width, padding, padding + i * width, padding + num * width);
//            vBox.getChildren().add(lineX);
//            vBox.getChildren().add(lineY);
//        }
        vBox.getChildren().add(new Line(100f, 100f, 200f, 50f));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }

}
