package com.example.luke.retag.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.luke.retag.R;
import com.example.luke.retag.customAdapters.AlbumAdapter;
import com.example.luke.retag.mediaLibraries.Album;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentAlbums extends Fragment {

    ArrayList<Album> library;
    GridView gridView;
    AlbumAdapter AlbumAdapter;

    public static FragmentAlbums getInstance(int position) {
        FragmentAlbums myFragment = new FragmentAlbums();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_albums, container, false);

        gridView = (GridView) mView.findViewById(R.id.albumGridView);
        try {
            AlbumAdapter = new AlbumAdapter(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        gridView.setAdapter(AlbumAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parentView, View albumBlock, int adapterPosition, long rowID) {

                if (library == null) {

                    library = AlbumAdapter.albumLibrary;
                }

                Album temp = library.get(adapterPosition);
                Toast.makeText(getContext(), "OnClick for " + temp.getAlbumName(), Toast.LENGTH_SHORT).show();


            }

        });

        return mView;
    }
}
