package com.slotmechine.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Created by devon on 10/31/17.
 * This is the main gui class
 * Also contains the onAction methods of the buttons and labels of the GUI
 */
public class MainGUI extends Application {

    //this is the main window of the application
    static Stage window;
    //these are the scenes which will be set to the main window
    static Scene mainScene, welcomeScene;

    //creating an instance of operation to acces the instance methods
    private Operations operations = new Operations();

    //defining the class variables
    static Label reel1, reel2, reel3;
    static ImageView imageviewReel1, imageviewReel2, imageviewReel3;
    static Image imageReel1, imageReel2, imageReel3;
    static TextField tfCredits;
    static TextField tfBets;
    static Label lblDisplayMsg;
    static ImageView logoIv;


    //this is the entry point to the program
    public static void main(String[] args) {
        //this is a method in the 'Application' class which start the program as a javaFX application
        launch(args);
    }


    //this method is called when  launch(args) is called
    @Override
    public void start(Stage primaryStage) throws Exception {
        //for easy indetification of the window and acces it outside this method the following statement is excecuted
        window = primaryStage;

        //setting the welcome scene to the window / primary stage
        window.setScene(getWelcomeScene());

        window.setMinHeight(660);
        window.setMinWidth(680);
        //setting window resizable to false
        // window.resizableProperty().setValue(false);
        //making the window visible
        window.show();

    }


    //this method will return the Main screen
    public Scene getMainScene() {
        //creating the root layout
        GridPane root = new GridPane();
        //setting the style class of the grid pane
        root.getStyleClass().add("gridPane");
        //setting the grid lines to be visible
        //root.setGridLinesVisible(true);
        //setting paddings
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);


        //==============================================   HEADER   =================================================

        //Creating an image object and setting path to image
        Image image = new Image(getClass().getResourceAsStream("Images/logo.png"));
        logoIv = new ImageView(image);
        logoIv.setFitHeight(72);
        logoIv.setFitWidth(320);

        //creating empty label
        Label labelTitle = new Label("");
        //setting image to the label
        labelTitle.setGraphic(logoIv);

        //creating button
        Button infoBtn = new Button("PayOut");

        //adding the labelTitle to the root grid pane
        root.add(labelTitle, 2, 1, 1, 1);
        //adding button
        root.add(infoBtn, 3, 1, 1, 1);

        GridPane.setHalignment(labelTitle, HPos.CENTER);//setting the alignment to center
        GridPane.setHalignment(infoBtn, HPos.RIGHT);//setting the alignment to RIGHT

        //setting the style class of the labelTitle
        labelTitle.getStyleClass().add("lblHeader");

        //==============================================   FOOTER   =================================================

        //creating lable for footer
        Label labelfooter = new Label("Copyrights 2017 - Devon Wijesinghe");

        //adding the labelfooter to the root grid pane
        root.add(labelfooter, 1, 8, 3, 1);
        GridPane.setHalignment(labelfooter, HPos.CENTER);//setting the alignment to center

        //setting the style class
        labelfooter.getStyleClass().add("lblFooter");


        //==============================================   3 REELS   =================================================

        //========  REEL1   ========

        //Creating an image object and setting path to image
        imageReel1 = new Image(getClass().getResourceAsStream("Images/bell.png"));
        imageviewReel1 = new ImageView(imageReel1);
        imageviewReel1.setFitHeight(100);
        imageviewReel1.setFitWidth(100);

        reel1 = new Label("");
        reel1.setGraphic(imageviewReel1);

        root.add(reel1, 1, 3, 1, 1);
        GridPane.setHalignment(reel1, HPos.CENTER);
        reel1.getStyleClass().add("reelBg");

        //========  REEL2   ========


        //Creating an image object and setting path to image
        imageReel2 = new Image(getClass().getResourceAsStream("Images/lemon.png"));
        imageviewReel2 = new ImageView(imageReel2);
        imageviewReel2.setFitHeight(100);
        imageviewReel2.setFitWidth(100);


        reel2 = new Label("");
        reel2.setGraphic(imageviewReel2);
        root.add(reel2, 2, 3, 1, 1);
        GridPane.setHalignment(reel2, HPos.CENTER);
        reel2.getStyleClass().add("reelBg");

        //========  REEL3  ========


        //Creating an image object and setting path to image
        imageReel3 = new Image(getClass().getResourceAsStream("Images/plum.png"));
        imageviewReel3 = new ImageView(imageReel3);
        imageviewReel3.setFitHeight(100);
        imageviewReel3.setFitWidth(100);


