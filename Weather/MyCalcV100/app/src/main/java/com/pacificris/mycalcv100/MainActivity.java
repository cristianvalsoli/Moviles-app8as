package com.pacificris.mycalcv100;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner operaciones;
    EditText N1, N2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operaciones=(Spinner) findViewById(R.id.opr);
        N1=(EditText) findViewById(R.id.n1);
        N2=(EditText) findViewById(R.id.n2);
        result=(TextView) findViewById(R.id.resul);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.op, android.R.layout.simple_spinner_item);
        operaciones.setAdapter(adapter);
    }

    public void cal(View vie){

        if(N1.getText().toString().length() != 0  && N2.getText().toString().length() != 0  ){
            float n1=Float.parseFloat(N1.getText().toString());
            float n2=Float.parseFloat(N2.getText().toString());


            switch( operaciones.getSelectedItem().toString()) {
                case "Addition":
                    result.setText((n1+n2)+"");
                    break;
                case "Subtraction":
                    result.setText((n1-n2)+"");
                    break;
                case "Multiplication":
                    result.setText((n1*n2)+"");
                    break;
                case "Division":
                    Toast toastD=Toast.makeText(getApplicationContext()," No es posible dividir entre 0",Toast.LENGTH_SHORT);

                    if( N2.getText().toString().equals("0") ){
                        toastD.show();
                        result.setText( "Error");
                    }else{

                        result.setText((n1/n2)+"");

                        }


                    break;
            }
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(N2.getWindowToken(), 0);
        }else{
            Toast toast=Toast.makeText(getApplicationContext()," Verificar números por favor",Toast.LENGTH_SHORT);



            toast.show();
        }
// tipo1 = spinnerCantidad.getSelectedItem().toString();








    }
}