package com.flipperdevices.share.export.composable

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flipperdevices.share.common.composable.ComposableAlertDialog
import com.flipperdevices.share.export.R
import com.flipperdevices.share.export.viewmodel.ShareViewModel
import com.flipperdevices.share.export.viewmodel.ShareViewModelFactory
import com.flipperdevices.share.model.ShareFile

@Composable
fun ComposableShare(
    shareFile: ShareFile,
    onCancel: () -> Unit,
    viewModel: ShareViewModel = viewModel(
        key = shareFile.flipperFilePath,
        factory = ShareViewModelFactory(
            shareFile,
            LocalContext.current.applicationContext as Application
        )
    )
) {
    val shareState by viewModel.getShareState().collectAsState()
    if (!shareState.dialogShown) {
        onCancel()
    }
    ComposableAlertDialog(
        title = stringResource(R.string.share_dialog_title, shareFile.name),
        downloadProgress = shareState.downloadProgress,
        onCancel = {
            viewModel.cancelDownload()
        }
    )
}