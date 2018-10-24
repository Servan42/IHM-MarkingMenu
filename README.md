# TP IHM 2 : Marking Menu

## Members

* CHANET Zoran
* CHARLOT Servan

## Choices

### Specification choices

* Use the right click to open the marking menu. It will open where you clicked.
* An item is selected when either releasing the right click over it, or when leaving the menu zone.
* Releasing the right click over the center of menu cancels the current choice. Previous choices are applied and can't be undone. 
* When the selection is canceled before going through all the menus, the settings that should have been set will remain at their last state.
* When leaving the zone, if the item has further options (as colors for example), it will open another marking menu.
* The marking menu doesn't accept a list of more than 8 elements.

### Implementation choices

* The Paint class has been rewritten to follow an MVC architecture.
* The main class is located under controller.Paint.
* Each tool contains a method that returns the list of the following tools list if any.
* The menu is a widget contained in another layer than the panel, thanks to javax.swing.JLayeredPane.
* Menus are instantiated when required, upon right click or leaving the previous menu.

## Known ~~bugs~~ features

* If the left click is held once the menu is open (thus right click held too), two things might occur when a new tool is selected : 
  * Either the newly selected tool has been used in the past, then a new shape of this tool is drawn until the right click is released (even if left click is released, clicked again, etc.)
  * Or the tool has never been selected, and exceptions will be raised until right click is released, but are transparent to the user
* Right clicking while drawing a shape (holding left click) will delete the current one and start drawing another at the current position of the cursor.
