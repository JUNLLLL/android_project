package com.learn.learnviewpager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.learn.learnviewpager.R;


public class ThreeFragment extends Fragment {

    public  String txt_conntent;

    // TODO: Rename and change types and number of parameters
    public  ThreeFragment(String param1) {
        txt_conntent = param1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_three, container, false);

        TextView textView = view.findViewById(R.id.txt_content);
        textView.setText(txt_conntent);
        return view;
    }

   public String getTitle()
    {
        return txt_conntent;
    }

}
