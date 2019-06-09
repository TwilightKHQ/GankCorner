package com.gankcorner.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gankcorner.R;
import com.gankcorner.Utils.BottomDialog;

public class Fragment_Four extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        Button button = (Button) view.findViewById(R.id.button_center);
        button.setText("Fragment_Home");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomDialog bottomDialog = new BottomDialog(getContext(), R.style.BottomDialog);
                bottomDialog.show();
            }
        });

        return view;
    }


}
