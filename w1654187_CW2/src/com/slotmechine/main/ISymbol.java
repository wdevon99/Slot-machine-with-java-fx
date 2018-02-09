package com.slotmechine.main;

/**
 * Created by devon on 10/30/17.
 * This interface contains the abstract methods that will be needed to set and get images and values to the reels
 * This interface will be implemented by the Symbol class
 *
 */
public interface ISymbol {

    //this method sets the image associated with one of the symbols in a reel
    void setImage(String s);

    //this method return the image
    String getImage();

    //this method which sets the value of the symbol
    void setValue(int v);

    //this method returns the value of the symbol.
    int getValue();

}
