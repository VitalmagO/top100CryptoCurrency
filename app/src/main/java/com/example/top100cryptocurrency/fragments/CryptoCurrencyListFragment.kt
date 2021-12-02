package com.example.top100cryptocurrency.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.top100cryptocurrency.R
import com.example.top100cryptocurrency.adapters.BaseAdapter
import com.example.top100cryptocurrency.adapters.CryptoCurrencyAdapter
import com.example.top100cryptocurrency.di.App
import com.example.top100cryptocurrency.mvp.contract.CryptoCurrencyContract
import com.example.top100cryptocurrency.mvp.presenter.CryptoCurrencyPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CryptoCurrencyListFragment : BaseListFragment(), CryptoCurrencyContract.View {

    @Inject
    lateinit var presenter: CryptoCurrencyPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_cryptocurrency_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }

    override fun createAdapterInstance(): BaseAdapter<*> {
        return CryptoCurrencyAdapter()
    }

    override fun addCurrency(currency: CryptoCurrencyAdapter.Currency) {
        viewAdapter.add(currency)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}