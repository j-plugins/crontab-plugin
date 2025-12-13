package com.github.xepozz.crontab.ide.inspections

object CronPatterns {
    val DAYS = arrayOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    val MONTHS = arrayOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

    val DAYS_PATTERN = DAYS.joinToString("|").let { "^$it$" }.toRegex(RegexOption.IGNORE_CASE)
    val MONTHS_PATTERN = MONTHS.joinToString("|").let { "^$it$" }.toRegex(RegexOption.IGNORE_CASE)
}