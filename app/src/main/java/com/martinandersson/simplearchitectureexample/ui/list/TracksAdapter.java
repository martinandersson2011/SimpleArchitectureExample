package com.martinandersson.simplearchitectureexample.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.martinandersson.simplearchitectureexample.R;
import com.martinandersson.simplearchitectureexample.data.database.Track;
import com.martinandersson.simplearchitectureexample.ui.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    private Context mContext;
    private List<Track> mTracks;

    TracksAdapter(Context context, List<Track> tracks) {
        mContext = context;
        mTracks = tracks;
    }

    public void updateData(List<Track> tracks) {
        mTracks = tracks;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_track, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Track track = mTracks.get(position);
        holder.rowArtistTextView.setText(track.getArtistName());
        holder.rowTrackNameTextView.setText(track.getTrackName());
        Picasso.get().load(track.getArtistUrl100()).into(holder.rowImageView);

        holder.rowLayout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(DetailActivity.ARG_TRACK_ID, track.getTrackId());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_layout)
        ConstraintLayout rowLayout;

        @BindView(R.id.row_image)
        ImageView rowImageView;

        @BindView(R.id.row_artist)
        TextView rowArtistTextView;

        @BindView(R.id.row_track_name)
        TextView rowTrackNameTextView;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
