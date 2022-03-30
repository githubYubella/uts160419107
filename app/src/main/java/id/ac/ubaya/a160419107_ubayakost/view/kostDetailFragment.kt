package id.ac.ubaya.a160419107_ubayakost.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import id.ac.ubaya.a160419107_ubayakost.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.kost_list_item.*

class kostDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = 0
        arguments?.let{
            id = kostDetailFragmentArgs.fromBundle(requireArguments()).kostIdId

        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)


        observeViewModel()
//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

    }

    private fun observeViewModel(){
        viewModel.kostLD.observe(viewLifecycleOwner) {
            val kost = viewModel.kostLD.value
            kost?.let {
                detailNamaKos.setText(it.nama)
                detailHarga.setText(it.harga.toString())
                detailFasilitas.setText(it.fasilitas)
                detailJenis.setText(it.jenis)
                detailAlamat.setText(it.alamat)
                imageView2.loadImage(it.photoUrl.toString(), progressBarDetail)


            }



        }
        buttonPesan.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Androidly Alert")
            builder.show()
        }

    }



}