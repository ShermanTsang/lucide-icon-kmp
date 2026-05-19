# 计划：LucideIconPicker 去 Material + 样式抽象

## Summary
- 目标：`LucideIconPicker` 组件内部不再使用 `androidx.compose.material3`（搜索框、分类筛选、网格文字等），改用原生 Compose（`foundation/layout/runtime/ui`）实现。
- 同时把颜色、大小、背景、间距、圆角、文字样式等抽象成可复用的 `Style` 配置对象，便于不同项目/主题复用与定制。
- 保持现有对外 API 尽量兼容：现有参数仍可用，新加 `style`（带默认值）提供更强的扩展能力。

## Current State Analysis
- `LucideIconPicker` 由 3 个内部子组件组成：
  - [LucideIconSearchBar](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconSearchBar.kt)：使用 `material3.OutlinedTextField` + `material3.Text`
  - [LucideIconCategoryTabs](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconCategoryTabs.kt)：使用 `material3.FilterChip` + `material3.Text`
  - [LucideIconGrid](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconGrid.kt)：使用 `material3.Text`
- `LucideIconGrid` 内部使用 `LucideIcon` 渲染图标；`LucideIcon` 本身仍基于 `material3.Icon`（本次范围明确为“仅 Picker 子组件”，因此保留复用）。
- 目前可定制项偏少：
  - 仅暴露 `columns/iconSize/iconColor/strokeWidth/showSearchBar/showCategories` 等，缺少背景、圆角、选中态、间距、文字样式、文案等抽象。

## Assumptions & Decisions
- 范围决策：只保证 `LucideIconPicker` 及其内部子组件不依赖 Material；允许继续复用现有 `LucideIcon`（其内部仍用 Material3）。
- 样式 API 决策：采用 `Style` 配置对象（推荐项），不走“新增大量扁平参数”的路线。
- 兼容性策略：
  - 不删除现有函数与参数。
  - 给 `LucideIconPicker(...)` 新增 `style: LucideIconPickerStyle = LucideIconPickerDefaults.style()` 参数（有默认值），从而不要求调用方修改现有调用。

## Proposed Changes

