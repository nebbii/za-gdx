#!/usr/bin/env python3

from pathlib import Path
import argparse
import shutil
import subprocess
import sys


def run(cmd, cwd=None):
    print("[assets]", " ".join(str(c) for c in cmd))
    subprocess.run(cmd, cwd=cwd, check=True)


def find_single_cue(project_root: Path) -> Path:
    cues = sorted(project_root.glob("*.cue"))

    if not cues:
        raise FileNotFoundError(
            "No .cue file found in the project root. Please source a copy of the game and place it in this folder."
        )

    if len(cues) > 1:
        raise RuntimeError(
            "Multiple .cue files found. Please only include one in this folder."
        )

    return cues[0]

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
    parser.add_argument("cue", nargs="?", help="Path to input .cue file")
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

    if not (extractor_dir / "chd_to_dat.py").exists():
        raise FileNotFoundError(f"Missing {extractor_dir / 'chd_to_dat.py'}")

    if not (extractor_dir / "ZAassetExtraction.py").exists():
        raise FileNotFoundError(f"Missing {extractor_dir / 'ZAassetExtraction.py'}")

    cue_path = Path(args.cue).resolve() if args.cue else find_single_cue(project_root).resolve()

    if not cue_path.exists():
        raise FileNotFoundError(f"CUE file does not exist: {cue_path}")

    bin_files = list(cue_path.parent.glob("*.bin"))
    if not bin_files:
        raise FileNotFoundError(
            f"No .bin file found next to {cue_path.name}. "
            "The .bin must be in the same folder as the .cue."
        )

    chd_path = cue_path.with_suffix(".chd")
    dat_path = cue_path.with_suffix(".dat")

    chdman = shutil.which("chdman") or shutil.which("chdman.exe")

    if not chdman:
        raise RuntimeError(
            "chdman was not found in PATH. Install MAME/mame-tools and add chdman to PATH."
        )

    if not chd_path.exists():
        run([
            chdman,
            "createcd",
            "-i",
            str(cue_path),
            "-o",
            str(chd_path),
        ])
    else:
        print(f"[assets] {chd_path.name} already exists; reusing it.")

    if not dat_path.exists():
        run([
            sys.executable,
            str(extractor_dir / "chd_to_dat.py"),
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
        str(extractor_dir / "ZAassetExtraction.py"),
        str(dat_path),
        str(output_dir),
    ], cwd=extractor_dir)

    print(f"[assets] Finished extracting assets to {output_dir}")
    return 0

if __name__ == "__main__":
    raise SystemExit(main())
