package com.example.monitoreoestadoambiental.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monitoreoestadoambiental.Model.RegistroTemperatura;
import com.example.monitoreoestadoambiental.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TemperatureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TemperatureFragment extends Fragment {

    private TextView txvTemperature;

    private DatabaseReference RootRef;

    private RecyclerView tempList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TemperatureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemperatureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TemperatureFragment newInstance(String param1, String param2) {
        TemperatureFragment fragment = new TemperatureFragment();
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

        View v = inflater.inflate(R.layout.fragment_temperature, container, false);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        txvTemperature = v.findViewById(R.id.txv_temperature);
        tempList = v.findViewById(R.id.temperature_list);
        ColocarTemperatura();
        LlenarRegistro();
        return v;
    }

    private void LlenarRegistro() {

        FirebaseRecyclerOptions<RegistroTemperatura> options =
                new FirebaseRecyclerOptions.Builder<RegistroTemperatura>()
                        .setQuery(RootRef.child("temps"), RegistroTemperatura.class)
                        .build();

        FirebaseRecyclerAdapter<RegistroTemperatura, TemperatureFragment.TemperaturaViewHolder> adapter =
                new FirebaseRecyclerAdapter<RegistroTemperatura, TemperatureFragment.TemperaturaViewHolder>(options) {

                    @Override
                    protected void onBindViewHolder(@NonNull TemperatureFragment.TemperaturaViewHolder holder, final int position, @NonNull RegistroTemperatura model)
                    {
                        holder.temp.setText((model.getTemp()));
                        holder.datetime.setText(model.getDatetime());
                    }

                    @NonNull
                    @Override
                    public TemperatureFragment.TemperaturaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
                    {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.temp_display_layout, viewGroup, false);
                        TemperatureFragment.TemperaturaViewHolder viewHolder = new TemperatureFragment.TemperaturaViewHolder(view);
                        return viewHolder;
                    }
                };

        tempList.setLayoutManager(new GridLayoutManager(getContext(),1));
        tempList.setAdapter(adapter);

        adapter.startListening();
    }

    public static class TemperaturaViewHolder extends RecyclerView.ViewHolder
    {
        TextView temp;
        TextView datetime;

        public TemperaturaViewHolder(@NonNull View itemView)
        {
            super(itemView);

            temp = (TextView) itemView.findViewById(R.id.temp_display_layout);
            datetime = (TextView) itemView.findViewById(R.id.datetimeTemp_display_layout);
        }
    }

    private void ColocarTemperatura() {
        RootRef.child("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String hum = snapshot.child("hum").getValue().toString();
                    String son = snapshot.child("son").getValue().toString();
                    String temp = snapshot.child("temp").getValue().toString();

                    txvTemperature.setText(temp);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}