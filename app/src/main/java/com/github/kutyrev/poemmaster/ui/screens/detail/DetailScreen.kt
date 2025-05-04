package com.github.kutyrev.poemmaster.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import com.github.kutyrev.poemmaster.R
import com.github.kutyrev.poemmaster.model.PoemWordVisualization
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailEvent
import com.github.kutyrev.poemmaster.ui.theme.PoemMasterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    poemName: String,
    poemWords: List<PoemWordVisualization>,
    poemText: String,
    isEditMode: Boolean,
    hidePercent: Int,
    numberOpened: Int,
    onEvent: (DetailEvent) -> Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(poemName)
                },
                navigationIcon = {
                    IconButton(onClick = { if (isEditMode) onEvent(DetailEvent.ChangeIsEditMode) else goBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_to_list)
                        )
                    }
                },
                actions = {
                    ElevatedButton(
                        enabled = !isEditMode,
                        onClick = { onEvent(DetailEvent.ChangeHidePercentage) }) {
                        Text(text = hidePercent.toString().plus(" %"))
                    }
                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_std)))
                    ElevatedButton(onClick = { onEvent(DetailEvent.ChangeIsEditMode) }) {
                        Icon(
                            imageVector = if (isEditMode) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = if (isEditMode) stringResource(R.string.save) else stringResource(
                                R.string.edit
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (isEditMode) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_std))
                            .fillMaxWidth(),
                        value = poemName,
                        placeholder = {
                            Text(stringResource(R.string.placeholder_title))
                        },
                        onValueChange = { onEvent(DetailEvent.ChangePoemName(it)) })
                    Spacer(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_std)))
                    TextField(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_std))
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        value = poemText,
                        placeholder = {
                            Text(stringResource(R.string.text_to_remember))
                        },
                        onValueChange = { onEvent(DetailEvent.ChangePoemText(it)) })
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_std))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.number_of_opened, numberOpened),
                        textAlign = TextAlign.Right
                    )
                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_std)))
                    Text(
                        text = buildAnnotatedString {
                            for (annotatedWord in poemWords) {
                                if (annotatedWord.word.isNotEmpty()) {
                                    val textStyle =
                                        if (annotatedWord.isHided) SpanStyle(
                                            background = MaterialTheme.colorScheme.onBackground,
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        ) else SpanStyle(
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        )
                                    withLink(
                                        LinkAnnotation.Clickable(
                                            tag = annotatedWord.word,
                                            styles = TextLinkStyles(style = textStyle)
                                        ) {
                                            onEvent(DetailEvent.AnnotatedWordClick(annotatedWord))
                                        }) {
                                        append(annotatedWord.word)
                                    }
                                }
                                append(annotatedWord.delimeter)
                            }
                        },
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val mockPoemWords = listOf(
        PoemWordVisualization(
            word = "Mock",
            isHided = false,
            delimeter = " "
        ),
        PoemWordVisualization(
            word = "Word",
            isHided = true,
            delimeter = ""
        )
    )
    val mockPoemText = "This is a preview of the poem."
    val mockPoemName = "Mock Poem"

    PoemMasterTheme {
        DetailScreen(
            poemName = mockPoemName,
            poemWords = mockPoemWords,
            poemText = mockPoemText,
            isEditMode = false,
            hidePercent = 50,
            numberOpened = 1,
            onEvent = {},
            goBack = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreviewDark() {
    val mockPoemWords = listOf(
        PoemWordVisualization(
            word = "Mock",
            isHided = false,
            delimeter = " "
        ),
        PoemWordVisualization(
            word = "Word",
            isHided = true,
            delimeter = ""
        )
    )
    val mockPoemText = "This is a preview of the poem."
    val mockPoemName = "Mock Poem"

    PoemMasterTheme {
        DetailScreen(
            poemName = mockPoemName,
            poemWords = mockPoemWords,
            poemText = mockPoemText,
            isEditMode = false,
            hidePercent = 50,
            numberOpened = 1,
            onEvent = {},
            goBack = {}
        )
    }
}
