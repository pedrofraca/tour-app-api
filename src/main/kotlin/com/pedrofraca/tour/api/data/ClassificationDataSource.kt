package com.pedrofraca.tour.api.data

import com.pedrofraca.tour.api.data.mongo.Classification
import io.github.pedrofraca.data.datasource.WriteDataSourceWithFilter
import io.github.pedrofraca.domain.model.StageClassificationModel
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassificationDataSource : WriteDataSourceWithFilter<StageClassificationModel, Int> {

    override fun getAll(): List<StageClassificationModel> {
        TODO("Not yet implemented")
    }

    override fun save(item: StageClassificationModel, stageId : Int) {
        val classification = Classification()
        item.general.forEach {
            classification.pos = it.pos
            classification.country = it.country
            classification.rider = it.rider
            classification.team = it.team
            classification.stage = stageId
            classification.type = "GENERAL"
            classification.persist()
        }

    }

    override fun get(param: Int): StageClassificationModel {
        TODO("Not yet implemented")
    }
}