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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.codelabs.state.ui.StateCodelabTheme

class TodoActivity : AppCompatActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()
    // Create a ViewModel the first time the system calls an activity's onCreate() method.
    // Re-created activities receive the same TodoViewModel instance created by the first activity.
    // https://developer.android.com/topic/libraries/architecture/viewmodel
    // https://www.evernote.com/shard/s223/sh/1ce8926c-87e8-44da-97c2-389396317035/847c3bc16f4ef58a8fdd307c2b7b7696
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    TodoActivityScreen(todoViewModel)
                }
            }
        }
    }
}


// This composable will be a bridge between
// the state stored in our ViewModel and the TodoScreen
@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
    // listof() is a function that returns an empty read-only list (see doc)
    // in this case an empty list of the TodoItem type (a data class)
    TodoScreen(
        items = items,  // state passed to TodoScreen
        onAddItem = { todoViewModel.addItem(it) },  // event passed to TodoScreen
        onRemoveItem = { todoViewModel.removeItem(it) } // event passed to TodoScreen
    )
    //When TodoScreen calls onAddItem or onRemoveItem,
    // we can pass the call to the correct event on our ViewModel.
}

