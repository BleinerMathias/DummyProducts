package br.edu.ifsp.scl.sdm.dummyproducts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.dummyproducts.model.Product
import br.edu.ifsp.scl.sdm.dummyproducts.model.ProductList

class ProductAdapter(
    private val activityContext: Context,
    private val productList:MutableList<Product>
):ArrayAdapter<Product>(activityContext, android.R.layout.simple_list_item_1, productList) {

    // Método getview para mostrar
    private data class ProductViewHolder(val productText:TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val productView = convertView?: LayoutInflater.from(activityContext)
            .inflate(android.R.layout.simple_list_item_1, parent, false).apply {
                tag = ProductViewHolder(findViewById(android.R.id.text1))
            } // se não for nulo, vai para product view e não inflo/

        // Posso alterar os dados do product view
        (productView.tag as ProductViewHolder).productText.text = productList[position].title

        return productView
    }

}