package com.martinandersson.simplearchitectureexample.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.martinandersson.simplearchitectureexample.R;
import com.martinandersson.simplearchitectureexample.data.database.Track;
import com.martinandersson.simplearchitectureexample.utilities.InjectorUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String ARG_TRACK_ID = "ARG_TRACK_ID";

    @BindView(R.id.track_imageview)
    ImageView mTrackImageView;

    @BindView(R.id.artist_textview)
    TextView mArtistTextView;

    @BindView(R.id.track_name_textview)
    TextView mTrackNameTextView;

    private DetailActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            DetailActivityViewModelFactory factory = InjectorUtils.provideDetailActivityViewModelFactory(this.getApplicationContext());
            mViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel.class);
            int trackId = intent.getIntExtra(ARG_TRACK_ID, -1);
            mViewModel.getTrackWithId(trackId).observe(this, track -> updateUIWithTrack(track));
        } else {
            Toast.makeText(this, "No track found", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void updateUIWithTrack(Track track) {
        if (track != null) {
            mArtistTextView.setText(track.getArtistName());
            mTrackNameTextView.setText(track.getTrackName());
            Picasso.get().load(track.getArtistUrl100()).into(mTrackImageView);
        }

    }

}
