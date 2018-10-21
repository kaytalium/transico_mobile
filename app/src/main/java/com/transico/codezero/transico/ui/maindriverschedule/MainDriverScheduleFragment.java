package com.transico.codezero.transico.ui.maindriverschedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transico.codezero.transico.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MainDriverScheduleFragment extends Fragment {

    private MainDriverScheduleViewModel mViewModel;

    public static MainDriverScheduleFragment newInstance() {
        return new MainDriverScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_driver_schedule_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainDriverScheduleViewModel.class);
        // TODO: Use the ViewModel
    }

}
