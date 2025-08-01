package id.application.sangugue.presentation.screen.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.application.sangugue.presentation.navigation.Screen
import id.application.sangugue.ui.theme.PLNBlue
import id.application.sangugue.ui.theme.White
import id.application.sangugue.utils.Utils.SetSystemBarColor

@Composable
fun LoginScreen(navController: NavHostController) {

    SetSystemBarColor(color = White, darkIcons = true)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sangu", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = PLNBlue)
        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email",style = TextStyle(color = Color.Gray)) },
            placeholder = {
                Text(
                    text = "Masukkan email kamu",
                    style = TextStyle(color = Color.Gray)
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color.Gray
            )
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password",style = TextStyle(color = Color.Gray)) },
            placeholder = {
                Text(
                    text = "Masukkan password kamu",
                    style = TextStyle(color = Color.Gray)
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color.Gray
            )
        )
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.Dashboard.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PLNBlue)
        ) {
            Text("Login", color = White)
        }

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
            Text("Belum punya akun? Daftar")
        }
    }
}
