package com.example.android_firebase

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_firebase.ui.theme.Android_firebaseTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_firebaseTheme {
                App(db)
            }
        }
    }
}

@Composable
fun App(db: FirebaseFirestore){
    var nome by remember {
        mutableStateOf("")
    }

    var telefone by remember{
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center
        ) {
            Text(text = "App Firebase Firestore")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))

        Row(
            Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Column(
                Modifier.fillMaxWidth(0.3f)
            ) {
                Text(text = "Nome: ")
            }
            Column {
                TextField(value = nome, onValueChange = {nome = it})
            }
        }

        Row(
            Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.fillMaxWidth(0.3f)
            ) {
                Text(text = "Telefone: ")
            }

            Column {
                TextField(value = telefone, onValueChange = {telefone = it})
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.Center
        ) {
            Button(onClick = {
                val city = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("Clientes").document("PrimeiroCliente")
                    .set(city)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
            }) {
                Text(text = "Cadastrar")
            }

        }
    }
}