package id.ac.ubaya.a160419107_ubayakost.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostDetailBinding
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.DetailViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_kost_detail.view.*
import kotlinx.android.synthetic.main.fragment_kost_list.view.*
import kotlinx.android.synthetic.main.fragment_pesan.*
import kotlinx.android.synthetic.main.kost_list_item.*
import kotlinx.android.synthetic.main.kost_list_item.view.*

class kostDetailFragment : Fragment(),ButtonPesanClickListener, FavoriteButtonListener, SaveChangesListener, UpdateButtonListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentKostDetailBinding

    private lateinit var sharedPref: preferencesHelper
    private lateinit var myViewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentKostDetailBinding>(inflater,R.layout.fragment_kost_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = 0
        arguments?.let{
            id = kostDetailFragmentArgs.fromBundle(requireArguments()).kostIdId

        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)

        sharedPref = preferencesHelper(requireActivity())

        dataBinding.pesanlistener = this
        dataBinding.imageListener = this
        dataBinding.updateListener = this
        dataBinding.saveListener = this

        myViewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        myViewModel.peran(sharedPref.getUsername())

        observeViewModel()
        observeViewModelUser()
//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

    }

    private fun observeViewModel(){
        viewModel.kostLD.observe(viewLifecycleOwner) {
            dataBinding.kost = it
//            val kost = viewModel.kostLD.value
//            kost?.let {
//                detailNamaKos.setText(it.nama)
//                detailHarga.setText(it.harga.toString())
//                detailFasilitas.setText(it.fasilitas)
//                detailJenis.setText(it.jenis)
//                detailAlamat.setText(it.alamat)
//                detailId.setText(it.id.toString())
//                imageView2.loadImage(it.photoUrl.toString(), progressBarDetail)
//
//
//            }



        }

//        buttonPesan.setOnClickListener {
////            val builder = AlertDialog.Builder(context)
////            builder.setTitle("Androidly Alert")
////            builder.show()
//
//
//                val namaKos= detailNamaKos.text.toString()
//                val harga = detailHarga.text.toString()
//                val id = detailId.text.toString()
//                val action = kostDetailFragmentDirections.actionKostDetailFragmentToPesanFragment(id.toInt())
//                Navigation.findNavController(it).navigate(action)
//
//
//        }

    }

    private fun observeViewModelUser(){
        myViewModel.penggunaLD.observe(viewLifecycleOwner){

            val role = myViewModel.penggunaLD.value

            if(role!!.peran == "Pemilik"){
                dataBinding.root.buttonPesan.visibility = View.GONE
//                dataBinding.root.buttonFavorite.visibility = View.GONE
                dataBinding.root.toggleFavorite.visibility = View.GONE
            }
            else{
                dataBinding.root.buttonEdit.visibility = View.GONE
            }

        }

    }

    override fun onButtonPesanClick(v: View) {
        val action = kostDetailFragmentDirections.actionKostDetailFragmentToPesanFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onUpdateButton(v: View) {
        v.visibility = View.GONE
        dataBinding.root.buttonSave.visibility = View.VISIBLE
        dataBinding.root.detailUrl.visibility = View.VISIBLE

        dataBinding.root.detailUrl.isEnabled = true
        dataBinding.root.detailNamaKos.isEnabled = true
        dataBinding.root.detailAlamat.isEnabled = true
        dataBinding.root.detailFasilitas.isEnabled = true
        dataBinding.root.detailHarga.isEnabled = true
        dataBinding.root.detailJenis.isEnabled = true
    }

    override fun onSaveButton(v: View, obj: KostUbaya) {
        viewModel.update(obj.nama!!, obj.jenis!!, obj.fasilitas!!, obj.alamat!!, obj.harga!!,
            obj.photoUrl!!, obj.rekening!!, obj.atas_nama, obj.id)

        Toast.makeText(v.context, "Data kost diperbarui", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onFavoriteImgClick(v: View) {
        viewModel.updateFav(dataBinding.kost!!)
    }


}