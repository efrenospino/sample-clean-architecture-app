package com.spotapp.mobile.ui.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.spotapp.mobile.ui.R
import com.spotapp.mobile.ui.common.StandardAlertDialog

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    paddingValues: PaddingValues,
) {

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.errorMessage != null) {
        StandardAlertDialog(
            onDismissRequest = viewModel::cleanError,
            dialogText = uiState.errorMessage!!
        )
    }

    if(uiState.showAddQuestionDialog){
        StandardAlertDialog(
            onDismissRequest = viewModel::hideQuestionDialog,
            dialogText = "Questions Added!"
        )
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Indicators(totalUsers = uiState.userList.size, totalQuestions = uiState.questionList.size)

        Ranking()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            FloatingActionButton(
                onClick = viewModel::playGame,
                containerColor = colorResource(id = R.color.MidnightBlue)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = colorResource(id = R.color.snowWhite)
                )
            }

            FloatingActionButton(
                onClick = {},
                containerColor = colorResource(id = R.color.MidnightBlue)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = colorResource(id = R.color.snowWhite)
                )
            }
        }
    }
}

@Composable
private fun Indicators(totalUsers: Int, totalQuestions: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9F)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Card(
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.snowWhite)
            ),
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, colorResource(id = R.color.MidnightBlue)),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Total Users: $totalUsers"
            )
        }
        Card(
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.snowWhite)
            ),
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, colorResource(id = R.color.mainColor)),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Questions to answer: $totalQuestions"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Ranking() {
    val userList = listOf(
        User("user1@example.com", 100),
        User("user2@example.com", 85),
        User("user3@example.com", 92),
        User("user4@example.com", 78),
        User("user5@example.com", 95),
        User("user1@example.com", 100),
        User("user2@example.com", 85),
        User("user3@example.com", 92),
        User("user4@example.com", 78),
        User("user5@example.com", 95)
    )
    Card(
        modifier = Modifier
            .fillMaxHeight(0.6F)
            .fillMaxWidth(0.9F)
            .border(
                border = BorderStroke(2.dp, colorResource(id = R.color.snowWhite)),
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.snowWhite)
        ),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(
                                2.dp,
                                colorResource(id = R.color.mainColor)
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(color = colorResource(id = R.color.snowWhite)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.padding(8.dp), text = "Ranking in Real Time 📈")
                }
            }

            userList.forEach {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(
                                border = BorderStroke(
                                    2.dp,
                                    colorResource(id = R.color.MidnightBlue)
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = it.email
                        )

                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "${it.score}"
                        )
                    }
                }
            }
        }
    }
}

data class User(val email: String, val score: Int)
