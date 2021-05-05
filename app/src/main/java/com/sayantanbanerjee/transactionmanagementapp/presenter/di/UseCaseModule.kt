package com.sayantanbanerjee.transactionmanagementapp.presenter.di

import com.sayantanbanerjee.transactionmanagementapp.domain.UseCase.*
import com.sayantanbanerjee.transactionmanagementapp.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// UseCaseModule creates the dependency injection for the different Use Cases of the project defined in the Domain section.
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesSaveRecordUseCase(appRepository: AppRepository): SaveRecordUseCase {
        return SaveRecordUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesAcceptedSumSentUseCase(appRepository: AppRepository): GetAcceptedSumSentUseCase {
        return GetAcceptedSumSentUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesAcceptedSumSentByAParticularContactUseCase(appRepository: AppRepository): GetAcceptedSumSentByAParticularContactUseCase {
        return GetAcceptedSumSentByAParticularContactUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesAcceptedSumReceivedByAParticularContactUseCase(appRepository: AppRepository): GetAcceptedSumReceivedByAParticularContact {
        return GetAcceptedSumReceivedByAParticularContact(appRepository)
    }

    @Singleton
    @Provides
    fun providesSumReceivedUseCase(appRepository: AppRepository): GetSumReceivedUseCase {
        return GetSumReceivedUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesGetAllRecordsUseCase(appRepository: AppRepository): GetAllRecordsUseCase {
        return GetAllRecordsUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesSaveStateToFirebaseUseCase(appRepository: AppRepository): SaveStateToFirebaseUseCase {
        return SaveStateToFirebaseUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesGetStateFromFirebaseUseCase(appRepository: AppRepository): GetStateFromFirebaseUseCase {
        return GetStateFromFirebaseUseCase(appRepository)
    }

    @Singleton
    @Provides
    fun providesGetAllRecordsOfAParticularContactUseCase(appRepository: AppRepository): GetAllRecordsOfAParticularContactUseCase {
        return GetAllRecordsOfAParticularContactUseCase(appRepository)
    }

}
