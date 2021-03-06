package com.tuccro.cars;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

        brandsFragment = BrandsFragment.getInstance();
        modelsFragment = ModelsFragment.getInstance();
        listFragment = ItemsListFragment.getInstance();
        editFragment = new EditFragment();

        if (savedInstanceState == null) {
            fragTrans = getFragmentManager().beginTransaction();
            fragTrans.add(R.id.layout_brands, brandsFragment);
            fragTrans.add(R.id.layout_models, modelsFragment);
            fragTrans.add(R.id.layout_list, listFragment);
            fragTrans.add(R.id.layout_edit, editFragment);
            fragTrans.commit();

            setListMode(ENGINES_MODE);
        }
    }

    @Override
    public void onBackPressed() {
        setListMode(ENGINES_MODE);
    }

    /**
     * Sets mode for list and edit buttons
     *
     * @param mode final static String variable with postfix "_MODE"
     */
    void setListMode(String mode) {
        dataBase = new DB(this);
        dataBase.open();
        fragTrans = getFragmentManager().beginTransaction();
        switch (mode) {
            case BRANDS_MODE:
                currentItemsList = Utils.getBrandsFromDBCursor(dataBase.getAllBrands());
                fragTrans.hide(modelsFragment);
                fragTrans.hide(brandsFragment);
                break;
            case MODELS_MODE:
                currentItemsList = Utils.getModelsFromDBCursor(dataBase.
                        getAllBrandModels(brandsFragment.getSelectedItemId()));
                modelsFragment.setEditMode(true);
                break;
            case ENGINES_MODE:
                currentItemsList = Utils.getEnginesFromDBCursor(dataBase.
                        getAllModelEngines(modelsFragment.getSelectedItemId()));
                fragTrans.show(modelsFragment);
                fragTrans.show(brandsFragment);
                if (modelsFragment.isEditMode()) modelsFragment.setEditMode(false);
                break;
        }
        this.mode = mode;
        initItemsList(currentItemsList);
        dataBase.close();
        fragTrans.commit();
    }

    /**
     * Initializes items list fragment
     *
     * @param list list with Items
     */
    void initItemsList(List list) {
        listFragment.setListAdapter(Utils.getItemsArrayAdapter(this, list));
    }

    /**
     * Handler for edit buttons
     *
     * @param v    Button that was pressed
     * @param name value of EditText
     */
    @Override
    public void onEdit(View v, String name) {
        if (((v.getId() == R.id.button_delete || v.getId() == R.id.button_update) &&
                listFragment.getSelectedPosition() >= 0 &&
                !listFragment.isActionDone()) ||
                v.getId() == R.id.button_add) {
            ActionDialog dialog = new ActionDialog(this, v, name);
            dialog.show();
        }
    }

    /**
     * Method that removes Item from database
     *
     * @param id item id in DB
     */
    private void deleteItem(int id) {
        DB db = new DB(getApplicationContext());
        db.open();
        switch (mode) {
            case BRANDS_MODE:
                db.deleteBrand(currentItemsList.get(id).getId());
                brandsFragment.init();
                break;
            case MODELS_MODE:
                db.deleteModel(currentItemsList.get(id).getId());
                modelsFragment.init();
                break;
            case ENGINES_MODE:
                db.deleteEngine(currentItemsList.get(id).getId());
                break;
        }
        setListMode(mode);
        db.close();
        listFragment.setActionDone(true);
    }

    /**
     * Method that adds Item to database
     *
     * @param name name of new Item
     */
    private void addItem(String name) {
        DB db = new DB(getApplicationContext());
        db.open();
        switch (mode) {
            case BRANDS_MODE:
                db.addBrand(name);
                brandsFragment.init();
                break;
            case MODELS_MODE:
                int start = modelsFragment.getEditTextStart();
                int end = modelsFragment.getEditTextEnd();
                if ((start < 1900 && start > Utils.getCurrentYear()) ||
                        (end < 1900 && end > Utils.getCurrentYear())) break;

                int brandId = brandsFragment.getSelectedItemId();

                if (brandId == -1) {
                    Toast.makeText(this, "Create a brand first!", Toast.LENGTH_SHORT).show();
                } else {
                    db.addModel(brandsFragment.getSelectedItemId(), name, start, end);
                    modelsFragment.init(brandId);
                }
                break;
            case ENGINES_MODE:
                int modelId = modelsFragment.getSelectedItemId();

                if (modelId == -1) {
                    Toast.makeText(this, "Create a model first!", Toast.LENGTH_SHORT).show();
                } else {
                    db.addEngine(modelId, name);
                }
                break;
        }
        setListMode(mode);
        db.close();
    }

    /**
     * Method that updates Item in DB by id
     *
     * @param id   id of Item
     * @param name new name
     */
    private void updateItem(int id, String name) {
        DB db = new DB(getApplicationContext());
        db.open();
        switch (mode) {
            case BRANDS_MODE:
                db.updateBrand(currentItemsList.get(id).getId(), name);
                brandsFragment.init();
                break;
            case MODELS_MODE:
                int start = modelsFragment.getEditTextStart();
                int end = modelsFragment.getEditTextEnd();
                if ((start < 1900 && start > Utils.getCurrentYear()) ||
                        (end < 1900 && end > Utils.getCurrentYear())) break;

                db.updateModel(currentItemsList.get(id).getId(), brandsFragment.getSelectedItemId(), name, start, end);
                modelsFragment.init();
                break;
            case ENGINES_MODE:
                db.updateEngine(currentItemsList.get(id).getId(), modelsFragment.getSelectedItemId(), name);
                break;
        }
        setListMode(mode);
        db.close();
        listFragment.setActionDone(true);
    }

    /**
     * Method from interfaces from BrandsFragment and ModelsFragment that handles buttons clicks
     *
     * @param v pressed button
     */
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

    @Override
    public void onModelsSpinnerClick(int modelId) {
        setListMode(mode);
    }

    @Override
    public void onBrandSpinnerSelect(int brandId) {
        modelsFragment.init(brandId);
        setListMode(mode);
    }

    /**
     * Inner class creates dialog for all DB actions.
     */
    public class ActionDialog extends AlertDialog {

        Context context;

        protected ActionDialog(Context context, final View v, final String name) {
            super(context);
            this.context = context;
            this.setTitle("Please confirm");

            StringBuilder builder = new StringBuilder("Are you really want to ");

            switch (v.getId()) {
                case R.id.button_delete:
                    builder.append("delete item " + listFragment.getSelectedPositionName() + "?");
                    break;
                case R.id.button_add:
                    builder.append("add item " + name + "?");
                    break;
                case R.id.button_update:
                    builder.append("update item " + listFragment.getSelectedPositionName() + "?");
                    break;
            }

            this.setMessage(builder.toString());

            this.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (v.getId()) {
                        case R.id.button_add:
                            if (!name.isEmpty()) addItem(name);
                            break;
                        case R.id.button_delete:
                            int id = listFragment.getSelectedPosition();
                            if (id != -1) {
                                deleteItem(id);
                            }
                            break;
                        case R.id.button_update:
                            if (!name.isEmpty())
                                updateItem(listFragment.getSelectedPosition(), name);
                            break;
                    }
                }
            });

            this.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
    }
}
