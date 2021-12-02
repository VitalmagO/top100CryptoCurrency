package com.example.top100cryptocurrency.mvp.presenter

import com.example.top100cryptocurrency.adapters.CryptoCurrencyAdapter
import com.example.top100cryptocurrency.di.App
import com.example.top100cryptocurrency.formatThousands
import com.example.top100cryptocurrency.mvp.contract.CryptoCurrencyContract
import com.example.top100cryptocurrency.rest.CoinGeckoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptoCurrencyPresenter : CryptoCurrencyContract.Presenter() {
    //внедряем источник данных
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    //инициализируем компоненты Даггера
    init {
        App.appComponent.inject(this)
    }

    /*создаем список, загружая данные с помощью RxJava
    * отображает прогрессбар и запускает Rx-цепочку подписки на данные и обработки их в процессе*/
    override fun makeList() {
        view.showProgress()

        //подписываемся на поток данных
        subscribe(geckoApi.getCoinMarket()

            //определяем отдельный поток для отправки данных (не MainThread)
            .subscribeOn(Schedulers.io())

            //получаем данные в основном потоке (MainThread)
            .observeOn(AndroidSchedulers.mainThread())

            //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
            .flatMap { Observable.fromIterable(it) }

            //наполняем поля элемента списка для адаптера
            .doOnNext {
                view.addCurrency(
                    CryptoCurrencyAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }

            //вызывается при вызове onComplete
            .doOnComplete {
                view.hideProgress()
            }

            /*подписывает Observer на Observable
            * принимает объект CoinGeckoApi, получающий данные от сервера вызовом функции getCoinMarket(),
            * которая возврящяет Observable<List<COinGecko>>*/
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )
    }

    //обновляем список
    override fun refreshList() {
        view.refresh()
        makeList()
    }
}