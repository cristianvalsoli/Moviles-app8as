package com.pacificris.usersapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    List<userobj> usuarios;
List<userobj> usuariosfiltrados;
public Context mCtx;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView email;
        private final TextView lastname;
        private  final LinearLayout linear;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            linear=(LinearLayout) view.findViewById(R.id.formenu);
            name = (TextView) view.findViewById(R.id.name);
            lastname = (TextView) view.findViewById(R.id.lastname);
            email = (TextView) view.findViewById(R.id.email);

        }
public  LinearLayout getLinear(){
            return linear;
}
        public TextView getname() {
            return name;
        }
        public TextView getemail() {
            return email;
        }
        public TextView getLastname(){return lastname;}
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MyAdapter(List dataSet, Context c) {
      this.usuarios= dataSet;
        usuariosfiltrados= new ArrayList<>(dataSet);
        mCtx=c;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getname().setText(usuarios.get(position).Firsname);
        viewHolder.getLastname().setText(usuarios.get(position).Lastname);
        viewHolder.getemail().setText(usuarios.get(position).Username);
        viewHolder.getLinear().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mCtx, viewHolder.getLinear());
                //inflating menu from xml resource
                popup.inflate(R.menu.crud);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.upd:


                                Intent  intent = new Intent(v.getContext(), Update_user.class);
                                intent.putExtra("id",position);
                                intent.putExtra("firsname",usuarios.get(position).Firsname);

                                intent.putExtra("Birth",usuarios.get(position).Birth);
                                intent.putExtra("country",usuarios.get(position).Country);
                                intent.putExtra("gender",usuarios.get(position).gender.trim());
                                intent.putExtra("lasname",usuarios.get(position).Lastname);
                                intent.putExtra("phone",usuarios.get(position).Phone);
                                intent.putExtra("username","hola");
                                intent.putExtra("password",usuarios.get(position).Password);


                                mCtx.startActivity(intent);

                                return true;
                            case R.id.dele:
                            //    Toast.makeText(this,(position+""), Toast.LENGTH_SHORT).show();

                                usuarios.remove(position);
                             notifyItemRemoved(position);
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

                return false;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }

    public Filter getgenderFilter() {
        return  gender_Filter;
    }
    public Filter getNoFilter() {
        return  No_Filter;
    }

//filtro para  el buscador

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint ) {
            ArrayList<userobj> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(usuariosfiltrados);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (userobj item : usuariosfiltrados) {
                    if (item.getFirsname().toLowerCase().contains(filterPattern) ||item.getLastname().toLowerCase().contains(filterPattern)||item.Username.toLowerCase().contains(filterPattern) ) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            usuarios.clear();
            usuarios.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };//filtro para  List gender

    private Filter gender_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint ) {
            ArrayList<userobj> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(usuariosfiltrados);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (userobj item : usuariosfiltrados) {
                    if (item.gender.toLowerCase().contains(filterPattern)  ) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            usuarios.clear();
            usuarios.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
//all
private Filter No_Filter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint ) {
        ArrayList<userobj> filteredList = new ArrayList<>();
        filteredList.addAll(usuariosfiltrados);
        FilterResults results = new FilterResults();
        results.values = filteredList;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        usuarios.clear();
        usuarios.addAll((ArrayList) results.values);
        notifyDataSetChanged();
    }
};

}

