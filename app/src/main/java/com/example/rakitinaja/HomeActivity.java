package com.example.rakitinaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.rakitinaja.adapter.CategoryAdapter;
import com.example.rakitinaja.adapter.BestAdapter;
import com.example.rakitinaja.model.BestModel;
import com.example.rakitinaja.model.CategoryModel;

import java.util.ArrayList;
import java.util.Arrays;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    public static ArrayList<CategoryModel> listModel;
    public static ArrayList<BestModel> listProduct;
    ArrayList<String> listInfo;
    ImageView profil;
    CategoryAdapter CategoryAdapter;
    BestAdapter BestAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Paper.init(this);
        //sementara logout ketika mengklik profil
        profil = findViewById(R.id.imageViewProfil);
        profil.setOnClickListener(v -> {
            Paper.book().destroy();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        //listcategory
        listModel = new ArrayList<>();
        listModel.add(new CategoryModel(R.drawable.category));
        listModel.add(new CategoryModel(R.drawable.category));
        listModel.add(new CategoryModel(R.drawable.category));
        listModel.add(new CategoryModel(R.drawable.category));

        //listproduct
        listProduct = new ArrayList<>();
        listProduct.addAll(DataDescriptionItem.getData());

        //listdescproduct
        listInfo = new ArrayList<>();
        listInfo.addAll(Arrays.asList(DataDescriptionItem.descProduct));

        //recyclerCategory
        recyclerView = findViewById(R.id.mainrecycler);
        CategoryAdapter = new CategoryAdapter(listModel);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setAdapter(CategoryAdapter);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerBestSelling
        recyclerView2 = findViewById(R.id.mainrecycler2);
        BestAdapter = new BestAdapter(listProduct, HomeActivity.this, listInfo);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView2.setAdapter(BestAdapter);
        recyclerView2.setLayoutManager(layoutManager2);

    }
}