package com.tuccro.cars;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;

public class MainActivity extends Activity implements EditFragment.OnFragmentInteractionListener{

    FragmentTransaction fragTrans;

    BrandsFragment brandsFragment;
    ModelsFragment modelsFragment;
    ItemsListFragment listFragment;
    EditFragment editFragment;

    DB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brandsFragment = new BrandsFragment();
        modelsFragment = new ModelsFragment();
        listFragment = new ItemsListFragment();
        editFragment = new EditFragment();

        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.layout_brands, brandsFragment);
        fragTrans.add(R.id.layout_models, modelsFragment);
        fragTrans.add(R.id.layout_list, listFragment);
        fragTrans.add(R.id.layout_edit, editFragment);
        fragTrans.commit();

        dataBase = new DB(this);
        dataBase.open();
        initItemsList(Utils.getEnginesFromDBCursor(dataBase.getAllEngines()));
//        initItemsList(Utils.getModelsFromDBCursor(dataBase.getAllModels()));
    }

    void initItemsList(List list) {

        listFragment.setListAdapter(Utils.getItemsArrayAdapter(this, list));

//        Toast toast = Toast.makeText(this, String.valueOf(listFragment.getSelectedPosition()), Toast.LENGTH_SHORT);
//        toast.show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
