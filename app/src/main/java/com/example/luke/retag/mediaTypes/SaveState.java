package com.example.luke.retag.mediaTypes;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 2017-04-15.
 */

public class SaveState implements Serializable {

    static public List<LibraryHolder> library = new ArrayList<LibraryHolder>();
    static SaveState instance = null;

    public static SaveState getInstance()   {
        if( instance == null )
            instance = new SaveState();
        return instance;
    }

    public static void saveData(SaveState instance) {
        ObjectOutput out;
        try {
            File outFile = new File(Environment.getExternalStorageDirectory(), "appSaveState.data");
            out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static SaveState loadData()  {
        ObjectInput in;
        SaveState ss=null;
        try {
            in = new ObjectInputStream(new FileInputStream("appSaveState.data"));
            ss=(SaveState) in.readObject();
            in.close();
        } catch (Exception e) {e.printStackTrace();}
        return ss;
    }
}