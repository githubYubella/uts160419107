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
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostListBinding
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ListViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_kost_list.view.*

class kostListFragment : Fragment(), RefreshClickListener, FabButtonClickListener{
    private lateinit var viewModel: ListViewModel
    private val kostListAdapter = kostListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentKostListBinding

    private lateinit var sharedPref: preferencesHelper
    private lateinit var myViewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentKostListBinding>(inflater,R.layout.fragment_kost_list, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        sharedPref = preferencesHelper(requireActivity())

        dataBinding.recyclerViewFav.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewFav.adapter = kostListAdapter

        dataBinding.refreshListener = this
        dataBinding.fabListener = this

        myViewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        myViewModel.peran(sharedPref.getUsername())

        observeViewModel()
        observeViewModelUser()

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

    private fun observeViewModelUser(){
        myViewModel.penggunaLD.observe(viewLifecycleOwner){

            val role = myViewModel.penggunaLD.value

            if(role!!.peran == "Penyewa"){
                dataBinding.root.fabAdd.visibility = View.GONE

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

    override fun onFabClick(v: View) {
        val action = kostListFragmentDirections.actionInputKost()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onStart() {
        super.onStart()

        if(!sharedPref.getBoolean()){
            val action = kostListFragmentDirections.actionListLogin()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

}