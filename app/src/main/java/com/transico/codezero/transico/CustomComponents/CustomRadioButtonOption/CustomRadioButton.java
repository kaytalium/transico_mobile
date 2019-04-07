package com.transico.codezero.transico.CustomComponents.CustomRadioButtonOption;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transico.codezero.transico.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;


public class CustomRadioButton extends RelativeLayout implements RadioCheckable {

//    Views
    private TextView mGroupNameTextView, mGroupDescriptionTextView, mCheckIconTextView;

//    Constants
    public static final int DEFAULT_TEXT_COLOR = Color.TRANSPARENT;

//    Attribute Variables
    private String mName = "";
    private String mDescription = "";
    private int mNameTextColor;
    private int mDescriptionTextColor;
    private  int mPressedTextColor;

//    Variables
    private Drawable mInitialBackgroundDrawable;
    private OnClickListener mOnClicklistener;
    private OnTouchListener mOnTouchListener;
    private boolean mChecked;
    private ArrayList<OnCheckedChangeListener> mOnCheckedChangeListeners = new ArrayList<>();

    //======================================================================================
    // Constructors
    //======================================================================================

    public CustomRadioButton(Context context) {
        super(context);
        setupView();
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        setupView();
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        setupView();
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        setupView();
    }


    //================================================================================
    // Init and setup method
    //================================================================================

    private void parseAttributes(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.CustomRadioButton, 0, 0);
        Resources resources = getContext().getResources();
        int n = a.getIndexCount();

        Log.d("CustomButton", "Count: "+n);

            mName = a.getString(R.styleable.CustomRadioButton_groupNameTextValue);
            mDescription = a.getString(R.styleable.CustomRadioButton_groupDescriptionTextValue);

            //Color attrs
            mNameTextColor = a.getColor(R.styleable.CustomRadioButton_groupNameTextColor, resources.getColor(R.color.colorPrimary));
            mDescriptionTextColor = a.getColor(R.styleable.CustomRadioButton_groupDescriptionTextColor, resources.getColor(R.color.informationText));
            mPressedTextColor = a.getColor(R.styleable.CustomRadioButton_selectionTextColor,resources.getColor(R.color.titleWhite));

            a.recycle();



    }


    private void setupView(){
        inflateView();
        bindView();
        setCustomTouchListner();
    }



    protected void inflateView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_radio_button_report, this,true);
        mGroupNameTextView = findViewById(R.id.tv_group_name);
        mGroupDescriptionTextView = findViewById(R.id.tv_group_description);
        mCheckIconTextView = findViewById(R.id.tv_check_icon);
        mInitialBackgroundDrawable = getBackground();
    }

    protected void bindView() {
        if(mNameTextColor != DEFAULT_TEXT_COLOR){
            mGroupNameTextView.setTextColor(mNameTextColor);
        }

        if(mDescriptionTextColor != DEFAULT_TEXT_COLOR){
            mGroupDescriptionTextView.setTextColor(mDescriptionTextColor);
        }

        mGroupNameTextView.setText(mName);
        mGroupDescriptionTextView.setText(mDescription);
    }

    //================================================================================
    // override default behaviour
    //================================================================================


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOnClicklistener = l;
    }

    private void setCustomTouchListner(){
        super.setOnTouchListener(new TouchListener());
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    public OnTouchListener getOnTouchListener(){
        return mOnTouchListener;
    }

    private void onTouchDown(MotionEvent motionEvent){
        setChecked(true);
    }

    private void onTouchUp(MotionEvent motionEvent){
        //Handle user defined click listener
        if(mOnClicklistener != null){
            mOnClicklistener.onClick(this);
        }
    }

    //=======================================================
    //Public Methods
    //=======================================================

    public void setCheckedState(){
//        setBackgroundResource(R.drawable.reset_password_btn_bg_left);
        mCheckIconTextView.setBackgroundResource(R.drawable.ic_baseline_check_24px);
        mGroupNameTextView.setTextColor(getResources().getColor(R.color.calendar_active_color));
    }

    public void setNormalState(){
        mCheckIconTextView.setBackgroundResource(0);
        mGroupNameTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        mGroupDescriptionTextView.setTextColor(mDescriptionTextColor);
    }

    public String getName(){
        return mName;
    }

    public void setName(String s){
        mName = s;
    }


    public String getDescription(){
        return mDescription;
    }

    public void setDescription(String s){
        mDescription = s;
    }



    //====================================================================================
    // Checkable Implementation
    //====================================================================================

    @Override
    public void setChecked(boolean checked) {
        if(mChecked != checked){
            mChecked = checked;
            if(!mOnCheckedChangeListeners.isEmpty()){
                for(int i = 0; i < mOnCheckedChangeListeners.size(); i++){
                    mOnCheckedChangeListeners.get(i).onCheckedChanged(this,mChecked);
                }
            }
            if(mChecked){
                setCheckedState();
            }else{
                setNormalState();
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }


    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public void addOnCheckChangeListner(OnCheckedChangeListener onCheckedChangeListener) {
            mOnCheckedChangeListeners.add((OnCheckedChangeListener) onCheckedChangeListener);
    }

    @Override
    public void removeOnCheckChangeListner(OnCheckedChangeListener onCheckedChangeListener) {
            mOnCheckedChangeListeners.remove(onCheckedChangeListener);
    }






    //================================================================================
    // Inner classes
    //================================================================================
    private final class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(event);
                    break;
                case MotionEvent.ACTION_UP:
                    onTouchUp(event);
                    break;
            }
            if (mOnTouchListener != null) {
                mOnTouchListener.onTouch(v, event);
            }
            return true;
        }
    }
}
