package com.example.moviesapp.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.MainViewModel
import com.example.moviesapp.utils.HtmlText

@Composable
fun DetailsScreen(viewModel: MainViewModel,itemId: String) {
    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt()}
    Surface(
        Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn(){
            item { Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = currentItem?.image?.medium),
                    contentDescription = null,
                    modifier = Modifier.size(512.dp)
                )
                Text(text = currentItem?.name?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Row {
                    Text(text = "Raiting: ",
                        fontWeight = FontWeight.Bold)
                    val raitingString = currentItem?.rating.toString()
                    Text(text = raitingString.split("=")[1].split(")")[0])
                }
                Row {
                    Text(text = "Genre:",
                        fontWeight = FontWeight.Bold)
                    currentItem?.genres?.take(2)?.forEach {Text(text = " $it ")}
                }
                HtmlText( currentItem?.summary?: "",
                    modifier = Modifier.padding(top = 10.dp)
                )


            } }
        }

    }
}