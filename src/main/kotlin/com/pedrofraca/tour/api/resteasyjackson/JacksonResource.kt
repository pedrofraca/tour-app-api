package com.pedrofraca.tour.api.resteasyjackson

import io.github.pedrofraca.domain.model.StageModel
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/tour")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class JacksonResource {

    private val quarks = Collections.newSetFromMap(Collections.synchronizedMap(LinkedHashMap<StageModel, Boolean>()))

    init {
        quarks.add(StageModel("this is a name", "Pedro Fraca", "Pedro Fraca", emptyList(), "This is a description", "80","","",1,","))
    }

    @GET
    fun list(): Set<StageModel> {
        return quarks
    }

    data class Tour( private val date: String)
}
