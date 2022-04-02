package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.viewmodel.FavoriteViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*


class favoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private val kostListAdapter = kostFavoriteAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.refresh()

        recyclerViewFav.layoutManager = LinearLayoutManager(context)
        recyclerViewFav.adapter = kostListAdapter

        observeViewModel()

        refreshLayoutFav.setOnClickListener {
            recyclerViewFav.visibility = View.GONE
            textErrorFav.visibility =View.GONE
            progressLoadFav.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFav.isRefreshing = false
        }
    }

    private fun observeViewModel(){
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner){
            textErrorFav.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                recyclerViewFav.visibility = View.GONE
                progressLoadFav.visibility = View.VISIBLE

            }
            else{
                recyclerViewFav.visibility = View.VISIBLE
                progressLoadFav.visibility = View.GONE
            }
        }
    }


}