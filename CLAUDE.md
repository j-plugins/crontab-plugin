# CLAUDE.md - Crontab Plugin for JetBrains IDEs

## Project Overview

A JetBrains IDE plugin providing crontab file language support: syntax highlighting, schedule validation, human-readable descriptions, shell command injection, crontab.guru integration, and more. Published on the JetBrains Marketplace as "Cron & Crontab Support" (ID: `com.github.xepozz.crontab`).

## Build & Development Commands

```bash
# Build the plugin
./gradlew buildPlugin

# Run tests
./gradlew check

# Run the IDE with plugin loaded (for manual testing)
./gradlew runIde

# Verify plugin compatibility with IDE versions
./gradlew verifyPlugin

# Run Qodana code inspections
./gradlew qodana

# Generate code coverage report (Kover)
./gradlew koverXmlReport
```

- **JDK**: 17 (required)
- **Gradle**: 9.2.1 (use `./gradlew` wrapper)
- **Kotlin**: 2.3.0, targeting JVM 17
- **Target IDE**: PhpStorm (PS) 2025.1.1, since build 242
- **Bundled plugin dependency**: `com.jetbrains.sh` (Shell Script)
- **Marketplace plugin dependency**: `com.jetbrains.hackathon.indices.viewer`

## Project Structure

```
src/main/
├── kotlin/com/github/xepozz/crontab/
│   ├── CrontabBundle.kt          # i18n message bundle accessor
│   ├── CrontabIcons.kt           # Plugin icon references
│   ├── language/                  # Language definition layer
│   │   ├── CrontabLanguage.kt    # Language singleton
│   │   ├── CrontabFileType.kt    # File type (.crontab, .cron)
│   │   ├── CrontabFile.kt        # PSI file wrapper
│   │   ├── CrontabAnnotator.kt   # Semantic highlighting (schedule, vars)
│   │   ├── CrontabSyntaxHighlighter.kt        # Token-level highlighting
│   │   ├── CrontabSyntaxHighlighterFactory.kt
│   │   ├── CrontabLanguageInjector.kt          # Shell language injection into commands
│   │   ├── parser/
│   │   │   ├── Crontab.bnf       # Grammar (GrammarKit BNF) — defines PSI tree
│   │   │   ├── Crontab.flex      # Lexer (JFlex) — tokenizer with SCHEDULE/COMMAND/VARIABLE states
│   │   │   ├── CrontabParserDefinition.kt
│   │   │   └── CrontabLexerAdapter.kt
│   │   └── psi/                   # PSI element types and utilities
│   │       ├── CrontabElementType.kt
│   │       ├── CrontabTokenType.kt
│   │       ├── CrontabTokenSets.kt
│   │       ├── CrontabElementFactory.kt   # Creates PSI elements programmatically
│   │       ├── CrontabPsiTreeUtils.kt
│   │       ├── CrontabRecursiveVisitor.kt
│   │       └── impl/              # PSI base implementations for grammar rules
│   │           ├── CrontabImplUtil.kt
│   │           ├── CrontabScheduleBaseImpl.kt
│   │           ├── CrontabCommandBaseImpl.kt
│   │           ├── CrontabVariableBaseImpl.kt
│   │           ├── CrontabTimeRangeBaseImpl.kt
│   │           └── CrontabTimeShortcutBaseImpl.kt
│   └── ide/                       # IDE integration features
│       ├── CronScheduleFoldingBuilder.kt      # Folds schedule into human-readable text
│       ├── CrontabCommenter.kt                # Comment/uncomment support (#)
│       ├── CrontabGuruUtils.kt                # crontab.guru URL generation
│       ├── CrontabInlayHintsProvider.kt       # Inline hints for cron expressions in strings
│       ├── CrontabInlayHintsProviderFactory.kt
│       ├── CrontabRunCommandMarkerContributor.kt  # Run gutter icon for commands
│       ├── CrontabTimeRangeUtil.kt            # Range collapsing utility
│       ├── actions/
│       │   ├── CrontabGuruIntention.kt        # Intention: open in crontab.guru
│       │   ├── OpenCrontabGuruAction.kt       # Editor action: open in crontab.guru
│       │   └── CrontabRunCommandAction.kt     # Run shell command action
│       ├── completion/
│       │   └── CrontabShortcutCompletionContributor.kt  # @daily, @hourly etc.
│       ├── describe/
│       │   └── CronScheduleDescriber.kt       # Converts cron expression to human text
│       ├── documentation/
│       │   ├── CrontabDocumentationProvider.kt
│       │   ├── CrontabDocumentationUtils.kt
│       │   └── PsiCommentDelegate.kt
│       ├── editor/
│       │   └── EditorNotificationProvider.kt  # Rate plugin promo banner
│       ├── inspections/
│       │   ├── CronPatterns.kt                # Regex patterns for valid months/days
│       │   ├── CrontabInspectionUtil.kt       # Registers inspection problems with quick fixes
│       │   ├── CrontabScheduleTimeRangeInspection.kt  # Validates time ranges per field
│       │   └── CrontabScheduleQuickFix.kt     # Base class for quick fixes
│       └── structureView/
│           ├── CrontabStructureViewFactory.kt
│           ├── CrontabStructureViewModel.kt
│           └── RecursiveStructureViewElement.kt
├── gen/                           # Generated parser/lexer/PSI (in .gitignore)
└── resources/
    ├── META-INF/
    │   ├── plugin.xml             # Plugin descriptor (extensions, actions, dependencies)
    │   ├── pluginIcon.svg
    │   └── pluginIcon_dark.svg
    ├── icons/                     # Icon assets
    ├── messages/
    │   └── CrontabBundle.properties  # i18n strings (English)
    └── intentionDescriptions/     # IDE intention descriptions
```

