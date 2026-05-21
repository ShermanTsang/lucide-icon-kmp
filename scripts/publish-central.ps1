param(
    [Parameter(Mandatory = $true)]
    [ValidateSet("publishCentralSnapshot", "publishCentralRelease", "validateCentralSnapshotConfig", "validateCentralReleaseConfig")]
    [string]$Task
)

$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $PSScriptRoot
$secretsPath = Join-Path $projectRoot ".secrets"
$gradleWrapperPath = Join-Path $projectRoot "gradlew.bat"

if (-not (Test-Path $secretsPath)) {
    throw "Missing local publish config: $secretsPath"
}

$secrets = @{}
Get-Content $secretsPath | ForEach-Object {
    $line = $_.Trim()
    if (-not $line -or $line.StartsWith("#")) {
        return
    }

    $separatorIndex = $line.IndexOf("=")
    if ($separatorIndex -lt 1) {
        return
    }

    $key = $line.Substring(0, $separatorIndex).Trim()
    $value = $line.Substring($separatorIndex + 1)
    $secrets[$key] = $value
}

function Get-SecretValue {
    param(
        [Parameter(Mandatory = $true)]
        [string]$Name,
        [switch]$Required
    )

    $value = if ($secrets.ContainsKey($Name)) { $secrets[$Name].Trim() } else { "" }
    if ($Required -and [string]::IsNullOrWhiteSpace($value)) {
        throw "$Name is required in .secrets"
    }

    return $value
}

$env:ORG_GRADLE_PROJECT_mavenCentralUsername = Get-SecretValue -Name "MAVEN_CENTRAL_USERNAME" -Required
$env:ORG_GRADLE_PROJECT_mavenCentralPassword = Get-SecretValue -Name "MAVEN_CENTRAL_PASSWORD" -Required

$signingKeyBase64 = Get-SecretValue -Name "SIGNING_KEY_BASE64"
$signingPassword = Get-SecretValue -Name "SIGNING_PASSWORD"

if (-not [string]::IsNullOrWhiteSpace($signingKeyBase64)) {
    $signingKeyBytes = [Convert]::FromBase64String($signingKeyBase64)
    $env:ORG_GRADLE_PROJECT_signingInMemoryKey = [Text.Encoding]::UTF8.GetString($signingKeyBytes)
}

if ($Task -in @("publishCentralRelease", "validateCentralReleaseConfig")) {
    if ([string]::IsNullOrWhiteSpace($signingKeyBase64)) {
        throw "SIGNING_KEY_BASE64 is required in .secrets for release publishing."
    }
    if ([string]::IsNullOrWhiteSpace($signingPassword)) {
        throw "SIGNING_PASSWORD is required in .secrets for release publishing."
    }
}

if (-not [string]::IsNullOrWhiteSpace($signingPassword)) {
    $env:ORG_GRADLE_PROJECT_signingInMemoryKeyPassword = $signingPassword
}

Push-Location $projectRoot
try {
    & $gradleWrapperPath $Task
    exit $LASTEXITCODE
} finally {
    Pop-Location
}
