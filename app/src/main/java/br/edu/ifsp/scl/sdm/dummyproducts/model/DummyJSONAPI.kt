package br.edu.ifsp.scl.sdm.dummyproducts.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class DummyJSONAPI(context: Context) {
    companion object {
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

}