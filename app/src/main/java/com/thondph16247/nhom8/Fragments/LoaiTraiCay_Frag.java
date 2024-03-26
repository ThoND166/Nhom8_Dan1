package com.thondph16247.nhom8.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.thondph16247.nhom8.Adapters.LoaiTraiCayAdapter;
import com.thondph16247.nhom8.DAO.LoaiTraiCayDAO;
import com.thondph16247.nhom8.DTO.LoaiTraiCayDTO;
import com.thondph16247.nhom8.R;


import java.util.ArrayList;


public class LoaiTraiCay_Frag extends Fragment {
    private RecyclerView rcv_loaiTraiCay;
    LoaiTraiCayDAO loaiTraiCayDAO;
    LoaiTraiCayAdapter loaiTraiCayAdapter;
    ArrayList<LoaiTraiCayDTO> listLoai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loai_trai_cay_fragment, container, false);
        rcv_loaiTraiCay = view.findViewById(R.id.rcv_loaiTraiCay);
        loaiTraiCayDAO = new LoaiTraiCayDAO(getContext());
        listLoai = loaiTraiCayDAO.getList();
        loaiTraiCayAdapter = new LoaiTraiCayAdapter(getContext(),listLoai);
        rcv_loaiTraiCay.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_loaiTraiCay.setAdapter(loaiTraiCayAdapter);
        ImageView img_add = view.findViewById(R.id.img_add_loaiTraiCay);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void showAdd() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_loai_trai_cay);

        TextInputEditText edt_loaiTraiCay = dialog.findViewById(R.id.edt_loaiTraiCay);
        Button btn_add = dialog.findViewById(R.id.btn_add_loaiTraiCay);
        Button btn_huy = dialog.findViewById(R.id.btn_huy_loaiTraiCay);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edt_loaiTraiCay.getText().toString();

                if (tenLoai.isEmpty()){
                    Toast.makeText(getContext(),"Vui lòng điền loại trái cây...",Toast.LENGTH_SHORT).show();
                }else {
                    LoaiTraiCayDTO dto = new LoaiTraiCayDTO();
                    dto.setTenLoai(tenLoai);
                    long kq= loaiTraiCayDAO.AddTraiCay(dto);
                    if( kq > 0){
                        listLoai.clear();
                        listLoai.addAll(loaiTraiCayDAO.getList());
                        loaiTraiCayAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else
                        Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}

