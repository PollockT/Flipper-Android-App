package com.flipperdevices.core.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class TaskWithLifecycle : LifecycleOwner {
    private val registry by lazy { LifecycleRegistry(this) }

    suspend fun onStart() = withContext(Dispatchers.Main) {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle {
        return registry
    }

    suspend fun onStop() = withContext(Dispatchers.Main) {
        if (registry.currentState == Lifecycle.State.DESTROYED) {
            return@withContext
        }
        registry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}
