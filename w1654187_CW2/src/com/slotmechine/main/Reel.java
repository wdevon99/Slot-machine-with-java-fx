package com.slotmechine.main;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by devon on 10/30/17.
 * This class represent one reel of the gui
 * It contain a method call spin() , to return a populated array list containing symbol objects
 */
public class Reel implements Runnable {

    //this instance variable is to store the label refference of the reel in order to update the image set to it
    private Label reelLabel;

    //this instance variables saves the url of the image
    private String urlOfImage;

    //this variable stores the random number genrated in the run method
    private int randomNumber;

    //reel Constructor
    public Reel(Label reelLabel) {
        this.reelLabel = reelLabel;

    }

    //this is the array list that will store the symbol objects
    private static ArrayList<Symbol> symbolsArray = new ArrayList<>();

    //this method returns the list with the six symbols (shuffled)
    public static ArrayList<Symbol> spin() {

        //creating Symbol objects and adding them to the symbolsArray
        symbolsArray.add(new Symbol("redseven", 7));
        symbolsArray.add(new Symbol("bell", 6));
        symbolsArray.add(new Symbol("watermelon", 5));
        symbolsArray.add(new Symbol("plum", 4));
        symbolsArray.add(new Symbol("lemon", 3));
        symbolsArray.add(new Symbol("cherry", 2));

        //returing the  populated symbolsArray arraylist
        return symbolsArray;

    }

    //this method is an abstract method in the Runnable interface and it is overriden in this class
    //this is the method that will run in three independent threads of the three reels
    @Override
    public void run() {


        //this while loop will run continouesly until the thread is stopped
        while (true) {
            //platform.runlater is used so that the gui wont crash due to the new thread
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //symbolsArray is populated using the spin method in the Reel class
                    ArrayList<Symbol> symbolsArray;
                    symbolsArray = Reel.spin();

                    //a random number between 1 and 5 is generated to used as and randon index
                    randomNumber = (int) (Math.random() * 5);
                    //url of the image is obtained and saved in the urlOfImage variable
                    urlOfImage=(symbolsArray.get(randomNumber).getImage());

                    //Creating an image object and setting path to image
                    Image imageReel1 = new Image(getClass().getResourceAsStream(urlOfImage));
                    //creating image view
                    ImageView imageviewReel1 = new ImageView(imageReel1);
                    // setting dimensions of the image views
                    imageviewReel1.setFitHeight(100);
                    imageviewReel1.setFitWidth(100);

                    //setting the image to the gui
                    reelLabel.setGraphic(imageviewReel1);
                }
            });

            //handling the check exception with this try catch block
            try {
                //sleeping the tread for 70 miliseconds on each iteration for the picture to be visible
                Thread.sleep(70);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }
    }

    //getters and setters


    public int getRandomNumber() {
        return randomNumber;
    }

    public Label getReelLabel() {
        return reelLabel;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

}
