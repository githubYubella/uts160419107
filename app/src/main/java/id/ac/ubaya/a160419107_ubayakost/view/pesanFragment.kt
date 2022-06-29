package id.ac.ubaya.a160419107_ubayakost.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentKostDetailBinding
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentPesanBinding
import id.ac.ubaya.a160419107_ubayakost.model.TransaksiKost
import id.ac.ubaya.a160419107_ubayakost.util.loadImage
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.DetailViewModel
import id.ac.ubaya.a160419107_ubayakost.viewmodel.PesanViewModel
import kotlinx.android.synthetic.main.fragment_bayar.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_pesan.*
import kotlinx.android.synthetic.main.fragment_pesan.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

//setelah itu kesini

class pesanFragment : Fragment(), ButtonBayarClickListener, ButtonDateClickListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var viewModel: PesanViewModel
    private lateinit var dataBinding: FragmentPesanBinding

    var year = 0
    var month = 0
    var day = 0

    private lateinit var sharedPref: preferencesHelper

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

        sharedPref = preferencesHelper(requireActivity())

        dataBinding.transaksi = TransaksiKost(0,0,"")

        dataBinding.tanggalListener = this
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
        val c = Calendar.getInstance()
        c.set(year, month, day)

        dataBinding.transaksi!!.tanggal = (c.timeInMillis/1000L).toInt()
        dataBinding.transaksi!!.idKost = v.tag.toString().toInt()
        dataBinding.transaksi!!.username = sharedPref.getUsername()

        val list = listOf(dataBinding.transaksi!!)
        viewModel.addTransaction(list)

        val action =
            pesanFragmentDirections.actionPesanFragmentToBayarFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonDateClick(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        activity?.let {
                it -> DatePickerDialog(it, this, year, month, day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            it.set(year, month, day)
            dataBinding.root.txtPesanTanggal.setText(day.toString().padStart(2,'0') + " - " +
                    (month + 1).toString().padStart(2, '0') + " - " + year)

            this.year = year
            this.month = month
            this.day = day
        }
    }
}