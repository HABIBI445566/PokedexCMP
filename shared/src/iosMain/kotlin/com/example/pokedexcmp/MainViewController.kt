package com.example.pokedexcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.pokedexcmp.core.di.initKoin
import platform.Foundation.NSLog
import kotlin.experimental.ExperimentalNativeApi

fun MainViewController(): platform.UIKit.UIViewController {
    NSLog("!!!!! MainViewController() called - console pipe check")
    return ComposeUIViewController {
        App()
    }
}

@OptIn(ExperimentalNativeApi::class)
fun doInitKoin() {
    setUnhandledExceptionHook { throwable ->
        NSLog("!!!!! UNCAUGHT KOTLIN EXCEPTION: ${throwable::class.simpleName}: ${throwable.message}")
        NSLog("!!!!! STACK:\n${throwable.stackTraceToString()}")
    }
    initKoin()
}