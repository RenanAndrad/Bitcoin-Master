package andrade.renan.com.bitcoinmaster.base

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P

    var sOpenSansFontRegular: Typeface? = null
    var sOpenSansFontBold: Typeface? = null
    var sUbuntuFontLight: Typeface? = null
    var sUbuntuFontBold: Typeface? = null
    var sUbuntuFontRegular: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        loadFronts()
    }

    protected abstract fun instantiatePresenter(): P


    private fun loadFronts() {
        //Open Sans font
        sOpenSansFontRegular = Typeface.createFromAsset(assets, "fonts/OpenSans-Regular.ttf")
        sOpenSansFontBold = Typeface.createFromAsset(assets, "fonts/OpenSans-Bold.ttf")

        //Ubuntu font
        sUbuntuFontLight = Typeface.createFromAsset(assets, "fonts/Ubuntu-Light.ttf")
        sUbuntuFontRegular = Typeface.createFromAsset(assets, "fonts/Ubuntu-Regular.ttf")
        sUbuntuFontBold = Typeface.createFromAsset(assets, "fonts/Ubuntu-Bold.ttf")
    }

     fun Context.toast(message: CharSequence) =
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}