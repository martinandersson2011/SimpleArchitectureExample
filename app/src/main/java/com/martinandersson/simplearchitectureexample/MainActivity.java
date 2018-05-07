package com.martinandersson.simplearchitectureexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.martinandersson.simplearchitectureexample.api.RestClient;
import com.martinandersson.simplearchitectureexample.model.Song;
import com.martinandersson.simplearchitectureexample.model.SongsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class MainActivity extends AppCompatActivity {
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
    private List<Song> mSongs = new ArrayList<>();
    private SongsResponse mSongsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchText.setText(DEFAULT_SEARCH_TERM);
        mSearchText.setSelection(mSearchText.getText().length());

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SongsAdapter(this, mSongs);
        mRecyclerView.setAdapter(mAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handleSearch();
                    return true;
                }
                return false;
            }
        });

        handleSearch();
    }

    @OnClick(R.id.search_button)
    public void handleSearch() {
        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);

        mProgressBar.setVisibility(View.VISIBLE);
        mNoResults.setVisibility(View.GONE);

        String searchTerm = mSearchText.getText().toString();

        Call<SongsResponse> call = RestClient.getSearchApi().getItunesSearchResults(searchTerm);
        call.enqueue(new Callback<SongsResponse>() {
            @Override
            public void onResponse(Call<SongsResponse> call, Response<SongsResponse> response) {
                mSongsResponse = response.body();
                mSongs = mSongsResponse.getSongs();
                mAdapter.updateData(mSongs);
                mProgressBar.setVisibility(View.GONE);
                mNoResults.setVisibility(mSongs != null && mSongs.size() > 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onFailure(Call<SongsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                mNoResults.setVisibility(View.VISIBLE);
            }
        });

    }

}