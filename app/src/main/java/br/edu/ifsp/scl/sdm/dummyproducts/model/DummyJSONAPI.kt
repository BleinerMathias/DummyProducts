package br.edu.ifsp.scl.sdm.dummyproducts.model

import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.net.HttpURLConnection.HTTP_NOT_MODIFIED
import java.net.HttpURLConnection.HTTP_OK

class DummyJSONAPI(context: Context) {
    companion object {
        const val PRODUCTS_ENDPOINT = "https://dummyjson.com/products/"

        @Volatile
        private var INSTANCE: DummyJSONAPI? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE?: DummyJSONAPI(context).also{
                INSTANCE = it
            }
        }

    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext) // aplicationContext para a fila valer para todos
    }

    // Única fila de request para atender todos
    fun<T> addToRequestQueue(request:Request<T>){
        requestQueue.add(request)
    }


    // Na função abaixo iremos isolar a conversão json que tinhamos na MAIN ACTIVITY
    class ProductListRequest(
        private val responseListener:Response.Listener<ProductList>,
        errorListener: Response.ErrorListener
    ):Request<ProductList>(Method.GET, PRODUCTS_ENDPOINT,errorListener){

        // Recebe um objeto que não contém formatação (byte)
        override fun parseNetworkResponse(response: NetworkResponse?): Response<ProductList> {
            // Converter para json
            return if(response?.statusCode == HTTP_OK || response?.statusCode == HTTP_NOT_MODIFIED){
                String(response.data).run{
                    Response.success(
                        Gson().fromJson(this,ProductList::class.java),
                        HttpHeaderParser.parseCacheHeaders(response)
                    )
                }
            }else{
                Response.error(VolleyError())
            }
        }

        override fun deliverResponse(response: ProductList?) {
            responseListener.onResponse(response)
        }

    }

}