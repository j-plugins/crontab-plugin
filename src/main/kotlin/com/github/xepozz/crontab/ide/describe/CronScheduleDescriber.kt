package com.github.xepozz.crontab.ide.describe

import com.github.xepozz.crontab.CrontabBundle.message
import com.intellij.util.containers.addIfNotNull

object CronScheduleDescriber {
    fun asHumanReadable(cron: String): String {
        when (cron) {
            "@hourly" -> return message("crontab.schedule.hourly")
            "@daily" -> return message("crontab.schedule.daily")
            "@weekly" -> return message("crontab.schedule.weekly")
            "@monthly" -> return message("crontab.schedule.monthly")
            "@yearly" -> return message("crontab.schedule.yearly")
            "@annually" -> return message("crontab.schedule.annually")
            "@reboot" -> return message("crontab.schedule.reboot")
        }

        val fields = cron.trim().split("\\s+".toRegex())

//        val (minute, hour, dayOfMonth, month, dayOfWeek) = fields
        val minute = fields.getOrNull(0) ?: "*"
        val hour = fields.getOrNull(1) ?: "*"
        val dayOfMonth = fields.getOrNull(2) ?: "*"
        val month = fields.getOrNull(3) ?: "*"
        val dayOfWeek = fields.getOrNull(4) ?: "*"

        return buildList {
            val hourMinuteSpecialCase = when {
                minute == "*" && hour == "*" -> message("crontab.schedule.each minute")
                minute == "*" && hour.isNumeric() -> message("crontab.schedule.every minute at {0}", hour)
                minute.isNumeric() && hour == "*" -> message("crontab.schedule.at minute {0} each hour", minute)
                minute.isNumeric() && hour.isNumeric() -> {
                    val padHour = hour.padStart(2, '0')
                    val padMinute = minute.padStart(2, '0')
                    message("crontab.schedule.at {0}:{1}", padHour, padMinute)
                }

                else -> null
            }
            addIfNotNull(hourMinuteSpecialCase)
            if (hourMinuteSpecialCase == null) {
                // Minute
                add(
                    when {
                        minute.matches(Regex("\\*/\\d+")) -> message(
                            "crontab.schedule.every {0} minute",
                            minute.substringAfter("/")
                        )

                        else -> message("crontab.schedule.at minute {0}", minute)
                    }
                )

                // Hour
                add(
                    when {
                        hour.matches(Regex("\\*/\\d+")) -> message(
                            "crontab.schedule.every {0} hour",
                            hour.substringAfter("/")
                        )

                        else -> message("crontab.schedule.past hour {0}", hour)
                    }
                )
            }

            // Day of month
            if (dayOfMonth != "*") {
                add(
                    when {
                        dayOfMonth.matches(Regex("\\*/\\d+")) -> message(
                            "crontab.schedule.every {0} day",
                            dayOfMonth.substringAfter("/")
                        )
                        else -> message("crontab.schedule.on day {0} of month", dayOfMonth)
                    }
                )
            }

            // Month
            if (month != "*") {
                add(
                    when {
                        month.matches(Regex("\\*/\\d+")) -> message(
                            "crontab.schedule.every {0} month",
                            month.substringAfter("/")
                        )
                        else -> message("crontab.schedule.in month {0}", month)
                    }
                )
            }

            // Day of week
            if (dayOfWeek != "*") {
                add(
                    when (dayOfWeek) {
                        "0", "7" -> message("crontab.days.on Sundays")
                        "1" -> message("crontab.days.on Mondays")
                        "2" -> message("crontab.days.on Tuesdays")
                        "3" -> message("crontab.days.on Wednesdays")
                        "4" -> message("crontab.days.on Thursdays")
                        "5" -> message("crontab.days.on Fridays")
                        "6" -> message("crontab.days.on Saturdays")
                        else -> message("crontab.days.on specific days {0}", dayOfWeek)
                    }
                )
            }
        }.joinToString(" ")
    }
}

fun String.isNumeric() = all { it.isDigit() }
fun String.isWildcard() = startsWith("*/")