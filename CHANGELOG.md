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
