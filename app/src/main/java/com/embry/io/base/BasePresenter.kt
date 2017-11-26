package com.embry.io.base

abstract class BasePresenter<T:Any> {

    abstract fun onStart(v: T)
     abstract fun onStop()
}