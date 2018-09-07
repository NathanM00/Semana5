package com.example.estudiante.listasavanzadas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter{

    ArrayList<Contacto> contactos;
    Activity activity;


    public ContactoAdapter(Activity activity){
        this.activity = activity;
        contactos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //Generar un renglon por objeto
    //position = posicion del arreglo

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        //el inflater trasnforma le xml a view

        View renglon = inflater.inflate(R.layout.renglon, null, false);
        TextView item_nombre = renglon.findViewById(R.id.item_nombre);
        ImageView item_sexo = renglon.findViewById(R.id.item_sexo);
        final TextView item_telefono = renglon.findViewById(R.id.item_telefono);

        ImageButton item_llamar = renglon.findViewById(R.id.item_llamar);
        ImageButton item_eliminar = renglon.findViewById(R.id.item_eliminar);

        String sexo = contactos.get(position).getSexo();

        if(sexo.toString().equals("Mujer")) {
            item_sexo.setImageResource(R.drawable.femenino);
        }else{
            item_sexo.setImageResource(R.drawable.masculino);
        }

        item_nombre.setText(contactos.get(position).getNombre());
        item_telefono.setText(contactos.get(position).getTelefono());

        final String telefono = contactos.get(position).getTelefono();

        item_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int Request_phone_call = 1;
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel: "+telefono));
                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},Request_phone_call);
                    }else {
                        activity.startActivity(llamar);
                    }
                }
            }
        });

        item_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactos.remove(position);
                notifyDataSetChanged();
            }
        });
        return renglon;
    }

    //ira a otra actividad
    //Intent intent = new Intent(activity, ContactoView.class);
    //activity.startActivity(intent);

    public void agregarContacto(Contacto contacto){
     contactos.add(contacto);
     notifyDataSetChanged();
    }

}
