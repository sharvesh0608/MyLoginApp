package com.sharveshapps.myloginapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OddNamesFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_odd_names, container, false);

        recyclerView = view.findViewById(R.id.oddRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        List<User> oddUserList = databaseHelper.getUsersWithOddPosition();

        userAdapter = new UserAdapter(oddUserList);
        recyclerView.setAdapter(userAdapter);

        return view;
    }
}
