package id.application.sangugue.presentation.screen.amount


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.appliation.core.theme.PLNBlue
import id.application.sangugue.R


@Composable
fun InputAmount(
    amount: String,
    option: String,
    category: String,
    description: String,
    onAmountChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onOptionSelected: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
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


        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "Rp. $amount",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = PLNBlue
            )
            Text(".00", fontSize = 24.sp, color = Color.LightGray)
        }

        Text("jumlah yang ditentukan", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(10.dp))

        OptionDropdownCard(
            selectedCategory = category,
            onCategorySelected = onCategorySelected
        )

        Spacer(modifier = Modifier.height(10.dp))

        CategoryDropdownCard(
            selectedCategory = option,
            onCategorySelected = onOptionSelected
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Deskripsi (opsional)") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onInvestClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PLNBlue)
        ) {
            Text("Input Jumlah", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        CustomNumberPad(onKeyPress = onKeypadPress)
    }
}


@Composable
fun TopAppBar(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Masukkan Nominal", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Kembali",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
                .clickable { navHostController.popBackStack() }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionDropdownCard(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("Pemasukan", "Pengeluaran")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        Row(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF1F4FF))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_management),
                contentDescription = null,
                tint = id.appliation.core.theme.PLNBlue,
                modifier = Modifier.size(40.dp),

            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = selectedCategory,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Pilih Kategori"
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdownCard(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("Gaji", "Freelance", "Bayar Listrik")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        Row(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF1F4FF))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_option),
                contentDescription = null,
                tint = id.appliation.core.theme.PLNBlue,
                modifier = Modifier.size(40.dp),

            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = selectedCategory,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Pilih Opsi"
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
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
