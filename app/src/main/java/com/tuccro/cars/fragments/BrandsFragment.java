package com.tuccro.cars.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.tuccro.cars.R;
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
    List<Item> brandsList;
    private OnButtonClickListener onButtonClickListener;

    public BrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brands, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner_brands);
        button = (Button) view.findViewById(R.id.button_brands);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onButtonClick(v);
                }
            }
        });

        init();
        return view;
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onButtonClickListener = (OnButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onButtonClickListener = null;
    }

    public void init() {
        DB data = new DB(getActivity().getApplicationContext());
        data.open();

        brandsList = Utils.getBrandsFromDBCursor(data.getAllBrands());

        data.close();

        spinner.setAdapter(Utils.getItemsArrayAdapter(getActivity(), brandsList));
    }

    public interface OnButtonClickListener {

        void onButtonClick(View v);
    }
}
