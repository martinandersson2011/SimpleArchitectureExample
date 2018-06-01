package com.martinandersson.simplearchitectureexample.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.martinandersson.simplearchitectureexample.R;
import com.martinandersson.simplearchitectureexample.data.Song;
import com.martinandersson.simplearchitectureexample.utilities.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String DEFAULT_SEARCH_TERM = "rock";

    @BindView(R.id.search_text)
    EditText mSearchText;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.no_results)
    TextView mNoResults;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private SongsAdapter mAdapter;

    private MainActivityViewModel mSongsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Hide keyboard by default and setup keyboard to show a search button
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mSearchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                handleSearch();
                return true;
            }
            return false;
        });

        // Setup RecyclerView
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SongsAdapter(this, new ArrayList<Song>());
        mRecyclerView.setAdapter(mAdapter);

        // Setup ViewModel and observe changes from LiveData
        MainActivityViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory();
        mSongsViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        mSongsViewModel.getSongsLiveData().observe(this, songs -> updateUIWithSongs(songs));

        final List<Song> songs = mSongsViewModel.getSongsLiveData().getValue();
        if (songs == null) {
            // Start with a default search
            mSearchText.setText(DEFAULT_SEARCH_TERM);
            mSearchText.setSelection(mSearchText.getText().length());
            handleSearch();
        }

    }

    private void updateUIWithSongs(List<Song> songs) {
        Log.w(TAG, "updateUIWithSongs");
        mAdapter.updateData(songs);
        mProgressBar.setVisibility(View.GONE);
        mNoResults.setVisibility(songs != null && songs.size() > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.search_button)
    public void handleSearch() {
        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);

        mProgressBar.setVisibility(View.VISIBLE);
        mNoResults.setVisibility(View.GONE);

        // Ask our songs view model to search for songs
        mSongsViewModel.searchForSongs(mSearchText.getText().toString());
    }

}