package com.pedrofraca.tour.api.resteasyjackson

import com.pedrofraca.tour.api.data.ClassificationDataSource
import com.pedrofraca.tour.api.data.StageDataSource
import io.github.pedrofraca.domain.model.StageClassificationModel
import io.github.pedrofraca.domain.model.StageModel
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/api/stage")
@Produces(MediaType.APPLICATION_JSON)

class StageResource {

    @Inject
    lateinit var stagesDataSource: StageDataSource
    @Inject
    lateinit var classificationDataSource : ClassificationDataSource

    @GET
    fun list(): List<StageModel> {
        return stagesDataSource.getAll()
    }

    @POST
    fun save(stage: StageModel) {
        stagesDataSource.save(stage)
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
