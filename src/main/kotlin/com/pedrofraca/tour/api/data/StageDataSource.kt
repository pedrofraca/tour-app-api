package com.pedrofraca.tour.api.data

import com.pedrofraca.tour.api.data.mongo.StageRepository
import com.pedrofraca.tour.api.data.mongo.StageMongo
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.Stage

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class StageDataSource : WriteDataSource<Stage> {
    @Inject
    lateinit var stageRepository: StageRepository

    override fun getAll(): List<Stage> {
        return stageRepository.findAll().list<StageMongo>().map {
            Stage(it.name,
                it.stage,
                it.winner,
                it.leader,
                it.images,
                it.description,
                it.km,
                it.imgUrl,
                it.profileImgUrl,
                it.date,
                it.averageSpeed,
                it.startFinish)
        }
    }

    override fun save(item: Stage): Boolean {
        stageRepository.delete("stage = ?1", item.stage)
        val stage = StageMongo()
        stage.name = item.name
        stage.stage = item.stage
        stage.winner = item.winner
        stage.images = item.images
        stage.description = item.description
        stage.km = item.km
        stage.imgUrl = item.imgUrl
        stage.km = item.km
        stage.date = item.date
        stage.averageSpeed = item.averageSpeed
        stage.startFinish = item.startFinish
        stage.persist()
        return true
    }
}