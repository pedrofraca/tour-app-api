package com.pedrofraca.tour.api.data

import com.pedrofraca.tour.api.data.mongo.StageFavourite
import com.pedrofraca.tour.api.data.mongo.StageFavouriteRepository
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.usecase.favourite.repository.SetStageAsFavoriteParam
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class StageAsFavoriteWriteDataSource : WriteDataSource<SetStageAsFavoriteParam> {
    @Inject
    lateinit var repository: StageFavouriteRepository

    override fun save(item: SetStageAsFavoriteParam): Boolean {
        item.apply {
            val stageFavourite = StageFavourite()
            stageFavourite.stage = this.stageId
            stageFavourite.username = this.username
            stageFavourite.favourite = this.favouriteState
            stageFavourite.persist()
        }
        return true
    }

    override fun getAll(): List<SetStageAsFavoriteParam> {
        return repository.findAll().list<StageFavourite?>()
            .map { SetStageAsFavoriteParam(it.username, it.stage, it.favourite) }
    }
}