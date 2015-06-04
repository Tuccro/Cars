package com.tuccro.cars;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;

public class MainActivity extends Activity {

    FragmentTransaction fragTrans;

    BrandsFragment brandsFragment;
    ModelsFragment modelsFragment;
    ItemsListFragment listFragment;

    DB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brandsFragment = new BrandsFragment();
        modelsFragment = new ModelsFragment();
        listFragment = new ItemsListFragment();

        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.layout_brands, brandsFragment);
        fragTrans.add(R.id.layout_models, modelsFragment);
        fragTrans.add(R.id.layout_list, listFragment);
        fragTrans.commit();

        dataBase = new DB(this);
        dataBase.open();
        initItemsList(Utils.getEnginesFromDBCursor(dataBase.getAllEngines()));
    }

    void initItemsList(List list) {

        listFragment.setListAdapter(Utils.getItemsArrayAdapter(this, list));
    }
}
