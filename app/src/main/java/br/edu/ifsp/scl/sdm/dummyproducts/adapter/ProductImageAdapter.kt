package br.edu.ifsp.scl.sdm.dummyproducts.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.dummyproducts.databinding.TileProductImageBinding

class ProductImageAdapter(
    val activityContext:Context,
    var productImageList:MutableList<Bitmap>
):RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {

    // Criar o view holder para o adapter
    inner class ProductImageViewHolder(tileProductImageBinding: TileProductImageBinding): RecyclerView.ViewHolder(
        tileProductImageBinding.productIv
    ){
        val productImageView:ImageView = tileProductImageBinding.productIv
    }


    // Implementações mínimas necessárias para um Recyvler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder =
        ProductImageViewHolder(TileProductImageBinding.inflate(
            LayoutInflater.from(activityContext), parent, false))

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        holder.productImageView.setImageBitmap(productImageList[position])
    }

    override fun getItemCount():Int = productImageList.size

}