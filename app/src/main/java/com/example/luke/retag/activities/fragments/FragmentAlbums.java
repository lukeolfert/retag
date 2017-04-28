package com.example.luke.retag.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.luke.retag.R;
import com.example.luke.retag.customAdapters.AlbumAdapter;

import java.io.IOException;

public class FragmentAlbums extends Fragment {

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

        return mView;
    }
}
