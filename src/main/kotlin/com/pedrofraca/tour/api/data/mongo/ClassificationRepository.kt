package com.pedrofraca.tour.api.data.mongo

import io.quarkus.mongodb.panache.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassificationRepository: PanacheMongoRepository<Classification> {}