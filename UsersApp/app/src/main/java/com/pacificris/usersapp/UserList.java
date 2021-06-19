package com.pacificris.usersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class UserList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
private List<userobj> usuarios = new ArrayList<>();
    String Firsname;
    String Lastname;
    String Username;
    String Password;
    String Birth;
    String Country;
    String Phone;
    String gender;
    public void upUsers(){
        usuarios.add(new userobj("Vanesa","Valdez","vanesa@gmail.com","super","12/08/25","colombia","3113662698","F"));
        usuarios.add(new userobj("Ivan","Valdez","vanesa@gmail.com","super","12/08/25","colombia","3113662698","M"));
        usuarios.add(new userobj("Cristian","Valdez","vanesa@gmail.com","super","12/08/25","colombia","3113662698","M"));
        usuarios.add(new userobj("Magaly","Valdez","vanesa@gmail.com","super","12/08/25","colombia","3113662698","F"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem= menu.findItem(R.id.search);
        SearchView sear=(SearchView) searchItem.getActionView();
        sear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ///
                Log.d("dbug","llego");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                  mAdapter.getFilter().filter(newText);
             //   usuarios.add(new userobj("Cristian","Valdez","vanesa@gmail.com","super","12/08/25","colombia","3113662698","M"));

              //  mRecyclerView.setAdapter(mAdapter);

                return false;
            }
        });
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mRecyclerView = findViewById(R.id.LuserView);
        mRecyclerView.setHasFixedSize(true);
        // Nuestro RecyclerView usará un linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Asociamos un adapter (ver más adelante cómo definirlo)
        upUsers();

        mAdapter = new MyAdapter(usuarios,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Lfemale:
                Toast.makeText(this,"mi texto f", Toast.LENGTH_SHORT).show();
                mAdapter.getgenderFilter().filter("f");
                return true;
            case R.id.Lmale:
                Toast.makeText(this,"mi texto m", Toast.LENGTH_SHORT).show();
                mAdapter.getgenderFilter().filter("m");
                return true;
            case R.id.Lall:
                Toast.makeText(this,"mi texto", Toast.LENGTH_SHORT).show();
                mAdapter.getNoFilter().filter("Cristian Valdez");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }  //
}