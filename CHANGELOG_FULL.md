#### v0.13.0-BETA
- Backpack from Backpack Slot gets dropped the first time you enter a World or server
  - needed because of internal changes for the Backpack Slot
- adding missing keybind settings to neoforge
- adding Backpack Slot for Creative Menu
- fixing Render issues with Backpacks
  - Entity Data wasn't synced correctly
- ArmorStands accepts Backpacks again as an additional slot
  - on Forge they are invisible when rejoining world/server, seems not being fixable
- apply Gamemode and keepInventory correctly on Player respawn
- Zombies and Creeper can spawn with a leather or copper Backpack
  - chances can be changed in config screen
  - Backpacks only drop when killed by a Player

#### v0.12.6-BETA
- backpack in your hand gets locked when opened from inventory

#### v0.12.5-BETA
- adding the following Settings
  - Chest Loot Settings
- Every Backpack has Categories and each drop chance can be changed
  - hover over the Settings name to see what Chest is included

#### v0.12.0-BETA
- adding Backpack slot for Survival Inventory
  - remove the ability to equip the Backpack on the Chest slot
  - removes the ability to place Backpacks on ArmorStands for now
- adding proper BlockStates when Backpack is placed on a wall
- fixing Inventory Label Color for Backpacks, based on used style
- internal network changes
  - on forge this produces log spam on the client, when showing Ender Backpack Tooltip

#### v0.11.6-BETA
- fixing quick move functionality

#### v0.11.5-BETA
- adding texture option with 4 options
  - Vanilla, Vanilla Dark, Ore UI and Ore UI Dark
- fixing config settings not getting synced properly with server,
  - causes wrong container sizes with Ghost items
- Backpack sizes can now be configured without a server restart
- fixing an issue where the main hand item gets locked when
  interacting with a placed backpack

#### v0.11.0-BETA
- applying Resource Config API changes
- opened backpack gets locked when opened via right-click
- player now need to crouch to place the backpack

#### v0.10.7-BETA
- dirty fix for Forge Server crashes due to executing client code on server side.

#### v0.10.6-BETA
- changing behavior of Preview Tooltips, this prevents crashes when one of the KeyBinds isn't bound

#### v0.10.4-BETA
- fixing Ender Chest duplication glitch
- re-enable ender backpack preview
- replace Backpack rendering Mixin with loader specific rendering registration

#### v0.10.1-BETA
- fix an issue where food in off-hand gets consumed when opening Backpack with right click

#### v0.10.0-BETA
- replacing ShulkerBoxTooltip Compatibility with built-in solution
  that provides the same Tooltip preview, without the feature to lock the Tooltip
- correctly apply the config option to open Backpacks from Inventory via right-click

#### v0.9.3-BETA
- fix issue where Backpacks are invisible when placed on an Armorstand
- add a Config Option to 'unlock' the Slots of End Backpacks,
  so that Backpacks and ShulkerBoxes can be placed and picked up
  - is disabled by default

#### v0.9.2-BETA
- Fixing an issue where Backpacks disappear when hit by flowing Water or placed underwater
- remove test backpack from registry
- Backpacks can be filled by a hopper from any side

#### v0.9.0-BETA
- complete internal rework
- Backpacks now can be placed as Blocks
- reduce the maximum Slots from 1250 to 250
  - Backpacks have fixed min and max rows/columns
- reduce fps drops with new render approach for Backpacks
  - Hint: when there's a heavy amount of Blocks (inventory or REI/JEI/NEI/EMI)  
    fps drops like crazy regardless of any mod
- Backpacks now can only be opened via KeyBind (default ``B``) when equipped in Chest slot
  - Backpacks can further be opened with the right-click(has to be enabled in the ``config``)
- compatibility with Accessories, Curios and Trinket is removed and will not be added back
  - This is a balancing choice to have some costs for more inventory space

#### v0.7.7-BETA
- update to 1.21.3
- remove compatibility with Accessories, Trinkets and Curios API
  - will be eventually added back later or with an independent replacement

#### v0.7.6-BETA
- fix the crash when crafting the leather backpack
  - only happened when Shulker Box Tooltip was installed

#### v0.7.5-BETA
- adding new Backpack Models made by Malcolm Riley
- Java versions above 21 are also supported now

#### v0.7.0-BETA
- port to Forge
- adding proper Item Tags
- adding ender Backpack
- Leather Backpack Recipe now also accepts Rabbit Hide
- Updating Resource Config API to 2.1.1

#### v0.6.2-BETA
- re-enable key to open backpacks in the inventory
- Trinkets support for Fabric
- fixing an issue where Items get lost when crafted(only on server)

#### v0.6.1-BETA
- Removing useless/unnecessary Settings
    - Allow_Chestslot -> Chest slot gets disabled when a supported Trinket Mod is installed
    - Allow_KeyBind -> why anyone would like to disable this
    - Open_Backpack_from_Inventory ->  Open/Close KeyBind only works when Backpack is equipped
      in Armor or Curio/Accessories Back Slot
- Fixing issues with crafting recipes
- Fixing Backpack Open/Close issue
- Fixing rare issue where Backpack gets closed and open at the same time

#### v0.6.0-BETA
- NeoForge
    - adding curios api support

#### v0.5.8-BETA
- Fix the shift-click issue
- removing trinket, accessories and curios setting

#### v0.5.7-BETA
- adding Accessories support
    - when Shift-click a Backpack out of the Chest Slot it "vanishes" to
      get it back open the Accessories screen then it appears in an empty slot.

#### v0.5.6-BETA
- fixing an issue where client code causes the server to crash

#### v0.5.5-BETA
- Fix: 2x2 Crafting wasn't usable

#### v0.5.4-BETA
- adding Shulker Box Tooltip compatibility

#### v0.5.3-BETA
- Backpack Settings get synced between client and server

#### v0.5.2-BETA
- fixing visual issues
    - slots are 1 pixel off on left and right sides
    - backpacks now can have at least 1 row and 9 columns
- Changing the default values of backpack sizes(this has no impact to already created configs)
    - Leather 3 rows 9 columns
    - Copper 3 rows 10 columns
    - Gold 4 rows 11 columns
    - Iron 5 rows 12 columns
    - Diamond 6 rows 12 columns
    - Netherite 7 rows 13 columns

#### v0.5.1-BETA
- Backpack can be opened via the Keybind when in Offhand
- Backpacks can no longer be equipped inside the Chestslot when the Keybind is disabled in the Cofig

#### v0.5.0-BETA
- initial Release
- adding 6 Backpacks
    - Inventory size can be changed through the config file
- Configurable keybind to open and close the Backpack
- Backpack can be placed in the Chest Slot