package com.slotmechine.main;

/**
 * Created by devon on 10/30/17.
 * this class represent one symbol in the gui
 */
public class Symbol implements ISymbol {

    private String imgPath; //this stores the path of the image
    private int value; //this stores therespective value of the symbol

    //Symbol constructor
    public Symbol(String imgPath,int value) {
        this.setImage(imgPath);
        this.setValue(value);
    }


   //this method sets the image associated with one of the symbols in a reel
    @Override
    public void setImage(String imgPath) {
        //setting the imgPath instance variable
        this.imgPath = "Images/"+imgPath + ".png";
    }

    //this method return the image
    @Override
    public String getImage() {
        return this.imgPath;
    }

    //this method which sets the value of the symbol
    @Override
    public void setValue(int v) {
       //setting the value instance variable
       this.value=v;
    }

    //this method returns the value of the symbol.
    @Override
    public int getValue() {
        return this.value;
    }

}
