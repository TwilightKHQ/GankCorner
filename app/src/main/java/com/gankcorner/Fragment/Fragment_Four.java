package com.gankcorner.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.BottomDialog;

public class Fragment_Four extends BaseFragment {


    private String TAG = "========zzq";

    private TelephonyManager tm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);


        Button button = (Button) view.findViewById(R.id.button_center);
        button.setText("Fragment_Home");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomDialog bottomDialog = new BottomDialog(getContext(), R.style.BottomDialog);
                bottomDialog.show();

                /** 返回电话状态
                 *
                 * CALL_STATE_IDLE 无任何状态时
                 * CALL_STATE_OFFHOOK 接起电话时
                 * CALL_STATE_RINGING 电话进来时
                 */
                tm.getCallState();
                if (tm.getCallState() == TelephonyManager.CALL_STATE_IDLE) {
                    Log.d(TAG, "call state idle...");
                } else if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                    Log.d(TAG, "call state offhook...");
                } else if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
                    Log.d(TAG, "call state ringing...");
                }
            }
        });


        return view;


    }


}
