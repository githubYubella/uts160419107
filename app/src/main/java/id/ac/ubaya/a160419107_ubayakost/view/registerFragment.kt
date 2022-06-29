package id.ac.ubaya.a160419107_ubayakost.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import id.ac.ubaya.a160419107_ubayakost.R
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentRegisterBinding
import id.ac.ubaya.a160419107_ubayakost.model.PenggunaKost
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_register.view.*

class registerFragment : Fragment(), ItemClickListener, RegisterButtonListener {
    private lateinit var viewModel: ProfilViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        dataBinding.profile = PenggunaKost("","","","","","")
        dataBinding.itemListener = this
        dataBinding.registerListener= this
    }

    override fun onRegisterButton(v: View) {
        val nama = dataBinding.root.txtNewNama.text.toString()
        val email = dataBinding.root.txtNewEmail.text.toString()
        val pass = dataBinding.root.txtNewPassword.text.toString()
        val telepon = dataBinding.root.txtNewTelepon.text.toString()
        val username = dataBinding.root.txtNewUsername.text.toString()

        viewModel.validation(username, pass)

        observeViewModel(username, pass, nama, email, telepon)
    }

    private fun observeViewModel(username: String, pass: String, nama: String,
                                 email: String, telepon: String)
    {
        viewModel.penggunaLD.observe(viewLifecycleOwner) {
            val data = viewModel.penggunaLD.value

            if(username != "" && pass != "" && nama != "" && email != "" && telepon != ""){

                if(data == null){
                    val list = listOf(dataBinding.profile!!)

                    viewModel.addUser(list)

                    val action = registerFragmentDirections.actionRegisterLoginFragment()
                    Navigation.findNavController(requireView()).safeNavigate(action)

                    Toast.makeText(context, "pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireView().context, "username sudah ada!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(requireView().context, "data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(v: View, obj: PenggunaKost) {
        obj.peran = v.tag.toString()
    }

    private fun NavController.safeNavigate(action: NavDirections) {
        currentDestination?.getAction(action.actionId)?.run {
            navigate(action)
        }
    }
}