package com.transico.codezero.transico.CustomComponents.CustomChatBox;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transico.codezero.transico.R;

import androidx.annotation.NonNull;

public class ChatBox extends RelativeLayout {

    private EditText mUserInput;
    private TextView mBtnConfirm;
    private RelativeLayout uiContainer;

    private OnEnterClickListener mOnClickListener;
    private boolean largerLines = false;
    private ViewGroup.LayoutParams params;

    public ChatBox(Context context) {
        super(context);
        init();
    }

    public ChatBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        init();
    }

    public ChatBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        init();
    }

    public ChatBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        init();
    }

    //set attr if any
    private void parseAttributes(AttributeSet attrs){}

    private void init(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_chat_box, this,true);

        mUserInput = findViewById(R.id.et_user_input);
        mBtnConfirm = findViewById(R.id.fbtn_confirm);
        uiContainer = findViewById(R.id.chatbox_ui_container);

        params = uiContainer.getLayoutParams();

        //Activate the onclick for the post button once the user type something and also show activation color
        //Deactivate the post button when the user remove all text
        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //check the length
                if(count > 0){
                    mBtnConfirm.setTextColor(getResources().getColor(R.color.primaryBlue));
                    mBtnConfirm.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mUserInput.getText().length() > 0)
                            mOnClickListener.enterClickListener(mUserInput);
                        }
                    });

                    if (s.toString().contains("\n")) {

                        // If we haven't already, increase the line spacing by 1.2x
                        if (!largerLines && mUserInput.getLineHeight() > 2) {
                            //get the height of the textbox

                            Log.d("Ovel", "Height of container: " + dpToPx(mUserInput.getHeight() - 40, getContext()));
                            params.height = dpToPx(mUserInput.getHeight() - 40, getContext());
                            uiContainer.setLayoutParams(params);
                            //add height to the ui container
                            largerLines = true;
                        }
                    }
                    // If there is no new line character & the line spacing is
                    // large, make it smaller again by 0.8x
                    else if (largerLines) {
//                        inputText.setLineSpacing(0, 0.8f);
//                        params.height = dpToPx(mUserInput.getHeight() - 10, getContext());
                        uiContainer.setLayoutParams(params);
                        largerLines = false;
                    }



                }else{
                    mBtnConfirm.setTextColor(getResources().getColor(R.color.informationText));
                }

            }

            public void afterTextChanged(Editable s) {
            }
        };

        mUserInput.addTextChangedListener(mTextEditorWatcher);

    }


    public void setOnEnterClickListener(@NonNull OnEnterClickListener onEnterClickListener){
        mOnClickListener = onEnterClickListener;
    }

    public interface OnEnterClickListener{
        void enterClickListener(EditText editText);
    }

    private int dpToPx(int dp, Context context) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }


}
