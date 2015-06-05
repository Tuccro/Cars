package com.tuccro.cars.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tuccro.cars.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnEditListener} interface
 * to handle interaction events.
 */
public class EditFragment extends Fragment {

    private OnEditListener mListener;

    private EditText editTextName;
    private Button buttonAdd;
    private Button buttonDelete;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        editTextName = (EditText) view.findViewById(R.id.edit_text_name);
        buttonAdd = (Button) view.findViewById(R.id.button_add);
        buttonDelete = (Button) view.findViewById(R.id.button_delete);

        buttonAdd.setOnClickListener(onClickListener);
        buttonDelete.setOnClickListener(onClickListener);

        return view;
    }

   View.OnClickListener onClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           if (mListener != null) {
               mListener.onEdit(v, editTextName.getText().toString());
           }
       }
   };



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnEditListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEditListener {
        // TODO: Update argument type and name
        void onEdit(View v, String name);
    }


}
