package com.pedrofraca.tour.api.data.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.PanacheMongoEntity

enum class ClassificationType(val value : String) {
    MOUNTAIN("mountain"),
    STAGE("stage"),
    REGULARITY("regularity"),
    GENERAL("general"),
    TEAM("team"),
    NONE("")
}
@MongoEntity(collection = "classification")
class ClassificationMongo : PanacheMongoEntity() {
    var stage : Int = 0
    var type : String = ""
    var time: String? = null
    var country: String? = null
    var team: String? = null
    var pos: String? = null
    var rider: String? = null
}