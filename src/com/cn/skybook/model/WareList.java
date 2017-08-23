package com.cn.skybook.model;

import org.json.*;
import java.util.ArrayList;

public class WareList {
	
    private String promotionIconUrl;
    private String author;
    private String cid2;
    private String noPriceMsg;
    private String targetUrl;
    private String upToSaving;
    private String catid;
    private String diffMobilePrice;
    private boolean canFreeRead;
    private boolean self;
    private String totalCount;
    private boolean availableInStore;
    private String interImgUrl;
    private String titleIconImg;
    private String priority;
    private String mPromotionId;
    private boolean preSale;
    private String exposalUrl;
    private boolean eBookFlag;
    private boolean eche;
    private String adword;
    private boolean isBooking;
    private String longImgUrl;
    private boolean localStore;
    private String stockQuantity;
    private String stockState;
    private boolean international;
    private String plusPrice;
    private String shopName;
    private String imageurl;
    private String lowestBuy;
    private String excPrice;
    private boolean newUserBenefit;
    private boolean secKill;
    private String wname;
    private String cid1;
    private boolean globalSales;
    private String gaiaAttrt;
    private String good;
    private String jdPrice;
    private boolean jdDelivery;
    private String wareId;
    private ArrayList<String> promotionFlag;
    private String toMURL;
    
    
	public WareList () {
		
	}	
        
    public WareList (JSONObject json) {
    
        this.promotionIconUrl = json.optString("promotionIconUrl");
        this.author = json.optString("author");
        this.cid2 = json.optString("cid2");
        this.noPriceMsg = json.optString("noPriceMsg");
        this.targetUrl = json.optString("targetUrl");
        this.upToSaving = json.optString("upToSaving");
        this.catid = json.optString("catid");
        this.diffMobilePrice = json.optString("diffMobilePrice");
        this.canFreeRead = json.optBoolean("canFreeRead");
        this.self = json.optBoolean("self");
        this.totalCount = json.optString("totalCount");
        this.availableInStore = json.optBoolean("availableInStore");
        this.interImgUrl = json.optString("interImgUrl");
        this.titleIconImg = json.optString("titleIconImg");
        this.priority = json.optString("priority");
        this.mPromotionId = json.optString("mPromotionId");
        this.preSale = json.optBoolean("preSale");
        this.exposalUrl = json.optString("exposalUrl");
        this.eBookFlag = json.optBoolean("eBookFlag");
        this.eche = json.optBoolean("eche");
        this.adword = json.optString("adword");
        this.isBooking = json.optBoolean("isBooking");
        this.longImgUrl = json.optString("longImgUrl");
        this.localStore = json.optBoolean("localStore");
        this.stockQuantity = json.optString("stockQuantity");
        this.stockState = json.optString("stockState");
        this.international = json.optBoolean("international");
        this.plusPrice = json.optString("plusPrice");
        this.shopName = json.optString("shopName");
        this.imageurl = json.optString("imageurl");
        this.lowestBuy = json.optString("lowestBuy");
        this.excPrice = json.optString("excPrice");
        this.newUserBenefit = json.optBoolean("newUserBenefit");
        this.secKill = json.optBoolean("secKill");
        this.wname = json.optString("wname");
        this.cid1 = json.optString("cid1");
        this.globalSales = json.optBoolean("globalSales");
        this.gaiaAttrt = json.optString("gaiaAttrt");
        this.good = json.optString("good");
        this.jdPrice = json.optString("jdPrice");
        this.jdDelivery = json.optBoolean("jdDelivery");
        this.wareId = json.optString("wareId");

        this.promotionFlag = new ArrayList<String>();
        JSONArray arrayPromotionFlag = json.optJSONArray("promotionFlag");
        if (null != arrayPromotionFlag) {
            int promotionFlagLength = arrayPromotionFlag.length();
            for (int i = 0; i < promotionFlagLength; i++) {
                String item = arrayPromotionFlag.optString(i);
                if (null != item) {
                    this.promotionFlag.add(item);
                }
            }
        }
        else {
            String item = json.optString("promotionFlag");
            if (null != item) {
                this.promotionFlag.add(item);
            }
        }

        this.toMURL = json.optString("toMURL");

    }
    
    public String getPromotionIconUrl() {
        return this.promotionIconUrl;
    }

