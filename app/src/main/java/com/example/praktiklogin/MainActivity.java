package com.example.praktiklogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.praktiklogin.Model.Modeluser;
import com.example.praktiklogin.User.useradapter;
import com.example.praktiklogin.Database.DatabaseHelper;
import com.example.praktiklogin.Model.Modeluser;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<Modeluser> modeluser;
    useradapter useradapter;
    DatabaseHelper sqliteHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.view_user);
        sharedPreferences = MainActivity.this.getSharedPreferences("username", Context.MODE_PRIVATE);
        modeluser = new ArrayList<>();
        sqliteHelper = new DatabaseHelper(MainActivity.this);
        String userFromIntent = getIntent().getStringExtra("Username");
        Toast.makeText(MainActivity.this, "Selamat datang " + userFromIntent, Toast.LENGTH_LONG).show();

        Log.e("TAG", "onCreate: " + sqliteHelper.getAllUser() );
        modeluser = sqliteHelper.getAllUser();
        useradapter = new useradapter(modeluser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(useradapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, login.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}