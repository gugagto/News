package com.example.appnews.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appnews.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article:Article):Long


    @Query("SELECT * FROM articles")
 fun getAll():LiveData<List<Article>>


    @Delete
    suspend fun delete(article: Article)

}