    public void setPromotionIconUrl(String promotionIconUrl) {
        this.promotionIconUrl = promotionIconUrl;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCid2() {
        return this.cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getNoPriceMsg() {
        return this.noPriceMsg;
    }

    public void setNoPriceMsg(String noPriceMsg) {
        this.noPriceMsg = noPriceMsg;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getUpToSaving() {
        return this.upToSaving;
    }

    public void setUpToSaving(String upToSaving) {
        this.upToSaving = upToSaving;
    }

    public String getCatid() {
        return this.catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getDiffMobilePrice() {
        return this.diffMobilePrice;
    }

    public void setDiffMobilePrice(String diffMobilePrice) {
        this.diffMobilePrice = diffMobilePrice;
    }

    public boolean getCanFreeRead() {
        return this.canFreeRead;
    }

    public void setCanFreeRead(boolean canFreeRead) {
        this.canFreeRead = canFreeRead;
    }

    public boolean getSelf() {
        return this.self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public boolean getAvailableInStore() {
        return this.availableInStore;
    }

    public void setAvailableInStore(boolean availableInStore) {
        this.availableInStore = availableInStore;
    }

    public String getInterImgUrl() {
        return this.interImgUrl;
    }

    public void setInterImgUrl(String interImgUrl) {
        this.interImgUrl = interImgUrl;
    }

    public String getTitleIconImg() {
        return this.titleIconImg;
    }

    public void setTitleIconImg(String titleIconImg) {
        this.titleIconImg = titleIconImg;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMPromotionId() {
        return this.mPromotionId;
    }

    public void setMPromotionId(String mPromotionId) {
        this.mPromotionId = mPromotionId;
    }

    public boolean getPreSale() {
        return this.preSale;
    }

    public void setPreSale(boolean preSale) {
        this.preSale = preSale;
    }

    public String getExposalUrl() {
        return this.exposalUrl;
    }

    public void setExposalUrl(String exposalUrl) {
        this.exposalUrl = exposalUrl;
    }

    public boolean getEBookFlag() {
        return this.eBookFlag;
    }

    public void setEBookFlag(boolean eBookFlag) {
        this.eBookFlag = eBookFlag;
    }

    public boolean getEche() {
        return this.eche;
    }

    public void setEche(boolean eche) {
        this.eche = eche;
    }

    public String getAdword() {
        return this.adword;
    }

    public void setAdword(String adword) {
        this.adword = adword;
    }

    public boolean getIsBooking() {
        return this.isBooking;
    }

    public void setIsBooking(boolean isBooking) {
        this.isBooking = isBooking;
    }

    public String getLongImgUrl() {
        return this.longImgUrl;
    }

    public void setLongImgUrl(String longImgUrl) {
        this.longImgUrl = longImgUrl;
    }

    public boolean getLocalStore() {
        return this.localStore;
    }

    public void setLocalStore(boolean localStore) {
        this.localStore = localStore;
    }

    public String getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockState() {
        return this.stockState;
    }

    public void setStockState(String stockState) {
        this.stockState = stockState;
    }

    public boolean getInternational() {
        return this.international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public String getPlusPrice() {
        return this.plusPrice;
    }

    public void setPlusPrice(String plusPrice) {
        this.plusPrice = plusPrice;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getImageurl() {
        return this.imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLowestBuy() {
        return this.lowestBuy;
    }

    public void setLowestBuy(String lowestBuy) {
        this.lowestBuy = lowestBuy;
    }

    public String getExcPrice() {
        return this.excPrice;
    }

    public void setExcPrice(String excPrice) {
        this.excPrice = excPrice;
    }

    public boolean getNewUserBenefit() {
        return this.newUserBenefit;
    }

    public void setNewUserBenefit(boolean newUserBenefit) {
        this.newUserBenefit = newUserBenefit;
    }

    public boolean getSecKill() {
        return this.secKill;
    }

    public void setSecKill(boolean secKill) {
        this.secKill = secKill;
    }

    public String getWname() {
        return this.wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getCid1() {
        return this.cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public boolean getGlobalSales() {
        return this.globalSales;
    }

    public void setGlobalSales(boolean globalSales) {
        this.globalSales = globalSales;
    }

    public String getGaiaAttrt() {
        return this.gaiaAttrt;
    }

    public void setGaiaAttrt(String gaiaAttrt) {
        this.gaiaAttrt = gaiaAttrt;
    }

    public String getGood() {
        return this.good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getJdPrice() {
        return this.jdPrice;
    }

    public void setJdPrice(String jdPrice) {
        this.jdPrice = jdPrice;
    }

    public boolean getJdDelivery() {
        return this.jdDelivery;
    }

    public void setJdDelivery(boolean jdDelivery) {
        this.jdDelivery = jdDelivery;
    }

    public String getWareId() {
        return this.wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public ArrayList<String> getPromotionFlag() {
        return this.promotionFlag;
    }

    public void setPromotionFlag(ArrayList<String> promotionFlag) {
        this.promotionFlag = promotionFlag;
    }

    public String getToMURL() {
        return this.toMURL;
    }

    public void setToMURL(String toMURL) {
        this.toMURL = toMURL;
    }


    
}
