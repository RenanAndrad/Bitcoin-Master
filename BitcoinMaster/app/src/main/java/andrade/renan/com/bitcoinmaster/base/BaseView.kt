package andrade.renan.com.bitcoinmaster.base



interface BaseView {

    fun showError(error:String)
    fun upadateData()
    fun showLoading()
    fun hideLoading()
    fun isLoading():Boolean

}