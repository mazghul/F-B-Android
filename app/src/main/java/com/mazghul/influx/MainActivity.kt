package com.mazghul.influx

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.mazghul.influx.Adapters.ViewPagerAdapter
import com.mazghul.influx.Models.FBResponse
import com.mazghul.influx.Provider.VolleyRequest
import kotlinx.android.synthetic.main.activity_main.*


var no_of_categories = -1

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val mRequestQueue = Volley.newRequestQueue(this)
        //    mProgressDialog.show();
        VolleyRequest.getAccessTokenFromPin(
            loginResponseListener, Response.ErrorListener {
                Log.d("Error ", it.message)
            }

        )
            .enqueue(mRequestQueue)
    }

    private val loginResponseListener =
        Response.Listener<FBResponse> { response ->
            Log.d("UserDetails", "onResponse:$response")
            no_of_categories = response.FoodList.size
            for (i in 0 until no_of_categories) {
                tabLayout.addTab(tabLayout.newTab().setText(response.FoodList[i].TabName))
            }
            val adapter = ViewPagerAdapter(supportFragmentManager, tabLayout.tabCount, response)
            viewPager.adapter = adapter
            tabLayout.addOnTabSelectedListener(this)

        }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewPager.setCurrentItem(tab!!.position)
    }


}
