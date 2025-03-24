package com.github.kutyrev.poemmaster.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.github.kutyrev.poemmaster.R
import com.github.kutyrev.poemmaster.model.PoemWordVisualization
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailEvent
import com.github.kutyrev.poemmaster.ui.screens.detail.model.SPACE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    poemName: String,
    poemWords: List<PoemWordVisualization>,
    poemText: String,
    isEditMode: Boolean,
    hidePercent: Int,
    onEvent: (DetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(poemName)
                },
                navigationIcon = {
                    IconButton(onClick = { TODO() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_to_list)
                        )
                    }
                },
                actions = {
                    OutlinedButton(onClick = { onEvent(DetailEvent.ChangeHidePercentage) }) {
                        Text(text = hidePercent.toString().plus(" %"))
                    }
                    Text(text = stringResource(R.string.edit_mode))
                    Switch(
                        checked = isEditMode,
                        onCheckedChange = { onEvent(DetailEvent.ChangeIsEditMode) })
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (isEditMode) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        value = poemName,
                        onValueChange = { onEvent(DetailEvent.ChangePoemName(it)) })
                    Spacer(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_std)))
                    TextField(
                        value = poemText,
                        onValueChange = { onEvent(DetailEvent.ChangePoemText(it)) })
                }
            } else {
                Text(
                    buildAnnotatedString {
                        for (annotatedWord in poemWords) {

                            withStyle(style = SpanStyle()) { }

                            val textStyle =
                                if (annotatedWord.isHided) SpanStyle(background = MaterialTheme.typography.bodyMedium.color) else SpanStyle()
                            withLink(
                                LinkAnnotation.Clickable(
                                    tag = annotatedWord.word,
                                    styles = TextLinkStyles(style = textStyle)
                                ) { onEvent(DetailEvent.AnnotatedWordClick(annotatedWord)) }) {
                                append(annotatedWord.word + SPACE)
                            }
                        }
                    }
                )
            }
        }
    }
}