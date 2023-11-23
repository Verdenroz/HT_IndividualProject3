package com.farmingdale.ht_individualproject3.viewmodels

import androidx.lifecycle.ViewModel
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo

/**
 * Was meant to handle login for child views
 * Things happened
 */
class ChildViewModel(repo: ImplParentChildRepo): ViewModel() {
    private val repo = repo

    fun getRepo(): ImplParentChildRepo{
        return repo
    }

}