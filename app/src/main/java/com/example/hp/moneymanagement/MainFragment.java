package com.example.hp.moneymanagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    ListView lstview;
    CustomizedListView adaptor;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageButton btn,btn1;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Data.values.clear();
        Data.category.clear();
        Data.id.clear();
        Data.date.clear();
        btn1 = (ImageButton) getActivity().findViewById(R.id.search);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
        lstview = (ListView) view.findViewById(R.id.lists);
        final Intent intent = new Intent(getContext(),Edit.class);
        top();
        Link();
        adaptor = new CustomizedListView(getContext());
        lstview.setAdapter(adaptor);
        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("value",i);
                startActivity(intent);
            }
        });
        adaptor.notifyDataSetChanged();
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        btn = (ImageButton) getActivity().findViewById(R.id.drawer);
        btn.setOnClickListener(this);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        top();
        Data.com = "";
    }
    public void top(){
        Data.Update();
        //TOP 3
        TextView t1 = (TextView) getActivity().findViewById(R.id.income_value);
        TextView t2 = (TextView) getActivity().findViewById(R.id.expanse_value);
        TextView t3 = (TextView) getActivity().findViewById(R.id.balance_value);
        t1.setText(String.valueOf(Data.income));
        t2.setText(String.valueOf(Data.expanse));
        t3.setText(String.valueOf(Data.balance));
    }

    public void Link()
    {
        //database handler
        MySQLiteHandler database = new MySQLiteHandler(getContext());
        List<Record> records;
        records = database.getAllRecord();
        if(records.size()>0)
        {
            for(int i=0;i < records.size();i++){
                Record record = records.get(i);
                Data.id.add(record.getId());
                Data.values.add(record.getMoney());
                Log.d("category in frag",record.getCategory());
                Data.category.add(record.getCategory());
                Data.date.add(record.getDate());
            }
        }

    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
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
    }

    @Override
    public void onClick(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
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

}
