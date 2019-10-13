package com.pedromoniz.gistlistmvvm.domain.interactors

import com.pedromoniz.gistlistmvvm.Utils.Either
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.Utils.UseCase
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway

class GetGistDetailUseCase(
    private val gistsGateway: GistsGateway
) : UseCase<GistEntity, GetGistDetailUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, GistEntity> {

        return try {
            val gistDetail = gistsGateway.getGist(params.gistId)
            Either.Right(gistDetail)
        }
        catch (exp: Exception) {
            Either.Left(Failure.NetworkConnection)
        }
    }

    data class Params(val gistId: String)

    //todo, for now we only care for failed connection
    data class GetGistDetailFailure(val error: Exception) : Failure.FeatureFailure(error)
}