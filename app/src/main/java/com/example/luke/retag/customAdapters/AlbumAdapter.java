package com.example.luke.retag.customAdapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.retag.R;
import com.example.luke.retag.mediaLibraries.Album;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Luke on 2017-03-31.
 */

public class AlbumAdapter extends BaseAdapter {

        public ArrayList<Album> albumLibrary = new ArrayList<>();
        Context context;
        Album temp;
        int iteration;

        public AlbumAdapter(Context context) throws IOException, ClassNotFoundException {

            this.context = context;

            Resources res = context.getResources();

            FileInputStream fis = context.openFileInput("AlbumLibrary");
            ObjectInputStream is = new ObjectInputStream(fis);
            this.albumLibrary = (ArrayList<Album>) is.readObject();
            is.close();
            fis.close();

            new Thread(new Runnable() {
                public void run(){

                    for(int i = 0; i < albumLibrary.size(); i++) {

                        Album temp = albumLibrary.get(i);
                        temp.setAlbumArt();

                    }

                }
            }).start();



        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return albumLibrary.size();

        }

        @Override
        public Object getItem(int i) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int i) {
            // TODO Auto-generated method stub
            return i;
        }



        @Override
        public View getView(final int i, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            this.iteration = i;

            if (row == null) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.albumblock, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
                holder.myAlbumCover.setClipToOutline(true);

            } else {
                holder = (ViewHolder) row.getTag();

            }

            // Establishes Temp Menu from List Object (i)
            this.temp = albumLibrary.get(i);

            // Sets View Resources from List Object (i) Instance Vars
            holder.myAlbumCover.setImageDrawable(temp.getAlbumArtwork());
            holder.myAlbumName.setText(temp.getAlbumName());
            holder.MyArtistName.setText(temp.getAlbumArtist());

            return row;
        }

        class ViewHolder {

            ImageView myAlbumCover;
            TextView myAlbumName;
            TextView MyArtistName;

            ViewHolder(View v) {
                myAlbumCover = (ImageView) v.findViewById(R.id.imageView1);
                myAlbumName = (TextView) v.findViewById(R.id.textView1);
                MyArtistName = (TextView) v.findViewById(R.id.textView2);
            }
        }

}


