package com.example.luke.retag.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.luke.retag.R;
import com.example.luke.retag.customAdapters.AlbumAdapter;

/**
 * Created by Luke on 2017-03-31.
 */

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
        AlbumAdapter = new AlbumAdapter(getActivity());
        gridView.setAdapter(AlbumAdapter);

        return mView;
    }
}
