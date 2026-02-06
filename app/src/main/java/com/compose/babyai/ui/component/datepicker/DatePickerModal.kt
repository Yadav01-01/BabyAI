package com.compose.babyai.ui.component.datepicker

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import com.compose.babyai.R

@Composable
fun DatePickerModal(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        R.style.DialogTheme,
        { _, year, month, day ->
            val formattedDate = String.format(
                "%02d-%02d-%04d",
                month + 1,  // Month is 0-based
                day,
                year
            )
            onDateSelected(formattedDate)
            onDismiss()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    datePickerDialog.datePicker.maxDate = calendar.timeInMillis

    // Handle cancel click so UI state resets
    datePickerDialog.setOnCancelListener {
        onDismiss()
    }

    datePickerDialog.show()
}
