package com.pedromoniz.gistlistmvvm.domain.interactors

import com.pedromoniz.gistlistmvvm.Utils.Either
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.Utils.UseCase
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway

class GetGistsTemplatesUseCase(
    private val gistsGateway: GistsGateway
) : UseCase<List<GistEntity>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<GistEntity>> {
        return try {
            val gists = gistsGateway.getGists()
            Either.Right(gists)
        } catch (exp: Exception) {
            Either.Left(GetGistDetailFailure(exp))
        }
    }

    data class GetGistDetailFailure(val error: Exception) : Failure.FeatureFailure()
}