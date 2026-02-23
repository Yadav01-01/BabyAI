package com.compose.babyai.ui.component.calender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.compose.babyai.R
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalenderModal(
    onDismiss: () -> Unit,
    onDatesSelected: (LocalDate?, LocalDate?) -> Unit
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }
    var showYearSelector by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(40.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Header: Icon, "Select Date", Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.47.dp)
                            .clip(CircleShape)
                            .background(PrimaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_month_date_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.27.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Select Date",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                        fontWeight = FontWeight.Medium,
                        color = Color(0XFF050505),
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.cal_cross),
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable { onDismiss() },
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Month/Year Selector and Navigation Arrows
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { showYearSelector = !showYearSelector }
                    ) {
                        Text(
                            text = if (showYearSelector) "${currentMonth.year}" else "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                            fontWeight = FontWeight.Medium,
                            color = Color(0XFF3C3C3C)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Select Year",
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    if (!showYearSelector) {
                        Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                            MonthNavButton(
                                icon = R.drawable.left_y_ic,
                                onClick = { currentMonth = currentMonth.minusMonths(1) }
                            )
                            MonthNavButton(
                                icon = R.drawable.right_y_ic,
                                onClick = {
                                    val nextMonth = currentMonth.plusMonths(1)
                                    if (!nextMonth.isAfter(YearMonth.now())) {
                                        currentMonth = nextMonth
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (showYearSelector) {
                    YearSelector(
                        selectedYear = currentMonth.year,
                        onYearSelected = { year ->
                            currentMonth = YearMonth.of(year, currentMonth.month)
                            showYearSelector = false
                        }
                    )
                } else {
                    // Day Names Row
                    DayNamesRow()

                    Spacer(modifier = Modifier.height(8.dp))

                    // Calendar Grid
                    WeeksGrid(
                        month = currentMonth,
                        startDate = startDate,
                        endDate = endDate,
                        onDateSelected = { date ->
                            when {
                                startDate == null || (startDate != null && endDate != null) -> {
                                    startDate = date
                                    endDate = null
                                }
                                date.isBefore(startDate) -> {
                                    startDate = date
                                }
                                else -> {
                                    endDate = date
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Action Buttons: Cancel, Apply
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .height(46.dp)
                            .padding(end = 12.dp),
                        shape = RoundedCornerShape(38.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 13.5.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0XFF181B1A)
                        )
                    }

                    Button(
                        onClick = { onDatesSelected(startDate, endDate) },
                        modifier = Modifier
                            .height(46.dp),
                        shape = RoundedCornerShape(38.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                    ) {
                        Text(
                            text = "Apply",
                            fontSize = 13.5.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearSelector(
    selectedYear: Int,
    onYearSelected: (Int) -> Unit
) {
    val currentYear = LocalDate.now().year
    val years = (currentYear - 100..currentYear).reversed().toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(250.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(years) { year ->
            val isSelected = year == selectedYear
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) PrimaryColor else Color.Transparent)
                    .clickable { onYearSelected(year) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = year.toString(),
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_medium))
                )
            }
        }
    }
}

@Composable
fun MonthNavButton(
    icon: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
private fun DayNamesRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT").forEach { day ->
            Text(
                text = day,
                color = Color(0xFF697383),
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeeksGrid(
    month: YearMonth,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val weeks = remember(month) { generateCalendarWeeks(month) }

    Column(modifier = Modifier.fillMaxWidth()) {
        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { date ->
                    val isSelected = date != null && (date == startDate || date == endDate)
                    val isInRange = date != null && startDate != null && endDate != null &&
                            date.isAfter(startDate) && date.isBefore(endDate)

                    DayCell(
                        date = date,
                        isCurrentMonth = date?.month == month.month,
                        isSelected = isSelected,
                        isInRange = isInRange,
                        isToday = date == LocalDate.now(),
                        onClick = { if (date != null) onDateSelected(date) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DayCell(
    date: LocalDate?,
    isCurrentMonth: Boolean,
    isSelected: Boolean,
    isInRange: Boolean,
    isToday: Boolean,
    onClick: () -> Unit
) {
    val isFuture = date?.isAfter(LocalDate.now()) ?: false
    val isEnabled = date != null && !isFuture

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
                when {
                    isSelected -> PrimaryColor
                    isInRange -> PrimaryColor.copy(alpha = 0.2f)
                    else -> Color.Transparent
                }
            )
            .clickable(enabled = isEnabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (date != null) {
            Text(
                text = date.dayOfMonth.toString(),
                color = when {
                    isSelected -> Color.White
                    isFuture -> Color(0xFFC5C5C7)
                    !isCurrentMonth -> Color(0xFFC5C5C7)
                    else -> Color.Black
                },
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun generateCalendarWeeks(yearMonth: YearMonth): List<List<LocalDate?>> {
    val weeks = mutableListOf<List<LocalDate?>>()
    var week = mutableListOf<LocalDate?>()

    val firstDayOfMonth = yearMonth.atDay(1)
    val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value % 7 // Convert to Sunday = 0

    // Add empty slots for the first week
    for (i in 0 until dayOfWeekValue) {
        week.add(null)
    }

    // Add current month's days
    for (day in 1..yearMonth.lengthOfMonth()) {
        week.add(yearMonth.atDay(day))
        if (week.size == 7) {
            weeks.add(week)
            week = mutableListOf()
        }
    }

    // Add empty slots to fill the last week
    if (week.isNotEmpty()) {
        while (week.size < 7) {
            week.add(null)
        }
        weeks.add(week)
    }

    return weeks
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalenderModalPreview() {
    BabyAITheme {
        CalenderModal(
            onDismiss = {},
            onDatesSelected = { _, _ -> }
        )
    }
}
