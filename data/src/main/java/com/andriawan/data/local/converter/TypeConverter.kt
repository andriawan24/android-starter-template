package com.andriawan.data.local.converter

import androidx.room.TypeConverter
import com.andriawan.domain.models.Genre
import com.andriawan.domain.models.Rating
import com.andriawan.domain.models.ShortScreenshot
import com.andriawan.domain.models.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun convertGenreListToString(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun convertStringToGenreList(genreString: String): List<Genre> {
        return Gson().fromJson(genreString, object : TypeToken<List<Genre>>() {}.type)
    }

    @TypeConverter
    fun convertRatingsToString(ratings: List<Rating>): String {
        return Gson().toJson(ratings)
    }

    @TypeConverter
    fun convertStringToRatings(ratingString: String): List<Rating> {
        return Gson().fromJson(ratingString, object : TypeToken<List<Rating>>() {}.type)
    }

    @TypeConverter
    fun convertTagsToString(tags: List<Tag>): String {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun convertStringToTags(tagsString: String): List<Tag> {
        return Gson().fromJson(tagsString, object : TypeToken<List<Tag>>() {}.type)
    }

    @TypeConverter
    fun convertShortScreenShotToString(shortScreenshots: List<ShortScreenshot?>?): String? {
        return Gson().toJson(shortScreenshots)
    }

    @TypeConverter
    fun convertStringToShortScreenShot(shortScreenshotsString: String?): List<ShortScreenshot?>? {
        return Gson().fromJson(
            shortScreenshotsString,
            object : TypeToken<List<ShortScreenshot>>() {}.type
        )
    }
}