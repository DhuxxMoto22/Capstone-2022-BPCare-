package com.example.bloodcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;




public class SettingsFragment extends Fragment {
 Button instre;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public SettingsFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        // Inflate the layout for this fragment


        //go logout
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        TextView logout = (TextView) view.findViewById(R.id.logout);
        TextView instre = (TextView) view.findViewById(R.id.instr);
        TextView feedbacks = (TextView) view.findViewById(R.id.feedback);
        TextView notifs = (TextView) view.findViewById(R.id.notif);
        TextView PrivSecs = (TextView) view.findViewById(R.id.PrivSec);
        TextView Helps = (TextView) view.findViewById(R.id.Help);
        TextView Abouts = (TextView) view.findViewById(R.id.About);

        PrivSecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent priv = new Intent(getActivity(), Privacy.class);
                priv.putExtra("some", "some data");
                startActivity(priv);

            }
        });

        notifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent not = new Intent(getActivity(), Notif.class);
                not.putExtra("some", "some data");
                startActivity(not);

            }
        });

        Helps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hel = new Intent(getActivity(), HelpSup.class);
                hel.putExtra("some", "some data");
                startActivity(hel);

            }
        });

        Abouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ab = new Intent(getActivity(), About.class);
                ab.putExtra("some", "some data");
                startActivity(ab) ;

            }
        });

        feedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fee = new Intent(getActivity(), GiveUsFeedback.class);
                fee.putExtra("some", "some data");
                startActivity(fee);

            }
        });

        instre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(getActivity(), instructions.class);
                inn.putExtra("some", "some data");
                startActivity(inn);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), loginpage.class);
                in.putExtra("some", "some data");
                startActivity(in);

            }
        });

        return view;
    }
        }

