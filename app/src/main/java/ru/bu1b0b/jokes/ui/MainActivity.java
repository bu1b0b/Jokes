package ru.bu1b0b.jokes.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bu1b0b.jokes.JokeApp;
import ru.bu1b0b.jokes.R;
import ru.bu1b0b.jokes.model.JokeModel;
import ru.bu1b0b.jokes.ui.base.BaseActivity;
import ru.bu1b0b.jokes.ui.base.EndlessRecyclerOnScrollListener;
import ru.bu1b0b.jokes.ui.base.JokesAdapter;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipe_container;
    private RecyclerView recyclerView;

    private JokesAdapter adapter;
    private List<JokeModel> jokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        jokes = new ArrayList<JokeModel>();

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        swipe_container = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipe_container.setOnRefreshListener(this);
        swipe_container.setBackgroundColor(Color.parseColor("#E0E0E0"));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadJokes(25 * current_page);
            }
        });

        adapter = new JokesAdapter(this, jokes);
        recyclerView.setAdapter(adapter);

        loadJokes(25);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        loadJokes(25);
    }

    private void loadJokes(int num) {
        JokeApp.getApi().getJokes(num).enqueue(new Callback<List<JokeModel>>() {
            private boolean isRussian(JokeModel joke) {
                String ss = "";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ss = String.valueOf((Html.fromHtml(joke.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY)));
                } else {
                    ss = String.valueOf((Html.fromHtml(joke.getElementPureHtml())));
                }
                char[] chr = ss.toCharArray();
                for (int i = 0; i < chr.length; i++) {
                    if (chr[i] >= 'А' && chr[i] <= 'я')
                        return true;
                }
                return false;
            }

            @Override
            public void onResponse(Call<List<JokeModel>> call, Response<List<JokeModel>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    JokeModel joke = response.body().get(i);
                    if (!jokes.contains(joke)) {
                        if (isRussian(joke)) {
                            jokes.add(response.body().get(i));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<JokeModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing(false);
                }
            }
        });
    }


}   //end class
