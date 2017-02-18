package ru.bu1b0b.jokes.ui.base;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.bu1b0b.jokes.R;
import ru.bu1b0b.jokes.model.JokeModel;

/**
 * Created by bu1b0b on 13.02.2017.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeViewHolder> {

    private CardView card_view;
    private List<JokeModel> jokes;
    private Context context;
    private LayoutInflater inflater;

    public JokesAdapter(Context context, List<JokeModel> jokes) {
        this.context = context;
        this.jokes = jokes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_joke, parent, false);
        card_view = (CardView) rootView.findViewById(R.id.card_view);
        return new JokeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        //TODO
        JokeModel post = jokes.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.joke_tV.setText(Html.fromHtml(post.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.joke_tV.setText(Html.fromHtml(post.getElementPureHtml()));
        }
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder {
        public TextView joke_tV;

        public JokeViewHolder(View itemView) {
            super(itemView);
            joke_tV = (TextView) itemView.findViewById(R.id.joke_tV);
        }
    }

}   //end class
