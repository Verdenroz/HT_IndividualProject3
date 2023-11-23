package com.farmingdale.ht_individualproject3.database

interface ParentChildRepo {

    suspend fun getParentByCredentials(username: String, password: String): Parent?

    suspend fun getChildByCredentials(username: String, password: String): Child?

}

/**
 * DB Helper mainly for login
 */
class ImplParentChildRepo(database: ParentChildDatabase): ParentChildRepo{
    val database = database

    override suspend fun getParentByCredentials(username: String, password: String): Parent? = database.parentDao().getParentByCredentials(username, password)

    override suspend fun getChildByCredentials(username: String, password: String): Child? = database.childDao().getChildByCredentials(username, password)

}