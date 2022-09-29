package com.pedrofraca.tour.api.data.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.PanacheMongoEntity

@MongoEntity(collection = "stage")
class StageMongo : PanacheMongoEntity() {
    var name: String = ""
    var stage: Int = 0
    var winner: String? = null
    val leader: String? = null
    var images: List<String>? = null
    var description: String? = null
    var km: String? = null
    var imgUrl: String? = null
    val profileImgUrl: String? = null
    var date: String? = null
    var averageSpeed: String? = null
    var startFinish: String? = null
}