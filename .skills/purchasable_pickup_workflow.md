# ZA Purchasable Pickup Workflow

This workflow describes how to add a new purchasable pickup item to the project. Use it when the user provides a pickup class name, price, sprite export path, and map placement for a shop item.

This workflow complements `.skills/actor_import_workflow.md`. Use that actor workflow for animated `Enemy`, `Npc`, or `Sprite` actors; use this workflow for `Pickup` subclasses that use static item art and optional price-image art.

## Validation policy

Run `./gradlew :core:compileJava` after code changes unless the user explicitly asks not to. If the purchase path, collision path, or save behavior changes, also run `./gradlew :core:test` when sandbox permissions allow it.

If Gradle fails because it needs to write under `~/.gradle`, rerun with escalation instead of treating it as a code failure.

## Required inputs

Ask for missing information only when it cannot be inferred from the repo:

```text
[ ] Pickup class name, for example PickupCandle
[ ] Target item enum, usually a Treasure or Weapon value
[ ] Price in rubies
[ ] Regular sprite path
[ ] Price-image sprite path, or confirmation that HUD number rendering should be used
[ ] Map location and coordinates, if the item should appear in JSON
[ ] Whether the item is single-purchase, restocking, passive, equippable, or immediately equipped
```

If the user gives an export path like `export/overworld/f26/sprites/desc1`, check the actual files under `assets/export/...` but load textures through asset-relative paths like `export/overworld/f26/sprites/desc1/group0/sprite0.png`.

## Inspect exports first

Do not trust descriptor numbers blindly. Verify the export before wiring `ImageLoader`.

Useful checks:

```text
file assets/export/.../sprite0.png
assets/export/<cell>/scripts.py
assets/export/<cell>/cast.json
scripts/extractor/spriteNames.json
```

Use visual inspection when two groups are ambiguous. Price-image groups often include both the item and the price digits.

Known f26 shop mapping:

```text
desc1 group0 sprite0 = candle
desc1 group1 sprite0 = candle with 100 price
desc2 group0 sprite0 = gold key
desc2 group1 sprite0 = gold key with 100 price
desc3 group0 sprite0 = magic shield
desc3 group1 sprite0 = magic shield with 500 price
```

If a user asks for `PickupMagicShield` in `f26/desc2`, verify `scripts.py`; the exported script identifies `desc2` as `item_treasure_goldKey` and `desc3` as `item_treasure_magicShield`.

## Pickup class pattern

Create one class in `core/src/main/java/com/nebbii/zagdx`:

```java
package com.nebbii.zagdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupItemName extends Pickup {
    public PickupItemName() {
        super();

        setImage(World.images.getItemName());
        setPriceImage(World.images.getItemNamePrice());

        setWidth(/* regular sprite width */);
        setHeight(/* regular sprite height */);
    }

    public void logic() {
        super.logic();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void onPickup(GameManager game) {
        super.onPickup(game);
        game.addTreasure(Treasure.ITEM_NAME, true);
        this.setState(State.DEAD);
    }
}
```

Use `game.addWeapon(Weapon.ITEM_NAME, true)` for weapons. Only auto-equip the item when nearby pickup classes already do that for the same item category or the user explicitly asks for it.

Call `super.onPickup(game)` for persistent pickups so sparkle and location-save behavior remain intact. `PickupHeart` is a special case that does not save a location.

## ImageLoader checklist

Update `ImageLoader.java` in all relevant places:

```text
[ ] Add private Texture fields for the regular item and price image
[ ] Load both textures in the constructor
[ ] Dispose both textures in `dispose()`
[ ] Add getters used by the pickup class
[ ] Update `getImageByItem(Item item)` so the pause screen can draw the owned item
```

Example texture paths:

```java
candle = new Texture("export/overworld/f26/sprites/desc1/group0/sprite0.png");
candlePrice = new Texture("export/overworld/f26/sprites/desc1/group1/sprite0.png");
magicShield = new Texture("export/overworld/f26/sprites/desc3/group0/sprite0.png");
magicShieldPrice = new Texture("export/overworld/f26/sprites/desc3/group1/sprite0.png");
```

If the item enum does not exist yet, add it to `Treasure` or `Weapon` before updating `getImageByItem`.

## Purchase behavior checklist

Before adding item-specific code, inspect the current `Pickup`, `WorldCollision`, `ActorJsonEntry`, and `MapManager` purchase support.

Expected baseline:

```text
[ ] ActorJsonEntry has `boolean purchasable` and `int price`
[ ] MapManager copies `entry.purchasable` and `entry.price` onto Pickup actors
[ ] Pickup has `isPurchasable`, `setPurchasable`, `getPrice`, `setPrice`
[ ] Pickup can store an optional `priceImage`
[ ] Free pickups still collect on overlap
```

If purchase completion is not wired yet, add a single shared method on `Pickup`:

```java
public boolean tryPickup(GameManager game) {
    if (isPurchasable()) {
        if (game.getRubies() < getPrice()) {
            return false;
        }

        game.decreaseRubies(getPrice(), true);
    }

    onPickup(game);
    return true;
}
```

Then make `WorldCollision.collideZeldaWithPickups` call `pickupActor.tryPickup(game)` when Zelda overlaps an active pickup.

For shop items, consider setting JSON-loaded purchasable pickups to `State.ACTIVE` in `MapManager.createActorFromJsonEntry`. That avoids the one-second dropped-item bounce and lets the price render immediately.

## Price-image rendering

There are two valid price display modes:

```text
priceImage supplied and distinct from item image -> draw the exported price image
no distinct priceImage -> draw the numeric price with HudNumberRenderer
```

If exported price art includes both the item and digits, preserve the price image's native dimensions instead of scaling it to the regular item bounds. Center it horizontally over the pickup's regular hitbox.

Do not draw HUD-number price text on top of an exported price image.

## JSON placement

Add shop pickups to the relevant map JSON under `assets/gamedata`.

Example:

```json
{
  "type": "PickupCandle",
  "x": 2108,
  "y": 1493,
  "purchasable": true,
  "price": 100
}
```

`MapManager.createActorFromJsonEntry` reflection-loads normal pickup classes by `type`, so no switch case is needed unless the pickup has custom constructor arguments like `PickupRuby`.

Location IDs are generated from the map location and actor index. Once bought, `super.onPickup(game)` records the generated location entry as `picked_up`, and reload logic hides the item.

## Equipping and passive items

Check whether the item should be selectable in the pause menu.

Passive treasures should be added to `MenuPauseSlotTreasures.NOTEQUIPPABLE`. The magic shield is passive in the current design: it should exist in inventory but does not need to occupy the active item slot.

Single-use action treasures, such as the candle, can remain equippable unless the user asks only for purchasability and no behavior exists yet. Do not invent candle dark-room behavior or shield projectile blocking as part of adding the purchasable pickup unless requested.

## Common gotchas

- The user may say `export/...`, but runtime assets live under `assets/export/...`; `ImageLoader` paths still omit `assets/`.
- Descriptor names can be wrong in the prompt. Use `scripts.py`, `spriteNames.json`, and visual inspection to verify.
- `priceImage` may be wider than the regular item sprite. Native-size drawing avoids squashing the price digits.
- If a purchasable pickup remains `PENDING`, it will bounce and render without a price until the pending duration expires.
- Do not save a purchase or deduct rubies when Zelda lacks enough rubies.
- Do not add restocking behavior unless the requested item explicitly needs it and the save model supports it.
