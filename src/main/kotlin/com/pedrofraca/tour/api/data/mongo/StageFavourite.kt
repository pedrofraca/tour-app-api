package com.pedrofraca.tour.api.data.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.PanacheMongoEntity

@MongoEntity(collection = "stage_favourite")
class StageFavourite : PanacheMongoEntity() {
    var stage: Int = 0
    var username: String = ""
    var favourite : Boolean = false
}