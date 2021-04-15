package com.example.ql_thuvien;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class listSachAdapter extends ArrayAdapter<Sach> {
    listSachAdapter(Context context, int resource, List<Sach> item) {
        super(context, resource, item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view ==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.row_list_sach, null);
        }
        Sach sach =getItem(position);
        if (sach!=null)
        {
            ImageView imgHinhDaidien= (ImageView) view.findViewById(R.id.imgAnhDaiDien);
            TextView txtTenSP= (TextView) view.findViewById(R.id.txtTen);
            TextView txtmota = (TextView)view.findViewById(R.id.txtMota);
            //TextView txxluotdoc = (TextView)view.findViewById(R.id.txtluotmuon);
            Bitmap bitmap= BitmapFactory.decodeByteArray(sach.Hinhanh, 0, sach.Hinhanh.length);
            imgHinhDaidien.setImageBitmap(bitmap);
            txtTenSP.setText(sach.TenSach);
            txtmota.setText(sach.Mota);
           // txxluotdoc.setText(sanPham.LuonMuon);

        }
        return view;
    }
}
