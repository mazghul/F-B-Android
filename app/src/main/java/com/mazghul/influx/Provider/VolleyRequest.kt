package com.mazghul.influx.Provider

import android.util.Log
import com.android.volley.*

import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonRequest
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mazghul.influx.Models.FBResponse

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class VolleyRequest<T>
    (
    httpMethod: Int, url: String, requestBody: Any?, listener: Response.Listener<T>,
    errorListener: Response.ErrorListener
) : JsonRequest<T>(httpMethod, url, GSON.toJson(requestBody), listener, errorListener) {
    private var responseClass: Class<T>? = null


    constructor(
        url: String,
        listener: Response.Listener<T>,
        errorListener: Response.ErrorListener
    ) : this(Method.GET, url, listener, errorListener) {
    }

    constructor(
        httpMethod: Int, url: String, listener: Response.Listener<T>,
        errorListener: Response.ErrorListener
    ) : this(httpMethod, url, null, listener, errorListener) {
    }

    constructor(
        httpMethod: Int,
        url: String,
        requestBody: Any,
        responseClass: Class<T>,
        listener: Response.Listener<T>,
        errorListener: Response.ErrorListener
    ) : this(httpMethod, url, requestBody, listener, errorListener) {
        this.responseClass = responseClass
    }

    init {
        retryPolicy = DefaultRetryPolicy(INITIAL_TIMEOUT_MS, MAX_NUM_RETRIES, BACKOFF_MULTIPLIER)
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Method:$httpMethod URL=$url")
        }
        setShouldCache(false) // do not cache any request.
    }


    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        try {
            val json = String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response.headers)))
            val jsonResponse = GSON.fromJson(json, responseClass!!)
            return Response.success(
                jsonResponse, HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: UnsupportedEncodingException) {
            return Response.error(ParseError(e))
        } catch (e: JsonSyntaxException) {
            return Response.error(ParseError(e))
        }

    }

    fun setTag(tag: String): VolleyRequest<T> {
        super.setTag(tag)
        return this
    }

    fun setClass(c: Class<T>): VolleyRequest<T> {
        responseClass = c
        return this
    }

    fun enqueue(requestQueue: RequestQueue) {
        requestQueue.cancelAll("Voley")
        requestQueue.add(this)
    }

    companion object {
        protected val TAG = VolleyRequest::class.java.simpleName
        private val GSON = Gson()
        private val MAX_NUM_RETRIES = 3
        private val INITIAL_TIMEOUT_MS = 5000
        private val BACKOFF_MULTIPLIER = 1.0f
        //
        private val HOST_NAME = "https://api.tentkotta.com/tkapi/"
        //private static final String HOST_NAME = "http://tkdevmachine.cloudapp.net:5000/tkapi/";
        private val URL_PREFIX = HOST_NAME + "v1/"
        private val PIN_ACCESS_TOKEN_URL = "http://www.mocky.io/v2/5b700cff2e00005c009365cf"



        fun getAccessTokenFromPin(
            listener: Response.Listener<FBResponse>,
            errorListener: Response.ErrorListener
        ): VolleyRequest<*> {
            return VolleyRequest(
                Method.GET,
                String.format(PIN_ACCESS_TOKEN_URL),
                listener,
                errorListener
            )
                .setClass(FBResponse::class.java)
        }
    }

    /* public static T getGsonFromJson(JsonObject jsonObject){
        T jsonResponse = GSON.fromJson(jsonObject, responseClass);
        return jsonResponse;
    }*/
}
