package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodpart.fooddata.FoodData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodDetailsTab(
    food: FoodData
) {
    val tabList = listOf("مواد اولیه", "طرز تهیه", "اطلاعات بیشتر")
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column (
        modifier = Modifier
            .padding(16.dp,0.dp)
    ){
        TabRow(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            indicator = {

                tabList.forEachIndexed { index, s ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(it[pagerState.currentPage])
                            .height(1.dp)
                            .background(color = MaterialTheme.colors.primary)
                            .width(20.dp)
                    )

                }


            }
        ) {
            tabList.forEachIndexed { index, s ->
                Tab(modifier = Modifier
                    .wrapContentWidth(unbounded = true)
                    .clip(shape = MaterialTheme.shapes.small),

                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onBackground,
                    text = {
                        Text(
                            text = s,
                            style = MaterialTheme.typography.h3
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = 3,
            state = pagerState,
        ) {
            Box(
                Modifier
                    .padding(end = 1.dp, start = 1.dp, top = 24.dp, bottom = 0.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .heightIn(300.dp)
            ) {
                when (it) {
                    0 -> {

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp,start = 10.dp,end = 5.dp)
                                .fillMaxWidth(),
                            text = ".دﺮﯿﮔ راﺮﻗ هدﺎﻔﺘﺳا درﻮﻣ ﺎﺳﺎﺳا ﯽﺣاﺮﻃ دﻮﺟﻮﻣ یﺎﯿﻧد ﻞﻫا ﻪﺘﺳﻮﯿﭘ تﺎﻟاﻮﺳ یﻮﮕﺑاﻮﺟ و ﯽﻠﺻا یﺎﻫدروﺎﺘﺳد ﯽﻨﯿﭽﻓوﺮﺣ ﻞﻣﺎﺷ زﺎﯿﻧ درﻮﻣ نﺎﻣزو ﺪﺳر نﺎﯾﺎﭘ ﻪﺑ ﭗﯾﺎﺗ ﺖﺨﺳ ﻂﯾاﺮﺷ و ﺎﻫرﺎﮑﻫار ﻪﺋارا رد دﻮﺟﻮﻣ یراﻮﺷد و مﺎﻤﺗ ﻪﮐ ﺖﺷاد ﺪﯿﻣا ناﻮﺗ ﯽﻣ ترﻮﺻ ﻦﯾا رد .دﺮﮐ دﺎﺠﯾا ﯽﺳرﺎﻓ نﺎﺑز رد وﺮﺸﯿﭘ ﮓﻨﻫﺮﻓ و ﯽﻗﺎﻠﺧ نﺎﺣاﺮﻃ صﻮﺼﺨﻟا ﯽﻠﻋ یا ﻪﻧﺎﯾار نﺎﺣاﺮﻃ یاﺮﺑ ار یﺮﺘﺸﯿﺑ ﺖﺧﺎﻨﺷ ﺎﻫراﺰﻓا مﺮﻧ ﺎﺑ ﺎﺗ ﺪﺒﻠﻃ ﯽﻣ ار نﺎﺼﺼﺨﺘﻣ و ﻪﻌﻣﺎﺟ ناواﺮﻓ ﺖﺧﺎﻨﺷ هﺪﻨﯾآ و لﺎﺣ ،ﻪﺘﺷﺬﮔ ﺪﺻرد ﻪﺳ و ﺖﺼﺷ رد یدﺎﯾز یﺎﻬﺑﺎﺘﮐ .ﺪﺷﺎﺑ ﯽﻣ یدﺮﺑرﺎﮐ یﺎﻫراﺰﺑا دﻮﺒﻬﺑ فﺪﻫ ﺎﺑ عﻮﻨﺘﻣ یﺎﻫدﺮﺑرﺎﮐ و زﺎﯿﻧ درﻮﻣ یژﻮﻟﻮﻨﮑﺗ ﯽﻠﻌﻓ ﻂﯾاﺮﺷ یاﺮﺑ و ﺖﺳا مزﺎﻟ ﻪﮐ نﺎﻨﭽﻧآﺮﻄﺳ و نﻮﺘﺳ رد ﻪﻠﺠﻣ و ﻪﻣﺎﻧزور ﻪﮑﻠﺑ نﻮﺘﻣ و ﺎﻫﺮﮕﭘﺎﭼ .ﺖﺳا ﮏﯿﻓاﺮﮔ نﺎﺣاﺮﻃ زا هدﺎﻔﺘﺳا ﺎﺑ و پﺎﭼ ﺖﻌﻨﺻ زا مﻮﻬﻔﻣﺎﻧ ﯽﮔدﺎﺳ ﺪﯿﻟﻮﺗ ﺎﺑ ﯽﮕﺘﺧﺎﺳ ﻦﺘﻣ مﻮﺴﭙﯾا مرﻮﻟ\n" +
                                    ".دﺮﯿﮔ راﺮﻗ هدﺎﻔﺘﺳا درﻮﻣ ﺎﺳﺎﺳا ﯽﺣاﺮﻃ دﻮﺟﻮﻣ یﺎﯿﻧد ﻞﻫا ﻪﺘﺳﻮﯿﭘ تﺎﻟاﻮﺳ یﻮﮕﺑاﻮﺟ و ﯽﻠﺻا یﺎﻫدروﺎﺘﺳد ﯽﻨﯿﭽﻓوﺮﺣ ﻞﻣﺎﺷ زﺎﯿﻧ درﻮﻣ نﺎﻣزو ﺪﺳر نﺎﯾﺎﭘ ﻪﺑ ﭗﯾﺎﺗ ﺖﺨﺳ ﻂﯾاﺮﺷ و ﺎﻫرﺎﮑﻫار ﻪﺋارا رد دﻮﺟﻮﻣ یراﻮﺷد و مﺎﻤﺗ ﻪﮐ ﺖﺷاد ﺪﯿﻣا ناﻮﺗ ﯽﻣ ترﻮﺻ ﻦﯾا رد .دﺮﮐ دﺎﺠﯾا ﯽﺳرﺎﻓ نﺎﺑز رد وﺮﺸﯿﭘ ﮓﻨﻫﺮﻓ و ﯽﻗﺎﻠﺧ نﺎﺣاﺮﻃ صﻮﺼﺨﻟا ﯽﻠﻋ یا ﻪﻧﺎﯾار نﺎﺣاﺮﻃ یاﺮﺑ ار یﺮﺘﺸﯿﺑ ﺖﺧﺎﻨﺷ ﺎﻫراﺰﻓا مﺮﻧ ﺎﺑ ﺎﺗ ﺪﺒﻠﻃ ﯽﻣ ار نﺎﺼﺼﺨﺘﻣ و ﻪﻌﻣﺎﺟ ناواﺮﻓ ﺖﺧﺎﻨﺷ هﺪﻨﯾآ و لﺎﺣ ،ﻪﺘﺷﺬﮔ ﺪﺻرد ﻪﺳ ",
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }

                    1 -> {

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp,start = 10.dp,end = 5.dp)
                                .fillMaxWidth(),
                            text = food.recipes,
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }

                    2 -> {

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp,start = 10.dp,end = 5.dp)
                                .fillMaxWidth(),
                            text = food.recipes,
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }
                }
            }


        }
    }
}