### 1) 新增 Style/Defaults 抽象
**新增文件**
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPickerStyle.kt`
  - 定义 `LucideIconPickerStyle`（data class），用于承载所有可定制样式。
  - 建议结构（保持简洁且可扩展）：
    - `containerPadding: PaddingValues`
    - `containerVerticalSpacing: Dp`
    - `containerBackgroundColor: Color`（默认 `Color.Unspecified`，避免强制背景）
    - `containerShape: Shape`（默认 `RectangleShape`）
    - `searchBar: LucideIconPickerSearchBarStyle`
    - `categories: LucideIconPickerCategoryTabsStyle`
    - `grid: LucideIconPickerGridStyle`
  - `LucideIconPickerSearchBarStyle`：
    - `height: Dp`
    - `shape: Shape`
    - `backgroundColor: Color`
    - `borderColor: Color`
    - `borderWidth: Dp`
    - `contentPadding: PaddingValues`
    - `labelText: String`
    - `labelTextStyle: TextStyle`
    - `textStyle: TextStyle`
    - `cursorColor: Color`
  - `LucideIconPickerCategoryTabsStyle`：
    - `chipSpacing: Dp`
    - `chipContentPadding: PaddingValues`
    - `chipShape: Shape`
    - `chipBorderWidth: Dp`
    - `unselectedBackgroundColor / unselectedBorderColor / unselectedContentColor`
    - `selectedBackgroundColor / selectedBorderColor / selectedContentColor`
    - `allLabelText: String`
    - `textStyle: TextStyle`
  - `LucideIconPickerGridStyle`：
    - `horizontalSpacing: Dp`
    - `verticalSpacing: Dp`
    - `itemPadding: PaddingValues`
    - `itemVerticalSpacing: Dp`
    - `labelTextStyle: TextStyle`
- `lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPickerDefaults.kt`
  - `object LucideIconPickerDefaults` 提供 `style(...)` 工厂方法，内置默认值，默认视觉尽量接近现状（16dp padding、12dp 间距等）。
  - 默认文案与现有保持一致：`"Search icons"`、`"All"`。

**为什么这样做**
- 避免在 `LucideIconPicker(...)` 上不断堆叠参数。
- `Style` 可被外部复用（多页面同一风格）并可按需局部 copy 覆盖。
- 默认值对齐当前 UI，降低升级成本。

### 2) Picker 子组件去 Material

**修改文件**
- [LucideIconSearchBar.kt](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconSearchBar.kt)
  - 将 `OutlinedTextField` 替换为 `BasicTextField` + `decorationBox`。
  - 渲染 label 文案使用 `foundation.text.BasicText`（或 `Text` 若 `ui-text` 中可用但避免 material）。
  - 按 `LucideIconPickerSearchBarStyle` 绘制：
    - `clip(style.shape)` + `background(style.backgroundColor)`（当非 Unspecified 时）
    - `border(style.borderWidth, style.borderColor, style.shape)`
    - `padding(style.contentPadding)`
    - `cursorBrush = SolidColor(style.cursorColor)`
  - 新增参数 `style: LucideIconPickerSearchBarStyle`（默认从 `LucideIconPickerStyle` 传入）。

- [LucideIconCategoryTabs.kt](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconCategoryTabs.kt)
  - 将 `FilterChip` 替换为 foundation 组合实现：
    - `Row(modifier.horizontalScroll(...), horizontalArrangement = Arrangement.spacedBy(style.chipSpacing))`
    - 每个 chip：`Box(Modifier.clip(shape).background(...).border(...).clickable {...}.padding(style.chipContentPadding))`
    - label 使用 `BasicText` 并根据 selected 切换颜色与 `TextStyle.color`。
  - 新增参数 `style: LucideIconPickerCategoryTabsStyle`。

- [LucideIconGrid.kt](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconGrid.kt)
  - 将 `material3.Text` 替换为 `foundation.text.BasicText`。
  - spacing/padding 等改从 `LucideIconPickerGridStyle` 读取：
    - `horizontalArrangement = Arrangement.spacedBy(style.horizontalSpacing)`
    - `verticalArrangement = Arrangement.spacedBy(style.verticalSpacing)`
    - item：`padding(style.itemPadding)` + `Arrangement.spacedBy(style.itemVerticalSpacing)`
  - 保持 `LucideIcon` 复用不变（符合范围决策）。

### 3) Picker 入口接入 style
**修改文件**
- [LucideIconPicker.kt](file:///d:/Code/Project/lucide-icon-kotlin@kmp/lucide-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/compose/picker/LucideIconPicker.kt)
  - 在两个 `LucideIconPicker` 重载中新增参数：
    - `style: LucideIconPickerStyle = LucideIconPickerDefaults.style()`
  - Column 容器：
    - padding 改为 `style.containerPadding`
    - 垂直间距改为 `style.containerVerticalSpacing`
    - 可选背景：当 `style.containerBackgroundColor != Color.Unspecified` 时，应用 `clip(style.containerShape).background(style.containerBackgroundColor)`
  - 调用子组件时把对应子 style 传入。
  - 保持现有参数（`columns/iconSize/iconColor/strokeWidth/showSearchBar/showCategories`）作为业务参数继续使用，不强制迁移到 style；style 负责“容器/输入/筛选/文字/背景/间距”等 UI 层视觉。

### 4) 更新示例（可选但建议）
**修改文件**
- [IconPickerDemoScreen.kt](file:///d:/Code/Project/lucide-icon-kotlin@kmp/sample-compose/src/commonMain/kotlin/io/github/lucideicons/kmp/sample/screens/IconPickerDemoScreen.kt)
  - 新增一个示例 style 覆盖（例如更紧凑/更大圆角/自定义背景色），演示如何适配不同场景。

## Verification Steps
- 编译验证：
  - `./gradlew :lucide-compose:compileKotlinMetadata`
  - `./gradlew :sample-compose:compileKotlinMetadata`
- 测试验证（现有状态测试应保持通过）：
  - `./gradlew :lucide-compose:commonTest`
- 手工验证（运行 sample）：
  - 打开 `sample-compose` 的 Icon Picker Demo，确认：
    - 搜索输入可编辑、单行、label 显示正常
    - 分类筛选可横向滚动且选中态变化正常
    - 网格点击回调正常
    - 应用自定义 style 后间距/背景/圆角/颜色符合预期

