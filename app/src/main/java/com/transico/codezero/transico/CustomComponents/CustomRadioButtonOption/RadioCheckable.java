package com.transico.codezero.transico.ReportLog;

import android.view.View;
import android.widget.Checkable;
import android.widget.RadioGroup;

public interface RadioCheckable extends Checkable {

    void addOnCheckChangeListner(OnCheckedChangeListener onCheckedChangeListener);
    void removeOnCheckChangeListner(OnCheckedChangeListener onCheckedChangeListener);

    public static interface OnCheckedChangeListener{
        void onCheckedChanged(View RadioGroup, boolean isChecked);
    }

}
