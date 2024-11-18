package com.example.composeApp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseActivity : ComponentActivity() {

    private val isProgressDialogVisible = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Free memory
     */
    open fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().gc()
        System.gc()
    }

    /**
     * Show a toast message on the main thread
     */
    fun showToast(msg: String?) {
        if (!msg.isNullOrEmpty()) {
            runOnUiThread {
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Show a progress dialog
     */
    open fun showProgressDialog() {
        isProgressDialogVisible.value = true

        // Hide the dialog after 10 seconds automatically
        CoroutineScope(Dispatchers.Main).launch {
            delay(10000)
            hideProgressDialog()
        }
    }

    /**
     * Hide the progress dialog
     */
    open fun hideProgressDialog() {
        isProgressDialogVisible.value = false
    }

    @Composable
    fun ProgressDialog() {
        if (isProgressDialogVisible.value) {
            Dialog(onDismissRequest = {}) {
                androidx.compose.material3.Surface {
                    CircularProgressIndicatorContent()
                }
            }
        }
    }

    @Composable
    private fun CircularProgressIndicatorContent() {
        androidx.compose.material3.CircularProgressIndicator(modifier = androidx.compose.ui.Modifier.padding(16.dp))
    }
}