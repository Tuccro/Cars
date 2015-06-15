package com.tuccro.cars.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tuccro.cars.R;
import com.tuccro.cars.core.Item;
import com.tuccro.cars.core.Model;
import com.tuccro.cars.database.DB;
import com.tuccro.cars.utils.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModelsFragment extends Fragment {

    Spinner spinner;
    Button button;
    EditText tvStart;
    EditText tvEnd;

    List<Item> modelsList;
    int selectedItemId;

    private OnButtonClickListener onButtonClickListener;

    public ModelsFragment() {
        // Required empty public constructor
    }

    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setEnableYearsEditTexts(boolean b) {
        tvStart.setEnabled(b);
        tvEnd.setEnabled(b);
    }

    public int getEditTextStart() {
        if (tvStart.getText().toString() != null) {
            return Integer.parseInt(tvStart.getText().toString());
        } else {
            return 0;
        }
    }

    public int getEditTextEnd() {
        if (tvEnd.getText().toString() != null) {
            return Integer.parseInt(tvEnd.getText().toString());
        } else {
            return 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_models, container, false);

        tvStart = (EditText) view.findViewById(R.id.text_start_date);
        tvEnd = (EditText) view.findViewById(R.id.text_end_date);

        spinner = (Spinner) view.findViewById(R.id.spinner_models);
        button = (Button) view.findViewById(R.id.button_models);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onButtonClick(v);
                }
            }
        });

        init();

        spinner.setOnItemSelectedListener(listener);
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

        modelsList = Utils.getModelsFromDBCursor(data.getAllModels());
        spinner.setAdapter(Utils.getItemsArrayAdapter(getActivity(), modelsList));

        data.close();
    }

    public void init(int brandId) {
        DB data = new DB(getActivity().getApplicationContext());
        data.open();

        modelsList = Utils.getModelsFromDBCursor(data.getAllBrandModels(brandId));
        spinner.setAdapter(Utils.getItemsArrayAdapter(getActivity(), modelsList));

        data.close();
    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int startDate, endDate;

            Model model = (Model) modelsList.get(position);
            selectedItemId = model.getId();
            onButtonClickListener.onModelsSpinnerClick(selectedItemId);

            startDate = model.getStartYear();
            endDate = model.getEndYear();

            tvStart.setText(String.valueOf(startDate));
            tvEnd.setText(String.valueOf(endDate));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public interface OnButtonClickListener {

        void onButtonClick(View v);
        void onModelsSpinnerClick(int modelId);
    }
}
