package com.tuccro.cars;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.tuccro.cars.core.Brand;
import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;

public class MainActivity extends Activity {

    FragmentTransaction fragTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemsListFragment list = new ItemsListFragment();

        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.layout_list, list);
        fragTrans.commit();



        DB data = new DB(getApplicationContext());
        data.open();

        List<Brand> brandsList = Utils.getBrandsFromDBCursor(data.getAllBrands());
        String brands[] = new String[brandsList.size()];
        for(int k = 0; k<brands.length; k++){
            brands[k] = brandsList.get(k).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, brands);
        list.setListAdapter(adapter);
    }
}