Other directories:
- `playground/` — Sample `.cron` and `crontab` files for manual testing
- `docs/screenshots/` — Screenshot assets for README
- `.github/workflows/` — CI/CD (release, Junie)
- `.github/non-workflows/` — Archived/disabled workflow definitions (build, UI tests)
- `gradle/libs.versions.toml` — Gradle version catalog

## Architecture

### Custom Language Implementation

The plugin implements a full IntelliJ custom language for crontab files:

1. **Lexer** (`Crontab.flex`): JFlex-based tokenizer with stateful modes — `YYINITIAL` (start of line), `SCHEDULE` (time fields), `COMMAND` (shell command), `VARIABLE` (environment variable definitions), `SIMPLE_SYNTAX` (`@daily` shortcuts)
2. **Parser** (`Crontab.bnf`): GrammarKit BNF grammar defining the PSI tree. A crontab file contains `cron_expression` (schedule + command) and `variable_definition` entries
3. **Generated code**: Parser, lexer, PSI classes, and visitor are generated into `src/main/gen/` (gitignored). Regenerate via GrammarKit IDE plugin

### Grammar Structure (Crontab.bnf)

```
crontab_file ::= (variable_definition | cron_expression | comment | newline)*
cron_expression ::= schedule command
schedule ::= (minute hour day month weekday) | time_shortcut
time_shortcut ::= @daily | @hourly | @weekly | @monthly | @yearly | @annually | @reboot
variable_definition ::= name = value
```

Each time field supports: exact numbers, wildcards (`*`), ranges (`1-5`), steps (`*/2`, `1-10/2`), lists (`1,2,3`), weekday names (MON-SUN), month names (JAN-DEC).

### Key Design Patterns

- **PSI base classes** in `psi/impl/`: Grammar rules extend custom base classes (`CrontabScheduleBaseImpl`, `CrontabCommandBaseImpl`, etc.) to add behavior to generated PSI elements
- **Shell language injection**: `CrontabLanguageInjector` injects Shell Script language into `CrontabCommand` elements, enabling shell completion/highlighting inside crontab commands
- **Inlay hints injection**: `CrontabInlayHintsProvider` detects cron expressions inside string literals across all languages and shows human-readable descriptions as inline hints
- **Element factory**: `CrontabElementFactory` creates PSI elements by parsing dummy crontab text, used by quick fixes and inspections

### Inspection System

`CrontabScheduleTimeRangeInspection` validates:
- Minute: 0-59, Hour: 0-23, Day: 1-31, Month: 1-12, Weekday: 0-7
- Named weekdays and months match valid patterns
- Ranges where first > second (offers swap quick fix)
- Ranges where first == second (offers simplify quick fix)
- Lists that can be collapsed into ranges

## Key Conventions

- **Package root**: `com.github.xepozz.crontab`
- **Language layer** (`language/`): Core parsing, PSI, file types — no IDE dependencies beyond `com.intellij.lang`
- **IDE layer** (`ide/`): All IDE integrations (inspections, intentions, folding, completion, inlay hints, structure view)
- **i18n**: All user-facing strings go through `CrontabBundle.properties` via `CrontabBundle.message()`
- **No Java source**: Pure Kotlin project (except generated code in `src/main/gen/`)
- **Generated code is gitignored**: `src/main/gen/` is not committed; regenerate from `.bnf` and `.flex` files using GrammarKit
- **Plugin extensions**: Registered in `plugin.xml` under `com.intellij` namespace
- **Icons**: SVG format in `src/main/resources/icons/`

## Testing

- Test framework: JUnit 4 + IntelliJ Platform test framework (`TestFrameworkType.Platform`)
- Tests go in `src/test/` (currently no test files committed)
- Run via `./gradlew check`
- Code coverage via Kover plugin, XML report generated on check

## CI/CD

- **Release** (`.github/workflows/release.yml`): Manual workflow dispatch. Auto-increments patch version, publishes to JetBrains Marketplace, creates GitHub release with tag
- **Build** (`.github/non-workflows/build.yml`): Currently disabled. Would run on push to main and PRs: build, test, Qodana inspections, plugin verification, draft release
- **Junie** (`.github/workflows/junie.yml`): JetBrains Junie AI integration

## Version Scheme

- Format: `YYYY.MAJOR.PATCH` (e.g., `2026.1.0`)
- Configured in `gradle.properties` as `pluginVersion`
- `pluginSinceBuild=242` — minimum supported IDE build number
