package edu.itm.smartcanteenadmin.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.itm.smartcanteenadmin.Models.CanteenServiceModel;
import edu.itm.smartcanteenadmin.R;

public class CanteenAdapter extends RecyclerView.Adapter<CanteenAdapter.MyViewHolder> {

    private List<CanteenServiceModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public CanteenAdapter(List<CanteenServiceModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.canteen_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CanteenServiceModel movie = moviesList.get(position);
        holder.title.setText(movie.getStudentName());
        holder.genre.setText(movie.getRollNumber());
        holder.year.setText(movie.getServiceType());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}