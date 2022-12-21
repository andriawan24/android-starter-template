package com.andriawan.data.local.converter

import androidx.room.TypeConverter
import com.andriawan.domain.models.GenreModel
import com.andriawan.domain.models.RatingModel
import com.andriawan.domain.models.ShortScreenshotModel
import com.andriawan.domain.models.TagModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun convertGenreListToString(genres: List<GenreModel>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun convertStringToGenreList(genreString: String): List<GenreModel> {
        return Gson().fromJson(genreString, object : TypeToken<List<GenreModel>>() {}.type)
    }

    @TypeConverter
    fun convertRatingsToString(ratings: List<RatingModel>): String {
        return Gson().toJson(ratings)
    }

    @TypeConverter
    fun convertStringToRatings(ratingString: String): List<RatingModel> {
        return Gson().fromJson(ratingString, object : TypeToken<List<RatingModel>>() {}.type)
    }

    @TypeConverter
    fun convertTagsToString(tags: List<TagModel>): String {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun convertStringToTags(tagsString: String): List<TagModel> {
        return Gson().fromJson(tagsString, object : TypeToken<List<TagModel>>() {}.type)
    }

    @TypeConverter
    fun convertShortScreenShotToString(shortScreenshots: List<ShortScreenshotModel?>?): String? {
        return Gson().toJson(shortScreenshots)
    }

    @TypeConverter
    fun convertStringToShortScreenShot(shortScreenshotsString: String?): List<ShortScreenshotModel?>? {
        return Gson().fromJson(
            shortScreenshotsString,
            object : TypeToken<List<ShortScreenshotModel>>() {}.type
        )
    }
}