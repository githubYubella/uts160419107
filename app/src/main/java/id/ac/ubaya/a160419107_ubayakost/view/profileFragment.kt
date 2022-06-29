package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentProfileBinding
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel


class profileFragment : Fragment(){
    private lateinit var viewModel: ProfilViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        var id = 0
//        arguments?.let{
//            id = profileFragmentArgs.fromBundle(requireParentFragment()).kostIdId
//
//        }
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        viewModel.fetchProfil("s160419107@student.ubaya.ac.id")

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.profilLD.observe(viewLifecycleOwner) {
            dataBinding.user = it
//            val kost = viewModel.profilLD.value
//            kost?.let {
//                txtProfilNama.setText(it.name)
//                txtEmail.setText(it.email)
//                txtTanggalLahir.setText(it.tanggal_lahir)
//                photoProfil.loadImage(it.photo.toString(), progressBarProfil)


        }

    }


}
