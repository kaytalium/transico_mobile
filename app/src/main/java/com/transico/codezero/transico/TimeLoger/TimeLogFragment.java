package com.transico.codezero.transico.TimeLoger;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transico.codezero.transico.FirestoreConnection.FirestoreConnection;
import com.transico.codezero.transico.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeLogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TimeLogFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecycler;
    TimeLogAdapter logAdapter;

    public TimeLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time_log, container, false);
        // Inflate the layouts for this fragment

        init(view);
        return view;
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

    private void init(View v){

        mRecycler = v.findViewById(R.id.time_log_rv_container);

//        now that we have the result we can apply to adapter to be shown in the recyclerview
        logAdapter = new TimeLogAdapter(FirestoreConnection.TimeManager.dailyLogQuery("35264"), getContext());

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(logAdapter);

//        logAdapter.notifyDataSetChanged();
        logAdapter.startListening();

    }
}
