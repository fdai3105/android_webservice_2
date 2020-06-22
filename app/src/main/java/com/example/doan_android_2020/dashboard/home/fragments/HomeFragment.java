package com.example.doan_android_2020.dashboard.home.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.adapters.BannersAdapter;
import com.example.doan_android_2020._general.adapters.ItemAdapter;
import com.example.doan_android_2020._general.models.Item;
import com.example.doan_android_2020._general.models.ItemPromotion;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020.dashboard.DashBoardActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    //views
    private ShimmerFrameLayout shimmerHome;
    private RecyclerView rvBannerHome;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView rvFragmentHomeItems;
    private ArrayList<Item> items = new ArrayList<>();
    private loadBanner loadBanner;

    private static final String TAG = "HomeFragment: ";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        shimmerHome = root.findViewById(R.id.shimmerHome);
        rvBannerHome = root.findViewById(R.id.rvBannerHome);
        swipeRefreshLayout = root.findViewById(R.id.swipeLayout);
        rvFragmentHomeItems = root.findViewById(R.id.rvFragmentHomeItems);

        loadBanner = new loadBanner();
        loadBanner.addBanner();
        new loadItem().execute();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!items.isEmpty()) {
                            items.clear();
                        }
                        new loadItem().execute();
                        //disable loading icon
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }
        });
        return root;
    }

    private class loadBanner {
        Timer timer;
        TimerTask timerTask;
        LinearLayoutManager layoutManager;
        int position;

        private void addBanner() {
            List<String> urls = new ArrayList<>();
            urls.add("https://cdn.luxstay.com/home/apartment/apartment_1_1578970876.jpg");
            urls.add("https://cdn.luxstay.com/home/apartment/apartment_2_1578970932.jpg");
            urls.add("https://cdn.luxstay.com/home/apartment/apartment_3_1578970988.jpg");
            urls.add("https://cdn.luxstay.com/home/apartment/apartment_4_1578971024.jpg");

            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rvBannerHome.setLayoutManager(layoutManager);

            BannersAdapter bannerAdapter = new BannersAdapter(getContext(), urls);
            rvBannerHome.setAdapter(bannerAdapter);

            position = Integer.MAX_VALUE / 2;
            rvBannerHome.scrollToPosition(position);

            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(rvBannerHome);
            rvBannerHome.smoothScrollBy(5, 0);
            runAutoScrollBanner();

            rvBannerHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == 1) {
                        stopAutoScrollBanner();
                    } else if (newState == 0) {
                        position = layoutManager.findFirstCompletelyVisibleItemPosition();
                        runAutoScrollBanner();
                    }
                }
            });
        }

        private void runAutoScrollBanner() {
            if (timer == null && timerTask == null) {
                timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (position == Integer.MAX_VALUE) {
                            position = Integer.MAX_VALUE / 2;
                            rvBannerHome.scrollToPosition(position);
                            rvBannerHome.smoothScrollBy(5, 0);
                        } else {
                            position++;
                            rvBannerHome.smoothScrollToPosition(position);
                        }
                    }
                };
                timer.schedule(timerTask, 3000, 3000);
            }
        }

        private void stopAutoScrollBanner() {
            if (timer != null && timerTask != null) {
                timerTask.cancel();
                timer.cancel();
                timer = null;
                timerTask = null;
                position = layoutManager.findFirstCompletelyVisibleItemPosition();
            }
        }
    }

    private class loadItem extends AsyncTask<Void, Void, Void> {
        Handler handler = new Handler();

        @Override
        protected Void doInBackground(Void... voids) {
            FiDaiService fiDaiService = new FiDaiService();
            String json = fiDaiService.callService(FiDaiService.GET_All_ITEMS_URL, FiDaiService.GET);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject != null) {
                        JSONArray jsonArray = jsonObject.getJSONArray("items");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                            int itemIds = jsonObject1.getInt("itemId");
                            String itemName = jsonObject1.getString("itemName");
                            String itemImage = jsonObject1.getString("itemImage");
                            int itemPrice = jsonObject1.getInt("itemPrice");
                            String itemBio = jsonObject1.getString("itemBio");
                            double itemReview = jsonObject1.getDouble("itemReview");

                            Random random = new Random();
                            boolean one = false;
                            boolean two = false;
                            boolean three = false;
                            one = random.nextBoolean();
                            if (!one) {
                                two = random.nextBoolean();
                                if (!two) {
                                    three = random.nextBoolean();
                                } else {
                                    one = true;
                                }
                            }
                            Item item = new Item(itemIds, itemName, "Danh mục", itemImage,
                                    itemPrice, new ItemPromotion(one, two, three), itemBio, itemReview);
                            items.add(item);

                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "line 126: " + e.toString());
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "line 126: Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvFragmentHomeItems.setLayoutManager(staggeredGridLayoutManager);
            //item change postion when fast scroll up
            rvFragmentHomeItems.setItemAnimator(null);
            ItemAdapter itemAdapter = new ItemAdapter(getContext(), items);
            rvFragmentHomeItems.setAdapter(itemAdapter);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    shimmerHome.stopShimmer();
                    shimmerHome.setVisibility(View.GONE);
                }
            }, 2500);

        }
    }

    //region stop or start banner when not in homeFragment
    @Override
    public void onPause() {
        super.onPause();
        loadBanner.stopAutoScrollBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBanner.runAutoScrollBanner();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            loadBanner.stopAutoScrollBanner();
        } else {
            loadBanner.runAutoScrollBanner();
        }
    }
    //endregion
}
