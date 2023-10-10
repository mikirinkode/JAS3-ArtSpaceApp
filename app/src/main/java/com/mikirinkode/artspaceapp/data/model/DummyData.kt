package com.mikirinkode.artspaceapp.data.model

import com.mikirinkode.artspaceapp.R

object DummyData {
    fun getArtList() = listOf<Art>(
        Art(
            title = "Sensei of Ancient Martial Arts",
            artist = "Wafa",
            releaseYear = 2020,
            imageId = R.drawable.martial_arts,
        ),
        Art(
            title = "A Lonely Wolf",
            artist = "Wafa",
            releaseYear = 2019,
            imageId = R.drawable.lonelywolf,
        ),
        Art(
            title = "The Man in Black",
            artist = "Wafa",
            releaseYear = 2021,
            imageId = R.drawable.man_in_black,
        ),
        Art(
            title = "Hanya Kita Berdua",
            artist = "Wafa",
            releaseYear = 2018,
            imageId = R.drawable.love_in_sunset,
        ),
        Art(
            title = "From Great Power Comes Great Responsibility",
            artist = "Wafa",
            releaseYear = 2020,
            imageId = R.drawable.figth_the_inner_power,
        ),
        Art(
            title = "Memories that we create",
            artist = "Wafa",
            releaseYear = 2018,
            imageId = R.drawable.memories_that_we_create,
        ),
    )

    fun getArtByIndex(index: Int) = getArtList()[index]

    fun getArtSize() = getArtList().size
}