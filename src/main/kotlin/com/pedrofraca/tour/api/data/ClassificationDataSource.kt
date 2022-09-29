package com.pedrofraca.tour.api.data

import com.pedrofraca.tour.api.data.mongo.ClassificationMongo
import com.pedrofraca.tour.api.data.mongo.ClassificationRepository
import com.pedrofraca.tour.api.data.mongo.ClassificationType
import io.github.pedrofraca.data.datasource.WriteDataSourceWithFilter
import io.github.pedrofraca.domain.model.Classification
import io.github.pedrofraca.domain.model.StageClassification
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ClassificationDataSource : WriteDataSourceWithFilter<StageClassification, Int> {
    @Inject
    lateinit var classificationRepository: ClassificationRepository

    override fun getAll(): List<StageClassification> {
        TODO("Not yet implemented")
    }

    override fun save(item: StageClassification, stageId : Int): Boolean {

        classificationRepository.delete("stage = ?1", stageId)
        val general = item.general.map {
            mapClassification(it,stageId, ClassificationType.GENERAL)
        }
        val stage = item.stageClassification.map {
            mapClassification(it,stageId, ClassificationType.STAGE)
        }
        val team = item.team.map {
            mapClassification(it,stageId, ClassificationType.TEAM)
        }
        val mountain = item.mountain.map {
            mapClassification(it,stageId, ClassificationType.MOUNTAIN)
        }
        val regularity = item.regularity.map {
            mapClassification(it,stageId, ClassificationType.REGULARITY)
        }

        classificationRepository.persistOrUpdate(general + stage + team + mountain + regularity)

        return true
    }

    private fun mapClassification(it: Classification, stageId: Int, type : ClassificationType) : ClassificationMongo {
        val classification = ClassificationMongo()
        classification.pos = it.pos
        classification.country = it.country
        classification.rider = it.rider
        classification.team = it.team
        classification.stage = stageId
        classification.time = it.time
        classification.type = type.value
        return classification
    }

    override fun get(stageId: Int): StageClassification {

        val result = classificationRepository.find("stage = ?1", stageId).list<ClassificationMongo>()
        val general = result.filter { it.type == ClassificationType.GENERAL.value }.map { Classification(it.time, it.country, it.team, it.pos, it.rider) }
        val regularity = result.filter { it.type == ClassificationType.REGULARITY.value }.map { Classification(it.time, it.country, it.team, it.pos, it.rider) }
        val mountain = result.filter { it.type == ClassificationType.MOUNTAIN.value }.map { Classification(it.time, it.country, it.team, it.pos, it.rider) }
        val stage = result.filter { it.type == ClassificationType.STAGE.value }.map { Classification(it.time, it.country, it.team, it.pos, it.rider) }
        val team = result.filter { it.type == ClassificationType.TEAM.value }.map { Classification(it.time, it.country, it.team, it.pos, it.rider) }
        return StageClassification(mountain, team, general, regularity, stage, stageId.toString())
    }
}