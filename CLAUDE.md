# Crontab Plugin

JetBrains IDE plugin providing crontab/cron syntax support: highlighting, inspections, completions, folding, inlay
hints, and crontab.guru integration.

## Tech Stack

- Kotlin (JVM 17), Gradle (Kotlin DSL)
- IntelliJ Platform Plugin SDK (`org.jetbrains.intellij.platform` plugin)
- GrammarKit: BNF grammar (`Crontab.bnf`) + JFlex lexer (`Crontab.flex`)
- Target platform: PhpStorm (PS), since build 242
- Depends on `com.jetbrains.sh` (Shell Script plugin)

## Project Structure

```
src/main/kotlin/com/github/xepozz/crontab/
  language/          # Language definition: file type, lexer adapter, parser, syntax highlighter, annotator, injector
    parser/          # Crontab.bnf (grammar), Crontab.flex (lexer), parser definition
    psi/             # PSI element types, tokens, visitors, impl utilities, base classes
  ide/               # IDE features
    actions/         # CrontabGuruIntention, OpenCrontabGuruAction, RunCommandAction
    completion/      # CrontabShortcutCompletionContributor
    describe/        # CronScheduleDescriber (human-readable descriptions)
    documentation/   # CrontabDocumentationProvider
    editor/          # EditorNotificationProvider
    inspections/     # CrontabScheduleTimeRangeInspection, CronPatterns, quick fixes
    structureView/   # Structure view factory/model
    Cron*.kt         # Folding builder, commenter, inlay hints, run markers, time range utils
src/main/gen/        # Generated parser & PSI (from BNF/flex, gitignored)
src/main/resources/
  META-INF/plugin.xml   # Plugin descriptor
  messages/             # CrontabBundle (i18n)
playground/          # Sample crontab files for testing
```

## Build & Run

```bash
./gradlew build              # Build the plugin
./gradlew runIde             # Run IDE sandbox with plugin installed
./gradlew test               # Run tests
./gradlew buildPlugin        # Build distributable plugin zip
./gradlew publishPlugin      # Publish to JetBrains Marketplace
```

## Key Conventions

- Generated code lives in `src/main/gen/` and is gitignored. Do NOT edit files there manually. Regenerate from
  `Crontab.bnf` and `Crontab.flex` using GrammarKit/JFlex in IDE.
- PSI base classes (`*BaseImpl.kt`) extend generated PSI and add custom logic (e.g., `CrontabScheduleBaseImpl`,
  `CrontabTimeRangeBaseImpl`).
- Utility methods used by generated PSI are in `CrontabImplUtil.kt` (referenced via `psiImplUtilClass` in BNF).
- Plugin version follows `YYYY.MAJOR.MINOR` format (e.g., `2026.1.1`), set in `gradle.properties`.
- Versions and plugin dependencies are managed in `gradle.properties` and `gradle/libs.versions.toml`.
- Plugin descriptor: `src/main/resources/META-INF/plugin.xml`. Plugin description is extracted from `README.md` between
  `<!-- Plugin description -->` markers.
- i18n strings are in `messages/CrontabBundle`.

## Crontab Grammar

Standard 5-field cron: `minute hour day month weekday command`
Also supports: `@shortcut command`, variable definitions (`KEY=VALUE`), comments (`#`).
Time fields support: exact numbers, ranges (`1-5`), steps (`*/2`, `1-5/2`), lists (`1,3,5`), wildcards (`*`), named
days (`MON-FRI`), named months (`JAN-DEC`).
