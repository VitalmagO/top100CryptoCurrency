package com.example.top100cryptocurrency.mvp.contract

import com.example.top100cryptocurrency.adapters.CryptoCurrencyAdapter

class CryptoCurrencyContract {
    interface View : BaseContract.View {
        fun addCurrency(currency: CryptoCurrencyAdapter.Currency)
        fun notifyAdapter()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
    }

    abstract class Presenter: BaseContract.Presenter<View>() {
        abstract fun makeList()
        abstract fun refreshList()
    }
}