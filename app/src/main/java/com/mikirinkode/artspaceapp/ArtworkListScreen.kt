package com.mikirinkode.artspaceapp

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikirinkode.artspaceapp.data.model.Art
import com.mikirinkode.artspaceapp.data.model.DummyData
import com.mikirinkode.artspaceapp.ui.theme.ArtSpaceAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkListScreen(
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableStateOf(0) }

    val art = DummyData.getArtByIndex(currentIndex)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Art Gallery") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            BottomActionBar(
                isPreviousButtonVisible = currentIndex != 0,
                isNextButtonVisible = (currentIndex < DummyData.getArtSize() - 1),
                onPreviousClicked = {
                    currentIndex--
                },
                onNextClicked = {
                    if (currentIndex < DummyData.getArtSize() - 1) {
                        currentIndex++
                    } else {
                        currentIndex = 0
                    }
                })
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            ArtItem(
                artTitle = art.title,
                artImageId = art.imageId,
                artArtist = art.artist,
                artReleaseYear = art.releaseYear
            )
        }
    }
}

@Composable
fun ArtItem(
    artTitle: String,
    artImageId: Int,
    artArtist: String,
    artReleaseYear: Int,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Card(
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Image(
                        painter = painterResource(id = artImageId),
                        contentDescription = "Art Photo",
                        modifier = Modifier
                            .padding(24.dp)
                            .height(400.dp)
                    )
                }
            }
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(artTitle, fontSize = 18.sp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(artArtist, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("($artReleaseYear)", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomActionBar(
    isPreviousButtonVisible: Boolean,
    isNextButtonVisible: Boolean,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isPreviousButtonVisible) {
            Arrangement.SpaceBetween
        } else {
            Arrangement.End
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        AnimatedVisibility(visible = isPreviousButtonVisible) {
            Row() {
                Button(onClick = onPreviousClicked, modifier = Modifier.widthIn(min = 120.dp)) {
                    Text(text = "Previous")
                }
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
        Button(onClick = onNextClicked, modifier = Modifier.widthIn(min = 120.dp)) {
            Text(text = if (isNextButtonVisible) "Next" else "Back To First")
        }
    }
}

@Preview(name = "light", showBackground = true, showSystemUi = true)
@Preview(
    name = "night",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ArtworkListScreenPreview() {
    ArtSpaceAppTheme {
        Surface() {
            ArtworkListScreen()
        }
    }
}