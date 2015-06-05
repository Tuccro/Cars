package com.tuccro.cars;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.tuccro.cars.core.Item;
import com.tuccro.cars.database.DB;
import com.tuccro.cars.fragments.BrandsFragment;
import com.tuccro.cars.fragments.EditFragment;
import com.tuccro.cars.fragments.ItemsListFragment;
import com.tuccro.cars.fragments.ModelsFragment;
import com.tuccro.cars.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements EditFragment.OnEditListener,
        BrandsFragment.OnButtonClickListener, ModelsFragment.OnButtonClickListener {

    private static final String BRANDS_MODE = "brands_mode";
    private static final String MODELS_MODE = "models_mode";
    private static final String ENGINES_MODE = "engines_mode";

    private String mode = ENGINES_MODE;

    FragmentTransaction fragTrans;

    BrandsFragment brandsFragment;
    ModelsFragment modelsFragment;
    ItemsListFragment listFragment;
    EditFragment editFragment;

    DB dataBase;

    ArrayList<Item> currentItemsList;

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

        setListMode(ENGINES_MODE);
    }

    @Override
    public void onBackPressed() {
        setListMode(ENGINES_MODE);
//        new AlertDialog.Builder(this)
//                .setTitle("Выйти из приложения?")
//                .setMessage("Вы действительно хотите выйти?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        //SomeActivity - имя класса Activity для которой переопределяем onBackPressed();
//
//                    }
//                }).create().show();
//        super.onBackPressed();
    }

    void setListMode(String mode) {
        dataBase = new DB(this);
        dataBase.open();
        switch (mode) {
            case BRANDS_MODE:
                currentItemsList = Utils.getBrandsFromDBCursor(dataBase.getAllBrands());
                break;
            case MODELS_MODE:
                currentItemsList = Utils.getModelsFromDBCursor(dataBase.getAllModels());
                break;
            case ENGINES_MODE:
                currentItemsList = Utils.getEnginesFromDBCursor(dataBase.getAllEngines());
                break;
        }
        initItemsList(currentItemsList);
        dataBase.close();
    }

    void initItemsList(List list) {

        listFragment.setListAdapter(Utils.getItemsArrayAdapter(this, list));
    }


    @Override
    public void onEdit(View v, String name) {
//        Toast toast = Toast.makeText(this, String.valueOf(v.getId())+" "+name, Toast.LENGTH_SHORT);
//        toast.show();
        switch (v.getId()) {
            case R.id.button_add:
                if (!name.isEmpty()) addItem(name);
                break;
            case R.id.button_delete:
                int id = listFragment.getSelectedPosition();
                if (id != -1) deleteItem(id);
                break;
        }

    }

    private void deleteItem(int id) {
        DB db = new DB(getApplicationContext());
        db.open();
        switch (mode) {
            case BRANDS_MODE:
                db.deleteBrand(currentItemsList.get(id).getId());
                break;
            case MODELS_MODE:
                db.deleteModel(currentItemsList.get(id).getId());
                break;
            case ENGINES_MODE:
                db.deleteEngine(currentItemsList.get(id).getId());
                break;
        }
        setListMode(mode);
        db.close();
    }


    private void addItem(String name) {
        DB db = new DB(getApplicationContext());
        db.open();
        switch (mode) {
            case BRANDS_MODE:
                break;
            case MODELS_MODE:
                break;
            case ENGINES_MODE:
                db.addEngine(modelsFragment.getSelectedItemId(), name);
                break;
        }
        setListMode(mode);
        db.close();
    }

    @Override
    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.button_brands:
                setListMode(BRANDS_MODE);
                break;
            case R.id.button_models:
                setListMode(MODELS_MODE);
                break;
        }
    }
}
