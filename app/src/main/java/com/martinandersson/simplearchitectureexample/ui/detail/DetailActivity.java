package com.martinandersson.simplearchitectureexample.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.martinandersson.simplearchitectureexample.R;
import com.martinandersson.simplearchitectureexample.data.database.Track;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String ARG_TRACK = "ARG_TRACK";

    @BindView(R.id.track_imageview)
    ImageView mTrackImageView;

    @BindView(R.id.artist_textview)
    TextView mArtistTextView;

    @BindView(R.id.track_name_textview)
    TextView mTrackNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            Track track = (Track) intent.getSerializableExtra(ARG_TRACK);

            if (track != null) {
                mArtistTextView.setText(track.getArtistName());
                mTrackNameTextView.setText(track.getTrackName());
                Picasso.get().load(track.getArtistUrl100()).into(mTrackImageView);
            }
        }
    }

}
