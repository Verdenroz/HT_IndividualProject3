package com.farmingdale.ht_individualproject3.login.registration.parent

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.room.Room
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.compose.md_theme_light_onPrimaryContainer
import com.example.compose.md_theme_light_primary
import com.farmingdale.ht_individualproject3.MainActivity
import com.farmingdale.ht_individualproject3.R
import com.farmingdale.ht_individualproject3.database.ChildProgress
import com.farmingdale.ht_individualproject3.database.ImplParentChildRepo
import com.farmingdale.ht_individualproject3.database.ParentChildDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.PrintWriter

/**
 * When parents login
 */
class ParentPortalActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            ParentChildDatabase::class.java,
            "parentchild.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parentID = intent.getIntExtra("ParentID", 0)
        setContent {
            val context = LocalContext.current
            val children = database.childDao().getChildrenByParent(parentID)
            var showFile by remember { mutableStateOf(false) }
            var showChart by remember { mutableStateOf(false) }
            val repo = ImplParentChildRepo(database)
            val scope = rememberCoroutineScope()

            //show this dialog when user wants to see progress report
            if (showFile) {
                Dialog(
                    onDismissRequest = { showFile = false }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(5f)
                    ) {
                        val scrollState = rememberScrollState()
                        Column(
                            Modifier.verticalScroll(state = scrollState),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val progress = readFromInternalFile()

                            Text(
                                text = "$progress",
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 1000,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(
                                horizontalArrangement = Arrangement.End,
                            ) {
                                IconButton(onClick = { showFile = false }) {
                                    Icon(Icons.Filled.Done, contentDescription = "")
                                }
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.login_registration_background),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = ColorFilter.lighting(md_theme_light_primary, Color.DarkGray)
                    ),
            ) {
                IconButton(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }

                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.hello),
                                style = MaterialTheme.typography.headlineLarge,
                                textAlign = TextAlign.Center,
                                color = md_theme_light_onPrimaryContainer
                            )

                            Text(
                                text = repo.database.parentDao().getParentById(parentID)?.firstName
                                    ?: "",
                                style = MaterialTheme.typography.headlineLarge,
                                textAlign = TextAlign.Center,
                                color = md_theme_light_onPrimaryContainer
                            )
                        }

                        Text(
                            text = "Your Children: ",
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            color = md_theme_light_onPrimaryContainer
                        )

                    }
                    if (children?.isEmpty() == true) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Button(onClick = {
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                            }) {
                                Text(text = stringResource(id = R.string.register_child))
                            }
                        }

                    }
                    if (children?.isNotEmpty() == true) {
                        LazyColumn(
                            Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(children) { child ->
                                Card(
                                    modifier = Modifier,
                                    colors = CardDefaults.cardColors(Color.LightGray)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = child.firstName)
                                        Text(text = child.lastName)

                                        Button(
                                            onClick = {
                                                writeToInternalFile(child.childId)
                                                showFile = true
                                            },
                                        ) {
                                            Text(
                                                text = stringResource(id = R.string.view_progress),
                                            )
                                        }
                                        Column {
                                            IconButton(
                                                onClick = {
                                                    var progress: List<ChildProgress>?
                                                    scope.launch(Dispatchers.IO) {
                                                        progress = database.childProgressDao()
                                                            .getProgressbyChild(child.childId)
                                                        if (!progress.isNullOrEmpty()) {
                                                            Log.d("Progress", progress.toString())
                                                            withContext(Dispatchers.Main) {
                                                                showChart = true
                                                            }
                                                        } else {
                                                            showChart = false
                                                            withContext(Dispatchers.Main) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "This child has no progress",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                        }
                                                    }

                                                }
                                            ) {
                                                Icon(Icons.Filled.Edit, contentDescription = "")
                                            }
                                            Text(text = stringResource(id = R.string.see_chart_label))
                                        }

                                    }
                                    if (showChart) {
                                        Dialog(onDismissRequest = { showChart = false }) {
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(.5f)
                                            ) {
                                                Column(
                                                    Modifier.fillMaxSize(),
                                                    verticalArrangement = Arrangement.Bottom
                                                ) {
                                                    ProgressChart(
                                                        childId = child.childId,
                                                        database = database
                                                    )
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Creates progress chart by child
     */
    @Composable
    fun ProgressChart(childId: Int, database: ParentChildDatabase) {
        var points: Map<Float, Int> by remember { mutableStateOf(mutableMapOf()) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(childId) {
            try {
                val progress = withContext(Dispatchers.IO) {
                    database.childProgressDao().getProgressbyChild(childId)
                }
                if (progress != null) {
                    val updatedPoints =
                        progress.associate { it.points.toFloat() to Integer.parseInt(it.day) }
                    points = points + updatedPoints
                }
                isLoading = false
            } catch (e: Exception) {
                Log.e("ProgressChart", "Error fetching points: ${e.message}")
            } finally {
                isLoading = false
            }
        }

        if (isLoading) {
            // Show loading indicator
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Blue
                )
            }
        } else if (points.isNotEmpty()) {
            //when points is populated, create the progress chart below
            val context = LocalContext.current
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(scrollState)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(text = "50000")
                            Spacer(modifier = Modifier.fillMaxHeight())
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(text = "25000")
                            Spacer(modifier = Modifier.fillMaxHeight(.5f))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .background(Color.Black)
                    )
                    points!!.forEach {
                        Box(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .width(20.dp)
                                .fillMaxHeight((it.key) / 50000f)
                                .background(md_theme_light_primary)
                                .clickable {
                                    Toast
                                        .makeText(context, it.key.toString(), Toast.LENGTH_SHORT)
                                        .show()
                                }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Black)
                )

                Row(
                    modifier = Modifier
                        .padding(start = 72.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    points.values.forEach {
                        Text(
                            modifier = Modifier.width(30.dp),
                            text = "${it.toString()} days",
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        } else {
            // Handle the case where points are empty
            Text(
                text = "No progress data available",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }


    /**
     * Writes to an internal file with progress by child
     */
    private fun writeToInternalFile(childId: Int) {
        val outputStream = openFileOutput("childprogress", MODE_PRIVATE)
        val writer = PrintWriter(outputStream)

        val progress = database.childProgressDao().getProgressbyChild(childId)
        // Write each task on a separate line
        if (progress != null) {
            for (progress in progress) {
                writer.println("Points Earned: ${progress.points} \t Month: ${progress.month} \t Day: ${progress.day} \t Year: ${progress.year}")
            }
        }
        writer.close()
    }

    /**
     * Reads from an internal file with progress by child
     */
    private fun readFromInternalFile(): String {
        val inputStream = openFileInput("childprogress")
        val reader = inputStream.bufferedReader()
        val stringBuilder = StringBuilder()
        val lineSeparator = System.getProperty("line.separator")

        // Append each task to stringBuilder
        reader.forEachLine { stringBuilder.append(it).append(lineSeparator) }


        return stringBuilder.toString()
    }

}