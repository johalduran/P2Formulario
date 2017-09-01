package com.johnduran.p2formulario;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String usuario, password, password2, email, sexo, ciudad,
            pasatiempo, camposvacios="Debe llenar los siguientes campos:",
            vusuario,vpassword,vpassword2,vemail,vhobbies, hobbievacio="no",botonfechapress="no";

    private EditText eUsuario, ePassword, ePassword2, eEmail;
    private RadioButton rMasculino, rFemenino;
    private Button bAceptar,bFecha;
    private TextView tInfo, tFecha, tUsuario, tPassword, tPassword2, tEmail,tInfoError,tHobbies;
    private CheckBox cCine, cDormir, cComer, cBailar, cDeporte, cViajar;
    private Spinner sCiudades;
    private int dia, mes, anho,d,m,a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tFecha = (TextView) findViewById(R.id.tFecha);
        bFecha = (Button) findViewById(R.id.bFecha);
        eUsuario = (EditText) findViewById(R.id.eUsuario);
        ePassword = (EditText) findViewById(R.id.ePassword);
        ePassword2 = (EditText) findViewById(R.id.ePassword2);
        eEmail = (EditText) findViewById(R.id.eEmail);
        bAceptar = (Button) findViewById(R.id.bAceptar);
        tInfo = (TextView) findViewById(R.id.tInfo);
        tInfoError = (TextView) findViewById(R.id.tInfoError);
        tUsuario = (TextView) findViewById(R.id.tUsuario);
        tPassword = (TextView) findViewById(R.id.tPassword);
        tPassword2 = (TextView) findViewById(R.id.tPassword2);
        tEmail = (TextView) findViewById(R.id.tEmail);
        tHobbies = (TextView) findViewById(R.id.tHobbies);
        rMasculino= (RadioButton) findViewById(R.id.rMasculino);
        rFemenino=(RadioButton) findViewById(R.id.rFemenino);
        cCine= (CheckBox) findViewById(R.id.cCine);
        cComer= (CheckBox) findViewById(R.id.cComer);
        cDormir= (CheckBox) findViewById(R.id.cDormir);
        cBailar= (CheckBox) findViewById(R.id.cBailar);
        cDeporte= (CheckBox) findViewById(R.id.cDeporte);
        cViajar= (CheckBox) findViewById(R.id.cViajar);
        sCiudades= (Spinner) findViewById(R.id.sCiudades);
        //Codigo para spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudades.setAdapter(adapter);
        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Fin de codigo para spinner
    }

    //Inicio codigo fecha
    public void fFecha(View view) {
        botonfechapress="si";
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anho=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                d=dayOfMonth;
                m=monthOfYear+1;
                a=year;
            }
        }
                ,dia,mes,anho);
        datePickerDialog.show();
    }
    //fin codigo fecah
    public void fAceptar(View view){
        usuario= eUsuario.getText().toString();
        password= ePassword.getText().toString();
        password2= ePassword2.getText().toString();
        email= eEmail.getText().toString();
        vusuario= tUsuario.getText().toString();
        vpassword= tPassword.getText().toString();
        vpassword2= tPassword2.getText().toString();
        vemail= tEmail.getText().toString();
        vhobbies= tHobbies.getText().toString();

        if (rMasculino.isChecked()) {
            sexo= "Masculino";
        }else{
            if (rFemenino.isChecked()){
                sexo = "Femenino";
            }else{
                sexo="Otro";
            }

        }
        if (cCine.isChecked() || cComer.isChecked() || cDormir.isChecked() || cBailar.isChecked() || cDeporte.isChecked() ||
                cViajar.isChecked()){

            if (cCine.isChecked()){ pasatiempo= "Cine "+pasatiempo;}
            if (cComer.isChecked()){ pasatiempo= "Comer "+pasatiempo;}
            if (cDormir.isChecked()){ pasatiempo= "Dormir "+pasatiempo;}
            if (cBailar.isChecked()){ pasatiempo= "Bailar "+pasatiempo;}
            if (cDeporte.isChecked()){ pasatiempo= "Deporte "+pasatiempo;}
            if (cViajar.isChecked()){ pasatiempo= "Viajar "+pasatiempo;}
            hobbievacio="no";
        }else{
            camposvacios= camposvacios+" '"+vhobbies+"'";
            hobbievacio="si";

        }

        if (usuario.isEmpty() || password.isEmpty() || password2.isEmpty() || email.isEmpty() || hobbievacio=="si" || botonfechapress=="no"){
            tInfo.setText("");
            if (email.isEmpty()){ camposvacios= camposvacios+" '"+vemail+"'";}
            if (usuario.isEmpty()){ camposvacios= camposvacios+" '"+vusuario+"'";}
            if (password.isEmpty()){ camposvacios= camposvacios+" '"+vpassword+"'";}
            if (password2.isEmpty()){ camposvacios= camposvacios+" '"+vpassword2+"'";}
            if (botonfechapress=="no"){ camposvacios= camposvacios+" '"+"Fecha"+"'";}
            tInfoError.setText(camposvacios);
            camposvacios="Debe llenar los siguientes campos:";

        }else{
            if(password.equals(password2)){
                tInfoError.setText(" ");
                tInfo.setText("\nUsuario: "+usuario+"\nEmail: "+email+"\nSexo: "+sexo+"\nPasatiempo: "+pasatiempo+"\nCiudad: "+ciudad+"\nFecha: "+d+"/"+m+"/"+a+"\n");
                usuario=""; email ="";sexo =""; pasatiempo="";
            }else{
                tInfoError.setText("Los passwords no coinciden: \n");
                tInfo.setText("");
            }

        }

    }

}

