package kr.co.bnksys.todoapp.ui.movie;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.ui.main.MainContract;
import kr.co.bnksys.todoapp.ui.main.MainRecyclerViewAdapter;

import static com.google.common.base.Preconditions.checkNotNull;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    List<Movie> list;
    MovieContract.Presenter presenter;

    public MovieRecyclerViewAdapter(List<Movie> list, MovieContract.Presenter presenter) {
        this.list = list;
        this.presenter = presenter;
    }

    public void replaceData(List<Movie> list) {
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<Movie> list) {
        this.list = checkNotNull(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie data = list.get(position);
        /*
        holder.thumbnail.setImageURI(Uri.parse(data.getSmallCoverImage()));
        holder.view.setOnClickListener((v) -> {
            // presenter.showDetail(data.getId());
        });
        */

        String thmubnailPath = data.getSmallCoverImage();
        Log.d("adapter", thmubnailPath);
        Glide.with(holder.itemView.getContext())
                .load(thmubnailPath)
                .into(holder.thumbnail);
        holder.title.setText(data.getTitle());
        holder.summary.setText(data.getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView thumbnail;
        TextView title;
        TextView summary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            summary = itemView.findViewById(R.id.summary);
        }
    }
}
