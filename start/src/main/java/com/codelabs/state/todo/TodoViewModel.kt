/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    // state: todoItems
    private var _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    // LiveData holds state which is observed by the UI
    // LiveData is lifecycle aware
    // State flows down from ViewModel

//    We want to update the list of TodoItem that's why we need
//    a MutableLiveData. But we don't want to expose this mutable property
//    to the outside world as no one outside the ViewModel
//    should be able to modify this list, so it is marked private.
//    So we expose a LiveData of the list, which is immutable.
//    Since MutableLiveData extends LiveData (to see this definition,
//    you need to select MutableLiveData in the code then press ctrl B.)
//    we can directly assign _todoItems to todoItems (they're related).
//    This answer found here:
//    https://stackoverflow.com/questions/69984268/what-is-the-exact-meaning-of-viewmodel-syntax-about-kotlin-syntax-jetpack-com

    // addItem is an event we're defining that the UI can invoke
    // (events flow up from UI)
    // event: addItem
    fun addItem(item: TodoItem) {
        _todoItems.value = _todoItems.value!! + listOf(item)
    }
    // listOf(item) returns an immutable list, in this case
    // made up the single element named item.

    // don't confuse with listOf<TodoItem>() - see
    // which creates a read-only empty list of
    // the specified class, in this case TodoItem.


    // event: removeItem
    fun removeItem(item: TodoItem) {
        _todoItems.value = _todoItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }
}
