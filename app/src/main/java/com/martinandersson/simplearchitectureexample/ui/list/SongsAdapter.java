package com.martinandersson.simplearchitectureexample.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martinandersson.simplearchitectureexample.R;
import com.martinandersson.simplearchitectureexample.data.SongEntity;
import com.martinandersson.simplearchitectureexample.ui.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private Context mContext;
    private List<SongEntity> mSongs;

    SongsAdapter(Context context, List<SongEntity> songs) {
        mContext = context;
        mSongs = songs;
    }

    public void updateData(List<SongEntity> songs) {
        mSongs = songs;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final SongEntity song = mSongs.get(position);
        holder.rowArtist.setText(song.getArtistName());
        holder.rowSong.setText(song.getTrackName());
        Picasso.get().load(song.getArtistUrl100()).into(holder.rowImage);

        holder.rowLayout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(DetailActivity.ARG_SONG, song);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_layout)
        RelativeLayout rowLayout;

        @BindView(R.id.row_image)
        ImageView rowImage;

        @BindView(R.id.row_artist)
        TextView rowArtist;

        @BindView(R.id.row_song)
        TextView rowSong;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
