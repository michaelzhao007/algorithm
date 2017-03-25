package com.algo.string;

import java.awt.Color;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdOut;

public class PictureDump {

    public static void main(String[] args) {
       int width = 40;
       int height = 40;
        Picture pic = new Picture(width, height);
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pic.set(j, i, Color.RED);
                if (!BinaryStdIn.isEmpty()) {
                    count++;
                    boolean bit = BinaryStdIn.readBoolean();
                    if (bit) pic.set(j, i, Color.BLACK);
                    else     pic.set(j, i, Color.WHITE);
                }
            }
        }
        pic.show();
        StdOut.println(count + " bits");
    }
}
