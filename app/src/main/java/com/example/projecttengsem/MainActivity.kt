package com.example.projecttengsem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttengsem.ui.theme.DashboardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen { currentScreen = "dashboard" }
        "dashboard" -> DashboardScreen(
            onNavigateToStokBarang = { currentScreen = "stok_barang" },
            onNavigateToPerhitungan = { currentScreen = "perhitungan" },
            onNavigateToBelanjaStok = { currentScreen = "belanja_stok" },
            onNavigateToKontakDistributor = { currentScreen = "kontak_distributor" },
            onLogout = { currentScreen = "login" }
        )
        "stok_barang" -> StokBarangScreen { currentScreen = "dashboard" }
        "perhitungan" -> PerhitunganScreen { currentScreen = "dashboard" }
        "belanja_stok" -> BelanjaStokScreen { currentScreen = "dashboard" }
        "kontak_distributor" -> KontakDistributorScreen { currentScreen = "dashboard" }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") } // TODO: Input email pengguna
    var password by remember { mutableStateOf("") } // TODO: Input kata sandi pengguna
    var loginMessage by remember { mutableStateOf("") } // TODO: Menampilkan pesan login

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(), // Menyembunyikan teks dengan titik
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email == "admin" && password == "admin123") {
                    loginMessage = "Login successful" // TODO: Pesan login berhasil
                    onLoginSuccess()
                } else {
                    loginMessage = "Login failed. Try again." // TODO: Pesan login gagal
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = loginMessage)
    }
}

@Composable
fun DashboardScreen(
    onNavigateToStokBarang: () -> Unit,
    onNavigateToPerhitungan: () -> Unit,
    onNavigateToBelanjaStok: () -> Unit,
    onNavigateToKontakDistributor: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Dashboard", style = MaterialTheme.typography.titleLarge) // TODO: Menampilkan judul dasbor

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToStokBarang, modifier = Modifier.fillMaxWidth()) {
            Text("Stok Barang") // TODO: Navigasi ke layar stok barang
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToPerhitungan, modifier = Modifier.fillMaxWidth()) {
            Text("Perhitungan") // TODO: Navigasi ke layar perhitungan
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToBelanjaStok, modifier = Modifier.fillMaxWidth()) {
            Text("Belanja Stok") // TODO: Navigasi ke layar belanja stok
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToKontakDistributor, modifier = Modifier.fillMaxWidth()) {
            Text("Kontak Distributor") // TODO: Navigasi ke layar kontak distributor
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Logout") // TODO: Tombol keluar dari aplikasi
        }
    }
}

@Composable
fun StokBarangScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Stok Barang", style = MaterialTheme.typography.titleLarge) // TODO: Menampilkan layar stok barang

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(10) { index ->
                Text("Item $index", modifier = Modifier.padding(8.dp)) // TODO: Menampilkan daftar stok barang
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Dashboard") // TODO: Kembali ke dasbor
        }
    }
}

@Composable
fun PerhitunganScreen(onBack: () -> Unit) {
    var number1 by remember { mutableStateOf("") } // TODO: Input angka pertama
    var number2 by remember { mutableStateOf("") } // TODO: Input angka kedua
    var result by remember { mutableStateOf<Double?>(null) } // TODO: Hasil perhitungan

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Perhitungan", style = MaterialTheme.typography.titleLarge) // TODO: Menampilkan judul layar perhitungan

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Angka Pertama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Angka Kedua") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { result = calculate(number1, number2, "+") }) {
                Text("+") // TODO: Tombol penjumlahan
            }
            Button(onClick = { result = calculate(number1, number2, "-") }) {
                Text("-") // TODO: Tombol pengurangan
            }
            Button(onClick = { result = calculate(number1, number2, "*") }) {
                Text("×") // TODO: Tombol perkalian
            }
            Button(onClick = { result = calculate(number1, number2, "/") }) {
                Text("÷") // TODO: Tombol pembagian
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Hasil: ${result ?: ""}", style = MaterialTheme.typography.bodyLarge) // TODO: Menampilkan hasil perhitungan

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Dashboard") // TODO: Kembali ke dasbor
        }
    }
}

fun calculate(number1: String, number2: String, operator: String): Double? {
    val num1 = number1.toDoubleOrNull() // TODO: Konversi angka pertama
    val num2 = number2.toDoubleOrNull() // TODO: Konversi angka kedua
    if (num1 == null || num2 == null) return null

    return when (operator) {
        "+" -> num1 + num2 // TODO: Operasi penjumlahan
        "-" -> num1 - num2 // TODO: Operasi pengurangan
        "*" -> num1 * num2 // TODO: Operasi perkalian
        "/" -> if (num2 != 0.0) num1 / num2 else null // TODO: Operasi pembagian
        else -> null // TODO: Operasi tidak valid
    }
}

@Composable
fun BelanjaStokScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Belanja Stok", style = MaterialTheme.typography.titleLarge) // TODO: Menampilkan layar belanja stok

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(5) { index ->
                Text("Produk $index", modifier = Modifier.padding(8.dp)) // TODO: Menampilkan daftar produk
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Dashboard") // TODO: Kembali ke dasbor
        }
    }
}

@Composable
fun KontakDistributorScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Kontak Distributor", style = MaterialTheme.typography.titleLarge) // TODO: Menampilkan layar kontak distributor

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(5) { index ->
                Text("Distributor $index: 0812-3456-78$index", modifier = Modifier.padding(8.dp)) // TODO: Menampilkan daftar kontak distributor
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Dashboard") // TODO: Kembali ke dasbor
        }
    }
}
