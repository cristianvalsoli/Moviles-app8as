package com.pacificris.usersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SingUp extends AppCompatActivity {
    Button BR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        BR=(Button) findViewById(R.id.ButtonRegister);
    }
    public void openSingUp(View view) {
        startActivity(new Intent(this, UserList.class));
    }
 public void validar (){

 }

    public  boolean Vacio(EditText campo){
        String dato = campo.getText().toString().trim();
        if(TextUtils.isEmpty(dato)){
            campo.setError("Campo Requerido");
            campo.requestFocus();
            return true;
        }
        else{
            return false;
        }
    }


}