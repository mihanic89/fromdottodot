package xyz.yapapa.fromdottodot.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.NativeExpressAdView;

import java.util.List;

import xyz.yapapa.fromdottodot.R;

/**
 * The {@link RecyclerViewAdapter} class.
 * <p>The adapter provides access to the items in the {@link MenuItemViewHolder}
 * or the {@link NativeExpressAdViewHolder}.</p>
 */
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // A menu item view type.
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The Native Express ad view type.
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;

    // An Activity's Context.
    private final Context mContext;

    // The list of Native Express ads and menu items.
    private final List<Object> mRecyclerViewItems;

    /**
     * For this example app, the recyclerViewItems list contains only
     * {@link MenuItem} and {@link NativeExpressAdView} types.
     */
    public RecyclerViewAdapter(Context context, List<Object> recyclerViewItems) {
        this.mContext = context;
        this.mRecyclerViewItems = recyclerViewItems;
    }

    /**
     * The {@link MenuItemViewHolder} class.
     * Provides a reference to each view in the menu item view.
     */
    public class MenuItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView menuItemImage;


        MenuItemViewHolder(View view) {
            super(view);
            menuItemImage = (ImageView) view.findViewById(R.id.menu_item_image);
// Define click listener for the ViewHolder's View.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    int imageId;

                    if (getAdapterPosition() >= 100) {
                        imageId=getAdapterPosition()-10;
                    } else if (getAdapterPosition() >= 89) {
                        imageId=getAdapterPosition()-9;
                    } else if (getAdapterPosition() >= 78) {
                        imageId=getAdapterPosition()-8;
                    } else if (getAdapterPosition() >= 67) {
                        imageId=getAdapterPosition()-7;
                    } else if (getAdapterPosition() >= 56) {
                        imageId=getAdapterPosition()-6;
                    } else if (getAdapterPosition() >= 45) {
                        imageId=getAdapterPosition()-5;
                    } else if (getAdapterPosition() >= 34) {
                        imageId=getAdapterPosition()-4;
                    } else if (getAdapterPosition() >= 23) {
                        imageId=getAdapterPosition()-3;
                    } else if (getAdapterPosition() >= 12) {
                        imageId=getAdapterPosition()-2;
                    } else if (getAdapterPosition() >= 1) {
                        imageId=getAdapterPosition()-1;
                    } else {
                        imageId=getAdapterPosition();
                    }

                    intent.putExtra("position", imageId);
                    mContext.startActivity(intent);
                   // Toast.makeText(mContext,getAdapterPosition() + "pressed",
                   //         Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    /**
     * The {@link NativeExpressAdViewHolder} class.
     */
    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {

        NativeExpressAdViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    /**
     * Determines the view type for the given position.
     */

    @Override
    public int getItemViewType(int position) {

        return (position % MainActivityRecycleView.ITEMS_PER_AD == 0) ? NATIVE_EXPRESS_AD_VIEW_TYPE
                : MENU_ITEM_VIEW_TYPE;
    }



    /**
     * Creates a new view for a menu item view or a Native Express ad view
     * based on the viewType. This method is invoked by the layout manager.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.item_list, viewGroup, false);
                return new MenuItemViewHolder(menuItemLayoutView);
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                View nativeExpressLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.native_express_ad_container,
                        viewGroup, false);
                return new NativeExpressAdViewHolder(nativeExpressLayoutView);
        }

    }

    /**
     *  Replaces the content in the views that make up the menu item view and the
     *  Native Express ad view. This method is invoked by the layout manager.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
                MenuItem menuItem = (MenuItem) mRecyclerViewItems.get(position);

                // Get the menu item image resource ID.
                int imageName = menuItem.getImageName();

                // Add the menu item details to the menu item view.
                menuItemHolder.menuItemImage.setImageResource(imageName);

                break;
            case NATIVE_EXPRESS_AD_VIEW_TYPE:
                // fall through
            default:
                NativeExpressAdViewHolder nativeExpressHolder =
                        (NativeExpressAdViewHolder) holder;
                NativeExpressAdView adView =
                        (NativeExpressAdView) mRecyclerViewItems.get(position);
                ViewGroup adCardView = (ViewGroup) nativeExpressHolder.itemView;
                // The NativeExpressAdViewHolder recycled by the RecyclerView may be a different
                // instance than the one used previously for this position. Clear the
                // NativeExpressAdViewHolder of any subviews in case it has a different
                // AdView associated with it, and make sure the AdView for this position doesn't
                // already have a parent of a different recycled NativeExpressAdViewHolder.
                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                // Add the Native Express ad to the native express ad view.
                adCardView.addView(adView);
        }
    }

}
