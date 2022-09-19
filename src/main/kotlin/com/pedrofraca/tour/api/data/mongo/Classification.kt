package com.pedrofraca.tour.api.data.mongo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.PanacheMongoEntity

@MongoEntity(collection = "classification")
class Classification : PanacheMongoEntity() {
    var stage : Int = 0
    var type : String = ""
    var time: String? = null
    var country: String? = null
    var team: String? = null
    var pos: String? = null
    var rider: String? = null
}