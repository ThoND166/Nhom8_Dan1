package com.thondph16247.nhom8.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thondph16247.nhom8.Adapters.GioHangAdapter;
import com.thondph16247.nhom8.Adapters.LoaiTraiCayAdapter;
import com.thondph16247.nhom8.DAO.GioHangDAO;
import com.thondph16247.nhom8.DAO.LoaiTraiCayDAO;
import com.thondph16247.nhom8.DTO.GioHangDTO;
import com.thondph16247.nhom8.DTO.LoaiTraiCayDTO;
import com.thondph16247.nhom8.R;

import java.util.ArrayList;
import java.util.Locale;


public class GioHang_Frag extends Fragment {

    private RecyclerView rcv_gioHang;
    private GioHangDAO gioHangDAO;
    private GioHangAdapter gioHangAdapter;
    private ArrayList<GioHangDTO> listGioHang;
    private EditText edt_search_gioHang;
    private TextView tv_tongTien; // Thêm biến tham chiếu đến TextView tổng tiền

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gio_hang_fragment, container, false);

        rcv_gioHang = view.findViewById(R.id.rcv_gioHang);
        gioHangDAO = new GioHangDAO(getContext());
        edt_search_gioHang = view.findViewById(R.id.edt_ser_gioHang);
        tv_tongTien = view.findViewById(R.id.tv_tongTien); // Khởi tạo tham chiếu đến TextView tổng tiền

        listGioHang = gioHangDAO.getList();
        gioHangAdapter = new GioHangAdapter(getContext(), listGioHang);
        rcv_gioHang.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_gioHang.setAdapter(gioHangAdapter);

        updateTotalPrice(); // Cập nhật tổng tiền khi Fragment được khởi tạo

        edt_search_gioHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString().toLowerCase().trim();
                ArrayList<GioHangDTO> ds = new ArrayList<>();

                if (searchText.isEmpty()) {
                    gioHangAdapter.updateData(gioHangDAO.getList());
                    updateTotalPrice(); // Cập nhật tổng tiền khi danh sách sản phẩm thay đổi
                    return;
                }

                for (GioHangDTO gioHangDTO : gioHangDAO.getList()) {
                    if (gioHangDTO.getTenSP().toLowerCase().contains(searchText)) {
                        ds.add(gioHangDTO);
                    }
                }

                gioHangAdapter.updateData(ds);
                updateTotalPrice(); // Cập nhật tổng tiền khi danh sách sản phẩm thay đổi
            }
        });

        return view;
    }

    // Phương thức để tính và cập nhật tổng tiền
    private void updateTotalPrice() {
        double totalPrice = 0;

        for (GioHangDTO gioHangDTO : listGioHang) {
            try {
                double price = Double.parseDouble(gioHangDTO.getGiaTienMoi());
                totalPrice += price;
            } catch (NumberFormatException e) {
                Log.e("GioHang_Frag", "Lỗi khi chuyển đổi giá tiền mới sang kiểu số", e);
            }
        }

        tv_tongTien.setText(String.format(Locale.getDefault(), "%.0f VND", totalPrice));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

