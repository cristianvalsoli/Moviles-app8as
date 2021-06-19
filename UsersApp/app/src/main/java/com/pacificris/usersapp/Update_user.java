package com.pacificris.usersapp;

import android.app.DatePickerDialog;;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Update_user extends AppCompatActivity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);




    //Widgets
    EditText etFecha;
    Intent recibir;

    EditText lname;

    EditText firtname;

    EditText username;
    EditText phone;
    Spinner spinner2;
    EditText password;
    ImageButton ibObtenerFecha;
    Spinner spinnercoun;
Spinner genr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        lname = (EditText) findViewById(R.id.imputlname);
        firtname = (EditText) findViewById(R.id.inputname);
        username = (EditText) findViewById(R.id.inputUsername); //Evento setOnClickListener - clic
        password = (EditText) findViewById(R.id.inputpass);
        phone = (EditText) findViewById(R.id.editTextPhone);

         spinnercoun= (Spinner) findViewById(R.id.coun);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Country, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnercoun.setAdapter(adapter);

        recibir = getIntent();

        spinner2 = (Spinner) findViewById(R.id.genr);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.genr, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        ibObtenerFecha.setOnClickListener(this);
        cargar_datos();
    }
    public void cargar_datos(){
        getIntent().getStringExtra("nombre");
        String idd = recibir.getStringExtra("id");
        lname.setText(recibir.getStringExtra("lasname"));
        firtname.setText(recibir.getStringExtra("firsname"));
        username.setText(recibir.getStringExtra("username"));
        password.setText(recibir.getStringExtra("password"));
        phone.setText(recibir.getStringExtra("phone"));
        Toast.makeText(this,"LLEGO; "+recibir.getStringExtra("gender"), Toast.LENGTH_SHORT).show();
        if (recibir.getStringExtra("gender").contains("M")) {
            spinner2.setSelection(0);

        } else {
            spinner2.setSelection(1);

        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
        }
    }
private void cargardata(){






}

    public void openUserlist(View view) {
        startActivity(new Intent(this, UserList.class));
    }
    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}