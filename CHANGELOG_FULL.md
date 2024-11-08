#### v0.7.7-BETA
- update to 1.21.3
- remove compatibility with Accessories, Trinkets and Curios API
  - will be eventually added back later or with an independent replacement

#### v0.7.6-BETA
- fix the crash when crafting the leather backpack
  - only happened when Shulker Box Tooltip ist installed

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