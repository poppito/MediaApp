package com.embry.io.base

open abstract class BasePresenter<T:Any> {

    abstract fun onStart(v: T)
     abstract fun onStop()
}