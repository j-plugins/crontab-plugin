package com.github.xepozz.crontab.ide

object CronScheduleDescriber {
    fun asHumanReadable(cron: String): String {
        when (cron) {
            "@hourly" -> return "every hour"
            "@daily" -> return "every day"
            "@weekly" -> return "every week"
            "@monthly" -> return "every month"
            "@yearly" -> return "every year"
            "@annually" -> return "every year"
            "@reboot" -> return "after machine boot"
        }

        val fields = cron.trim().split("\\s+".toRegex())

//        val (minute, hour, dayOfMonth, month, dayOfWeek) = fields
        val minute = fields.getOrElse(0, { "*" })
        val hour = fields.getOrElse(1, { "*" })
        val dayOfMonth = fields.getOrElse(2, { "*" })
        val month = fields.getOrElse(3, { "*" })
        val dayOfWeek = fields.getOrElse(4, { "*" })

        return buildList {
            // Minute
            add(
                when {
                    minute == "*" -> "every minute"
                    minute.matches(Regex("\\*/\\d+")) -> "every ${minute.split("/").last()} minute"
                    else -> "at minute $minute"
                }
            )

            // Hour
            add(
                when {
                    hour == "*" -> "every hour"
                    hour.matches(Regex("\\*/\\d+")) -> "every ${hour.split("/").last()} hour"
                    else -> "past hour $hour"
                }
            )

            // Day of month
            if (dayOfMonth != "*") {
                add(
                    when {
                        dayOfMonth.matches(Regex("\\*/\\d+")) -> "every ${dayOfMonth.split("/").last()} day"
                        else -> "on day $dayOfMonth of the month"
                    }
                )
            }

            // Month
            if (month != "*") {
                add(
                    when {
                        month.matches(Regex("\\*/\\d+")) -> "every ${month.split("/").last()} month"
                        else -> "in $month"
                    }
                )
            }

            // Day of week
            if (dayOfWeek != "*") {
                add(
                    when (dayOfWeek) {
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
        }.joinToString(" ")
    }
}