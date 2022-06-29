package id.ac.ubaya.a160419107_ubayakost.view

import android.view.View
import id.ac.ubaya.a160419107_ubayakost.model.KostUbaya
import id.ac.ubaya.a160419107_ubayakost.model.PenggunaKost

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonPesanClickListener{
    fun onButtonPesanClick(v: View)
}

interface RefreshClickListener{
    fun onRefreshClick(v: View)
}

interface ButtonBayarClickListener{

    fun onButtonBayarClickListener(v: View)
}

interface LoginButtonListener{
    fun onLoginButton(v:View)
}

interface RegisterButtonListener{
    fun onRegisterButton(v:View)
}

interface ItemClickListener{
    fun onItemClick(v:View, obj: PenggunaKost)
}

interface UpdateButtonListener{
    fun onUpdateButton(v:View)
}

interface SaveChangesListener{
    fun onSaveButton(v:View, obj: KostUbaya)
}

interface FavoriteButtonListener{
    fun onFavoriteImgClick(v:View)
}

interface FabButtonClickListener{
    fun onFabClick(v: View)
}

interface ButtonAddClickListener{
    fun onButtonAddClick(v: View)
}

interface ButtonDateClickListener{
    fun onButtonDateClick(v: View)
}

