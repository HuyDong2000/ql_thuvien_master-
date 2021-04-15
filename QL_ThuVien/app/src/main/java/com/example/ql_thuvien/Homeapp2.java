package com.example.ql_thuvien;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Homeapp2 extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    listSachAdapter adapter;
    SQL db;
    NavigationView nav;
    ArrayList<Sach> arraySanPham = new ArrayList<>();
    public static final int EDIT_STUDENT = 114;
    public static final int SAVE_STUDENT = 115;
    public static final int DELETE_STUDENT = 2;
    int posselected = -1;
    String values;
    int ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeapp2);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView = (NavigationView)findViewById(R.id.navigationveiw);
        nav = (NavigationView)findViewById(R.id.navigationveiw);
        Intent intent = getIntent();
        values = intent.getStringExtra("name");
        ID = intent.getIntExtra("id",0);
        db=new SQL(this,"Qlthuvien.db",null,1);
        listView = (ListView)findViewById(R.id.lvSach);
        registerForContextMenu(listView);
        actionToolbar();
        try {
            Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from Sach ");

            while (cursor.moveToNext()) {
                arraySanPham.add(new Sach(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getBlob(5),cursor.getInt(6)));
            }
            adapter = new listSachAdapter(Homeapp2.this, R.layout.row_list_sach, arraySanPham);
            listView.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getApplication(),"looi"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posselected = position;
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posselected = position;
                int i1d = arraySanPham.get(posselected).getId();
                Intent intent = new Intent (Homeapp2.this,HomeApp.class);
                intent.putExtra("vt",i1d);
                intent.putExtra("name",values);
                startActivity(intent);
            }
        });
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ThemSachlefft:
                        Intent intent = new Intent(Homeapp2.this,ThemSach.class);
                        startActivity(intent);
                        return  true;
                    case R.id.sachmuon:
                        Intent intent1 = new Intent(Homeapp2.this,SachMuon.class);
                        intent1.putExtra("name",ID);
                        startActivity(intent1);
                        return  true;
                    case  R.id.ttuser:
                        Intent intent2 = new Intent(Homeapp2.this,TTCaNhan.class);
                        intent2.putExtra("name",values);
                        startActivity(intent2);
                        return  true;
                    case R.id.login:
                        Toast.makeText(getApplication(),"logout",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

    }
    private void actionToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnds,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSua:
                int id = arraySanPham.get(posselected).getId();
                Sach sach = arraySanPham.get(posselected);
                Intent intent = new Intent (Homeapp2.this,EditSach.class);
                intent.putExtra("vt",id);
                startActivity(intent);
                return true;
            case R.id.mnXoa:
               delete();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void delete(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this    );
        alert.setTitle("Xác nhận xóa ");
        alert.setMessage("Bạn xác nhận muốn xóa sách ");
        alert.setCancelable(false);
        alert.setPositiveButton("Đồng ý ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase DP = openOrCreateDatabase(MainActivity.DataBasename,MODE_PRIVATE,null);
                String id = String.valueOf(arraySanPham.get(posselected).getId());
                if(DP.delete("Sach","ID=?", new String[]{id} )!=-1){
                   arraySanPham.remove(posselected);
                   adapter.notifyDataSetChanged();
                    Toast.makeText(getApplication(), "Xóa thành Sách" , Toast.LENGTH_SHORT).show();
                }
            }
        });
                alert.setNegativeButton("Không đồng ý ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
    }


}