        reel3 = new Label("");
        reel3.setGraphic(imageviewReel3);

        root.add(reel3, 3, 3, 1, 1);
        GridPane.setHalignment(reel3, HPos.CENTER);
        reel3.getStyleClass().add("reelBg");

        //==============================================   BUTTONS   =================================================

        //creating the buttons
        Button btnBetMax = new Button("Bet Max");
        Button btnBetOne = new Button("Bet One");
        Button btnSpin = new Button("SPIN!");
        Button btnAddCoin = new Button("Add Coin");
        Button btnReset = new Button("Reset");
        Button btnStats = new Button("Statistics");


        //============  ========== Making the gridpane responsive  ========== ============

        //creating row constraints
        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(0);
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(5);
        RowConstraints r3 = new RowConstraints();
        r3.setPercentHeight(0);
        RowConstraints r4 = new RowConstraints();
        r4.setPercentHeight(40);
        RowConstraints r5 = new RowConstraints();
        r5.setPercentHeight(20);

        //creating column constraints
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(0);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(60);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(60);
        ColumnConstraints c4 = new ColumnConstraints();
        c4.setPercentWidth(60);
        ColumnConstraints c5 = new ColumnConstraints();
        c5.setPercentWidth(0);


        //adding the row contraints to the grid pane
        root.getRowConstraints().addAll(r1, r2, r3, r4, r5);
        //adding the row contraints to the grid pane
        root.getColumnConstraints().addAll(c1, c2, c3, c4, c5);

        //========== ========== ========== ========== ========== ==========


        //adding the buttons to the grid pane
        root.add(btnStats, 3, 7, 1, 1);
        root.add(btnSpin, 2, 5, 1, 1);
        root.add(btnBetOne, 1, 5, 1, 1);
        root.add(btnAddCoin, 1, 7, 1, 1);
        root.add(btnReset, 2, 7, 1, 1);
        root.add(btnBetMax, 3, 5, 1, 1);

        //setting the position of the buttons to center
        GridPane.setHalignment(btnBetMax, HPos.CENTER);
        GridPane.setHalignment(btnSpin, HPos.CENTER);
        GridPane.setHalignment(btnBetOne, HPos.CENTER);
        GridPane.setHalignment(btnAddCoin, HPos.CENTER);
        GridPane.setHalignment(btnReset, HPos.CENTER);
        GridPane.setHalignment(btnStats, HPos.CENTER);

        //setting the style classes for the buttons
        btnBetMax.getStyleClass().add("btn1");
        btnBetOne.getStyleClass().add("btn1");
        btnStats.getStyleClass().add("btn1");
        btnSpin.getStyleClass().add("btn2");
        btnAddCoin.getStyleClass().add("btn1");
        btnReset.getStyleClass().add("btn1");
        infoBtn.getStyleClass().add("btn4");

        //==== to add sound effects====
        Operations op = new Operations();
        //adding sound effect when won
        MediaPlayer mediaPlayer = op.getSountEffect("Audio/spin.mp3");
        //=============================

        //==== BUTTON - ON CLICK EVENTS ====

         /*this booleans array is used to check if reels are stoped
        * First three reels are for checking three induvidal reels and the fourth boolean is to check if all three are stopped
        * */
        boolean[] isStopedReelArr = {false, false, false, false};

        //on click actions of the buttons


        btnSpin.setOnAction((ActionEvent e) -> {

            //checking if the bet amount is less than zero
            if (Operations.getBet() <= 0) {
                //this warning will popup if bet amount is less than zero
                PopUps.popUp("Warning", "Bet amount can not be 0", "Please make a bet using the BET MAX button or BET ONE button", Alert.AlertType.WARNING);

            } else {
                //checking if credits is more than zero
                if (Operations.getCredits() > 0) {
                    //this statement will execute when the SPIN button is pressed if credits is more than zero
                    try {

                        //this method will start spinning the reels
                        operations.spinReels();
                        //enabling the bet max button
                        btnBetMax.setDisable(false);

                        //Disabling the spin button
                        btnSpin.setDisable(true);
                        //creating an object of operation class
                        mediaPlayer.play();

                        //setting the is stopped boolean varable to false - which means reels are spinning
                        isStopedReelArr[0] = false;
                        isStopedReelArr[1] = false;
                        isStopedReelArr[2] = false;
                        isStopedReelArr[3] = false;


                        //setting betMaxClickCount to 0 to show that the bet max button is not clicked in that game
                        operations.betMaxClickCount = 0;


                        System.out.printf("");
                    } catch (InterruptedException e1) {
                        //if the above operation fails, this catch block will get exciuted
                        e1.printStackTrace();
                    }
                } else {
                    //if credit is equal to 0, the following error popup will show
                    PopUps.popUp("Error", "Insufficient amount of credits!", "You can not continue to play with 0 credits! Use the add coin button to add credits.", Alert.AlertType.ERROR);

                }

            }


        });

