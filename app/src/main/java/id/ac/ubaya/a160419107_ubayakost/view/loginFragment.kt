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
import id.ac.ubaya.a160419107_ubayakost.databinding.FragmentLoginBinding
import id.ac.ubaya.a160419107_ubayakost.model.PenggunaKost
import id.ac.ubaya.a160419107_ubayakost.util.preferencesHelper
import id.ac.ubaya.a160419107_ubayakost.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*

class loginFragment : Fragment(), LoginButtonListener, RegisterButtonListener {
    private lateinit var viewModel: ProfilViewModel
    private lateinit var dataBinding: FragmentLoginBinding
    private lateinit var sharedPref: preferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = preferencesHelper(requireActivity())

        dataBinding.profile = PenggunaKost("","", "", "","", "")
        dataBinding.loginListener = this
        dataBinding.registerListener = this

        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

    }

    override fun onLoginButton(v: View) {
        val username = dataBinding.root.txtUsername.text.toString()
        val password = dataBinding.root.txtPassword.text.toString()

        viewModel.validation(username, password)

        observeViewModel(username, password)
    }

    private fun observeViewModel(username: String, password: String) {
        viewModel.penggunaLD.observe(viewLifecycleOwner) {
            val data = viewModel.penggunaLD.value

            if(username != "" && password != ""){

                if(data != null){
                    sharedPref.putLogin(true)
                    sharedPref.putUsername(username)

                    val action = loginFragmentDirections.actionLoginListKostNav()
                    Navigation.findNavController(requireView()).safeNavigate(action)

                    Toast.makeText(context, "login berhasil", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(requireView().context, "username tidak ada!", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(requireView().context, "data tidak boleh kosong!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRegisterButton(v: View) {
        val action = loginFragmentDirections.actionRegisterFragment()
        Navigation.findNavController(v).navigate(action)
    }

//untuk mengecek apakah sebelumnya sudah membuka halaman atau belum agar tidak menimbulkan error
    private fun NavController.safeNavigate(action: NavDirections) {
        currentDestination?.getAction(action.actionId)?.run {
            navigate(action)
        }
    }
}

