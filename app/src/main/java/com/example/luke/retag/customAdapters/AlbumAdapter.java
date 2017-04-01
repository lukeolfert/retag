package com.example.luke.retag.customAdapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.retag.R;

import java.util.ArrayList;

/**
 * Created by Luke on 2017-03-31.
 */

public class AlbumAdapter extends BaseAdapter {

        ArrayList<Menus> list;
        Context context;

        public AlbumAdapter(Context context) {

            this.context = context;
            list = new ArrayList<Menus>();

            Resources res = context.getResources();
            /*
            String[] tempAlbumNames = {"The Money Store", "The Money Store", "The Money Store",

                                        "The Money Store", "The Money Store", "The Money Store",
                                        "The Money Store", "The Money Store", "The Money Store",
                                        "The Money Store", };

            String[] tempArtistNames = {"Death Grips", "Death Grips", "Death Grips", "Death Grips",
                                        "Death Grips", "Death Grips", "Death Grips", "Death Grips",
                                        "Death Grips", "Death Grips", };

            int[] tempCoverPaths = {R.drawable.deathgrips, R.drawable.deathgrips, R.drawable.deathgrips,
                                    R.drawable.deathgrips, R.drawable.deathgrips, R.drawable.deathgrips,
                                    R.drawable.deathgrips, R.drawable.deathgrips, R.drawable.deathgrips,
                                    R.drawable.deathgrips};
            */

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            // TODO Auto-generated method stub
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            // TODO Auto-generated method stub
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.albumblock, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Menus temp = list.get(i);
            holder.myAlbumCover.setImageResource(temp.albumCover);
            holder.myAlbumName.setText(temp.albumNames);
            holder.MyArtistName.setText(temp.artistNames);

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

        class Menus {
            int albumCover;
            String albumNames;
            String artistNames;

            Menus(int albumCover, String albumNames, String artistNames) {
                this.albumCover = albumCover;
                this.albumNames = albumNames;
                this.artistNames = artistNames;
            }
        }

    }


