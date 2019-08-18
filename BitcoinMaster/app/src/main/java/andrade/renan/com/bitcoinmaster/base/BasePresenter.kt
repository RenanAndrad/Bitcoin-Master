package andrade.renan.com.bitcoinmaster.base

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    open fun onViewCreated(){}

    open fun onViewDestroyed(){}

}