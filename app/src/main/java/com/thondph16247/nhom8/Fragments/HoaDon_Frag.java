package com.thondph16247.nhom8.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thondph16247.nhom8.Adapters.GioHangAdapter;
import com.thondph16247.nhom8.Adapters.HoaDonAdapter;
import com.thondph16247.nhom8.DAO.GioHangDAO;
import com.thondph16247.nhom8.DAO.HoaDonDAO;
import com.thondph16247.nhom8.DTO.GioHangDTO;
import com.thondph16247.nhom8.DTO.HoaDonDTO;
import com.thondph16247.nhom8.R;

import java.util.ArrayList;


public class HoaDon_Frag extends Fragment {

    private RecyclerView rcv_hoaDon;
    private HoaDonDAO hoaDonDAO;
    private HoaDonAdapter hoaDonAdapter;
    private ArrayList<HoaDonDTO> listHoaDon;
    private EditText edt_search_hoaDon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hoa_don_fragment, container, false);

        rcv_hoaDon = view.findViewById(R.id.rcv_hoaDon);
        hoaDonDAO = new HoaDonDAO(getContext());
        edt_search_hoaDon = view.findViewById(R.id.edt_ser_hoaDon);

        listHoaDon = hoaDonDAO.getList();
        hoaDonAdapter = new HoaDonAdapter(getContext(), listHoaDon);
        rcv_hoaDon.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_hoaDon.setAdapter(hoaDonAdapter);
        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
