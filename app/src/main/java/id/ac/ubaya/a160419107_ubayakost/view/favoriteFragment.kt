package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentFavoriteBinding
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostListBinding
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.FavoriteViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_kost_list.view.*


class favoriteFragment : Fragment(), RefreshClickListener {

    private lateinit var viewModel: FavoriteViewModel
    private val kostListAdapter = kostFavoriteAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentFavoriteBinding

    private lateinit var sph: preferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentFavoriteBinding>(inflater, R.layout.fragment_favorite, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.refresh()

        sph = preferencesHelper(requireActivity())

        dataBinding.recyclerViewFav.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewFav.adapter = kostListAdapter

        observeViewModel()

//        refreshLayoutFav.setOnClickListener {
//            recyclerViewFav.visibility = View.GONE
//            textErrorFav.visibility =View.GONE
//            progressLoadFav.visibility = View.VISIBLE
//            viewModel.refresh()
//            refreshLayoutFav.isRefreshing = false
//        }
    }

    private fun observeViewModel(){
        viewModel.kostLiveData.observe(viewLifecycleOwner){
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner){
            dataBinding.textErrorFav.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                dataBinding.recyclerViewFav.visibility = View.GONE
                dataBinding.progressLoadFav.visibility = View.VISIBLE

            }
            else{
                dataBinding.recyclerViewFav.visibility = View.VISIBLE
                dataBinding.progressLoadFav.visibility = View.GONE
            }
        }
    }

    override fun onRefreshClick(v: View) {
        dataBinding.recyclerViewFav.visibility = View.GONE
        dataBinding.textErrorFav.visibility =View.GONE
        dataBinding.progressLoadFav.visibility = View.VISIBLE
        viewModel.refresh()
        v.refreshLayoutFav.isRefreshing = false
    }

    override fun onStart() {
        super.onStart()

        if(!sph.getBoolean()){
            val action = favoriteFragmentDirections.actionFavoriteLogin()
            Navigation.findNavController(requireView()).navigate(action)

        }

    }
}