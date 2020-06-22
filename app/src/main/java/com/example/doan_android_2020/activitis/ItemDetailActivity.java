package com.example.doan_android_2020.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2020.R;
import com.example.doan_android_2020._general.models.Item;
import com.example.doan_android_2020._general.models.ItemPromotion;
import com.example.doan_android_2020._general.networks.FiDaiService;
import com.example.doan_android_2020._general.utils.UsersToSharePrefe;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemDetailActivity extends AppCompatActivity {
    //views
    ImageView ivItemDetailImage;

    private static final String TAG = "ItemDetailActivity: ";
    UsersToSharePrefe usersToSharePrefe;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check and set dark mode
        usersToSharePrefe = new UsersToSharePrefe(getApplicationContext());
        boolean darkMode = usersToSharePrefe.getSettings().isDARK_MODE();
        if (darkMode) {
            setTheme(R.style.ItemDetailThemeDark);
        } else {
            setTheme(R.style.ItemDetailThemeLight);
        }

        setContentView(R.layout.activity_item_detail);
        ivItemDetailImage = findViewById(R.id.ivItemDetailImage);
        int ItemIds = getIntent().getIntExtra("ItemIds", 0);
        new loadItem(ItemIds).execute();
    }

    private class loadItem extends AsyncTask<Void, Void, Void> {
        int itemIds;

        String itemName;
        String itemImage;
        int itemPrice;
        String itemBio;
        String itemReview;

        public loadItem(int itemName) {
            this.itemIds = itemName;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            FiDaiService fiDaiService = new FiDaiService();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair("itemIds", String.valueOf(itemIds)));
            String json = fiDaiService.callService(FiDaiService.GET_SINGLE_ITEM_URL, FiDaiService.POST, args);
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString(FiDaiService.KEY_FEEDBACK_STATUS);
                    String message = jsonObject.getString(FiDaiService.KEY_FEEDBACK_MESSAGE);
                    if (!status.isEmpty()) {
                        if (Integer.parseInt(status) == 0) {
                            itemName = jsonObject.getString("itemName");
                            itemImage = jsonObject.getString("itemImage");
                            itemPrice = jsonObject.getInt("itemPrice");
                            itemBio = jsonObject.getString("itemBio");
                            itemReview = jsonObject.getString("itemReview");
                        } else if (Integer.parseInt(status) == 2) {
                            Log.e(TAG, "line 73: " + message);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "line 86: " + e.toString());
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "line 90: Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Glide.with(getApplicationContext()).load(itemImage).fitCenter().placeholder(R.drawable.placeholder).into(ivItemDetailImage);
        }
    }

}
