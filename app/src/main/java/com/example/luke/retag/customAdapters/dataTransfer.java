package com.example.luke.retag.customAdapters;

import android.app.Application;

import com.example.luke.retag.mediaTypes.LibraryHolder;

/**
 * Created by Luke on 2017-04-06.
 */

public class dataTransfer extends Application {

    LibraryHolder library;

    public LibraryHolder getLibrary() {
        return library;
    }

    public void setLibrary(LibraryHolder library) {
        this.library = library;
    }
}
