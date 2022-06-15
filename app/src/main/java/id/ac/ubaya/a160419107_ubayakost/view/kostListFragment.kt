package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostListBinding
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_kost_list.view.*

class kostListFragment : Fragment(), RefreshClickListener{
    private lateinit var viewModel: ListViewModel
    private val kostListAdapter = kostListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentKostListBinding

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

        dataBinding!!.root.recyclerViewFav.layoutManager = LinearLayoutManager(context)
        dataBinding!!.root.recyclerViewFav.adapter = kostListAdapter

        dataBinding.refreshListener = this

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
            dataBinding!!.root.textErrorFav.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                dataBinding!!.root.recyclerViewFav.visibility = View.GONE
                dataBinding!!.root.progressLoadFav.visibility = View.VISIBLE

            }
            else{
                dataBinding!!.root.recyclerViewFav.visibility = View.VISIBLE
                dataBinding!!.root.progressLoadFav.visibility = View.GONE
            }
        }
    }

    override fun onRefreshClick(v: View) {
        dataBinding!!.root.recyclerViewFav.visibility = View.GONE
        dataBinding!!.root.textErrorFav.visibility =View.GONE
        dataBinding!!.root.progressLoadFav.visibility = View.VISIBLE
        viewModel.refresh()
        v.refreshLayoutFav.isRefreshing = false
    }


}