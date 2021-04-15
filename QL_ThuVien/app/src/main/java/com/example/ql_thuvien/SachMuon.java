package com.example.ql_thuvien;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SachMuon extends AppCompatActivity {
    ListView listView;
    ArrayList<Sach> arraySanPham = new ArrayList<>();
    ArrayList<TTSachMuon> ttSachMuons = new ArrayList<>();
    listSachAdapter adapter;
    SQL db;
    int id;
    int posselected = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_sach_muon);
        Intent intent = getIntent();
        id = intent.getIntExtra("name",0);
        db=new SQL(this,"Qlthuvien.db",null,1);
        listView = (ListView)findViewById(R.id.lvdsSachmuon);
        registerForContextMenu(listView);
        try {
            Cursor cursor = MainActivity.db.TruyVanTraVe("Select TenSach,Mota,Hinhanh from Sach , TTSachMuon  Where TTSachMuon.Id_Sach = Sach.ID and TTSachMuon.Id_user ="+id+"");

            while (cursor.moveToNext()) {
                arraySanPham.add(new Sach(cursor.getString(0),cursor.getString(1),cursor.getBlob(2)));
            }
            adapter = new listSachAdapter(SachMuon.this, R.layout.row_list_sach, arraySanPham);
            listView.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getApplication(),"looi"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        try {
            Cursor cursor1 = MainActivity.db.TruyVanTraVe("Select * from TTSachMuon ");

            while (cursor1.moveToNext()) {
               ttSachMuons.add(new TTSachMuon(cursor1.getInt(0),cursor1.getInt(1),cursor1.getInt(2)));
            }
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


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnds_sachmuon,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnTraSach:
                delete();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void delete(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this    );
        alert.setTitle("Xác nhận trả ");
        alert.setMessage("Bạn xác nhận muốn trả sách  ");
        alert.setCancelable(false);
        alert.setPositiveButton("Đồng ý ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase DP = openOrCreateDatabase(MainActivity.DataBasename,MODE_PRIVATE,null);
                String id = String.valueOf(ttSachMuons.get(posselected).getId());
                if(DP.delete("Sach","ID=?", new String[]{id} )!=-1){
                    arraySanPham.remove(posselected);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplication(), "Trả sách hành công " , Toast.LENGTH_SHORT).show();
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
