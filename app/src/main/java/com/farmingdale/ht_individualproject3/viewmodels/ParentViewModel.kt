package com.farmingdale.ht_individualproject3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmingdale.ht_individualproject3.database.Child
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo
import com.farmingdale.ht_individualproject3.database.Parent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * For ParentRegistration and Login views
 */
class ParentViewModel(repo: ImplParentChildRepo): ViewModel() {
    private val repo = repo

    fun createParentAccount(account: Parent){
        viewModelScope.launch(Dispatchers.IO) {
            repo.database.parentDao().insertItem(account)
        }
    }

    fun getRepo(): ImplParentChildRepo{
        return repo
    }

}