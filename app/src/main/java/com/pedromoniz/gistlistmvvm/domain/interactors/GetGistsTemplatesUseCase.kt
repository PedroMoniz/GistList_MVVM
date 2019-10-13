package com.pedromoniz.gistlistmvvm.domain.interactors

import com.pedromoniz.gistlistmvvm.Utils.Either
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.Utils.UseCase
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.exceptions.NetworkConnectionError
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway

class GetGistsTemplatesUseCase(
    private val gistsGateway: GistsGateway
) : UseCase<List<GistEntity>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<GistEntity>> {
        return try {
            val gists = gistsGateway.getGists()
            Either.Right(gists)
        }
        catch (exp: Exception) {
            Either.Left(Failure.NetworkConnection)
        }
        //todo, for now we only care for failed connection
//        catch (exp: NetworkConnectionError) {
//            Either.Left(Failure.NetworkConnection)
//        }
//        catch (exp: Exception) {
//            Either.Left(GetGistsTemplatesFailure(exp))
//        }
    }

    data class GetGistsTemplatesFailure(val error: Exception) : Failure.FeatureFailure(error)
}