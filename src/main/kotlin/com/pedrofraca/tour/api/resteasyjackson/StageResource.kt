package com.pedrofraca.tour.api.resteasyjackson

import com.pedrofraca.tour.api.data.ClassificationDataSource
import com.pedrofraca.tour.api.data.StageAsFavoriteReadDataSource
import com.pedrofraca.tour.api.data.StageAsFavoriteWriteDataSource
import com.pedrofraca.tour.api.data.StageDataSource
import io.github.pedrofraca.data.datasource.favourites.FavouritesRepositoryImpl
import io.github.pedrofraca.domain.model.StageClassificationModel
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.domain.usecase.favourite.SetStageAsFavoriteUseCase
import io.github.pedrofraca.domain.usecase.favourite.SetStageAsFavoriteUseCaseImpl
import io.github.pedrofraca.domain.usecase.favourite.repository.SetStageAsFavoriteParam
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api/stage")
@Produces(MediaType.APPLICATION_JSON)

class StageResource {

    @Inject
    lateinit var stagesDataSource: StageDataSource
    @Inject
    lateinit var classificationDataSource : ClassificationDataSource
    @Inject
    lateinit var stageAsFavoriteReadDataSource : StageAsFavoriteReadDataSource
    @Inject
    lateinit var stageAsFavoriteWriteDataSource : StageAsFavoriteWriteDataSource

    @GET
    fun list(): List<StageModel> {
        return stagesDataSource.getAll()
    }

    @POST
    fun save(stage: StageModel) {
        stagesDataSource.save(stage)
    }

    @PATCH
    fun saveFavourite(stageAsFavorite : SetStageAsFavoriteParam) : Response {
        //TODO consider a better way to build the use case
        val setStageAsFavoriteUseCase = SetStageAsFavoriteUseCaseImpl(FavouritesRepositoryImpl(stageAsFavoriteWriteDataSource,
        stageAsFavoriteReadDataSource))

        try {
            setStageAsFavoriteUseCase(stageAsFavorite)
        } catch(e: Exception) {
            return Response.serverError().entity(e.message).build()
        }

        return Response.ok().entity(stageAsFavorite).build()
    }

    @GET
    @Path("{id}/classification")
    fun getClassificationForStage(@PathParam("stage") stage: Int) : StageClassificationModel {
        return classificationDataSource.get(stage)
    }

    @POST
    @Path("{id}/classification")
    fun saveClassificationForStage(@PathParam("stage") stage: Int, classificationModel: StageClassificationModel) : StageClassificationModel {
        classificationDataSource.save(classificationModel, stage)
        return classificationModel
    }
}
