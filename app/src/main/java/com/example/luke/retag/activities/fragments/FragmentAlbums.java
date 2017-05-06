package com.example.luke.retag.activities.fragments;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.luke.retag.R;
import com.example.luke.retag.customAdapters.AlbumGridAdapter;
import com.example.luke.retag.mediaLibraries.Album;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentAlbums extends Fragment {

    Typeface openLight, moonBold, moonLight;
    ArrayList<Album> library;
    GridView gridView;
    AlbumGridAdapter AlbumGridAdapter;
    PopupWindow popupWindow;
    View popupView;
    FrameLayout bgLayout;

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
        final Context context = getContext();

        DisplayMetrics metrics = new DisplayMetrics();

        float density  = getResources().getDisplayMetrics().density;
        int dpHeight = (int) ((metrics.heightPixels / density) * 0.8);
        int dpWidth  = (int) ((metrics.widthPixels / density) * 0.8);

        this.bgLayout = (FrameLayout) mView.findViewById(R.id.albumFrameLayout);

        LayoutInflater inflater2 = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final ViewGroup container2 = (ViewGroup) inflater2.inflate(R.layout.album_pop, null);

        final AssetManager mngr = context.getAssets();

        this.openLight = Typeface.createFromAsset(mngr, "fonts/openLight.ttf");
        this.moonBold = Typeface.createFromAsset(mngr, "fonts/moonBold.otf");
        this.moonLight = Typeface.createFromAsset(mngr, "fonts/moonLight.otf");



        gridView = (GridView) mView.findViewById(R.id.albumGridView);

        try {
            AlbumGridAdapter = new AlbumGridAdapter(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        gridView.setAdapter(AlbumGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parentView, View albumBlock, int adapterPosition, long rowID) {

                if (library == null) {

                    library = AlbumGridAdapter.albumLibrary;

                }

                LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.album_pop, null);

                LinearLayout popUpBg = (LinearLayout) popupView.findViewById(R.id.popUpBGLinear);
                ImageView albumArt = (ImageView) popupView.findViewById(R.id.albumArtPop);
                TextView genre = (TextView) popupView.findViewById(R.id.albumGenre);
                TextView albumName = (TextView) popupView.findViewById(R.id.albumName);
                TextView albumArtist = (TextView) popupView.findViewById(R.id.albumArtist);

                genre.setTypeface(moonBold);
                albumName.setTypeface(openLight);
                albumArtist.setTypeface(moonLight);

                Album temp = library.get(adapterPosition);

                albumName.setText(temp.getAlbumName());
                albumArtist.setText(temp.getAlbumArtist());
                albumArt.setImageDrawable(temp.getAlbumArtwork());
                popUpBg.setClipToOutline(true);
                albumArt.setClipToOutline(true);

                popupWindow = new PopupWindow(
                        popupView, (1100), (ListPopupWindow.WRAP_CONTENT), true);

                popupWindow.setAnimationStyle(R.style.albumPopAnimation);

                popupWindow.showAtLocation(bgLayout,Gravity.CENTER, 0, 32);

            }

        });

        return mView;
    }

}
