package id.ac.ubaya.a160419107_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*

class kostFavoriteAdapter(val kostFav: ArrayList<KostUbaya>): RecyclerView
.Adapter<kostFavoriteAdapter.kostFavHolder>()  {
    class kostFavHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateKostList(newKostList: List<KostUbaya>){
        kostFav.clear()
        kostFav.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kostFavHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent,false)
        return kostFavHolder(view)
    }

    override fun onBindViewHolder(holder: kostFavHolder, position: Int) {
        holder.view.textNama.text = kostFav[position].nama
        holder.view.textHarga.text =kostFav[position].harga.toString()
        holder.view.imageView.loadImage(kostFav[position].photoUrl.toString(), holder.view.progressBar)

        holder.view.buttonDetail.setOnClickListener {
            val action = favoriteFragmentDirections.actionItemFavoriteToKostDetailFragment(kostFav[position].id.toString().toInt())
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return kostFav.size
    }
}