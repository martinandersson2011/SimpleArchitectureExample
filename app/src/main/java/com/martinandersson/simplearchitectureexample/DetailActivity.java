package com.martinandersson.simplearchitectureexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.martinandersson.simplearchitectureexample.model.Song;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String ARG_SONG = "ARG_SONG";

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.artist)
    TextView mArtist;

    @BindView(R.id.song)
    TextView mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            Song song = (Song) intent.getSerializableExtra(ARG_SONG);

            if (song != null) {
                mArtist.setText(song.getArtistName());
                mSong.setText(song.getTrackName());
                Picasso.with(this).load(song.getArtistUrl100()).into(mImage);
            }
        }
    }

}
