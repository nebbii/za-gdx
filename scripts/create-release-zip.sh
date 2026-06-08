#!/usr/bin/env bash
set -euo pipefail

if [ $# -lt 1 ]; then
  echo "Usage: $0 <version>"
  echo "Example: $0 0.0.1"
  exit 1
fi

VERSION="$1"

REPO_ROOT="$(git rev-parse --show-toplevel)"
REPO_NAME="$(basename "$REPO_ROOT")"
PARENT_DIR="$(dirname "$REPO_ROOT")"

SOURCE_DIR_NAME="${REPO_NAME}-${VERSION}"
RELEASES_DIR="${REPO_ROOT}/releases"
SOURCE_ZIP_PATH="${RELEASES_DIR}/${SOURCE_DIR_NAME}.zip"

mkdir -p "$RELEASES_DIR"

cd "$PARENT_DIR"

rm -rf "$SOURCE_DIR_NAME"
rm -f "$SOURCE_ZIP_PATH"

rsync -a \
  --filter=':- .gitignore' \
  --exclude='.codex/' \
  --exclude='.skills/' \
  --exclude='assets/export/' \
  --exclude='lwjgl3/bin/' \
  --exclude='venv/' \
  --exclude='releases/' \
  --exclude='.git/logs/' \
  --exclude='.git/hooks/' \
  --exclude='.git/refs/remotes/' \
  "$REPO_NAME/" "$SOURCE_DIR_NAME/"

cd "$SOURCE_DIR_NAME"

git submodule update --init --recursive

cd "$PARENT_DIR"

zip -r "$SOURCE_ZIP_PATH" "$SOURCE_DIR_NAME"

echo "Created: $SOURCE_ZIP_PATH"
