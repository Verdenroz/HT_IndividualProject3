package com.farmingdale.ht_individualproject3.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import java.sql.Date


/**
 * Parent Table
 */
@Entity
data class Parent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val month: String,
    val day: String,
    val year: String
)

/**
 * Child Table
 */
@Entity(
    foreignKeys = arrayOf(
        ForeignKey(entity = Parent::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE)
    )
)
data class Child(
    @PrimaryKey(autoGenerate = true)
    val childId: Int = 0,
    val parentId: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val month: String,
    val day: String,
    val year: String
)

/**
 * ChildProgress table
 * Holds data on earned points by date
 */
@Entity(
    foreignKeys = arrayOf(
        ForeignKey(entity = Child::class,
            parentColumns = arrayOf("childId"),
            childColumns = arrayOf("childNumber"),
            onDelete = ForeignKey.CASCADE)
    )
)
data class ChildProgress(
    @PrimaryKey(autoGenerate = true)
    val progressId: Int = 0,
    val childNumber: Int,
    val points: Int,
    val month: String,
    val day: String,
    val year: String
)

@Dao
interface ParentDao{
    @Query("SELECT * FROM Parent WHERE userName = :userName AND password = :password")
    fun getParentByCredentials(userName: String, password: String): Parent?

    @Query("SELECT * FROM Parent WHERE id = :parentId")
    fun getParentById(parentId: Int): Parent?

    @Insert
    fun insertItem(item: Parent)
}

@Dao
interface ChildDao{
    @Query("SELECT * FROM Child WHERE userName = :userName AND password = :password")
    fun getChildByCredentials(userName: String, password: String): Child?

    @Query("SELECT * FROM Child WHERE childId = :id")
    fun getChild(id: Int): Child?

    @Query("SELECT * FROM CHILD WHERE parentId = :parentId")
    fun getChildrenByParent(parentId: Int): List<Child>?

    @Insert
    fun insertItem(item: Child)

    @Delete
    fun deleteItem(item: Child)

}

@Dao
interface ChildProgressDao{
    @Query("SELECT * FROM ChildProgress WHERE childNumber = :childId")
    fun getProgressbyChild(childId: Int): List<ChildProgress>?

    //returns latest amount of points
    @Query("SELECT points FROM ChildProgress WHERE childNumber = :childId ORDER BY points DESC LIMIT 1")
    fun getPoints(childId: Int): Int

    @Insert
    fun insertItem(progress: ChildProgress)

}

//Database
@Database(version = 1, entities = [Parent::class, Child::class, ChildProgress::class])
abstract class ParentChildDatabase : RoomDatabase(){
    abstract fun parentDao(): ParentDao
    abstract fun childDao() : ChildDao
    abstract fun childProgressDao(): ChildProgressDao
}