# ZA Actor Import Workflow

This workflow describes how to generate a new animated actor for the project. The actor may be either an `Enemy` or an `Npc`. The goal is that the user can provide the actor name, the current `ImageLoader.java`, the sprite files, and a file tree of the animation folders; then ChatGPT either edits the project files directly or returns downloadable Java files instead of snippets.

## Output files to generate

For each new actor, create or update these Java files:

```text
ImageLoader.java
ActorName.java
ActorNameAnimation.java
```

`ActorNameAnimation.java` must live in the animation package (`package com.nebbii.zagdx.animation;`) because it extends the existing `GameAnimation` class in that package.

When directly adding and editing files in the repository, do not generate a ZIP file. The ZIP is only useful when the output is being returned as downloadable files rather than written to the workspace.

When returning downloadable files instead of editing the repository directly, name the ImageLoader replacement and ZIP like this:

```text
ImageLoader_<ActorName>.java
ActorName_files.zip
```

The updated `ImageLoader` should be edited in place when working directly in the repository. When returning downloadable files, provide a full replacement file, not a patch snippet, unless the user explicitly asks for only changed sections.

## Validation policy

Do not run Gradle compile, test, or launch commands as part of this actor import workflow unless the user explicitly asks for that validation. Limit default verification to file/diff sanity checks and obvious source inspection.

## Required inputs

Do not make assumptions if any of these are missing. Ask for the missing information instead.

```text
[ ] Actor class name, for example EnemyExample or NpcExample
[ ] Superclass: Enemy or Npc
[ ] Current ImageLoader.java file
[ ] Sprite files, or enough sprite metadata to determine frame count and offsets
[ ] File tree showing animation folder layout
[ ] Actor-specific values required by the chosen superclass
[ ] Whether placeholder stats/values are allowed when exact values are unknown
```

Sprite root and animation layout should be inferred from the supplied file tree whenever possible. For example, if the file tree shows:

```text
desc0
├── group0
│   ├── sprite0.png
│   ├── sprite1.png
│   └── sprite2.png
└── group1
    ├── sprite0.png
    ├── sprite1.png
    └── sprite2.png
```

then infer that the sprite root is the path ending at `desc0`, that there are two animation groups, and that each group has three frames.

If the file tree does not include the path relative to the assets root, ask for it. The generated `ImageLoader` paths must be asset-relative, for example:

```text
export/underworld/s120/sprites/desc0/group0
```

not an absolute local path like:

```text
/home/ben/Documents/projects/za-gdx/assets/export/underworld/s120/sprites/desc0/group0
```

## Superclass rules

The superclass will always be either `Enemy` or `Npc`.

Use `Enemy` when the actor is a combat actor. The current `Enemy` base class already provides movement state, direction, health, damage, defense, bonus damage, knockback, weakness hooks, alert box logic, and death/save behavior.

Use `Npc` when the actor is non-combat or primarily interactable. The current `Npc` base class provides actor state, collision/hitbox behavior, map/location entry hooks, draw order, and a default `Direction.DOWN` direction.

If the user does not say whether the actor is an enemy or NPC, ask before generating files.

## Enemy class pattern

Use this pattern when `Superclass: Enemy`.

```java
package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nebbii.zagdx.animation.ActorNameAnimation;

// TODO: Set actual original game accurate values
public class ActorName extends Enemy {
    public ActorNameAnimation animation;

    public ActorName() {
        super(ActorType.ENEMY, true);
        setWidth(/* required or placeholder only if allowed */);
        setHeight(/* required or placeholder only if allowed */);
        setHealth(/* required or placeholder only if allowed */);
        setDamage(/* required or placeholder only if allowed */);
        setDefense(/* required or placeholder only if allowed */);
        setBonusDamage(/* optional, only if used */);

        this.animation = new ActorNameAnimation(this);

        this.enemyState = EnemyState.SEARCH;
    }

    @Override
    public void logic() {
        super.logic();

        switch(enemyState) {
            case SEARCH:
                setSpeed(80f);
                break;
            case FIGHT:
                setSpeed(110f);
                break;
            default:
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (knockback > 0) drawFlashOverlay(batch, hurtWeakness);

        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());

        if (knockback > 0) endDrawFlashOverlay(batch);
    }

    @Override
    public Array<String> getWeaknesses() {
        return Array.with(/* required values, or return new Array<>() if no weaknesses */);
    }
}
```

