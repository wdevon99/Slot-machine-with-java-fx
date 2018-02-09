package com.slotmechine.main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by devon on 11/1/17.
 * This class contains all the methods that are run when events in the GUI are triggered by the user
 */

public class Operations {

    //this variable is used to store the player credits
    private static int credits = 10;
    //this variable is used to store the player bets
    private static int bet = 0;

    //this boolean variable is used to store true of false bases on the result
    private static boolean isWon = false;
    //these two varibles are used to store number of times won and lost
    private static int wonCount = 0, lostCount = 0;

    //this will store amount the user has lost or won
    private static int wonAmmount = 0, lostAmmount = 0, totalNetWonAmmountNet = 0;

    //the three threads that spin the 3 reels
    protected static Thread reel1Thread, reel2Thread, reel3Thread;

    //these string variable will hold the url of the random images temp
    private static String urlOfReel1Image, urlOfReel2Image, urlOfReel3Image;

    //these varaible will store the referece to the reels object
    private Reel reel1, reel2, reel3;

    //this variable track the number of times bet max is pressed
    protected static int betMaxClickCount=0;

    //this variable will store the total bets
    protected static double totalBetsAmount=0;

    //============================================================================================================================================================================

    //this method is set as the the on click action of the addCoin button in the gui
    public static void addCoin() {
        //incrementing credits by one
        credits++;
        //updating UI
        MainGUI.tfCredits.setText("CREDITS : $ " + credits);
    }

    //============================================================================================================================================================================


    //this method is set as the the on click action of the betOne button in the gui
    public static void betOne() {

        //checks if there is sufficient credit to bet one
        if (!(credits >= 1)) {
            //shows a pop up error
            PopUps.popUp("ERROR ! ", "Insufficent Credit to Bet One", "You must have at least 1 credit to bet one", Alert.AlertType.ERROR);
        } else {
            //increasing the bet by 1
            bet++;
            //updating UI
            MainGUI.tfBets.setText("BET : $ " + bet);

            //decreasing credits by 1
            credits--;
            //updating UI
            MainGUI.tfCredits.setText("CREDITS : $ " + credits);

        }


    }
    //============================================================================================================================================================================


    //this method is set as the the on click action of the betMax button in the gui
    public static void betMax() {

        //checks if there is sufficient credit to bet max
        if (!(credits >= 3)) {
            //shows a pop up error
            PopUps.popUp("ERROR ! ", "Insufficent Credit to Bet Max", "You must have at least 3 credit to bet max", Alert.AlertType.ERROR);
        } else {

            //if bet max button is NOT already pressed
            if (betMaxClickCount == 0) {

                //increasing the bet by 3
                bet += 3;
                //updating UI
                MainGUI.tfBets.setText("BET : $ " + bet);

                //decreasing credits by 3
                credits -= 3;
                //updating UI
                MainGUI.tfCredits.setText("CREDITS : $ " + credits);

                //setting betMaxClickCount to one to show that the bet max button is clicked
                betMaxClickCount = 1;

            } else {
                //if bet max button is already pressed
                PopUps.popUp("Warning", "Can not press bet max again", "Can not press bet max two times in a single game", Alert.AlertType.WARNING);
            }




        }
    }
    //============================================================================================================================================================================


    //this method is set as the the on click action of the statistics button in the gui
    public static void statistics() {
        MainGUI mgui = new MainGUI();
        //setting the main gui scene to the main stage
        mgui.getStatsWindow();
    }

    //============================================================================================================================================================================

