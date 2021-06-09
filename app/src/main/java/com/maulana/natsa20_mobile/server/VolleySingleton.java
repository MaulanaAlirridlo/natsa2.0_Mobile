package com.maulana.natsa20_mobile.server;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static  VolleySingleton vInstance;
    private RequestQueue requestQueue;
    private static Context vContext;

    private VolleySingleton (Context context) {
        vContext = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(vContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (vInstance == null) {
            vInstance = new VolleySingleton(context);
        }

        return vInstance;
    }

    public<T> void addToRequestQue (Request<T> request) {
        getRequestQueue().add(request);
    }
}
