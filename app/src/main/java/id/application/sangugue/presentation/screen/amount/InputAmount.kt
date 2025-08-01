package id.application.sangugue.presentation.screen.amount


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.application.sangugue.R

@Composable
fun InputAmount(
    amount: String,
    category: String,
    onAmountChange: (String) -> Unit,
    onCategoryEdit: () -> Unit,
    onInvestClick: () -> Unit,
    onKeypadPress: (String) -> Unit,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(navHostController = navHostController)

        Spacer(modifier = Modifier.height(32.dp))

        Text("How much do you want to save?", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$$amount",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E5BF6)
            )
            Text(".00", fontSize = 24.sp, color = Color.LightGray)
        }

        Text("Set amount of the goal", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))

        CategoryCard(category = category, onEdit = onCategoryEdit)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onInvestClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E5BF6))
        ) {
            Text("Invest in low risk fund", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        CustomNumberPad(onKeyPress = onKeypadPress)
    }
}

@Composable
fun TopAppBar(navHostController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Kembali",
            tint = Color.Black,
            modifier = Modifier
                .size(28.dp)
                .clickable { navHostController.popBackStack() }
        )
    }
}


@Composable
fun CategoryCard(category: String, onEdit: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF1F4FF))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = null,
            tint = Color(0xFF3E5BF6),
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = category, fontWeight = FontWeight.Medium, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}

@Composable
fun CustomNumberPad(onKeyPress: (String) -> Unit) {
    val keys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf(".", "0", "⌫")
    )

    Column {
        keys.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { key ->
                    OutlinedButton(
                        onClick = { onKeyPress(key) },
                        modifier = Modifier
                            .size(80.dp)
                            .padding(4.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text(text = key, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}