Enemy-specific values that must be supplied or explicitly allowed as placeholders:

```text
[ ] width
[ ] height
[ ] health
[ ] damage
[ ] defense
[ ] bonusDamage, if used
[ ] weaknesses, if any
[ ] solid true/false, if not obvious from the example
[ ] movement speeds, if not using SEARCH=80 and FIGHT=110
```

Do not copy dimensions from an unrelated enemy without saying they are placeholders. If sprite files are attached, inspect sprite dimensions and transparent bounds before choosing width, height, and draw offsets.

## NPC class pattern

Use this pattern when `Superclass: Npc`.

```java
package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nebbii.zagdx.animation.ActorNameAnimation;

public class ActorName extends Npc {
    public ActorNameAnimation animation;

    public ActorName() {
        super(ActorType.NPC, true);
        setWidth(/* required or placeholder only if allowed */);
        setHeight(/* required or placeholder only if allowed */);

        this.animation = new ActorNameAnimation(this);
    }

    @Override
    public void logic() {
        super.logic();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.playCurrentAnimation(), animation.getX(), animation.getY());
    }
}
```

NPC-specific values that must be supplied or explicitly allowed as placeholders:

```text
[ ] width
[ ] height
[ ] solid true/false
[ ] ActorType value, if the project uses a more specific type than ActorType.NPC
[ ] whether the NPC has directional animation or a single idle/talk animation
```

The base `Npc` currently returns `Direction.DOWN` by default. If a generated NPC animation class needs directional switching, first confirm that the specific NPC class will store or expose direction. Otherwise, generate a single-animation or non-directional animation class.

## Animation layout inference

Infer frame count from the file tree by counting `sprite0.png`, `sprite1.png`, and so on inside each group.

Infer the asset-relative root from the provided path and tree. For example:

```text
cd /home/ben/Documents/projects/za-gdx/assets/export/underworld/s120/sprites
 tree desc0
```

means the asset-relative root is:

```text
export/underworld/s120/sprites/desc0
```

If the path is not under an obvious `assets/` directory, ask for the asset-relative path.

Infer common group layouts when the file tree is clear:

```text
1 group  = single animation
4 groups = four directional animations
```

For four-direction actors, use the project convention unless the user says otherwise:

```text
group0 = UP
group1 = RIGHT
group2 = DOWN
group3 = LEFT
```

For single-animation actors:

```text
group0 = default animation
```

Do not ask for the sprite root or animation layout when they can be cleanly inferred from the given file tree and path.

## Animation group naming

For enemies, use names like:

```java
public enum ActorNameAnimationGroup {
    WALKUP,
    WALKRIGHT,
    WALKDOWN,
    WALKLEFT
}
```

For NPCs, choose animation names based on the layout and context. Examples:

```java
public enum ActorNameAnimationGroup {
    IDLE
}
```

or:

```java
public enum ActorNameAnimationGroup {
    IDLEUP,
    IDLERIGHT,
    IDLEDOWN,
    IDLELEFT
}
```

If the file tree has multiple groups but their meanings are not obvious, ask what each group represents.

## Animation class base rules

Generated animation classes must follow the existing project animation pattern:

