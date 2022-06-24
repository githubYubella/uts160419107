package id.ac.ubaya.a160419107_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.KostListItemBinding
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*

class kostFavoriteAdapter(val kostFav: ArrayList<KostUbaya>): RecyclerView
.Adapter<kostFavoriteAdapter.kostFavHolder>(), ButtonDetailClickListener {
    class kostFavHolder(val view: KostListItemBinding): RecyclerView.ViewHolder(view.root)

    fun updateKostList(newKostList: List<KostUbaya>){
        kostFav.clear()
        kostFav.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kostFavHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<KostListItemBinding>(inflater, R.layout.kost_list_item, parent,false)
        return kostFavHolder(view)
    }

    override fun onBindViewHolder(holder: kostFavHolder, position: Int) {
        holder.view.kost = kostFav[position]
        holder.view.listener = this
//        holder.view.textNama.text = kostFav[position].nama
//        holder.view.textHarga.text =kostFav[position].harga.toString()
//        holder.view.imageView.loadImage(kostFav[position].photoUrl.toString(), holder.view.progressBar)
//
//        holder.view.buttonDetail.setOnClickListener {
//            val action = favoriteFragmentDirections.actionItemFavoriteToKostDetailFragment(kostFav[position].id.toString().toInt())
//            Navigation.findNavController(it).navigate(action)
//
//        }
    }

    override fun getItemCount(): Int {
        return kostFav.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = favoriteFragmentDirections.actionItemFavoriteToKostDetailFragment(v.tag.toString().toInt())
            Navigation.findNavController(v).navigate(action)
    }
}