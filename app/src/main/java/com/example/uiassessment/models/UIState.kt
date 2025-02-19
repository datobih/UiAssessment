package com.example.lostandfound.utils


sealed class UIState<out T>{

    class SuccessState <out R>(val data:R):UIState<R>()
    class ErrorState<J>(val data:String):UIState<J>()
    class LoadingState:UIState<Nothing>()
    class InitialState:UIState<Nothing>()

}
