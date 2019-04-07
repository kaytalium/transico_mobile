package com.transico.codezero.transico.ProfileManagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.transico.codezero.transico.R;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileManagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProfileManagerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageButton editbtn;

    public ProfileManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layouts for this fragment
        View v =  inflater.inflate(R.layout.profile_manager_fragment, container, false);

        editbtn = v.findViewById(R.id.ib_change_password);

        editbtn.setOnClickListener(changePasswordListener());
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Onclick listner for edit button on the profile view UI
     */
    private View.OnClickListener changePasswordListener(){
       return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Change my password",Toast.LENGTH_LONG).show();


//                create activity
                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(i);

            }
        };
    }


}
