package com.transico.codezero.transico.SystemHelper;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * This class handles all the firebaseFirestore call and complex mutation of the data
 * then pass it on to the calling object.
 * the reason for this call is to have a centralize class to improve maintainability
 */
public class FirebaseManager  {

    private  FirebaseFirestore firestoreDB;
    private CollectionReference collectionRef;


    public FirebaseManager(){firestoreDB = FirebaseFirestore.getInstance();}

    public FirebaseManager(String collectionRefName){
         firestoreDB = FirebaseFirestore.getInstance();
        setCollectionRef(collectionRefName);
    }

    public void setCollectionRef(String collectionRefName) {
        this.collectionRef = firestoreDB.collection(collectionRefName);
    }

    public CollectionReference getCollectionRef(){
        return collectionRef;
    }


}