        infoBtn.setOnAction(e -> {
            //this statement will execute when the Payout Table button is pressed
            getInfoWindow();
        });


        btnAddCoin.setOnAction(e -> {
            //this statement will execute when the ADD COIN button is pressed
            Operations.addCoin();
        });

        btnBetOne.setOnAction(e -> {
            //this statement will execute when the BET ONE button is pressed
            Operations.betOne();
        });


        btnBetMax.setOnAction(e -> {

            //this statement will execute when the BET MAX button is pressed
            Operations.betMax();

        });

        btnStats.setOnAction(e -> {
            //this statement will execute when the STATISTICS button is pressed
            if (Operations.getLostCount() == 0 && Operations.getWonCount() == 0) {
                //if there is not stats found, the following warning will appear
                PopUps.popUp("Warning!", "No statistics available!", "You have to play a game first to view statistics.", Alert.AlertType.WARNING);
            } else {
                Operations.statistics();
            }

        });

        btnReset.setOnAction(e -> {
            if (Operations.reel1Thread == null && Operations.reel2Thread == null && Operations.reel3Thread == null) {

            }
            //this statement will execute when the RESET button is pressed
            Operations.resetGui();

            //enabling the spin button
            btnSpin.setDisable(false);

        });
        //==== LABELS - ON CLICK EVENTS ====


        reel1.setOnMouseClicked(e -> {
            //checking if the threads are not null, then  Operations.stopReels() will run
            if (Operations.reel1Thread != null && Operations.reel2Thread != null && Operations.reel3Thread != null) {

                //setting is Stoped to true to state that
                isStopedReelArr[0] = true;

                //checking if all three reel have been stopped
                if (isStopedReelArr[0] == true && isStopedReelArr[1] == true && isStopedReelArr[2] == true) {
                    //this is set to true when all three reel have been clicked
                    isStopedReelArr[3] = true;
                    //enabling the spin button only if all three reels are stoped
                    btnSpin.setDisable(false);

                    mediaPlayer.stop();//stopping the spinning sound
                    System.out.printf("");
                }

                operations.stopReel(Operations.reel1Thread, isStopedReelArr[3]);


            }
        });
        reel2.setOnMouseClicked(e -> {
            //checking if the threads are not null, then  Operations.stopReels() will run
            if (Operations.reel1Thread != null && Operations.reel2Thread != null && Operations.reel3Thread != null) {

                isStopedReelArr[1] = true;

                //checking if all three reel have been stopped
                if (isStopedReelArr[0] == true && isStopedReelArr[1] == true && isStopedReelArr[2] == true) {
                    //this is set to true when all three reel have been clicked
                    isStopedReelArr[3] = true;
                    //enabling the spin button only if all three reels are stoped
                    btnSpin.setDisable(false);
                    System.out.printf("");
                    mediaPlayer.stop();//stopping the spinning sound

                }

                operations.stopReel(Operations.reel2Thread, isStopedReelArr[3]);

            }
        });

        reel3.setOnMouseClicked(e -> {
            //checking if the threads are not null, then  Operations.stopReels() will run
            if (Operations.reel1Thread != null && Operations.reel2Thread != null && Operations.reel3Thread != null) {

                System.out.printf("");
                isStopedReelArr[2] = true;


                //checking if all three reel have been stopped
                if (isStopedReelArr[0] == true && isStopedReelArr[1] == true && isStopedReelArr[2] == true) {
                    //this is set to true when all three reel have been clicked
                    isStopedReelArr[3] = true;

                    //enabling the spin button only if all three reels are stoped
                    btnSpin.setDisable(false);

                    mediaPlayer.stop();//stopping the spinning sound
                }

                operations.stopReel(Operations.reel3Thread, isStopedReelArr[3]);


            }
        });


        //==============================================   TEXT FIELDS AND DISPLAY LABEL  =================================================

        //creating the text fields
        tfBets = new TextField("BET : $ 0");
        tfCredits = new TextField("CREDITS : $ 10");
        //making them un-edditable
        tfBets.setEditable(false);
        tfCredits.setEditable(false);

        //creating the Display message label
        lblDisplayMsg = new Label("WELCOME!");

