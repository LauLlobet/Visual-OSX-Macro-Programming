package model;

import Constants.TSOConstants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * Created by quest on 4/4/16.
 */
public class AssetsPersistanceAccessor {

    Hashtable<String,Object> persisting;
    Hashtable<String,Object> temporal;
    Hashtable<Object, String> persistingKeys;
    Hashtable<Object, String> temporalKeys;

    static private  AssetsPersistanceAccessor instance = null;

    public AssetsPersistanceAccessor(){
        persisting = new Hashtable<String, Object>();
        temporal = new Hashtable<String, Object>();
        persistingKeys = new Hashtable<Object, String>();
        temporalKeys = new Hashtable<Object, String>();
    }

    public static AssetsPersistanceAccessor getInstance() {
        if(instance == null){
            instance = new AssetsPersistanceAccessor();
        }
        return instance;
    }

    public void removeAsset(Object object) {
        if(object == null){
            return;
        }
        if(persisting.contains(object)){
            persisting.remove(persistingKeys.get(object));
            persistingKeys.remove(object);
        }
        if(temporal.contains(object)){
            temporal.remove(temporalKeys.get(object));
            temporalKeys.remove(object);
        }

    }


    public String addNewAssetToPersist(Object object) {
        String id = ""+ System.currentTimeMillis()+""+Math.floor(Math.random()*1000);
        persisting.put(id,object);
        persistingKeys.put(object,id);
        return id;
    }

    public String getIdKeyForPersistentAsset(Object object) {
        return persistingKeys.get(object);

    }

    public Object getPersistantAssetFromKey(String key) {
        if (key.equals(TSOConstants.NOASSET)) {
            return null;
        }
        return persisting.get(key);
    }
}
