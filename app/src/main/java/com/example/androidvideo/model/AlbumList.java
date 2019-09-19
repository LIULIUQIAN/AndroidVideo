package com.example.androidvideo.model;

import java.util.ArrayList;

public class AlbumList extends ArrayList<Album> {

    public void debug () {
        for (Album a : this) {
            System.out.println(a.toString());
        }
    }


}
