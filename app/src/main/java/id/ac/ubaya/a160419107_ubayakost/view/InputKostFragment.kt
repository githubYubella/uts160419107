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
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentInputKostBinding
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_input_kost.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class InputKostFragment : Fragment(), ButtonAddClickListener {
    private lateinit var viewModel: ListViewModel
    private lateinit var dataBinding:FragmentInputKostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentInputKostBinding>(inflater, R.layout.fragment_input_kost, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        dataBinding.kost = KostUbaya("","", "", "","", "", "", "", 0)
        dataBinding.listener = this
    }

    override fun onButtonAddClick(v: View) {
        val nama = dataBinding.root.txtNamaKos.text.toString()
        val jenis = dataBinding.root.txtJenis.text.toString()
        val fasilitas = dataBinding.root.txtFasilitas.text.toString()
        val alamat = dataBinding.root.txtAlamat.text.toString()
        val harga = dataBinding.root.txtHarga.text.toString()
        val atas_nama = dataBinding.root.txtAtasNama.text.toString()
        val rekening = dataBinding.root.txtNoRek.text.toString()
        val url = dataBinding.root.txtUrl.text.toString()

        if(nama != "" && jenis != "" && fasilitas != "" && alamat != "" && harga != "" && atas_nama != ""
            && rekening != "" && url != ""){
            val list = listOf(dataBinding.kost!!)

            viewModel.addKost(list)

            Toast.makeText(v.context, "Data kost dibuat", Toast.LENGTH_SHORT).show()

            Navigation.findNavController(v).popBackStack()
        }
        else{
            Toast.makeText(v.context, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()

        }
    }

}