package ma.emsi.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ma.emsi.pizzaapp.adapter.PizzaAdapter;
import ma.emsi.pizzaapp.beans.Pizza;
import ma.emsi.pizzaapp.service.PizzaService;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private PizzaService ps;
    private ListView liste;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnadd);

        ps = PizzaService.getInstance();

        ps.create(new Pizza("Italian", 120.5, R.mipmap.pizza1,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("Francais", 200, R.mipmap.pizza2,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("American", 121.45, R.mipmap.pizza3,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("Greque", 222, R.mipmap.pizza4,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("?", 0121, R.mipmap.pizza2,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("@", 157, R.mipmap.pizza3,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("d", 1002, R.mipmap.pizza4,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("d", 0000, R.mipmap.pizza2,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("2", 174, R.mipmap.pizza3,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("f", 120, R.mipmap.pizza4,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("dfd", 1002, R.mipmap.pizza2,"Pizza, fresh from the oven!"));
        ps.create(new Pizza("xy", 1012, R.mipmap.pizza3,"Pizza, fresh from the oven!"));

        liste = findViewById(R.id.liste);
        liste.setAdapter(new PizzaAdapter(this, ps.findAll()));

        liste.setOnItemClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

    }
    void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.custom_dialog);

        //Initializing the views of the dialog.
        final EditText nameEt = dialog.findViewById(R.id.name);
        final EditText prixEt = dialog.findViewById(R.id.price);
        final EditText descEt = dialog.findViewById(R.id.descr);

        Button submitButton = dialog.findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                Double prix = Double.parseDouble(prixEt.getText().toString());
                String desc = descEt.getText().toString();
                ps.create(new Pizza(name,prix,R.mipmap.pizza1,desc));
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView idd = view.findViewById(R.id.id);

       // Toast.makeText(this, idd.getText().toString(), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choisir une option");
        alertDialogBuilder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                ps.delete(ps.findById(Integer.parseInt(idd.getText().toString())));
                liste.setAdapter(new PizzaAdapter(MainActivity.this, ps.findAll()));

            }
        });
        alertDialogBuilder.setNegativeButton("Details",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,ps.findById(Integer.parseInt(idd.getText().toString())).getDesc().toString(),Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                myIntent.putExtra("Desc", ps.findById(Integer.parseInt(idd.getText().toString())).getDesc()); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}