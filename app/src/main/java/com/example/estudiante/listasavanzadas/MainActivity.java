package com.example.estudiante.listasavanzadas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ListView lv_contactos;
    ContactoAdapter customAdapter;
    ImageButton btn_generar;
    EditText et_contacto;
    EditText et_telefono;
    Switch swt_sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_contacto=findViewById(R.id.et_contacto);
        et_telefono=findViewById(R.id.et_telefono);
        btn_generar=findViewById(R.id.btn_generar);
        swt_sexo=findViewById(R.id.swt_sexo);

         lv_contactos = findViewById(R.id.lv_contactos);
         customAdapter = new ContactoAdapter(this);
         lv_contactos.setAdapter(customAdapter);

         Contacto contacto1 = new Contacto("Valentina alegria","3182726766", "Mujer");
         Contacto contacto2 = new Contacto("Ana Isabel","3226422998", "Mujer");
         Contacto contacto3 = new Contacto("Jose Devera","3023428234", "Hombre");

         customAdapter.agregarContacto(contacto1);
         customAdapter.agregarContacto(contacto2);
         customAdapter.agregarContacto(contacto3);

        lv_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 Contacto noticia_click = (Contacto) customAdapter.getItem(position);
                 Toast.makeText(MainActivity.this,noticia_click.getNombre(), Toast.LENGTH_SHORT).show();
             }
         });

        btn_generar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //traernos fecha actual
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                month++;
                int day = c.get(Calendar.DAY_OF_MONTH);

                String nombre = et_contacto.getText().toString();
                String telefono = et_telefono.getText().toString();
                String sexo ;

                if(swt_sexo.isChecked()){
                    sexo = "Mujer";
                } else {
                    sexo = "Hombre";
                }

                Contacto newContacto = new Contacto(nombre,telefono,sexo);
                customAdapter.agregarContacto(newContacto);
            }
        });
    }
}
