package com.windranger.domain.usecases

import io.reactivex.rxjava3.core.*

object BaseUseCase {
    interface CompletableUseCase<in Params> : UseCase<Params, Completable>
    interface FlowableUseCase<in Params, T> : UseCase<Params, Flowable<T>>
    interface MaybeUseCase<in Params, T> : UseCase<Params, Maybe<T>>
    interface ObservableUseCase<in Params, T> : UseCase<Params, Observable<T>>
    interface SingleUseCase<in Params, T> : UseCase<Params, Single<T>>
}
