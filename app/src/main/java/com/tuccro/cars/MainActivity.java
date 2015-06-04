package com.tuccro.cars;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.tuccro.cars.core.Brand;
import com.tuccro.cars.core.Item;
import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;

public class MainActivity extends Activity {

    FragmentTransaction fragTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BrandsFragment brandsFragment = new BrandsFragment();
        ModelsFragment modelsFragment = new ModelsFragment();

        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.layout_brands, brandsFragment);
        fragTrans.add(R.id.layout_models, modelsFragment);
        fragTrans.commit();

    }

    void initItemsList(List<Item> list){
        String strings[] = new String[list.size()];

        for (Item o:list){

        }
    }
}
