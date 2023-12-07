package edu.vassar.cmpu203.brewerscloset.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Blob;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a single item
 */
public class Item implements java.io.Serializable{
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String PICTURES = "pictures";
    private static final String SELLERID = "sellerId";
    private static final String INTERESTS = "interests";
    private static final String ID = "id";
    public String title;
    public String description;
    public Double price;
    //both types below will need to be changed soon
    public Bitmap pictures;
    public String sellerId;
    public User seller;
    public ItemInterestCatalog interests;
    public String id;

    public Item(String title, Double price, String description, Bitmap pictures, User seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        if (seller != null) {
            this.sellerId = seller.id;}
        this.seller = seller;
        this.interests = new ItemInterestCatalog(this);
        this.id = UUID.randomUUID().toString();
    }
    public void getSellerFromID(UserCatalog users) {
        this.seller = users.getItemFromID(this.sellerId);
    }

    // Define methods for posts
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }
    public String getPriceString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##"); // Two decimal places
        String priceString = decimalFormat.format(this.price);

        return priceString;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public User getSeller() {return this.seller;}
    public void setSeller(User seller) {
        this.sellerId = seller.id;
        this.seller = seller;
    }

    // convert bitmap -> blob
    public Blob getBlobFromBitmap(Bitmap bitmap) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 70, stream);
        final byte[] bytes = stream.toByteArray();
        return Blob.fromBytes(bytes);
    }

    // convert blob -> bitmap
    public static Bitmap getBitmapFromBlob(Blob imageBlob) {
        final byte[] bytes = imageBlob.toBytes();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    //data persistence mapping functions
    /**
     * return a map with the items contents
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        //write String title, Double price, String description, String pictures, User seller
        map.put(TITLE, this.title);
        map.put(DESCRIPTION, this.description);
        map.put(PRICE, this.price);
        map.put(ID, this.id);
        // saving images
        Blob imageBlob = getBlobFromBitmap(this.pictures);
        map.put(PICTURES, imageBlob);
        map.put(SELLERID, this.sellerId);
        map.put(INTERESTS, this.interests.toMap());

        return map;
    }
    /**
     * Creates and returns a Sales from a previously created string to object map
     */
    @NonNull
    public static Item fromMap(@NonNull Map<String, Object> map) {
        Item item = new Item(null, null, null, null, null);

        //add String title, Double price, String description, String pictures, User seller
        item.title = (String) map.get(TITLE);
        item.description = (String) map.get(DESCRIPTION);
        item.price = (Double) map.get(PRICE);
        item.id = (String) map.get(ID);
        //retrieving pictures
        Blob blob = (Blob) map.get(PICTURES);
        if (blob != null) item.pictures =  getBitmapFromBlob(blob);
        item.sellerId = (String) map.get(SELLERID);
        item.interests = ItemInterestCatalog.fromMap((Map<String, Object>)map.get(INTERESTS));

        return item;
    }


}


