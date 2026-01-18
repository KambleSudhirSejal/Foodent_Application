package com.example.foodentapplication.data.repoImpl

import android.net.Uri
import com.example.foodentapplication.common.AppLogger
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.common.USER_COLLECTION
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.data.models.UserData
import com.example.foodentapplication.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject




class RepoImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFireStore: FirebaseFirestore

            ): Repo {


    override suspend fun registerWithEmailAndPassword(userData: UserData): ResultState<UserData>{

        AppLogger.repo(
            "RepoImpl",
            "Register started | email=${userData.email}"
        )

        return try{

            AppLogger.repo(
                "RepoImpl",
                "Calling FirebaseAuth.createUserWithEmailAndPassword"
            )
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(
                    userData.email,
                    userData.password
                )
                .await()
            val uid = authResult.user?.uid

            AppLogger.repo(
                "RepoImpl",
                "FirebaseAuth SUCCESS | uid=$uid"
            )

            if (uid.isNullOrEmpty()) {
                AppLogger.error(
                    "RepoImpl",
                    "UID is null after registration"
                )
                return ResultState.Error("User ID not generated")
            }

            AppLogger.repo(
                "RepoImpl",
                "Saving user to Firestore | collection=$USER_COLLECTION"
            )

            firebaseFireStore
                .collection(USER_COLLECTION)
                .document(authResult.user?.uid.orEmpty())
                .set(userData)
                .await()

            AppLogger.repo(
                "RepoImpl",
                "Firestore SUCCESS | user saved"
            )


            ResultState.Success(userData)

        }catch(e:Exception){

            AppLogger.error(
                "RepoImpl",
                "Registration FAILED | ${e.localizedMessage}"
            )
            ResultState.Error(e.localizedMessage ?:"Registration failed")
        }

    }

    override suspend fun loginWithEmailAndPassword(userData: UserData): ResultState<String> {


        AppLogger.repo(
            "RepoImple","Login started | email = ${userData.email}"
        )
        return try{

            AppLogger.repo("RepoImple","Calling Calling FirebaseAuth.signInWithEmailAndPassword")
           val result= firebaseAuth
                .signInWithEmailAndPassword(
                    userData.email,
                    userData.password
                ).await()

            val uid = result.user?.uid

            AppLogger.repo(
                "RepoImpl","login success with uid= $uid"
            )

            ResultState.Success("User Logged In Successfully")

        }catch(e: Exception){
            AppLogger.repo("RepoImpl","Login Failed | ${e.localizedMessage}")
            ResultState.Error(e.localizedMessage ?:"Login failed")

        }
    }

    override suspend fun addFoodItem(foodItem: FoodItem): ResultState<String> {
      return try{







          AppLogger.repo("Repo","Enter into Add Food item")
          val docRef = firebaseFireStore.collection("foods").document()

          val foodWitId = foodItem.copy(id=docRef.id)

          docRef.set(foodWitId).await()

          ResultState.Success("Food item added successfully")

      }catch(e:Exception){
          AppLogger.error("FoodRepo", e.message ?: "Add failed", e)
          ResultState.Error("Failed to add food item")
      }

    }

    override suspend fun getFoodItem(category: String): ResultState<List<FoodItem>> {
        return try{

            AppLogger.repo("Repo","Enter into get food item list")

            val query =if(category == "all"){
                firebaseFireStore.collection("foods")
            }else{
                firebaseFireStore.collection("foods")
                    .whereEqualTo("category",category)
            }

            val  snapshot = query.get().await()

            val foodList = snapshot.documents.mapNotNull {
                it.toObject(FoodItem::class.java)
            }

            AppLogger.repo("Repo","$foodList fetching ")

            ResultState.Success(foodList)

        }catch(e:Exception){
            AppLogger.repo("Repo",e.message?:"Fetch failed , $e")
            ResultState.Error("Failed to fetch food item")
        }


    }



}