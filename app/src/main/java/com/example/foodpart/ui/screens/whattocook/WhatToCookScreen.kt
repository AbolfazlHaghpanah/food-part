package com.example.foodpart.ui.screens.whattocook

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.ui.components.foodPartButton
import com.example.foodpart.ui.components.foodPartTextField

@Composable
fun whatToCookScreen(
    navController: NavController

) {

    var selectedDefficultyItems: DefficultyItems by remember {
        mutableStateOf(DefficultyItems.noMatter)
    }
    val itemTextState = remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "چی بپزم؟",
                        style = MaterialTheme.typography.h1
                    )
                },
                backgroundColor = MaterialTheme.colors.background
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "راهنما",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1F)
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "close"
                        )
                    }


                }
                Text(
                    text = "کاربر گرامی مواد اولیه ی موجود را در قسمت “تو خونه چی داری ؟” وارد کرده و با علامت “،” از یکدیگر جدا کنید سپس کل زمانی که دارین را به دقیقه در بخش “چقدر وقت داری ؟” وارد کنین. سطح دستور پخت را انتخاب کرده و کلیک جستجو را بزنید تا با توجه به اطلاعات شما غذاهای پیشنهاد را نمایش دهیم.",
                    style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start)
                )
            }

            foodPartTextField(
                textFieldState = itemTextState,
                label = "چی تو خونه داری ؟",
                height = 50.dp
            )

            Text(
                text = "با ، جدا کنید ",
                style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start),
                modifier = Modifier
                    .fillMaxWidth()
            )

            foodPartTextField(
                textFieldState = itemTextState,
                label = "چقد وقت داری؟",
                height = 60.dp
            )

            Text(
                text = "دستور چقدر سخت باشه ؟",
                style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                defficultyItemsList.forEach { item ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .clickable {
                                item.icon = R.drawable.check_circle_outline
                                selectedDefficultyItems = item
                                Log.d("TAG", "whatToCookScreen: ")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name
                        )
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }

                }
            }

            Spacer(
                modifier = Modifier
                    .weight(1F)
            )

            foodPartButton(onClick = { /*TODO*/ }, text = "تایید")


        }

    }
}