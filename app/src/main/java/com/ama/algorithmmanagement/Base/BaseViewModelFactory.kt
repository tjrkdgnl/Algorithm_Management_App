package com.ama.algorithmmanagement.Base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.InvocationTargetException

class BaseViewModelFactory(
    private val repository: BaseRepository,
    private val lifecycleOwner: LifecycleOwner? = null
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                if (lifecycleOwner != null) {
                    return modelClass.getConstructor(
                        BaseRepository::class.java,
                        LifecycleOwner::class.java
                    )
                        .newInstance(repository, lifecycleOwner)
                }

                return modelClass.getConstructor(BaseRepository::class.java)
                    .newInstance(repository)

            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
        return super.create(modelClass)
    }
}