package com.marcosholgado.domain.useCases.currencies

import com.marcosholgado.domain.model.Currency
import com.marcosholgado.domain.repository.CurrencyRepository
import com.marcosholgado.domain.useCases.UseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject

open class GetCurrencies @Inject constructor(
    private val repository: CurrencyRepository,
    observeScheduler: Scheduler
) : UseCase<List<Currency>, Float, Void?>(observeScheduler) {

    override fun createObservable(param1: Float?, param2: Void?): Flowable<List<Currency>> {
        return repository.getCurrencies()
            .map {
                val currencies = mutableListOf<Currency>()
                it.forEach { cur ->
                    currencies.add(Currency(cur.currency, cur.rate * (param1 ?: 1f)))
                }
                currencies
            }
    }

}