package com.gankcorner.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.BottomDialogFragment;

public class Fragment_Two extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        Button button = (Button) view.findViewById(R.id.button_center);
        button.setText("Fragment_Two");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
                bottomDialogFragment.show(getActivity().getSupportFragmentManager(), "bottomDialogFragment");
            }
        });

        Log.i("========zzq", "Fragment_Two_isVisibleToUser: " + isVisibleToUser());

        return view;
    }

}
