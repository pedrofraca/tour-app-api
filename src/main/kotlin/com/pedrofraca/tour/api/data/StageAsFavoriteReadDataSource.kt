package com.pedrofraca.tour.api.data

import com.pedrofraca.tour.api.data.mongo.Classification
import com.pedrofraca.tour.api.data.mongo.StageFavourite
import com.pedrofraca.tour.api.data.mongo.StageFavouriteRepository
import io.github.pedrofraca.data.datasource.ReadOnlyDataSourceWithFilter
import io.github.pedrofraca.domain.usecase.favourite.repository.SetStageAsFavoriteParam
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class StageAsFavoriteReadDataSource : ReadOnlyDataSourceWithFilter<List<SetStageAsFavoriteParam>, String> {
    @Inject
    lateinit var repository: StageFavouriteRepository

    override fun get(param: String): List<SetStageAsFavoriteParam> {
        return repository.find("username = ?1", param).list<StageFavourite?>()
            .map { SetStageAsFavoriteParam(it.username, it.stage, it.favourite) }
    }

    override fun getAll(): List<List<SetStageAsFavoriteParam>> {
        return emptyList()
    }
}