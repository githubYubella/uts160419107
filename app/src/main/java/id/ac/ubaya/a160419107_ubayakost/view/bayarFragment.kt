package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.viewmodel.BayarViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.PesanViewModel
import kotlinx.android.synthetic.main.fragment_bayar.*
import kotlinx.android.synthetic.main.fragment_pesan.*


class bayarFragment : Fragment() {
    private lateinit var viewModel: BayarViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bayar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = 0
        arguments?.let{
            id = bayarFragmentArgs.fromBundle(requireArguments()).kostIdBayar

        }
        viewModel = ViewModelProvider(this).get(BayarViewModel::class.java)
        viewModel.fetchbayar(id)


        observeViewModel()


        btnDone.setOnClickListener {
            val action = bayarFragmentDirections.actionBayarFragmentToKostListFragment2()
            Navigation.findNavController(it).navigate(action)
        }



        }


    private fun observeViewModel() {
        viewModel.bayarPesanLD.observe(viewLifecycleOwner) {
            val kost = viewModel.bayarPesanLD.value
            kost?.let {

                textAtasNama.setText(it.atas_nama)
                textRekening.setText(it.rekening)


            }
        }

//        btnBayar.setOnClickListener {
//            val id = detailIdBayar.text.toString()
//            val action = pesanFragmentDirections.actionPesanFragmentToBayarFragment(id.toInt())
//            Navigation.findNavController(it).navigate(action)
//
//
//        }


    }
    }




