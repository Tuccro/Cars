package com.tuccro.cars;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.tuccro.cars.core.Brand;
import com.tuccro.cars.core.Item;
import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends Fragment {

    Spinner spinner;
    Button button;
    List<Brand> brandsList;

    public BrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brands, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner_brands);
        button = (Button) view.findViewById(R.id.button_brands);

        init();
        return view;
    }

    public void init() {
        DB data = new DB(getActivity().getApplicationContext());
        data.open();

        brandsList = Utils.getBrandsFromDBCursor(data.getAllBrands());

        data.close();

        spinner.setAdapter(Utils.getItemsArrayAdapter(getActivity(),  brandsList));
    }
}