        //adding the text fields and label to the grid pane
        root.add(tfBets, 1, 4, 1, 1);
        root.add(tfCredits, 3, 4, 1, 1);
        root.add(lblDisplayMsg, 2, 4, 1, 1);

        //setting the position of the text fields and label to center
        GridPane.setHalignment(tfBets, HPos.CENTER);
        GridPane.setHalignment(tfCredits, HPos.CENTER);
        GridPane.setHalignment(lblDisplayMsg, HPos.CENTER);

        //setting the style classes for the text fields and label
        tfBets.getStyleClass().add("tfBets");
        tfCredits.getStyleClass().add("tfCredits");
        lblDisplayMsg.getStyleClass().add("lblDisplayMsg");

        //================================================================================================================


        //creating the scene
        mainScene = new Scene(root, 680, 670);
        //setting the style sheet of the main scene
        mainScene.getStylesheets().add("com/slotmechine/main/Style/style.css");

        return mainScene;


    }

    //this method will return the welcome screen
    public Scene getWelcomeScene() {

        //creating the root layout
        GridPane root = new GridPane();
        //root.gridLinesVisibleProperty().setValue(true);
        //setting the style class of the grid pane
        root.getStyleClass().add("gridPane");
        //setting paddings
        root.setPadding(new Insets(20));
        root.setHgap(24);
        root.setVgap(15);

        //Creating an image object and setting path to image
        Image image = new Image(getClass().getResourceAsStream("Images/logo.png"));
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(110);
        imageview.setFitWidth(520);

        //creating empty label
        Label labelTitle = new Label("");
        //setting image to the label
        labelTitle.setGraphic(imageview);
        GridPane.setHalignment(labelTitle, HPos.CENTER);//setting the alignment to center

        //creating the play button
        Button btnPlay = new Button("PLAY");
        //setting allignments
        GridPane.setHalignment(btnPlay, HPos.CENTER);


        //adding the labelTitle to the root grid pane
        root.add(labelTitle, 2, 14, 5, 1);
        //adding to the grid pane
        root.add(btnPlay, 2, 15, 5, 1);

        //setting the on action block of the play button
        btnPlay.setOnAction(e -> {
            //changing the scene to the main scene
            window.setScene(getMainScene());
        });


        //setting the style class of the grid pane
        root.getStyleClass().add("gridPane");
        labelTitle.getStyleClass().add("lblHeader");
        btnPlay.getStyleClass().add("btn3");


        //creating the scene
        welcomeScene = new Scene(root, 680, 600);
        //setting the style sheet of the main scene
        welcomeScene.getStylesheets().add("com/slotmechine/main/Style/style.css");
        return welcomeScene;

    }


    //this method will return the statistics screen
    public void getStatsWindow() {

        //creating stats stage
        Stage statsStage = new Stage();


        //creating the root layout
        GridPane root = new GridPane();
        //setting paddings
        root.setPadding(new Insets(20));
        root.setHgap(26);
        root.setVgap(15);

        //for alignment while making
        //root.gridLinesVisibleProperty().setValue(true);

        //creating a back button
        Button backBtn = new Button("Back");
        //creating a save button
        Button saveBtn = new Button("Save");

        //creating text field objects
        TextField tfNumWon = new TextField(" Won : " + Operations.getWonCount() + "        Net Credits per game : $ " + Operations.getTotalNetWonAmmountNet());
        TextField tfNumLost = new TextField(" Lost : " + Operations.getLostCount());

        //seting textfield to be uneditable
        tfNumWon.setEditable(false);
        tfNumLost.setEditable(false);

        //Creating an image object and setting path to image
        Image image = new Image(getClass().getResourceAsStream("Images/logo.png"));
        ImageView imageview = new ImageView(image);

        imageview.setFitHeight(50);
        imageview.setFitWidth(220);

        //creating empty label
        Label labelTitle = new Label("");
        //setting image to the label
        labelTitle.setGraphic(imageview);

        //setting the alignment to center
        GridPane.setHalignment(labelTitle, HPos.CENTER);
        GridPane.setHalignment(backBtn, HPos.CENTER);
        GridPane.setHalignment(saveBtn, HPos.CENTER);
        GridPane.setHalignment(tfNumWon, HPos.CENTER);
        GridPane.setHalignment(tfNumLost, HPos.CENTER);


        //calculationg the won and lost percentages
        double lostPercentage = Math.floor(((double) Operations.getLostCount() / (double) (Operations.getWonCount() + Operations.getLostCount())) * 100);
        double wontPercentage = Math.floor(((double) Operations.getWonCount() / (double) (Operations.getWonCount() + Operations.getLostCount())) * 100);

        //setting the pie chart data
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("WINS : " + wontPercentage + "%", Operations.getWonCount()),
                        new PieChart.Data("LOSSES : " + lostPercentage + "%", Operations.getLostCount())
                );

        //creating a new pie chart
        final PieChart chart = new PieChart(pieChartData);

        //setting the title of the chart
        chart.setTitle("STATISTICS");

        //============  ========== Making the gridpane responsive  ========== ==========

        //creating row constraints
        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(10);
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(50);
        RowConstraints r3 = new RowConstraints();
        r3.setPercentHeight(10);
        RowConstraints r4 = new RowConstraints();
        r4.setPercentHeight(10);

        //creating column constraints
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(300);

        //adding the row contraints to the grid pane
        root.getRowConstraints().addAll(r1, r2, r3, r4);
        //adding the row contraints to the grid pane
        root.getColumnConstraints().addAll(c1);

        //========== ========== ========== ========== ========= ==========  ==========


        //adding the child nodes to the root grid pane
        root.add(labelTitle, 0, 0, 1, 1);
        root.add(chart, 0, 1);
        root.add(tfNumWon, 0, 2, 1, 1);
        root.add(tfNumLost, 0, 3, 1, 1);
        root.add(saveBtn, 0, 4);
        root.add(backBtn, 0, 5);


        //setting the style class of the grid pane
        root.getStyleClass().add("gridPane");
        backBtn.getStyleClass().add("btn1");
        saveBtn.getStyleClass().add("btn1");
        tfNumWon.getStyleClass().add("tfWon");
        tfNumLost.getStyleClass().add("tfLost");


        //the on click action of the back button
        backBtn.setOnAction(e -> {
            //it will set the scene to the main scene
            statsStage.close();

        });

        //the on click action of the save button
        saveBtn.setOnAction(e -> {
            Operations.saveStatistics();
        });

        //creating a new scene for the statistic page
        Scene statsScene = new Scene(root, 550, 570);

        statsStage.setScene(statsScene);
        statsStage.setMinHeight(550);
        statsStage.setMinWidth(570);
        statsStage.show();

        statsScene.getStylesheets().add("com/slotmechine/main/Style/style.css");


    }


    //this method will return the statistics screen
    public void getInfoWindow() {

        //creating stats stage
        Stage infoStage = new Stage();


        //creating the root layout
        GridPane root = new GridPane();
        //setting paddings
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
       // root.gridLinesVisibleProperty().setValue(true);



        //creating UI objects
        Image shorttable = new Image(getClass().getResourceAsStream("images/tables.png"));
        ImageView imageViewPayoutTable1 = new ImageView(shorttable);

        Label heading = new Label("Computation Modeling");

        Button btnCalcPayOutToNewWindow = new Button("Calculate Pay out to a new window");
        Button btnCalcPayOutToTextFile  = new Button("Calculate pay out to a text file");

        //adding to grid pane
        root.add(heading,0,0,1,1);
        root.add(imageViewPayoutTable1,0,1,1,1);
        root.add(btnCalcPayOutToNewWindow,0,4,1,1);
        root.add(btnCalcPayOutToTextFile,0,5,1,1);




        //setting the alignment to center
        GridPane.setHalignment(btnCalcPayOutToNewWindow, HPos.CENTER);
        GridPane.setHalignment(btnCalcPayOutToTextFile, HPos.CENTER);
        GridPane.setHalignment(imageViewPayoutTable1, HPos.CENTER);
        GridPane.setHalignment(heading, HPos.CENTER);


        //adding the style
        btnCalcPayOutToNewWindow.getStyleClass().add("btn3");
        btnCalcPayOutToTextFile.getStyleClass().add("btn3");
        heading.getStyleClass().add("headingLbl");
        root.getStyleClass().add("gridPane");


        //setting on click listeners
        btnCalcPayOutToTextFile.setOnAction(e -> {
           Operations.calcPayOutToTextFile();
            System.out.printf("Clicked");
        });

        btnCalcPayOutToNewWindow.setOnAction(e -> {
            Operations.calcPayOutToNewWindow();
        });


        //creating a new scene for the statistic page
        Scene infoScene = new Scene(root, 500, 560);

        infoStage.setScene(infoScene);
        infoStage.setMinHeight(550);
        infoStage.setMinWidth(535);
        infoStage.setResizable(false);

        infoStage.show();

        infoScene.getStylesheets().add("com/slotmechine/main/Style/style.css");


    }


}