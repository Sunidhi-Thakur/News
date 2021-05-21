package com.example.news

object MyColorsList {

    private val myColors = arrayOf(
        "#ABDEE6", "#CBAACB", "#FFFFB5", "#FFCCB6", "#F3B0C3", "#97C1A9", "#FEE1E8",
        "#FFC8A2", "#D4F0F0"
    )

    private var colorIndex1 = 1
    private var colorIndex2 = 1
    private var colorIndex3 = 1
    fun getColor1(): String {
        return myColors[colorIndex1++ % myColors.size]
    }

    fun getColor2(): String {
        return myColors[colorIndex2++ % myColors.size]
    }

    fun getColor3(): String {
        return myColors[colorIndex3++ % myColors.size]
    }
}