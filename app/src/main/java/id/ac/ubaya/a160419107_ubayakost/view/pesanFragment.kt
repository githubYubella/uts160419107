package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import id.ac.ubaya.a160419107_ubayakost.viewmodel.DetailViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.PesanViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_pesan.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class pesanFragment : Fragment() {
    private lateinit var viewModel: PesanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = 0
        arguments?.let{
            id = pesanFragmentArgs.fromBundle(requireArguments()).kostIdPesan

        }
        viewModel = ViewModelProvider(this).get(PesanViewModel::class.java)
        viewModel.fetchPesan(id)


        observeViewModel()
//        arguments?.let {
//            val namaKos= kostDetailFragmentArgs.fromBundle(requireArguments()).namaKos
//            val harga = kostDetailFragmentArgs.fromBundle(requireArguments()).harga
//            val id =  kostDetailFragmentArgs.fromBundle(requireArguments()).kostIdId
//            txtId.text=id.toString()
//            txtHarga.text =namaKos
//            txtHarga.text=harga
//        }
    }

    private fun observeViewModel() {
        viewModel.kostLDPesan.observe(viewLifecycleOwner) {
            val kost = viewModel.kostLDPesan.value
            kost?.let {
                txtHarga.setText(it.harga)
                txtPesanNamaKos.setText(it.nama)


            }
        }

        btnBayar.setOnClickListener {
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("Androidly Alert")
//            builder.show()
            val action = pesanFragmentDirections.actionPesanFragmentToBayarFragment()
            Navigation.findNavController(it).navigate(action)


        }


    }
}