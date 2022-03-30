package id.ac.ubaya.a160419107_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.fragment_kost_list.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*

class kostListAdapter(val kostList: ArrayList<KostUbaya>): RecyclerView
.Adapter<kostListAdapter.kostViewHolder>() {
    class kostViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateKostList(newKostList: List<KostUbaya>){
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent,false)
        return kostViewHolder(view)
    }

    override fun onBindViewHolder(holder: kostViewHolder, position: Int) {
//        val kost = kostList[position]
//        with(holder.view){
        holder.view.textNama.text = kostList[position].nama
        holder.view.textHarga.text =kostList[position].harga.toString()
        holder.view.imageView.loadImage(kostList[position].photoUrl.toString(), holder.view.progressBar)

        holder.view.buttonDetail.setOnClickListener {
                val action = kostListFragmentDirections.actionItemListKostToKostDetailFragment(kostList[position].id.toString().toInt())
                Navigation.findNavController(it).navigate(action)

            }
//        holder.view.imageView.loadImage(kostList[position].photoUrl.toString(), holder.view.progressBar)

        }

    override fun getItemCount():Int{
        return  kostList.size
    }
}




