# Current Features of the Crontab Plugin

## 1. Basic Language Support
- **File Type Recognition**: Recognizes `.crontab` and `.cron` files as Crontab files
  - [CrontabFileType.kt](src/main/kotlin/com/github/xepozz/crontab/language/CrontabFileType.kt)
  - [plugin.xml](src/main/resources/META-INF/plugin.xml) (file type registration)

- **Syntax Highlighting**: Highlights different elements of crontab files
  - [CrontabSyntaxHighlighter.kt](src/main/kotlin/com/github/xepozz/crontab/language/CrontabSyntaxHighlighter.kt)
  - [CrontabAnnotator.kt](src/main/kotlin/com/github/xepozz/crontab/language/CrontabAnnotator.kt)

- **Parser and PSI Structure**: Provides a parser and PSI structure for crontab files
  - [Crontab.bnf](src/main/kotlin/com/github/xepozz/crontab/language/parser/Crontab.bnf) (grammar definition)
  - [CrontabParserDefinition.kt](src/main/kotlin/com/github/xepozz/crontab/language/parser/CrontabParserDefinition.kt)

- **Comments Support**: Provides support for comments in crontab files
  - [CrontabCommenter.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CrontabCommenter.kt)

## 2. Schedule Visualization and Validation

- **Human-Readable Schedule Folding**: Allows folding cron expressions into human-readable descriptions
  - [CronScheduleFoldingBuilder.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CronScheduleFoldingBuilder.kt)
  - [CronScheduleDescriber.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CronScheduleDescriber.kt)

- **Schedule Validation and Quick Fixes**: Validates crontab schedules and provides quick fixes for common issues
  - [CrontabScheduleTimeRangeInspection.kt](src/main/kotlin/com/github/xepozz/crontab/ide/inspections/CrontabScheduleTimeRangeInspection.kt)
  - [CrontabInspectionUtil.kt](src/main/kotlin/com/github/xepozz/crontab/ide/inspections/CrontabInspectionUtil.kt)
  - [CrontabScheduleQuickFix.kt](src/main/kotlin/com/github/xepozz/crontab/ide/inspections/CrontabScheduleQuickFix.kt)

## 3. Integration with External Tools

- **Crontab.guru Integration**: Allows opening cron schedules in crontab.guru for visualization and explanation
  - [CrontabGuruIntention.kt](src/main/kotlin/com/github/xepozz/crontab/ide/actions/CrontabGuruIntention.kt)
  - [OpenCrontabGuruAction.kt](src/main/kotlin/com/github/xepozz/crontab/ide/actions/OpenCrontabGuruAction.kt)
  - [CrontabGuruUtils.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CrontabGuruUtils.kt)

- **Run Command in Terminal**: Allows running crontab commands directly from the editor
  - [CrontabRunCommandMarkerContributor.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CrontabRunCommandMarkerContributor.kt)
  - [CrontabRunCommandAction.kt](src/main/kotlin/com/github/xepozz/crontab/ide/actions/CrontabRunCommandAction.kt)

## 4. IDE Integration Features

- **Structure View**: Provides a structure view for crontab files
  - [CrontabStructureViewFactory.kt](src/main/kotlin/com/github/xepozz/crontab/ide/structureView/CrontabStructureViewFactory.kt)
  - [CrontabStructureViewModel.kt](src/main/kotlin/com/github/xepozz/crontab/ide/structureView/CrontabStructureViewModel.kt)

- **Documentation**: Provides documentation for crontab elements
  - [CrontabDocumentationProvider.kt](src/main/kotlin/com/github/xepozz/crontab/ide/documentation/CrontabDocumentationProvider.kt)
  - [CrontabDocumentationUtils.kt](src/main/kotlin/com/github/xepozz/crontab/ide/documentation/CrontabDocumentationUtils.kt)

- **Inlay Hints**: Provides inlay hints for crontab expressions in string literals
  - [CrontabInlayHintsProvider.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CrontabInlayHintsProvider.kt)
  - [CrontabInlayHintsProviderFactory.kt](src/main/kotlin/com/github/xepozz/crontab/ide/CrontabInlayHintsProviderFactory.kt)

- **Completion**: Provides completion for crontab shortcuts
  - [CrontabShortcutCompletionContributor.kt](src/main/kotlin/com/github/xepozz/crontab/ide/completion/CrontabShortcutCompletionContributor.kt)

- **Language Injection**: Injects crontab language into string literals in other languages
  - [CrontabLanguageInjector.kt](src/main/kotlin/com/github/xepozz/crontab/language/CrontabLanguageInjector.kt)