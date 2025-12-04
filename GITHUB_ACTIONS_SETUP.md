# GitHub Actions CI/CD Setup Guide

This guide explains how to set up automated builds and releases for your Android app using GitHub Actions.

## What Was Configured

### 1. GitHub Actions Workflow
**File**: `.github/workflows/build-and-release.yml`

This workflow automatically:
- Builds your Android app (APK and AAB)
- Signs the release builds with your keystore
- Uploads artifacts to GitHub
- Creates GitHub releases with version tags

**Triggers**:
- Automatic: On every push to `main` branch
- Manual: Via workflow_dispatch in GitHub Actions tab

### 2. App Signing Configuration
**File**: `app/build.gradle.kts`

Added `signingConfigs` block that reads credentials from environment variables:
- `KEYSTORE_PATH`: Path to keystore file
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: Key alias name
- `KEY_PASSWORD`: Key password

## Required GitHub Secrets

You need to add the following secrets to your GitHub repository:

### How to Add Secrets:
1. Go to your GitHub repository: https://github.com/Dheeraj620339/Dheeraj
2. Click **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret** for each of the following:

### Secrets to Add:

#### 1. `KEYSTORE_BASE64`
Your keystore file encoded in Base64.

**To generate this:**
```bash
# On Windows (PowerShell):
[Convert]::ToBase64String([IO.File]::ReadAllBytes("path\to\your-keystore.jks"))

# On Linux/Mac:
base64 -w 0 path/to/your-keystore.jks
```

Copy the entire output and paste it as the secret value.

#### 2. `KEYSTORE_PASSWORD`
The password you use to access your keystore file.

#### 3. `KEY_ALIAS`
The alias name of your signing key within the keystore.

#### 4. `KEY_PASSWORD`
The password for your specific key (may be the same as keystore password).

## Creating a Keystore (If You Don't Have One)

If you don't have a keystore yet, create one:

```bash
keytool -genkey -v -keystore my-release-key.keystore -alias my-key-alias -keyalg RSA -keysize 2048 -validity 10000
```

**Important**: 
- Save your keystore file securely
- Remember your passwords and alias
- **Never commit the keystore to Git!**

## How It Works

1. **Push to main branch** triggers the workflow
2. Workflow decodes your Base64 keystore
3. Gradle builds and signs APK and AAB using environment variables
4. Artifacts are uploaded to GitHub Actions
5. A new release is created with tag `v1.0` (based on versionName)

## Next Release

To create a new release:
1. Update `versionName` in `app/build.gradle.kts` (e.g., "1.0" → "1.1")
2. Commit and push to `main`
3. Workflow automatically creates release `v1.1`

## Verifying the Setup

After adding secrets and pushing to `main`:
1. Go to **Actions** tab in your GitHub repo
2. You should see the workflow running
3. Once complete, check **Releases** for your APK and AAB files

## Files Modified

- ✅ Created: `.github/workflows/build-and-release.yml`
- ✅ Modified: `app/build.gradle.kts` (added signingConfigs)
