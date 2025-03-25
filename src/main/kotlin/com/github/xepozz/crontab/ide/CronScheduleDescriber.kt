package com.github.xepozz.crontab.ide

object CronScheduleDescriber {
    fun asHumanReadable(cron: String): String {
        when (cron) {
            "@hourly" -> return "Every hour"
            "@daily" -> return "Every day"
            "@weekly" -> return "Every week"
            "@monthly" -> return "Every month"
            "@yearly" -> return "Every year"
            "@annually" -> return "Every year"
            "@reboot" -> return "After machine boot"
        }

        val fields = cron.trim().split("\\s+".toRegex())
        if (fields.size != 5) {
            throw IllegalArgumentException("Invalid cron expression. Expected 5 fields, got ${fields.size} in: \"$cron\"")
        }

        val (minute, hour, dayOfMonth, month, dayOfWeek) = fields

        return buildString {
            append("Runs ")

            // Minute
            append(
                when (minute) {
                    "*" -> "every minute"
                    else -> "at minute $minute"
                }
            )

            // Hour
            append(
                when (hour) {
                    "*" -> ""
                    else -> " past hour $hour"
                }
            )

            append(" ")

            // Day of month
            append(
                when (dayOfMonth) {
                    "*" -> "every day"
                    else -> "on day $dayOfMonth of the month"
                }
            )

            append(" ")

            // Month
            append(
                when (month) {
                    "*" -> "in every month"
                    else -> "in $month"
                }
            )

            append(" ")

            // Day of week
            append(
                when (dayOfWeek) {
                    "*" -> ""
                    "0", "7" -> "on Sundays"
                    "1" -> "on Mondays"
                    "2" -> "on Tuesdays"
                    "3" -> "on Wednesdays"
                    "4" -> "on Thursdays"
                    "5" -> "on Fridays"
                    "6" -> "on Saturdays"
                    else -> "on specific days ($dayOfWeek)"
                }
            )
        }
    }
}