    //this method is set as the the on click action of the statistics button in the gui
    public static void saveStatistics() {
        //creating a date object
        Date date = new Date();
        //saving it to a string variable
        String dateString = date.toString();
        //relacing the spaces with and underscore
        dateString = dateString.replace(' ', '_');

        //this variable hold the file name
        String fileName = dateString + ".txt";
        //creating a file write variable and setting it to null
        FileWriter fw = null;

        //this block will get try to execute
        try {
            //creating a new file write object
            fw = new FileWriter(fileName);

            //wrting to the file
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("Saved on : " + date.toString());
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("Number of wins = " + wonCount);
            fw.write("\n");
            fw.write("Number of losses = " + lostCount);
            fw.write("\n");
            fw.write("Average number of credits that he/she has netted per game = " + totalNetWonAmmountNet);
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");

            //closing the filewriter
            fw.close();
            PopUps.popUp("Saved", "Succesfully Saved", "The statistics are save to a file!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            //if try block throws an error, this catch block will ecetute and prin the error
            e.printStackTrace();

        }


    }

    //============================================================================================================================================================================


    //this method is set as the the on click action of the SPIN button in the gui
    public void spinReels() throws InterruptedException {

        //creating a reel object and assiging to reel1
        reel1 = new Reel(MainGUI.reel1);
        //creating a thread using the real objeect,since reel implements the runnable interface, this is possible
        reel1Thread = new Thread(reel1);
        //starting the thread of reel 1
        reel1Thread.start();

        //creating a reel object and assiging to reel2
        reel2 = new Reel(MainGUI.reel2);
        //creating a thread using the real objeect,since reel implements the runnable interface, this is possible
        reel2Thread = new Thread(reel2);
        //starting the thread of reel 2
        reel2Thread.start();

        //creating a reel object and assiging to reel3
        reel3 = new Reel(MainGUI.reel3);
        //creating a thread using the real objeect,since reel implements the runnable interface, this is possible
        reel3Thread = new Thread(reel3);
        //starting the thread of reel 3
        reel3Thread.start();

    }


    //============================================================================================================================================================================


    //this method will stop all the three reels from spinning
    public  void stopReel(Thread reelThread,boolean okayToDoCalculation) {

        //Stopping a reel threads and setting them as null
        reelThread.stop();

        //this method is called here because this is the point where we need to decide if he won
        if (okayToDoCalculation){
            //getting the image urls and saving in the static variables
            urlOfReel1Image=reel1.getUrlOfImage();
            urlOfReel2Image=reel2.getUrlOfImage();
            urlOfReel3Image=reel3.getUrlOfImage();

            //calling the calculation method
            calculateResult();

        }



    }

    //============================================================================================================================================================================


    //this method will find if the player has won or lost
    public  void calculateResult() {

        //checking all three images are the same
        if (urlOfReel1Image.equals(urlOfReel2Image) && urlOfReel1Image.equals(urlOfReel3Image)) {
            //setting is won to true
            isWon = true;
            //won count is increment
            wonCount++;

            //setting the label to you win if user wins with all three
            MainGUI.lblDisplayMsg.setText("YOU WON :)");

            //this method will process and increase or decrease the credits of the user bases on the result
            progressResults();

            //creating an object of operation class
            Operations op = new Operations();
            //adding sound effect when won
            op.getSountEffect("Audio/won.mp3").play();

            //this popup will show if user wins with all three
            PopUps.popUp("Result", "Congratulations! YOU WON !", "You won with ALL THREE matching. AMOUNT : $ " + wonAmmount, Alert.AlertType.INFORMATION);


        }
        //checking two images are the same
        else if (urlOfReel1Image.equals(urlOfReel2Image) || urlOfReel1Image.equals(urlOfReel3Image) || urlOfReel2Image.equals(urlOfReel3Image)) {
            //setting isWon to true
            isWon = true;
            //won count is increment
            wonCount++;

            //System.out.println("All two same"); //for testing purposes
            MainGUI.lblDisplayMsg.setText("YOU WON :)");

            //this method will process and increase or decrease the credits of the user bases on the result
            progressResults();

            //creating an object of operation class
            Operations op = new Operations();
            //adding sound effect when won
            op.getSountEffect("Audio/won.mp3").play();


            //this popup will show if user wins with all three
            PopUps.popUp("Result", "Congratulations! YOU WON !", "You won with ONLY TWO matching. AMOUNT : $ " + wonAmmount, Alert.AlertType.INFORMATION);


        }
        //if all three images are different , this else block will get executed
        else

        {
            //setting isWon to false
            isWon = false;
            //lost count is increment
            lostCount++;
            //System.out.println("All three different"); //for testing purposes
            MainGUI.lblDisplayMsg.setText("YOU LOST :(");

            //getting the suumaation of all the bets
            totalBetsAmount+=bet;
            System.out.println(totalBetsAmount);

            bet = 0;
            //updating UI
            MainGUI.tfBets.setText("BET : $ " + bet);

            Operations op = new Operations();
            //adding sound effect when won
            MediaPlayer mediaPlayer = op.getSountEffect("Audio/lost.mp3");
            mediaPlayer.play();

            //this popup will show if user wins with all three
            PopUps.popUp("Result", "Sorry! YOU LOST !", "You lost.Try again!", Alert.AlertType.INFORMATION);
        }


    }
    //============================================================================================================================================================================


    //this method will process and increase or decrease the credits of the user bases on the result
    public  void progressResults() {
        int imgVal = 0;
        //symbolsArray is populated using the spin method in the Reel class
        ArrayList<Symbol> symbolsArray;
        symbolsArray = Reel.spin();

        //Checking if 2nd and 3rd iamges  are same
        if (reel2.getRandomNumber() == reel3.getRandomNumber()) {
            imgVal = symbolsArray.get(reel2.getRandomNumber()).getValue();
        } else {
            imgVal = symbolsArray.get(reel1.getRandomNumber()).getValue();
        }

        //calculating won amount
        wonAmmount = imgVal * bet;
        totalNetWonAmmountNet += Math.floor((double) (wonAmmount - bet) / (double) (wonCount + lostCount));
        credits += wonAmmount;


        //updating UI
        MainGUI.tfCredits.setText("CREDITS : $ " + credits);

        //getting the suumaation of all the bets
        totalBetsAmount+=bet;
        System.out.println(totalBetsAmount);

        //setting bet to zero
        bet = 0;
        //updating UI
        MainGUI.tfBets.setText("BET : $ " + bet);
    }

    //============================================================================================================================================================================

    //this method will reset the GUI
    public static void resetGui() {

        //adding the currently bet amount back to the credits
        credits += bet;
        //setting bet to zero
        bet = 0;
        //updating UI
        MainGUI.tfCredits.setText("CREDITS : $ " + credits);
        MainGUI.tfBets.setText("BET : $ " + bet);
        MainGUI.lblDisplayMsg.setText("WELCOME!");

        //setting betMaxClickCount to one to show that the bet max button is clicked
        betMaxClickCount = 0;

    }
    //============================================================================================================================================================================


    //this method will return a media player object which can be used to play sounds , when the url of mp3 is given
    public MediaPlayer getSountEffect(String url) {
        //these statemets are to get audio
        final URL resource = getClass().getResource(url);//getting the audio resource from url
        final Media media = new Media(resource.toString()); //creating a media object and passed th resource
        final MediaPlayer mediaPlayer = new MediaPlayer(media); //creating a mediaPlaye Object and passing the media
        return mediaPlayer;

    }

    //============================================================================================================================================================================

    //this method will Calculate the pay percentage out value
    public static double calcPayOutPerecetageValue() {

        double payoutPercentage =0;

        //Probability of getting all three symbols matching (for each symbol)
        double allThreePossiblity= (1.0/216.0 );
        //Probability of getting two symbols matching (for each symbol)
        double twoPossiblity =(1.0/36.0);

        double valuesOfSymbols [] = {7,6,5,4,3,2};

        double totalPay=0;

        for (int i=0 ;  i<valuesOfSymbols.length; i++ ){

            //adding up to make the total pay
            totalPay+= (valuesOfSymbols[i]*allThreePossiblity) + (valuesOfSymbols[i] * twoPossiblity);

        }

        //multiplying by 100 to get the percentage pay out
        payoutPercentage = (totalPay)*100;

        System.out.println(payoutPercentage);

        return payoutPercentage;

    }


    //============================================================================================================================================================================

    //this method will Calculate the total pay  out value
    public static double calcTotalPayOutValue() {

        double totalPayOut =0;
        double totalBets= totalBetsAmount;
        //multiplying the total bets and the percentage divide by 100 to get the total pay out of the slot machine
        totalPayOut= totalBets * (calcPayOutPerecetageValue()/100);

        return totalPayOut;

    }


    //============================================================================================================================================================================

    //this method will Calculate the pay  out value per game
    public static double calcTotalPayOutValuePerGame() {

        double payOutperGame =0;

        double totalNumberOfGames= Operations.getWonCount() + Operations.getLostCount();

        payOutperGame=calcTotalPayOutValue()/totalNumberOfGames;

        return payOutperGame;

    }

    //============================================================================================================================================================================


    //this method will create a new window and show the pay out
    public static void calcPayOutToNewWindow() {

        //creating stats stage
        Stage infoStage = new Stage();


        //creating the root layout
        GridPane root = new GridPane();
        //setting paddings
        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);
        // root.gridLinesVisibleProperty().setValue(true);


        Label percentage = new Label("Pay out percentage : " + Math.round( calcPayOutPerecetageValue() * 100.0) / 100.0 +"%");
        Label total = new Label("Total Pay out  : " +  Math.round(calcTotalPayOutValue() * 100.0) / 100.0);
        Label perGame = new Label("Pay out per game : " + Math.round(calcTotalPayOutValuePerGame() * 100.0) / 100.0 );

        //adding to grid pane
        root.add(percentage,0,0,1,1);
        root.add(total,0,2,1,1);
        root.add(perGame,0,4,1,1);

        //setting the alignment to center
        GridPane.setHalignment(percentage, HPos.LEFT);
        GridPane.setHalignment(total, HPos.LEFT);
        GridPane.setHalignment(perGame, HPos.LEFT);

        //setting style
        percentage.getStyleClass().add("headingLbl");
        total.getStyleClass().add("headingLbl");
        perGame.getStyleClass().add("headingLbl");

        root.getStyleClass().add("gridPane");


        //creating a new scene for the statistic page
        Scene infoScene = new Scene(root, 450, 270);

        infoStage.setScene(infoScene);
        infoStage.setMinHeight(270);
        infoStage.setMinWidth(450);
        infoStage.setResizable(false);
        infoStage.show();

        infoScene.getStylesheets().add("com/slotmechine/main/Style/style.css");


    }