```text
[ ] Use `package com.nebbii.zagdx.animation;`
[ ] Extend the existing `GameAnimation` class
[ ] Call `super("initialAnimationName")` as the first statement in the constructor
[ ] Set `baseOffsetX`, `baseOffsetY`, `offsetX`, and `offsetY` in the constructor after `super(...)`
[ ] Store the actor instance in a final field
[ ] Import the actor class from `com.nebbii.zagdx`
[ ] Import the relevant `ImageLoader.ActorNameAnimationGroup`
[ ] Import `World` and read textures through `World.images.getActorNameAnimation(...)`
[ ] Define a local `int[][] frameData` block inside each animation init method, with rows shaped as `{frameIndex, offsetX, offsetY}`
```

Example constructor shape:

```java
public ActorNameAnimation(ActorName actor) {
    super("walk");
    this.actor = actor;

    baseOffsetX = 0;
    baseOffsetY = 0;
    offsetX = 0;
    offsetY = 0;

    walk = initWalk();
}
```

For directional animations, use a stable default animation name such as `"walkDown"` or `"idleDown"` in the `super(...)` call. For a single-animation actor, use the single group name, such as `"walk"` or `"idle"`.

Do not generate animation classes that omit the `super(...)` call. `GameAnimation` is not optional; it owns shared animation state such as `animation`, `stateTime`, `baseOffsetX`, `baseOffsetY`, `offsetX`, `offsetY`, and `play()`.

Always make frame offsets directly editable in the generated animation class. Each generated animation init method (`initIdle()`, `initIdle0()`, `initWalkDown()`, etc.) must declare its own local `int[][] frameData` block and populate offset arrays from that block. Do not use one shared static/global frame-data constant across multiple animations, because each animation may need independent per-frame X/Y offsets.

## Four-direction animation class pattern

For a four-direction, five-frame actor, the animation class should:

```text
[ ] Use `package com.nebbii.zagdx.animation;`
[ ] Extend GameAnimation
[ ] Call `super("walkDown")` or another appropriate initial animation name as the first constructor statement
[ ] Store the actor instance
[ ] Have one Animation<TextureRegion> per direction
[ ] Have X/Y offset arrays per direction
[ ] Select animation from actor.getDirection(), only if the actor exposes direction
[ ] Use World.images.getActorNameAnimation(...)
[ ] Use the 8-step ping-pong frame pattern: 0, 1, 2, 3, 4, 3, 2, 1
[ ] Use a separate local `int[][] frameData` block for each direction init method
[ ] Use state-aware animation speed when the actor is an Enemy
[ ] Use a simple fixed animation speed when the actor is an Npc unless told otherwise
[ ] Draw relative to actor position using getX() and getY()
```

Standard five-frame ping-pong sequence:

```java
int[][] frameData = {
    {0, 0, 0},
    {1, 0, 1},
    {2, 0, 2},
    {3, 0, 1},
    {4, 0, 0},
    {3, 0, 1},
    {2, 0, 2},
    {1, 0, 1}
};
```

If the file tree shows a different number of frames, generate the sequence to match the actual frame count. Do not assume five frames unless the tree shows `sprite0.png` through `sprite4.png`.

## Single-animation class pattern

For a single-animation actor, the animation class should:

```text
[ ] Use `package com.nebbii.zagdx.animation;`
[ ] Extend GameAnimation
[ ] Call `super("walk")`, `super("idle")`, or another appropriate initial animation name as the first constructor statement
[ ] Store the actor instance
[ ] Have one Animation<TextureRegion>
[ ] Have X/Y offset arrays
[ ] Use a local `int[][] frameData` block for the animation init method
[ ] Use World.images.getActorNameAnimation(...)
[ ] Use a looped animation
[ ] Draw relative to actor position using getX() and getY()
```

Single-animation actors do not need direction switching, but they still must extend `GameAnimation` and call `super(...)` in the constructor.

## Offset policy

If the user says to use the same offsets as another actor, copy those offsets exactly.

All generated animation frame rows must use this shape:

```java
{frameIndex, offsetX, offsetY}
```

Generate these rows even when all offsets are zero, so the user can edit X/Y offsets per frame later.

