package com.example.somato.seeAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.somato.R;
import com.example.somato.dashboard.DashBoardView;
import com.example.somato.dashboard.adapters.HomeAdapter;
import com.example.somato.dashboard.model.SearchedResponse;
import com.example.somato.dashboard.presenter.DashboardPresenter;
import com.example.somato.network.Config;
import com.example.somato.utitlities.ErrorResponse;

public class PubsFragment extends Fragment implements DashBoardView {
    private DashboardPresenter presenter;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ProgressBar progressBar;
    TextView msg;
    public PubsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_see_all, container, false);
        // Inflate the layout for this fragment
        recyclerView=view.findViewById(R.id.res_list);
        msg=view.findViewById(R.id.msg);
        progressBar=view.findViewById(R.id.progressBar);
        presenter=new DashboardPresenter(this.getActivity(),this);
        presenter.getResultsForDashboard(11,Double.valueOf(Config.lattitude), Double.valueOf(Config.longitude), "real_distance", 20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }
    public static PubsFragment newInstance() {
        PubsFragment fragment = new PubsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onSearchResultSucess(SearchedResponse response) {
        progressBar.setVisibility(View.GONE);
        if(response.getRestaurants().size()==0){
            msg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            msg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new HomeAdapter(response.getRestaurants(), this.getActivity());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(ErrorResponse error) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        msg.setText(View.VISIBLE);
    }

}
