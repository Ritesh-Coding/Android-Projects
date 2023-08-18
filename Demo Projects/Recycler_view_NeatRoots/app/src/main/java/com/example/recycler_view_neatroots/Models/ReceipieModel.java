package com.example.recycler_view_neatroots.Models;

public class ReceipieModel {
    int pic;   //we define pic as int bcz image is in pixels
    String text;

    public ReceipieModel(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