If the user asks for pixel alignment and provides sprite images, inspect the sprite dimensions and transparent bounds before choosing offsets.

If the user provides no sprites and does not say to reuse offsets, use neutral offsets only if placeholders are explicitly allowed:

```java
{0, 0, 0}
```

Otherwise, ask for sprites or offset instructions.

## ImageLoader update checklist

When updating `ImageLoader.java`, do all of this:

```text
[ ] Add ActorNameAnimationGroup enum
[ ] Add private EnumMap<ActorNameAnimationGroup, Texture[]> actorName field
[ ] Add constructor load block with inferred asset paths and frame counts
[ ] Add dispose loop
[ ] Add getActorNameAnimation(ActorNameAnimationGroup anim) getter
[ ] Keep existing ImageLoader content intact
[ ] Fix obvious missing getters only if directly required by the generated files
[ ] Preserve package, imports, comments, and existing style as much as possible
[ ] If returning downloadable output, return the full updated ImageLoader as a downloadable file
```

Use lower camel case for the texture map field:

```java
private EnumMap<ActorNameAnimationGroup, Texture[]> actorName;
```

Example:

```java
private EnumMap<EnemyExampleAnimationGroup, Texture[]> enemyExample;
private EnumMap<NpcExampleAnimationGroup, Texture[]> npcExample;
```

## Missing-information policy

If any of these are missing, stop and ask instead of generating guessed files:

```text
Missing actor name -> ask for class name
Missing superclass -> ask whether it extends Enemy or Npc
Missing current ImageLoader.java -> ask for upload
Missing asset-relative path and cannot infer it from the file tree/path -> ask for asset-relative path
Missing frame count and cannot infer it from the tree -> ask for frame count or sprite files
Missing group meaning for non-obvious multi-group layouts -> ask what each group represents
Missing Enemy stats -> ask unless placeholders are explicitly allowed
Missing NPC solidity/dimensions -> ask unless placeholders are explicitly allowed
Missing weakness/loot/special behavior -> ask or mark TODO only when allowed
```

## Future prompt format

A good future prompt can look like this:

```text
Please import a new actor.

Actor name: EnemyExample
Superclass: Enemy
Stats: health=80, damage=50, defense=30, bonusDamage=70
Weaknesses: ZeldaActionJadeRing
Offsets: same as EnemyOtherExample

File tree:
/path/to/project/assets/export/underworld/s120/sprites
└── desc0
    ├── group0
    │   ├── sprite0.png
    │   ├── sprite1.png
    │   ├── sprite2.png
    │   ├── sprite3.png
    │   └── sprite4.png
    ├── group1
    │   ├── sprite0.png
    │   ├── sprite1.png
    │   ├── sprite2.png
    │   ├── sprite3.png
    │   └── sprite4.png
    ├── group2
    │   ├── sprite0.png
    │   ├── sprite1.png
    │   ├── sprite2.png
    │   ├── sprite3.png
    │   └── sprite4.png
    └── group3
        ├── sprite0.png
        ├── sprite1.png
        ├── sprite2.png
        ├── sprite3.png
        └── sprite4.png

Attached:
  - current ImageLoader.java
  - sprite files, if pixel alignment is needed

Please return:
  - updated ImageLoader.java
  - EnemyExample.java
  - EnemyExampleAnimation.java
```

For an NPC:

```text
Please import a new actor.

Actor name: NpcExample
Superclass: Npc
Solid: true
Dimensions: width=32, height=32
Animation: single idle animation

File tree:
/path/to/project/assets/export/overworld/example/sprites/desc0
└── group0
    ├── sprite0.png
    ├── sprite1.png
    ├── sprite2.png
    ├── sprite3.png
    └── sprite4.png

Attached:
  - current ImageLoader.java

Please return:
  - updated ImageLoader.java
  - NpcExample.java
  - NpcExampleAnimation.java
```
