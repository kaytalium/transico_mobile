package com.transico.codezero.transico.DriverScheduler;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.transico.codezero.transico.CustomComponents.CustomDatePicker.HorizontalDatePicker;
import com.transico.codezero.transico.CustomComponents.CustomDatePicker.OnClickDatePickListener;
import com.transico.codezero.transico.CustomComponents.CustomDatePicker.OnHorizontalScrollListener;
import com.transico.codezero.transico.EmployeeActivity;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.FirestoreConnection.FirebaseManager;
import com.transico.codezero.transico.SystemHelper.Helper;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Date;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DriverScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DriverScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverScheduleFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Vars
    //    create a schedule object
    private ScheduleProcessor scheduleProcessor;

    //    Firebase firestore database references
//    private FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
//    private CollectionReference scheduleCollectionRef = firestoreDB.collection(databaseCommand.driverSchedule);

    //design Components
    private RecyclerView sch_mainList;

    private OnFragmentInteractionListener mListener;

    private BottomNavigationView bottomNavigation;

    public DriverScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DriverScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DriverScheduleFragment newInstance(String param1, String param2) {
        DriverScheduleFragment fragment = new DriverScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layouts for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_schedule, container, false);

        loadSchedule(view);
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
        scheduleProcessor.scheduleAdapter.stopListening();
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

    private void loadSchedule(View view){

        sch_mainList = view.findViewById(R.id.sch_recyclerView);
        HorizontalDatePicker mHorizontalDatePicker = view.findViewById(R.id.rv_calendar);

        //Set current month in the title bar
        setActivityTitle(new Date());

        mHorizontalDatePicker.setOnClickDatePickerListener(new OnClickDatePickListener() {
            @Override
            public void datePickListener(String shortMonth, String longMonth, String dayDate, Date selectedDate) {
                       //do what you want to do with the date that was clicked
                scheduleProcessor.updateFilterDate(selectedDate);

                //Setup the month that is displayed in the toolbar
                //i.e. if its the current year then shows only month "January"
                //however if the year is greater or less than the current Year then show month and year
                //December 2018
                setActivityTitle(selectedDate);
            }
        });

        mHorizontalDatePicker.setOnHorizontalScrollListener(new OnHorizontalScrollListener() {
            @Override
            public void horizontalScrollListener(Date firstDayDate) {
                scheduleProcessor.updateFilterDate(firstDayDate);
                setActivityTitle(firstDayDate);
            }
        });



        //load the recyclerview with the logged in driver schedule
        scheduleProcessor = new ScheduleProcessor(sch_mainList,getContext());




        //process and load the schedule for the current driver and current day
        //Followed by the days in sequence
        scheduleProcessor.loadSchedule();
        scheduleProcessor.scheduleAdapter.startListening();
    }

    private void setActivityTitle(Date date){
        //current year
        int currentYear = Integer.valueOf(Helper.DateFormatter(databaseCommand.DateTimeFormat.year,new Date()));
        int selectedYear = Integer.valueOf(Helper.DateFormatter(databaseCommand.DateTimeFormat.year,date));

        if(currentYear == selectedYear) {
            ((EmployeeActivity) getActivity()).setActivityTitle(Helper.DateFormatter(databaseCommand.DateTimeFormat.longMonth,date));
        }else{
            ((EmployeeActivity) getActivity()).setActivityTitle(Helper.DateFormatter(databaseCommand.DateTimeFormat.longMonthWithYear,date));
        }
    }
    public void setBottomNavigation(BottomNavigationView bottomNavigation){
        this.bottomNavigation = bottomNavigation;
    }
}
