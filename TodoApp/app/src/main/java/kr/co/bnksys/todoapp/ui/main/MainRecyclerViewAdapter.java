package kr.co.bnksys.todoapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    List<Todo> list;
    MainContract.Presenter presenter;

    MainRecyclerViewAdapter(List<Todo> list, MainContract.Presenter presenter) {
        this.list = list;
        this.presenter = presenter;
    }

    public void replaceData(List<Todo> list) {
        setList(list);
        notifyDataSetChanged();
    }

    private void setList(List<Todo> list) {
        this.list = checkNotNull(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MainRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
        Todo todo = list.get(position);
        holder.title.setText(todo.getId() + " : " + todo.getTodo());
        holder.view.setOnClickListener((v) -> {
            presenter.showDetail(todo.getId());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
