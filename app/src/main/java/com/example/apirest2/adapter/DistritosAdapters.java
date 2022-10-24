package com.example.apirest2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.apirest2.R;
import com.example.apirest2.activities.DetallActivity;
import com.example.apirest2.model.Producto;

import java.util.List;

public class DistritosAdapters extends BaseAdapter {
    List<Producto> distritos;
    Context context;
    TextView nameText;
    TextView escuelaText;
    Button viewButton;

    public DistritosAdapters(List<Producto> distritos, Context context) {
        this.distritos = distritos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return distritos.size();
    }

    @Override
    public Object getItem(int i) {
        return distritos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return distritos.get(i).getIdproductos();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      if(view == null){
          view= LayoutInflater.from(context).inflate(R.layout.distritolist,viewGroup,false);
      }
      nameText=view.findViewById(R.id.nameTexts);
      escuelaText=view.findViewById(R.id.escuelaTexts);
      nameText.setText(distritos.get(position).getNombre());
      escuelaText.setText(distritos.get(position).getCategoria());
      viewButton=view.findViewById(R.id.viewButton);
      viewButton.setOnClickListener(new View.OnClickListener() {
          @Override

          public void onClick(View view) {
              callDetail(distritos.get(position).getIdproductos());
          }
      });
        return view;
    }
    private void callDetail(int id){
        Intent intent = new Intent(context,DetallActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



}
