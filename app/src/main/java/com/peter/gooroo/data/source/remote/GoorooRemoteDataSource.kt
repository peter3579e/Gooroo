package com.peter.gooroo.data.source.remote

import android.util.Log
import com.peter.gooroo.R
import com.peter.gooroo.data.Combine
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.data.Result
import com.peter.gooroo.data.source.GoorooDataSource
import com.peter.gooroo.network.GoorooApi
import com.peter.gooroo.util.Logger
import com.peter.gooroo.util.Util.isInternetConnected
import com.peter.gooroo.util.Util.getString

/**
 *
 * Implementation of the Gooroo source that from network.
 */

object GoorooRemoteDataSource : GoorooDataSource {

    override suspend fun getNumberValue(number: Int): Result<Process> {

        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }

        return try {

            val result = GoorooApi.retrofitService.getNumberValue(number)

            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }

    override suspend fun postTenNumber(tenNumber: PostTen): Result<Combine> {

        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }

        return try {

            val result = GoorooApi.retrofitService.postTenNumber(tenNumber)

            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }
}