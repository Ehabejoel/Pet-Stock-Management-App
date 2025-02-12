package com.example.petmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView totalPetsCount;
    private TextView petsCountTrend;
    private TextView lastUpdateTime;
    private RecyclerView recentActivityList;
    // Add new view references
    private TextView stockCount;
    private TextView stockStatus;
    private TextView monthlyRevenue;
    private TextView revenueTrend;
    private TextView lowStockCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(view);
        setupDashboard();
        return view;
    }

    private void initializeViews(View view) {
        totalPetsCount = view.findViewById(R.id.totalPetsCount);
        petsCountTrend = view.findViewById(R.id.petsCountTrend);
        lastUpdateTime = view.findViewById(R.id.lastUpdateTime);
        recentActivityList = view.findViewById(R.id.recentActivityList);

        MaterialButton addPetButton = view.findViewById(R.id.addPetButton);
        MaterialButton checkStockButton = view.findViewById(R.id.checkStockButton);

        addPetButton.setOnClickListener(v -> navigateToPets());
        checkStockButton.setOnClickListener(v -> navigateToInventory());

        // Initialize new views
        stockCount = view.findViewById(R.id.stockCount);
        stockStatus = view.findViewById(R.id.stockStatus);
        monthlyRevenue = view.findViewById(R.id.monthlyRevenue);
        revenueTrend = view.findViewById(R.id.revenueTrend);
        lowStockCount = view.findViewById(R.id.lowStockCount);
    }

    private void setupDashboard() {
        // Update last refresh time
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
        lastUpdateTime.setText("Last updated: " + sdf.format(new Date()));

        // Update metrics
        updateMetrics();

        // Setup recent activity
        setupRecentActivity();
    }

    private void updateMetrics() {
        // Update existing metrics
        totalPetsCount.setText("24");
        petsCountTrend.setText("↑ 8.5% vs last month");

        // Update new metrics
        stockCount.setText("156");
        stockStatus.setText("Items in stock");
        monthlyRevenue.setText("$5,280");
        revenueTrend.setText("↑ 12.3% vs last month");
        lowStockCount.setText("3");
    }

    private void setupRecentActivity() {
        recentActivityList.setLayoutManager(new LinearLayoutManager(getContext()));
        // TODO: Set up RecyclerView adapter with actual data
    }

    private void navigateToPets() {
        getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, new PetsFragment())
            .addToBackStack(null)
            .commit();
    }

    private void navigateToInventory() {
        getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, new InventoryFragment())
            .addToBackStack(null)
            .commit();
    }
}
