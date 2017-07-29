package xyz.yapapa.fromdottodot.ui.activity;

/**
 * The {@link MenuItem} class.
 * <p>Defines the attributes for a restaurant menu item.</p>
 */
class MenuItem {


    private final int imageName;

    public MenuItem(int imageName) {

        this.imageName = imageName;
    }


    public int getImageName() {
        return imageName;
    }
}
