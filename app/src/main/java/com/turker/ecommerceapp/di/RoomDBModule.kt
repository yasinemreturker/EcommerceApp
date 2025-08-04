package com.turker.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.turker.ecommerceapp.data.datasource.local.ProductRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ProductRoomDB::class.java,
            "products_room_db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()


    @Provides
    @Singleton
    fun provideProductDao(roomDB: ProductRoomDB) = roomDB.productDao()

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE favproducts ADD COLUMN discount REAL DEFAULT 0.0 NOT NULL")
        }
    }

}