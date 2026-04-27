#!/usr/bin/env python3

from pathlib import Path
import argparse
import shutil
import subprocess
import sys


def run(cmd, cwd=None):
    print("[assets]", " ".join(str(c) for c in cmd))
    subprocess.run(cmd, cwd=cwd, check=True)


def find_single_source(project_root: Path) -> Path:
    cues = sorted(project_root.glob("*.cue"))
    chds = sorted(project_root.glob("*.chd"))

    if cues:
        if len(cues) > 1:
            raise RuntimeError(
                "Multiple .cue files found. Please only include one in this folder."
            )

        return cues[0]

    if chds:
        if len(chds) > 1:
            raise RuntimeError(
                "Multiple .chd files found. Please only include one in this folder."
            )

        return chds[0]

    raise FileNotFoundError(
        "No .cue or .chd file found in the project root. "
        "Please source a copy of the game and place it in this folder."
    )


def require_python_module(module_name: str):
    try:
        __import__(module_name)
    except ImportError:
        raise RuntimeError(
            f"Missing Python dependency: {module_name}\n"
            f"Install it with:\n"
            f"  {sys.executable} -m pip install {module_name}"
        )


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("source", nargs="?", help="Path to input .cue or .chd file")
    parser.add_argument(
        "--extractor",
        default="ZA_AssetExtraction",
        help="Path to ZA_AssetExtraction submodule",
    )
    parser.add_argument(
        "--output",
        default="assets/export",
        help="Output export folder",
    )

    args = parser.parse_args()

    project_root = Path(__file__).resolve().parents[1]
    extractor_dir = (project_root / args.extractor).resolve()
    output_dir = (project_root / args.output).resolve()

    if output_dir.exists():
        print(f"[assets] {output_dir} already exists; skipping asset extraction.")
        return 0

    if not extractor_dir.exists():
        raise FileNotFoundError(
            f"Extractor submodule not found: {extractor_dir}\n"
            "Run: git submodule update --init --recursive --remote"
        )

    chd_to_dat_script = extractor_dir / "chd_to_dat.py"
    asset_extraction_script = extractor_dir / "ZAassetExtraction.py"

    if not chd_to_dat_script.exists():
        raise FileNotFoundError(f"Missing {chd_to_dat_script}")

    if not asset_extraction_script.exists():
        raise FileNotFoundError(f"Missing {asset_extraction_script}")

    source_path = (
        Path(args.source).resolve()
        if args.source
        else find_single_source(project_root).resolve()
    )

    if not source_path.exists():
        raise FileNotFoundError(f"Source file does not exist: {source_path}")

    source_suffix = source_path.suffix.lower()

    if source_suffix not in [".cue", ".chd"]:
        raise RuntimeError(
            f"Unsupported source file type: {source_path.suffix}. "
            "Please provide a .cue or .chd file."
        )

    chd_path = source_path.with_suffix(".chd")
    dat_path = source_path.with_suffix(".dat")

    if source_suffix == ".cue":
        bin_files = list(source_path.parent.glob("*.bin"))

        if not bin_files:
            existing_chd = source_path.with_suffix(".chd")

            if existing_chd.exists():
                print(
                    f"[assets] No .bin found, but {existing_chd.name} exists; "
                    "using CHD instead."
                )
                chd_path = existing_chd
            else:
                raise FileNotFoundError(
                    f"No .bin file found next to {source_path.name}, "
                    f"and no existing {existing_chd.name} was found."
                )
    else:
        chd_path = source_path

    if not chd_path.exists():
        if source_suffix != ".cue":
            raise FileNotFoundError(f"CHD file does not exist: {chd_path}")

        chdman = shutil.which("chdman") or shutil.which("chdman.exe")

        if not chdman:
            raise RuntimeError(
                "chdman was not found in PATH. "
                "Install MAME/mame-tools and add chdman to PATH."
            )

        run([
            chdman,
            "createcd",
            "-i",
            str(source_path),
            "-o",
            str(chd_path),
        ])
    else:
        print(f"[assets] {chd_path.name} already exists; reusing it.")

    if not dat_path.exists():
        run([
            sys.executable,
            str(chd_to_dat_script),
            str(chd_path),
        ], cwd=extractor_dir)
    else:
        print(f"[assets] {dat_path.name} already exists; reusing it.")

    if not dat_path.exists():
        raise FileNotFoundError(
            f"Expected DAT was not created: {dat_path}"
        )

    output_dir.parent.mkdir(parents=True, exist_ok=True)

    require_python_module("tqdm")

    run([
        sys.executable,
        str(asset_extraction_script),
        str(dat_path),
        str(output_dir),
    ], cwd=extractor_dir)

    print(f"[assets] Finished extracting assets to {output_dir}")
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
