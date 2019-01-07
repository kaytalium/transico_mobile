package com.transico.codezero.transico.DriverSchedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.transico.codezero.transico.EmployeeActivity;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;


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
    private FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
    private CollectionReference scheduleCollectionRef = firestoreDB.collection(databaseCommand.driverSchedule);

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
        RecyclerView calendarList = view.findViewById(R.id.rv_calender);


        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(calendarList);


        scheduleProcessor = new ScheduleProcessor(scheduleCollectionRef,sch_mainList,getContext());

        CalendarViewOnClickListner mlistner = (new CalendarViewOnClickListner() {
            @Override
            public void recyclerViewClickListner(int shortDate, Date longDate) {
//                Toast.makeText(MainActivity.this,"Outside called" + String.valueOf(shortDate),Toast.LENGTH_LONG ).show();

                /* update the recyclerview with the new filter date */
                scheduleProcessor.updateFilterDate(longDate);

                /* Update the Title bar the month of the date that was clicked or selected */
//                getContext().setTitle(Helper.DateFormatter(databaseCommand.LongMonth,longDate));

            }
        });

        Kcalendar kc = new Kcalendar();
        kc.generateCalendar(calendarList, getContext(), mlistner);



        calendarList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ((EmployeeActivity)getActivity()).getSupportActionBar().setTitle("November");
            }
        });


        //process and load the schedule for the current driver and current day
        //Followed by the days in sequence
        scheduleProcessor.loadSchedule();
        scheduleProcessor.scheduleAdapter.startListening();
    }

    public void setBottomNavigation(BottomNavigationView bottomNavigation){
        this.bottomNavigation = bottomNavigation;
    }
}
