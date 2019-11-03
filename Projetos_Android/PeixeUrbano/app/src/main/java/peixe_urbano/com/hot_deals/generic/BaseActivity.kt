package peixe_urbano.com.hot_deals.generic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import peixe_urbano.com.hot_deals.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
