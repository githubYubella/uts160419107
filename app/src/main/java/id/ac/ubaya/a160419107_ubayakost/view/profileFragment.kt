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
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_profile.*


class profileFragment : Fragment() {
    private lateinit var viewModel: ProfilViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var email = ""
//        arguments?.let{
//            email =
//
//        }
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.fetchProfil("s160419107@student.ubaya.ac.id")


        observeViewModel()


    }

    private fun observeViewModel(){
        viewModel.profilLD.observe(viewLifecycleOwner) {
            val kost = viewModel.profilLD.value
            kost?.let {
                txtProfilNama.setText(it.name)
                txtEmail.setText(it.email)
                txtTanggalLahir.setText(it.tanggal_lahir)
                photoProfil.loadImage(it.photo.toString(), progressBarProfil)


            }



        }


    }


}