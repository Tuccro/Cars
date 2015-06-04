package com.tuccro.cars;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
    TextView tvStart;
    TextView tvEnd;

    List<Model> modelsList;

    public ModelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_models, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner_models);
        button = (Button) view.findViewById(R.id.button_models);
        tvStart = (TextView) view.findViewById(R.id.text_start_date);
        tvEnd = (TextView) view.findViewById(R.id.text_end_date);

        init();

        spinner.setOnItemSelectedListener(listener);
        return view;
    }

    public void init() {
        DB data = new DB(getActivity().getApplicationContext());
        data.open();

        modelsList = Utils.getModelsFromDBCursor(data.getAllModels());

        spinner.setAdapter(Utils.getItemsArrayAdapter(getActivity(),  modelsList));
    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int startDate, endDate;

            startDate = modelsList.get(position).getStartYear();
            endDate = modelsList.get(position).getEndYear();

            tvStart.setText(String.valueOf(startDate));
            tvEnd.setText(String.valueOf(endDate));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
