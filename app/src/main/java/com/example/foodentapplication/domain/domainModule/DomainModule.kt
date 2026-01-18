package com.example.foodentapplication.domain.domainModule

import com.example.foodentapplication.data.repoImpl.RepoImpl
import com.example.foodentapplication.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {


    @Provides
    @Singleton
    fun ProvideRepo(firebaseAuth: FirebaseAuth,firebaseFireStore: FirebaseFirestore): Repo {
        return RepoImpl(firebaseAuth, firebaseFireStore)



    }
}