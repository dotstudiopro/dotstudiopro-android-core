package com.dotstudioz.dotstudioPRO.models.dto;

import java.util.ArrayList;

/**
 * Created by user on 15-06-2016.
 */
public class SubscriptionDTO {
    /**
     * {
         "_id": "5af3d0e798f8154f1959ec9f",
         "company_id": "5ab1600597f815014b357891",
         "chargify_id": "4654324",
         "entire_catalogue": false,
         "ads_enabled": false,
         "status": "Active",
         "channels": [
             "5ad0587c99f8152756181dc4",
             "5ada87ef97f815ec050ccb80",
             "5ae23f1699f81537226a043b",
             "5ae3c3e699f815e5516a043b"
         ],
         "duration": {
             "interval": 1,
             "interval_unit": "month"
         },
         "price": "6.99",
         "name": "Revry Monthly",
         "trial": null
     }
     */
    private String id;
    private String companyId;
    private String chargifyId;
    private boolean entireCatalogue;
    private boolean adsEnabled;
    private String status;
    private ArrayList<String> channels = new ArrayList<>();
    private DurationDTO durationDTO = new DurationDTO();
    private int interval;
    private String intervalUnit;
    private String price;
    private String name;
    private String trial;
    private String handle;
    private double total_revenue;

    public double getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(double total_revenue) {
        this.total_revenue = total_revenue;
    }
    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getChargifyId() {
        return chargifyId;
    }

    public void setChargifyId(String chargifyId) {
        this.chargifyId = chargifyId;
    }

    public boolean isEntireCatalogue() {
        return entireCatalogue;
    }

    public void setEntireCatalogue(boolean entireCatalogue) {
        this.entireCatalogue = entireCatalogue;
    }

    public boolean isAdsEnabled() {
        return adsEnabled;
    }

    public void setAdsEnabled(boolean adsEnabled) {
        this.adsEnabled = adsEnabled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<String> channels) {
        this.channels = channels;
    }

    public DurationDTO getDurationDTO() {
        return (new DurationDTO());
    }

    public void setDurationDTO(DurationDTO durationDTO) {
        this.durationDTO = durationDTO;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrial() {
        return trial;
    }

    public void setTrial(String trial) {
        this.trial = trial;
    }
}


