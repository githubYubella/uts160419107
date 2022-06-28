package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostDetailBinding
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentPesanBinding
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import id.ac.ubaya.a160419107_ubayakost.viewmodel.DetailViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.PesanViewModel
import kotlinx.android.synthetic.main.fragment_bayar.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_pesan.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

//setelah itu kesini

class pesanFragment : Fragment(), ButtonBayarClickListener {
    private lateinit var viewModel: PesanViewModel
    private lateinit var dataBinding: FragmentPesanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentPesanBinding>(
            inflater,
            R.layout.fragment_pesan,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = 0
        arguments?.let {
            id = pesanFragmentArgs.fromBundle(requireArguments()).kostIdPesan

        }
        viewModel = ViewModelProvider(this).get(PesanViewModel::class.java)
        viewModel.fetchPesan(id)

        dataBinding.pesanlistener = this
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.kostLDPesan.observe(viewLifecycleOwner) {
            dataBinding.kost = it
//            val kost = viewModel.kostLDPesan.value
//            kost?.let {
//                txtHarga.setText(it.harga)
//                txtPesanNamaKos.setText(it.nama)
//                detailIdPesan.setText(it.id.toString())

        }
    }

    override fun onButtonBayarClickListener(v: View) {
        val action =
            pesanFragmentDirections.actionPesanFragmentToBayarFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}