    //============================================================================================================================================================================


    //this method will create a new text file and show the pay out
    public static void calcPayOutToTextFile() {
        //creating a date object
        Date date = new Date();
        //saving it to a string variable
        String dateString = date.toString();
        //relacing the spaces with and underscore
        dateString = dateString.replace(' ', '_');
        //this variable hold the file name
        String fileName = "Payout_"+dateString + ".txt";
        //creating a file write variable and setting it to null
        FileWriter fw = null;

        //this block will get try to execute
        try {
            //creating a new file write object
            fw = new FileWriter(fileName);

            //wrting to the file
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("Pay out percentage value =  " + Math.round( calcPayOutPerecetageValue() * 100.0) / 100.0 +"%" );
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("Total Pay out value =  " + Math.round(calcTotalPayOutValue() * 100.0) / 100.0   );
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("------------------------------------------------------------------");
            fw.write("\n");
            fw.write("Pay out per game  =  " +  Math.round(calcTotalPayOutValuePerGame() * 100.0) / 100.0  +" per game" );
            fw.write("\n");
            fw.write("------------------------------------------------------------------");

            //closing the filewriter
            fw.close();
            PopUps.popUp("Pay out Saved", "Pay out Succesfully Saved in text file", "The statistics are save to a file!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            //if try block throws an error, this catch block will ecetute and prin the error
            e.printStackTrace();

        }

    }

    //============================================================================================================================================================================

    //getters and setters

    public static int getCredits() {
        return credits;
    }

    public static int getBet() {
        return bet;
    }

    public static int getWonCount() {
        return wonCount;
    }

    public static int getLostCount() {
        return lostCount;
    }

    public static int getTotalNetWonAmmountNet() {
        return totalNetWonAmmountNet;
    }


}
